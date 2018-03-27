package com.glens.eap.platform.es.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glens.eap.platform.es.ElasticSearchService;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.pwCloudOs.km.base.vo.EskmBase;

public class ElasticSearchServiceImpl implements ElasticSearchService,
		InitializingBean, DisposableBean {

	private List<String> clusterList = null;

	private Logger logger = Logger.getLogger(ElasticSearchServiceImpl.class);

	private Client searchClient = null;

	private String index;

	private String type;

	private IndexResponse response;

	public void setIndex(String index) {
		this.index = index;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setClusterList(List<String> clusterList) {
		this.clusterList = clusterList;
	}

	@Override
	public void addIndex(EskmBase kmbase) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonValue = mapper.writeValueAsString(kmbase);
			response = searchClient
					.prepareIndex(index, type, kmbase.getRowid() + "")
					.setSource(jsonValue).execute().actionGet();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateIndex(EskmBase kmbase) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonValue = mapper.writeValueAsString(kmbase);
			searchClient.prepareUpdate(index, type, kmbase.getRowid() + "")
					.setDoc(jsonValue).execute().get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delIndex(EskmBase kmbase) {
		DeleteResponse response = searchClient.prepareDelete(index, type,
				kmbase.getRowid() + "").get();
		System.out.println(response);
	}

	@Override
	public List<EskmBase> query(String keyword) {
		SearchRequestBuilder searchRequestBuilder = this.searchClient
				.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DEFAULT).setFrom(0).setSize(100);
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(QueryBuilders.matchQuery("btext", keyword))
				.should(QueryBuilders.matchQuery("fileTitle", keyword))
				.should(QueryBuilders.matchQuery("keywords", keyword));
		searchRequestBuilder = searchRequestBuilder.setQuery(query);
		SearchResponse searchResponse = searchRequestBuilder.execute()
				.actionGet();
		long count = searchResponse.getHits().getTotalHits();
		SearchHits searchHits = searchResponse.getHits();
		List<EskmBase> list = new ArrayList<EskmBase>();
		for (SearchHit hit : searchHits) {
			System.out.println(hit.getSource());
			EskmBase eb = new EskmBase();
			eb.setFileTitle(hit.getSource().get("fileTitle") + "");
			eb.setCatalogName(hit.getSource().get("catalogName") + "");
			eb.setKeywords(hit.getSource().get("keywords") + "");
			eb.setRowid(Long.parseLong(hit.getSource().get("rowid") + ""));
			list.add(eb);
		}
		return list;
	}

	@Override
	public PageBean queryForPage(String keyword, Integer pageSize,
			Integer pageNo, String isSearch, String status) {
		PageBean page = null;
		BoolQueryBuilder query = null;
		pageNo = null == pageNo ? 1 : pageNo;
		if (StringUtils.isEmpty(isSearch)) {
			isSearch = "0";
		}

		SearchRequestBuilder searchRequestBuilder = this.searchClient
				.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

		if (StringUtils.isNotEmpty(keyword)) {

			query = QueryBuilders.boolQuery()
					.should(QueryBuilders.matchQuery("fileTitle", keyword))
					.should(QueryBuilders.matchQuery("keywords", keyword));
			if (isSearch.equals("1")) {
				query.should(QueryBuilders.matchQuery("btext", keyword));
			}

			query = QueryBuilders.boolQuery().must(query);
			query.must(QueryBuilders.termQuery("status1", "2"));

			searchRequestBuilder = searchRequestBuilder.setQuery(query)
					.addSort("createTime", SortOrder.DESC)
					.setFrom((pageNo - 1) * pageSize).setSize(pageSize)
					.setExplain(true);

		} else {

			query = query.must(QueryBuilders.termQuery("status1", "2"));

			// 搜索前10条
			searchRequestBuilder = searchRequestBuilder.setQuery(query)
					.addSort("createTime", SortOrder.DESC).setFrom(0)
					.setSize(pageSize).setExplain(true);
		}

		SearchResponse searchResponse = searchRequestBuilder.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		int totalCount = (int) searchHits.getTotalHits();
		page = new PageBean(totalCount, pageNo, pageSize);
		List<EskmBase> list = new ArrayList<EskmBase>();
		for (SearchHit hit : searchHits) {
			System.out.println(hit.getSourceAsString());
			EskmBase eb = new EskmBase();
			eb.setFileTitle(hit.getSource().get("fileTitle") + "");
			eb.setCatalogName(hit.getSource().get("catalogName") + "");
			eb.setKeywords(hit.getSource().get("keywords") + "");
			eb.setBtext(hit.getSource().get("btext") + "");
			eb.setRowid(Long.parseLong(hit.getSource().get("rowid") + ""));
			eb.setCreateUser(hit.getSource().get("createUser") + "");
			eb.setCreateTime(hit.getSource().get("createTime") + "");
			eb.setRemark(hit.getSource().get("remark")+ "");
			list.add(eb);
		}
		page.setList(list);
		return page;
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		this.logger.info("-------关闭ES知识库搜索-------");
		this.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		this.logger.info("-------连接ES知识库搜索-------");
		//this.open();
	}

	/*
	 */
	private void open() {
		try {
			this.searchClient = TransportClient.builder().build();
			for (String item : this.clusterList) {
				String address = item.split(":")[0];
				int port = Integer.parseInt(item.split(":")[1]);
				this.searchClient = ((TransportClient) this.searchClient)
						.addTransportAddress(new InetSocketTransportAddress(
								InetAddress.getByName(address), port));
			}

		} catch (Exception e) {
			this.logger.error(e.getMessage());
		}
	}

	private void close() {
		if (this.searchClient == null) {
			return;
		}
		this.searchClient.close();
		this.searchClient = null;
	}

}

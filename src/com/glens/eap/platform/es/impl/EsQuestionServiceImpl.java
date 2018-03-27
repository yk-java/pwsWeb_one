package com.glens.eap.platform.es.impl;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glens.eap.platform.es.EsQuestionService;
import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.pwCloudOs.km.question.vo.EsQuestionVo;

public class EsQuestionServiceImpl implements EsQuestionService,
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
	public void addIndex(EsQuestionVo question) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonValue = mapper.writeValueAsString(question);
			response = searchClient
					.prepareIndex(index, type, question.getRowid() + "")
					.setSource(jsonValue).execute().actionGet();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateIndex(EsQuestionVo question) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonValue = mapper.writeValueAsString(question);
			searchClient.prepareUpdate(index, type, question.getRowid() + "")
					.setDoc(jsonValue).execute().get();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delIndex(EsQuestionVo question) {
		DeleteResponse response = searchClient.prepareDelete(index, type,
				question.getRowid() + "").get();
		System.out.println(response);
	}

	@Override
	public List<EsQuestionVo> query(String keyword) {
		SearchRequestBuilder searchRequestBuilder = this.searchClient
				.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DEFAULT).setFrom(0).setSize(100);
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(QueryBuilders.matchQuery("questionTitle", keyword))
				.should(QueryBuilders.matchQuery("keywords", keyword));
		searchRequestBuilder = searchRequestBuilder.setQuery(query);
		SearchResponse searchResponse = searchRequestBuilder.execute()
				.actionGet();
		long count = searchResponse.getHits().getTotalHits();
		SearchHits searchHits = searchResponse.getHits();
		List<EsQuestionVo> list = new ArrayList<EsQuestionVo>();
		for (SearchHit hit : searchHits) {
			System.out.println(hit.getSource());
			EsQuestionVo eb = new EsQuestionVo();
			eb.setCompanyCode(hit.getSource().get("companyCode") + "");
			eb.setTypeName(hit.getSource().get("typeName") + "");
			eb.setQuestionCode(hit.getSource().get("questionCode") + "");
			eb.setQuestionTitle(hit.getSource().get("questionTitle") + "");
			eb.setKeywords(hit.getSource().get("keywords") + "");
			eb.setRowid(Long.parseLong(hit.getSource().get("rowid") + ""));
			eb.setQuestionContent(hit.getSource().get("questionContent") + "");
			eb.setAnswer(hit.getSource().get("answer") + "");
			eb.setCollator(hit.getSource().get("collator") + "");
			eb.setSovler(hit.getSource().get("sovler") + "");
			eb.setSovleDate(hit.getSource().get("sovleDate") + "");
			eb.setPublicStatus(hit.getSource().get("publicStatus") + "");
			eb.setCreateDate(hit.getSource().get("createDate") + "");
			eb.setRemarks(hit.getSource().get("remarks") + "");
			eb.setProName(hit.getSource().get("proName") + "");
			eb.setProNo(hit.getSource().get("proNo") + "");
			eb.setChecker(hit.getSource().get("checker") + "");
			eb.setCheckOption(hit.getSource().get("checkOption") + "");
			list.add(eb);
		}
		return list;
	}

	@Override
	public PageBean queryForPage(String keyword, Integer pageSize,
			Integer pageNo, String isSearch, String status, String alertStatus) {
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
					.should(QueryBuilders.matchQuery("questionTitle", keyword))
					.should(QueryBuilders.matchQuery("keywords", keyword));
			if (isSearch.equals("1")) {
				query.should(QueryBuilders.matchQuery("questionContent",
						keyword));
				query.should(QueryBuilders.matchQuery("answer", keyword));
			}

			// 如果不为空
			if (!Strings.isNullOrEmpty(status)) {
				query.must(QueryBuilders.termQuery("status", status));
				if (status.equals("1") && !Strings.isNullOrEmpty(alertStatus)) {
					// planSoveDate
					Calendar cal = Calendar.getInstance();

					RangeQueryBuilder rangequerybuilder = null;
					RangeQueryBuilder rangequerybuilder2 = null;
					// 发布
					if (alertStatus.equals("0")) {
						// 正常
						String now = new SimpleDateFormat("yyyy-MM-dd")
								.format(cal.getTime());

						rangequerybuilder = QueryBuilders.rangeQuery(
								"planSoveDate").gte(now);

						rangequerybuilder2 = QueryBuilders.rangeQuery(
								"createDate").lte(now);

						query.must(rangequerybuilder);
						query.must(rangequerybuilder2);
					} else if (alertStatus.equals("1")) {
						// 告警
						String now = new SimpleDateFormat("yyyy-MM-dd")
								.format(cal.getTime());
						rangequerybuilder = QueryBuilders.rangeQuery(
								"alertDate").gte(now);

						rangequerybuilder2 = QueryBuilders.rangeQuery(
								"planSoveDate").lte(now);

						query.must(rangequerybuilder);
						query.must(rangequerybuilder2);
					} else if (alertStatus.equals("2")) {
						// 预期
						String now = new SimpleDateFormat("yyyy-MM-dd")
								.format(cal.getTime());
						rangequerybuilder = QueryBuilders.rangeQuery(
								"alertDate").lt(now);
						query.must(rangequerybuilder);
					}

				}

			}

			searchRequestBuilder = searchRequestBuilder.setQuery(query)
					.addSort("createDate", SortOrder.DESC)
					.setFrom((pageNo - 1) * pageSize).setSize(pageSize)
					.setExplain(true);
		} else {
			query = QueryBuilders.boolQuery();

			// 如果不为空
			if (!Strings.isNullOrEmpty(status)) {
				query.must(QueryBuilders.termQuery("status", status));
				if (status.equals("1") && !Strings.isNullOrEmpty(alertStatus)) {
					// planSoveDate
					Calendar cal = Calendar.getInstance();

					RangeQueryBuilder rangequerybuilder = null;
					RangeQueryBuilder rangequerybuilder2 = null;
					// 发布
					if (alertStatus.equals("0")) {
						// 正常
						// 时间范围的设定
						cal.add(Calendar.DATE, 3);
						String now = new SimpleDateFormat("yyyy-MM-dd")
								.format(cal.getTime());
						rangequerybuilder = QueryBuilders.rangeQuery(
								"planSoveDate").gt(now);

						query.must(rangequerybuilder);
					} else if (alertStatus.equals("1")) {
						// 告警
						String now = new SimpleDateFormat("yyyy-MM-dd")
								.format(cal.getTime());
						rangequerybuilder = QueryBuilders.rangeQuery(
								"planSoveDate").lte(now);

						rangequerybuilder2 = QueryBuilders.rangeQuery(
								"alertDate").gte(now);

						query.must(rangequerybuilder);
						query.must(rangequerybuilder2);
					} else if (alertStatus.equals("2")) {
						// 预期
						String now = new SimpleDateFormat("yyyy-MM-dd")
								.format(cal.getTime());
						rangequerybuilder = QueryBuilders.rangeQuery(
								"planSoveDate").lt(now);

						query.must(rangequerybuilder);
					}

				}

			}
			searchRequestBuilder = searchRequestBuilder.setQuery(query)
					.addSort("createDate", SortOrder.DESC).setFrom(0)
					.setSize(pageSize).setExplain(true);

		}

		SearchResponse searchResponse = searchRequestBuilder.execute()
				.actionGet();
		SearchHits searchHits = searchResponse.getHits();
		int totalCount = (int) searchHits.getTotalHits();
		page = new PageBean(totalCount, pageNo, pageSize);
		List<EsQuestionVo> list = new ArrayList<EsQuestionVo>();
		for (SearchHit hit : searchHits) {
			EsQuestionVo eb = new EsQuestionVo();
			eb.setCompanyCode(hit.getSource().get("companyCode") + "");
			eb.setTypeName(hit.getSource().get("typeName") + "");
			eb.setQuestionCode(hit.getSource().get("questionCode") + "");
			eb.setQuestionTitle(hit.getSource().get("questionTitle") + "");
			eb.setKeywords(hit.getSource().get("keywords") + "");
			eb.setRowid(Long.parseLong(hit.getSource().get("rowid") + ""));
			eb.setQuestionContent(hit.getSource().get("questionContent") + "");
			eb.setAnswer(hit.getSource().get("answer") + "");
			eb.setCollator(hit.getSource().get("collator") + "");
			eb.setSovler(hit.getSource().get("sovler") + "");
			eb.setSovleDate(hit.getSource().get("sovleDate") + "");
			eb.setPublicStatus(hit.getSource().get("publicStatus") + "");
			eb.setCreateDate(hit.getSource().get("createDate") + "");
			eb.setRemarks(hit.getSource().get("remarks") + "");
			eb.setProName(hit.getSource().get("proName") + "");
			eb.setProNo(hit.getSource().get("proNo") + "");
			eb.setChecker(hit.getSource().get("checker") + "");
			eb.setCheckOption(hit.getSource().get("checkOption") + "");
			eb.setPlanSoveDate(hit.getSource().get("planSoveDate") + "");
			list.add(eb);
		}
		page.setList(list);
		return page;
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		this.logger.info("-------关闭ES问题库搜索-------");
		this.close();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		this.logger.info("-------连接ES问题库搜索-------");
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

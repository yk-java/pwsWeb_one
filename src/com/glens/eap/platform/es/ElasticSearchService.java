package com.glens.eap.platform.es;

import java.util.List;

import com.glens.eap.platform.framework.beans.PageBean;
import com.glens.pwCloudOs.km.base.vo.EskmBase;

/**
 * es服务类
 * 
 * @author Administrator
 * 
 */
public interface ElasticSearchService {

	/**
	 * 创建索引文档
	 */
	public void addIndex(EskmBase kmbase);

	/**
	 * 更新索引文档
	 */
	public void updateIndex(EskmBase kmbase);

	/**
	 * 删除索引文档
	 */
	public void delIndex(EskmBase kmbase);

	/**
	 * 查询
	 * 
	 * @param keyword
	 * @return
	 */
	public List<EskmBase> query(String keyword);

	/**
	 * 分页查询
	 * 
	 * @param keyword
	 *            关键字
	 * @param pageSize
	 *            每页数量
	 * @param pageNo
	 *            当前页
	 * @param isSearch
	 *            1 全文检索
	 * @param status
	 *            状态
	 * @return
	 */
	public PageBean queryForPage(String keyword, Integer pageSize,
			Integer pageNo, String isSearch, String status);
}

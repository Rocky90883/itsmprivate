package com.koron.web.workflowUtils.bean;

import org.swan.bean.MessageBean;

import com.koron.util.Constant;

public class MessageEntity<T> extends MessageBean<T>{
	/**
	 * 当前页
	 */
	private Integer page;
	/**
	 * 每页条数
	 */
	private Integer pagecount;
	/**
	 * 列表数据总条数
	 */
	private Integer totalCount;
	/**
	 * 列表数据总条数
	 */
	private Integer total;

	
	/**
	 * 返回成功消息体
	 * @return
	 */
	public static <T> MessageEntity<T> success(){
		MessageEntity<T> bean = new MessageEntity<T>();
		bean.setCode(Constant.MESSAGE_INT_SUCCESS);
		bean.setDescription("success");
		return bean;
	}
	/**
	 * 返回成功消息体
	 * @param data 数据
	 * @return
	 */
	public static <T> MessageEntity<T> success(T data){
		MessageEntity<T> bean = new MessageEntity<T>();
		bean.setCode(Constant.MESSAGE_INT_SUCCESS);
		bean.setDescription("success");
		bean.setData(data);
		return bean;
	}
	/**
	 * 返回成功消息体
	 * @param data 数据
	 * @param page 起始页
	 * @param pagecount 每页条数
	 * @return
	 */
	public static <T> MessageEntity<T> success(T data,Integer page,Integer pagecount){
		MessageEntity<T> bean = new MessageEntity<T>();
		bean.setCode(Constant.MESSAGE_INT_SUCCESS);
		bean.setDescription("success");
		bean.setPage(page);
		bean.setPagecount(pagecount);
		bean.setData(data);
		return bean;
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagecount() {
		return pagecount;
	}
	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}

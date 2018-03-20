package blog.dao;

import java.util.List;

import blog.model.History;

public interface HistoryDao {

	

	/**
	 * 获取最新number条历史更新
	 * 
	 * @param
	 * @return
	 */
	List<History> getHistoryList(int start,int length);

	int howManyVersions();



}
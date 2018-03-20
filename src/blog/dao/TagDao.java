package blog.dao;

import java.util.List;

import blog.model.Tag;

public interface TagDao {


	// 删除标签时(id,tag) 若仅有某一个属性
	// 可以使用这些default值表示在sql语句中跳过这个属性
	int DEFAULT_ID = 0;
	
	/**
	 * 新的标签
	 * 
	 * @param id
	 * @param tag
	 * @return
	 */
	boolean addTag(String name);

	

	/**
	 * 获取所有的标签 不含重复
	 * 
	 * @return
	 */
	List<Tag> getAllTag();

	List<Tag> getRankedTags(int number);

	


	boolean addNewTag(String name);

	boolean deleteTag(int id);

	boolean dropRelationByTag(int tagId);

	boolean dropRelationByPost(int postId);

	boolean addRelation(int tagId, int postId);

	boolean updateTagNumber(int id, int delta);

	

	List<Integer>  getPostListByTag(int tagId);

	List<Integer> getTagListByPost(int postId);

	int howManyTags();
	
	Tag getTagById(int id);

}
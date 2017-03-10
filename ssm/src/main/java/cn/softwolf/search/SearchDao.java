package cn.softwolf.search;

import java.util.List;

import cn.softwolf.pojo.Item;

public interface SearchDao {

	public void createOrUpdateIndex(Item item) throws Exception;	
	
	public void deleteIndex(Integer id) throws Exception;
	
	public List<Item> searchItem(String keywords) throws Exception;
}



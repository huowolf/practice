package cn.softwolf.service;

import java.util.List;

import cn.softwolf.pojo.Item;

public interface ItemService {
	public void insertItem(Item item);

	public void deleteItem(Integer id);

	public void UpdateItem(Item item);

	public List<Item> selectAllItem();

	public Item findItemById(Integer id);
	
	public List<Item> seachItems(String keywords) throws Exception;
	
	public void deleteIndex(Integer id) throws Exception;
	
	public void createOrUpdateIndex(Item item) throws Exception;
}

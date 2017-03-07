package cn.softwolf.service;

import java.util.List;

import cn.softwolf.pojo.Item;

public interface ItemService {
	public void insertItem(Item item);

	public void deleteItem(Integer id);

	public void UpdateItem(Item item);

	public List<Item> selectAllItem();

	public Item findItemById(Integer id);
}

package cn.softwolf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.softwolf.mapper.ItemMapper;
import cn.softwolf.pojo.Item;
import cn.softwolf.search.SearchDao;
import cn.softwolf.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SearchDao searchDao;
	
	@Override
	public void insertItem(Item item) {
		itemMapper.insert(item);

	}

	@Override
	public void deleteItem(Integer id) {
		itemMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void UpdateItem(Item item) {
		itemMapper.updateByPrimaryKey(item);

	}

	@Override
	public List<Item> selectAllItem() {
		List<Item> items = itemMapper.selectByExample(null);
		return items;
	}

	@Override
	public Item findItemById(Integer id) {
		Item item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

	@Override
	public List<Item> seachItems(String keywords) throws Exception {
		List<Item> items = searchDao.searchItem(keywords);
		return items;
	}

	@Override
	public void deleteIndex(Integer id) throws Exception {
		searchDao.deleteIndex(id);
		
	}

	@Override
	public void createOrUpdateIndex(Item item) throws Exception {
		searchDao.createOrUpdateIndex(item);
		
	}

}

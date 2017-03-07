package cn.softwolf.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.softwolf.mapper.ItemMapper;
import cn.softwolf.pojo.Item;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})  
public class ItemCRUDTest {

	@Autowired
	private ItemMapper itemMapper;
	
	@Test
	public void insertItem(){
		Item item = new Item();
		item.setDescription("aaaaaaaaaaa");
		item.setTitle("bbbbbbbbbbbbb");
		item.setPic("ccccccccccccccc");
		item.setPrice(58.5);
		itemMapper.insert(item);
	}
	
	@Test
	public void deleteItem(){
		itemMapper.deleteByPrimaryKey(2);
	}
	
	@Test
	public void UpdateItem(){
		Item item = itemMapper.selectByPrimaryKey(1);
		item.setPrice(35.6);
		itemMapper.updateByPrimaryKeySelective(item);
	}
	
	@Test
	public void selectAllItem(){
		 List<Item> Items = itemMapper.selectByExample(null);
		 for (Item Item : Items) {
			System.out.println(Item);
		}
	}
}

package cn.softwolf.test;

import java.util.List;

import org.junit.Test;

import cn.softwolf.pojo.Item;
import cn.softwolf.util.HttpClientUtils;
import net.sf.json.JSONArray;

public class HttpClientTest {

	@Test
	public void getAllItemTest(){
		String result = HttpClientUtils.doGet("http://localhost:8080/ssm/findItemsRest",null);
		
		JSONArray jsonArray = JSONArray.fromObject(result);
		List<Item> items = (List<Item>) JSONArray.toCollection(jsonArray,Item.class);
		System.out.println(items);
	}
	
	public static void main(String[] args) {
		String result = HttpClientUtils.doGet("http://localhost:8080/ssm/findItemsRest",null);
		
		JSONArray jsonArray = JSONArray.fromObject(result);
		List<Item> items = (List<Item>) JSONArray.toCollection(jsonArray,Item.class);
		System.out.println(items);
	}
	
}

package cn.softwolf.test;

import java.util.List;

import org.junit.Test;

import cn.softwolf.pojo.Item;
import cn.softwolf.util.HttpClientUtils;
import net.sf.json.JSONArray;

public class HttpClientTest {

	@Test
	public void getAllItemTest(){
		String result = HttpClientUtils.executeByGET("http://localhost:8080/ssm/finditems",null);
		
		JSONArray jsonArray = JSONArray.fromObject(result);
		List<Item> items = (List<Item>) JSONArray.toCollection(jsonArray);
		System.out.println(items);
	}
	
}

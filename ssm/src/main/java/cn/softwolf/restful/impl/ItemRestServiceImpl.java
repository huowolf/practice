package cn.softwolf.restful.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.softwolf.pojo.Item;
import cn.softwolf.restful.ItemRestService;
import cn.softwolf.util.HttpClientUtils;
import net.sf.json.JSONArray;

@Service
public class ItemRestServiceImpl implements ItemRestService{

	@Value("${items_rest_path}")
	private String itemsRest;
	
	@Override
	public List<Item> getAllItems() {
		
		String result = HttpClientUtils.doGet(itemsRest,null);
		JSONArray jsonArray = JSONArray.fromObject(result);
		List<Item> items = (List<Item>) JSONArray.toCollection(jsonArray,Item.class);
		return items;
	}

}

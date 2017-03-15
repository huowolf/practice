package cn.softwolf.restful.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.softwolf.pojo.Item;
import cn.softwolf.restful.ItemRestService;
import cn.softwolf.util.HttpClientUtils;
import net.sf.json.JSONArray;

@Service
public class ItemRestServiceImpl implements ItemRestService{

	@Override
	public List<Item> getAllItems() {
		
		String result = HttpClientUtils.executeByGET("http://localhost:8080/ssm/finditems");
		JSONArray jsonArray = JSONArray.fromObject(result);
		List<Item> items = (List<Item>) JSONArray.toCollection(jsonArray);
		return items;
	}

}

package cn.softwolf.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.softwolf.dto.Product;
import net.sf.json.JSONArray;

@Controller
public class EChartController {

	@RequestMapping("/productBar")
	@ResponseBody
	public String productBar(){
		List<Product> products = new ArrayList<>();
		products.add(new Product("羊毛衫",30));
		products.add(new Product("裤子",20));
		products.add(new Product("衬衫",26));
		products.add(new Product("高跟鞋",18));
		products.add(new Product("雪纺衫",10));
		
		return JSONArray.fromObject(products).toString();
	}
	
	@RequestMapping("/chart")
	public String echartshow(){
		return "chart";
	}
}

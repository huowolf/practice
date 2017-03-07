package cn.softwolf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.softwolf.pojo.Item;
import cn.softwolf.service.ItemService;


@Controller
public class ItemController {
	
	@Autowired 
	private ItemService itemService;
	
	@RequestMapping("/finditems")
	public String findAllItems(Model model) throws Exception{
		List<Item> items = itemService.selectAllItem();
		model.addAttribute("items", items);
		
		return "itemList";
	}
}

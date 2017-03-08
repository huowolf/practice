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
	
	@RequestMapping("/itemManager")
	public String itemManager(Model model)throws Exception{
		List<Item> items = itemService.selectAllItem();
		model.addAttribute("items", items);
		return "itemManager";
	}
	
	@RequestMapping("/saveItemUI")
	public String saveItemUI(Model model,Integer id) throws Exception{
		Item item = itemService.findItemById(id);
		model.addAttribute("item", item);
		return "saveItemUI";
	}
	
	@RequestMapping("/saveItem")
	public String saveItem(Item item){
		if(item.getId() == null){
			itemService.insertItem(item);
		}else{
			itemService.UpdateItem(item);
		}
		return "redirect:itemManager";
	}
		
	@RequestMapping("/deleteItem")
	public String deleteItem(Integer id){
		itemService.deleteItem(id);
		return "redirect:itemManager";
	}
}

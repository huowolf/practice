package cn.softwolf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.softwolf.pojo.Item;
import cn.softwolf.service.ItemService;


@Controller
public class ItemController {
	@Value("${file_server_path}")
	private String fileServer;
	
	@Autowired 
	private ItemService itemService;
	
	@RequestMapping("/finditems")
	public String findAllItems(Model model) throws Exception{
		List<Item> items = itemService.selectAllItem();
		
		for (Item item : items) {
			item = this.changePic(item);
		}
		
		model.addAttribute("items", items);	
		return "itemList";
	}
	
	@RequestMapping("/itemManager")
	public String itemManager(Model model)throws Exception{
		List<Item> items = itemService.selectAllItem();
		for (Item item : items) {
			item = this.changePic(item);
		}
		model.addAttribute("items", items);
		return "itemManager";
	}
	
	@RequestMapping("/saveItemUI")
	public String saveItemUI(Model model,Integer id) throws Exception{
		if(id != null){
			Item item = itemService.findItemById(id);
			
			item = this.changePic(item);
			
			model.addAttribute("item", item);
		}
		
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
	
	private Item changePic(Item item){
		String pic = item.getPic();
		if(!pic.contains("http:")&& pic!=null && !pic.equals("")){
			item.setPic(fileServer + item.getPic());
		}	
		return item;
	}
}

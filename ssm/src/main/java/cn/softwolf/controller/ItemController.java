package cn.softwolf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.softwolf.pojo.Item;
import cn.softwolf.restful.ItemRestService;
import cn.softwolf.service.ItemService;
import cn.softwolf.service.StaticPageService;
import net.sf.json.JSONArray;


@Controller
public class ItemController {
	@Value("${file_server_path}")
	private String fileServer;
	
	@Autowired 
	private ItemService itemService;
	
	@Autowired
	private StaticPageService staticPageService;
	
	@Autowired
	private ItemRestService ItemRestService;
	
	@RequestMapping("/finditems")
	@ResponseBody
	public String findAllItems(Model model) throws Exception{
		List<Item> items = itemService.selectAllItem();
		
		for (Item item : items) {
			item = this.changePic(item);
		}
		
		return JSONArray.fromObject(items).toString();
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
		//更新索引库、
		try {
			itemService.createOrUpdateIndex(item);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//生成静态页
		try {
			staticPageService.createHtml(item.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:itemManager";
	}
		
	@RequestMapping("/deleteItem")
	public String deleteItem(Integer id){
		itemService.deleteItem(id);
		
		//删除索引库
		try {
			itemService.deleteIndex(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:itemManager";
	}
	
	
	@RequestMapping("/searchItem")
	public String searchItem(String keyword,Model model){
		try {
			List<Item> items = itemService.seachItems(keyword);
			
			for (Item item : items) {
				item = this.changePic(item);
			}
			
			model.addAttribute("items", items);
			model.addAttribute("keyword", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "itemManager";
	}
	
	/**
	 * 将图片的相对地址拼接成绝对地址
	 * @param item
	 * @return
	 */
	private Item changePic(Item item){
		String pic = item.getPic();
		if(!pic.contains("http:")&& pic!=null && !pic.equals("")){
			item.setPic(fileServer + item.getPic());
		}	
		return item;
	}
	
	
	@RequestMapping("/itemList")
	public String itemList(Model model){		
		List<Item> items = ItemRestService.getAllItems();
		model.addAttribute("items", items);
		return "itemManager";
	}
}

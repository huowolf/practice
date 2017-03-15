package cn.softwolf.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.softwolf.dto.TreeDTO;
import cn.softwolf.pojo.User;
import cn.softwolf.service.ResourceService;
import cn.softwolf.service.UserService;
import net.sf.json.JSONArray;

@Controller
public class UserControllerForEasyUI {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/userMain")
	public String userMain(Model model){
		return "userLists";
	}
	
	@RequestMapping("/userList")
	public void userLists( HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		List<User> users = userService.selectAllUser();
		String json = "{\"total\":"+ 10 + ",\"rows\":"+ JSONArray.fromObject(users)+"}";
		
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/loadTree")
	public void loadTree(Integer id,HttpServletResponse response){
		List<TreeDTO> treeList = resourceService.getChildByParentId(id);
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write(JSONArray.fromObject(treeList).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

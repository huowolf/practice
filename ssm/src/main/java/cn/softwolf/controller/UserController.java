package cn.softwolf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.softwolf.pojo.User;
import cn.softwolf.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/findusers")
	public String findAllUsers(Model model) throws Exception{
		List<User> users = userService.selectAllUser();
		model.addAttribute("users", users);
		
		return "userList";
	}
	
	@RequestMapping("/saveUserUI")
	public String updateUserUI(Model model,Integer id) throws Exception{	
		User user = userService.findUserById(id);
		
		//回显数据到页面
		model.addAttribute("user", user);
		return "saveUserUI";
	}
	
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Integer id) throws Exception{
		userService.deleteUser(id);
		return "redirect:findusers";
	}
	

	@RequestMapping("/saveUser")
	public String saveUser(User user) throws Exception{
		if(user.getId() == null){
			userService.insertUser(user);
		}else{
			userService.UpdateUser(user);
		}
		return "redirect:findusers";
	}
}

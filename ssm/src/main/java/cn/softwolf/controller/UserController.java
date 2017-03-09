package cn.softwolf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.softwolf.dto.UserQuery;
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
	public String saveUserUI(Model model,Integer id) throws Exception{	
		if(id != null){
			User user = userService.findUserById(id);
			model.addAttribute("user", user);
		}
		
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
	
	@RequestMapping("/searchUser")
	public String searchUser(UserQuery userQuery,Model model){
		List<User> users = userService.selectUserByExample(userQuery);
		model.addAttribute("users", users);
		model.addAttribute("userQuery", userQuery);
		return "userList";
	}
}

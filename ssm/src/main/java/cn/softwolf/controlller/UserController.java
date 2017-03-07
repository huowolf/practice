package cn.softwolf.controlller;

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
	
	
	@RequestMapping("/findAllUsers")
	public String findAllUsers(Model model) throws Exception{
		List<User> users = userService.selectAllUser();
		model.addAttribute("users", users);
		
		return "userList";
	}
}

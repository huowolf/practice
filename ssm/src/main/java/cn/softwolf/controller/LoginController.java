package cn.softwolf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.softwolf.pojo.User;
import cn.softwolf.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(HttpSession session,String name,String password,Model model){
		User user = userService.findUserByNameAndPwd(name, password);
		if( user !=null){
			session.setAttribute("user", user);
			return "index";
		}else{
			model.addAttribute("error", "用户名或密码错误！");
			return "login";
		}
	}
}

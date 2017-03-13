package cn.softwolf.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping("exportUser")
	public void exportUser(HttpServletResponse response){
		response.setContentType("application/vnd.ms-excel; charset=utf-8");  
        try  
        {  
            ServletOutputStream outputStream = response.getOutputStream();  
            response.setHeader("Content-disposition", "attachment; filename=user.xlsx");// 组装附件名称和格式   
            String[] titles = { "姓名", "性别", "年龄","密码" };  
            userService.exportExcel(titles, outputStream);  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
	}
	
	@RequestMapping("importUser")
	public String exportUser(MultipartFile execl){
	        if (execl == null)  
	        {  
	            return null;  
	        }  
	        try  
	        {  
 	            InputStream input = execl.getInputStream();  
	            XSSFWorkbook workBook = new XSSFWorkbook(input);  
	            XSSFSheet sheet = workBook.getSheetAt(0);  
	            if (sheet != null)  
	            {  
	                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)  
	                {  
	                    XSSFRow row = sheet.getRow(i);  
	                    
	                    String name = row.getCell(0).toString();
	                    String sex = row.getCell(1).toString();
	                    String age = row.getCell(2).toString();
	                    String password = row.getCell(3).toString();
	                    
	                    
	                    User user = new User();
	                    user.setName(name);
	                    user.setAge((int)Double.parseDouble(age));
	                    user.setPassword(password);
	                    if(sex.equals("男")){
	                    	user.setSex((byte)1);
	                    }else{
	                    	user.setSex((byte)0);
	                    }
	                    userService.insertUser(user);
	                }  
	  
	            }  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	        
	        return "redirect:findusers";
	}
}

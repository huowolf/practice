package cn.softwolf.service;

import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.softwolf.dto.UserQuery;
import cn.softwolf.pojo.User;

public interface UserService {
	public void insertUser(User user);
	
	public void deleteUser(Integer id);
	
	public void UpdateUser(User user);
	
	public List<User> selectAllUser();
	
	public List<User> selectUserByExample(UserQuery userQuery);
	
	public User findUserById(Integer id);
	
	public User findUserByNameAndPwd(String name,String password);
	
	public void exportExcel(String [] titles,ServletOutputStream outputStream);  
	
}

package cn.softwolf.service;

import java.util.List;

import cn.softwolf.pojo.User;

public interface UserService {
	public void insertUser(User user);
	
	public void deleteUser(Integer id);
	
	public void UpdateUser(User user);
	
	public List<User> selectAllUser();
	
	public User findUserById(Integer id);
	
	public User findUserByNameAndPwd(String name,String password);
}

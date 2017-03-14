package cn.softwolf.service;

import java.util.List;
import java.util.Set;

import cn.softwolf.pojo.TUser;

public interface UserService {
	public void insertUser(TUser user);
	
	public void deleteUser(Integer id);
	
	public void UpdateUser(TUser user);
	
	public List<TUser> selectAllUser();
	
	public TUser findUserById(Integer id);
	
	public TUser findUserByName(String name);
	
	public TUser getUser(Integer id);
	
	public Set<String> findRoles(String username);

	public Set<String> findPermissions(String username);
}

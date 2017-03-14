package cn.softwolf.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.softwolf.mapper.TUserMapper;
import cn.softwolf.pojo.TUser;
import cn.softwolf.pojo.TUserExample;
import cn.softwolf.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TUserMapper userMapper;
	
	@Override
	public void insertUser(TUser user) {
		userMapper.insert(user);	

	}

	@Override
	public void deleteUser(Integer id) {
		userMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void UpdateUser(TUser user) {
		userMapper.updateByPrimaryKeySelective(user);

	}

	@Override
	public List<TUser> selectAllUser() {
		List<TUser> users = userMapper.selectByExample(null);
		return users;
	}

	@Override
	public TUser findUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public TUser findUserByName(String name) {
		TUserExample userExample = new TUserExample();
		userExample.createCriteria().andUsernameEqualTo(name);
		List<TUser> users = userMapper.selectByExample(userExample);
		if(users==null  || users.size()==0){
			return null;
		}else{
			return users.get(0);
		}
	}

	@Override
	public TUser getUser(Integer id) {
		
		return userMapper.getUser(id);
	}

	@Override
	public Set<String> findRoles(String username) {
		return userMapper.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {
		
		return userMapper.findPermissions(username);
	}

}

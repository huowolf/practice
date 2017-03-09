package cn.softwolf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.softwolf.dto.UserQuery;
import cn.softwolf.mapper.UserMapper;
import cn.softwolf.pojo.User;
import cn.softwolf.pojo.UserExample;
import cn.softwolf.pojo.UserExample.Criteria;
import cn.softwolf.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;


	@Override
	public List<User> selectAllUser() {
		List<User> users = userMapper.selectByExample(null);
		return users;
	}



	@Override
	public void insertUser(User user) {
		userMapper.insert(user);	
	}



	@Override
	public void deleteUser(Integer id) {
		userMapper.deleteByPrimaryKey(id);
		
	}


	@Override
	public void UpdateUser(User u) {
		userMapper.updateByPrimaryKeySelective(u);
		
	}


	@Override
	public User findUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}



	@Override
	public User findUserByNameAndPwd(String name, String password) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
		List<User> users = userMapper.selectByExample(userExample);
		if(users==null || users.size()==0){
			return null;
		}else{
			return users.get(0);
		}
	}



	@Override
	public List<User> selectUserByExample(UserQuery userQuery) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if(userQuery.getName() != null){
			criteria.andNameLike("%"+userQuery.getName()+"%");
		}
		if(userQuery.getSex() == 1){//男
			criteria.andSexEqualTo((byte)1);
		}
		if(userQuery.getSex() == 0){//女
			criteria.andSexEqualTo((byte)0);
		}
		if(userQuery.getMinAge() != null){
			criteria.andAgeGreaterThanOrEqualTo(userQuery.getMinAge());
		}
		if(userQuery.getMaxAge() != null){
			criteria.andAgeLessThanOrEqualTo(userQuery.getMaxAge());
		}
		List<User> users = userMapper.selectByExample(example);
		return users;
	}

}

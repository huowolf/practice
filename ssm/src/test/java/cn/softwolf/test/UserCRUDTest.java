package cn.softwolf.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.softwolf.mapper.UserMapper;
import cn.softwolf.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})  
public class UserCRUDTest {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void insertUser(){
		User u = new User();
		u.setName("haha");
		u.setSex((byte)1);
		u.setAge(25);
		userMapper.insert(u);
	}
	
	@Test
	public void deleteUser(){
		userMapper.deleteByPrimaryKey(6);
	}
	
	@Test
	public void UpdateUser(){
		User u = userMapper.selectByPrimaryKey(5);
		u.setAge(35);
		userMapper.updateByPrimaryKeySelective(u);
	}
	
	@Test
	public void selectAllUser(){
		 List<User> users = userMapper.selectByExample(null);
		 for (User user : users) {
			System.out.println(user);
		}
	}
}

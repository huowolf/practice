package cn.softwolf.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.softwolf.mapper.TUserMapper;
import cn.softwolf.pojo.TUser;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})  
public class UserDAOTest {

	@Autowired
	private TUserMapper userMapper;
	
	@Test
	public void selectUserTest() {
		TUser user = userMapper.getUser(1);
		System.out.println(user);
	}

}

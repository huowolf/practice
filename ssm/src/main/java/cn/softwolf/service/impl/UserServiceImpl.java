package cn.softwolf.service.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.softwolf.dto.UserQuery;
import cn.softwolf.mapper.UserMapper;
import cn.softwolf.pojo.User;
import cn.softwolf.pojo.UserExample;
import cn.softwolf.pojo.UserExample.Criteria;
import cn.softwolf.service.UserService;
import cn.softwolf.util.ExportUtil;

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
		if(userQuery.getSex() != null){
			if(userQuery.getSex() == 1){//男
				criteria.andSexEqualTo((byte)1);
			}
			if(userQuery.getSex() == 0){//女
				criteria.andSexEqualTo((byte)0);
			}
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



	@Override
	public void exportExcel(String[] titles, ServletOutputStream outputStream) {
		List<User> list = this.selectAllUser();
        // 创建一个workbook 对应一个excel应用文件  
        XSSFWorkbook workBook = new XSSFWorkbook();  
        // 在workbook中添加一个sheet,对应Excel文件中的sheet  
        XSSFSheet sheet = workBook.createSheet();  
        ExportUtil exportUtil = new ExportUtil(workBook, sheet);  
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();  
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();  
        // 构建表头  
        XSSFRow headRow = sheet.createRow(0);  
        XSSFCell cell = null;  
        for (int i = 0; i < titles.length; i++)  
        {  
            cell = headRow.createCell(i);  
            cell.setCellStyle(headStyle);  
            cell.setCellValue(titles[i]);  
        }  
        // 构建表体数据  
        if (list != null && list.size() > 0)  
        {  
            for (int j = 0; j < list.size(); j++)  
            {  
                XSSFRow bodyRow = sheet.createRow(j + 1);  
                User user = list.get(j);  
  
                cell = bodyRow.createCell(0);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(user.getName());  
  
                cell = bodyRow.createCell(1);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(user.getSex());  
  
                cell = bodyRow.createCell(2);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(user.getAge());  
                
                cell = bodyRow.createCell(3);  
                cell.setCellStyle(bodyStyle);  
                cell.setCellValue(user.getPassword());  
            }  
        }  
        try  
        {  
            workBook.write(outputStream);  
            outputStream.flush();  
            outputStream.close();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            try  
            {  
                outputStream.close();  
            }  
            catch (IOException e)  
            {  
                e.printStackTrace();  
            }  
        }  
  
    }  
		

}

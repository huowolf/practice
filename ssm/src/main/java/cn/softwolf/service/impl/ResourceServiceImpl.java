package cn.softwolf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.softwolf.dto.TreeDTO;
import cn.softwolf.mapper.ResourceMapper;
import cn.softwolf.pojo.Resource;
import cn.softwolf.pojo.ResourceExample;
import cn.softwolf.pojo.ResourceExample.Criteria;
import cn.softwolf.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceMapper resourceMapper;
	
	@Override
	public List<TreeDTO> getChildByParentId(Integer id) {
		List<TreeDTO> treeList = new ArrayList<>();
		
		ResourceExample resourceExample = new ResourceExample();
		Criteria criteria = resourceExample.createCriteria();
		if(id==null){
			criteria.andParentIdEqualTo(9999);
		}else{
			criteria.andParentIdEqualTo(id);
		}
		
		List<Resource> resources = resourceMapper.selectByExample(resourceExample);
		
		//转换成treeDTO对象
		for (Resource res : resources) {
			TreeDTO treeDTO = new TreeDTO();
			
			treeDTO.setId(res.getId());
			treeDTO.setText(res.getName());
			treeDTO.setChecked(res.getChecked());
			if(resources!=null && resources.size()!=0){	//当前是非叶子节点，则默认不用自动展开
				treeDTO.setState("closed");
			}else{
				treeDTO.setState("open");
			}
			treeDTO.getAttributes().put("url", res.getUrl());
			
			treeList.add(treeDTO);
		}
		return treeList;
	}

}

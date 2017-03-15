package cn.softwolf.service;

import java.util.List;

import cn.softwolf.dto.TreeDTO;

public interface ResourceService {

	List<TreeDTO> getChildByParentId(Integer id);
}

package cn.softwolf.service.impl;


import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.softwolf.mapper.ItemMapper;
import cn.softwolf.pojo.Item;
import cn.softwolf.service.StaticPageService;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class StaticPageServiceImpl implements StaticPageService {
	
	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	
	@Value("${static_page_path}")
	private String staticPage;
	
	@Override
	public void createHtml(Integer id) throws Exception {
		Item item = itemMapper.selectByPrimaryKey(id);
		
		Configuration config =  freeMarkerConfigurer.getConfiguration();
		Template template =  config.getTemplate("item.ftl");
		
		Map<String, Object> root = new HashMap<>();
		root.put("items", item);
		
		Writer writer = new FileWriter(staticPage+id+".html");
		
		template.process(root, writer);
		
		writer.flush();
		writer.close();
	}

}

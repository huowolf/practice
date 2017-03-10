package cn.softwolf.search.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.softwolf.pojo.Item;
import cn.softwolf.search.SearchDao;

@Component
public class SeachDaoImpl implements SearchDao {
	
	@Autowired
	private SolrClient solrClient;

	@Override
	public void createOrUpdateIndex(Item item) throws Exception {
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", item.getId());
		document.addField("item_title", item.getTitle());
		document.addField("item_price", item.getPrice());
		document.addField("item_description", item.getDescription());
		document.addField("item_pic", item.getPic());
		
		solrClient.add(document);
		solrClient.commit();
	}

	@Override
	public void deleteIndex(Integer id) throws Exception {
		solrClient.deleteById(id+"");
		solrClient.commit();
	}

	@Override
	public List<Item> searchItem(String keywords) throws Exception {
		SolrQuery query = new SolrQuery(keywords);
		query.set("df", "item_keywords");
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		
		QueryResponse response = solrClient.query(query);
		
		SolrDocumentList list = response.getResults();
		
		List<Item> items = new ArrayList<>();
		
		for (SolrDocument doc : list) {
			Item item = new Item();
			item.setId(Integer.parseInt((String)doc.get("id")));
			item.setPic((String)doc.get("item_pic"));
			item.setPrice((Double)doc.get("item_price"));
			item.setDescription((String)doc.get("item_description"));
			
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> highlist = highlighting.get(doc.get("id")).get("item_title");
			String itemTitle = "";
			if (highlist != null && highlist.size() > 0) {
				//取高亮后的结果
				itemTitle = highlist.get(0);
			} else {
				itemTitle = (String) doc.get("item_title");
			}
			item.setTitle(itemTitle);
			
			items.add(item);
		}
		
		return items;
	}

}

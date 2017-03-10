package cn.softwolf.test;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.softwolf.pojo.Item;
import cn.softwolf.search.SearchDao;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:spring/applicationContext-*.xml"})  
public class SolrjTest {

	@Autowired
	private SolrClient solrClient;
	
	@Autowired
	private SearchDao searchDao;
	
	@Test
	public void SearchItemTest() throws Exception{
		SolrQuery query = new SolrQuery("*:*");
		query.set("df", "item_keywords");
		
		QueryResponse response = solrClient.query(query);
		
		SolrDocumentList list = response.getResults();
		
		for (SolrDocument doc : list) {
			System.out.println("id:" + doc.get("id"));
			System.out.println("item_title:" + doc.get("item_title"));
			System.out.println("item_price:" + doc.get("item_price"));
			System.out.println("item_pic:" + doc.get("item_pic"));
			System.out.println("item_description:" + doc.get("item_description"));
			System.out.println("==========================================");
		}
		
		solrClient.close();
	}
	
	@Test
	public void SearchDaoTest() throws Exception{
		List<Item> items = searchDao.searchItem("馄饨");
		for (Item item : items) {
			System.out.println(item);
		}
	}
}

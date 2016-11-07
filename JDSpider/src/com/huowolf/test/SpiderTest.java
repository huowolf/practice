/**
 * 
 */
package com.huowolf.test;


import org.junit.Test;

import com.huowolf.service.SpiderService;

/**
 * @author huowolf
 *
 */
public class SpiderTest {

	SpiderService spider = new SpiderService();
	
	@Test
	public void testSpiderOnepage() {
		spider.spiderOnepage("零食", 7);
		System.out.println("------------------");
		spider.spiderOnepage("零食", 8);
		System.out.println("------------------");
		spider.spiderOnepage("零食",9);
	}
	
	@Test
	public void testSpiderData() {
		spider.spiderData("零食");
	}
	

}

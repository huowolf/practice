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
		spider.spiderOnepage("��ʳ", 7);
		System.out.println("------------------");
		spider.spiderOnepage("��ʳ", 8);
		System.out.println("------------------");
		spider.spiderOnepage("��ʳ",9);
	}
	
	@Test
	public void testSpiderData() {
		spider.spiderData("��ʳ");
	}
	

}

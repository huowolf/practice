package com.huowolf.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.huowolf.pojo.Product;

public class SpiderService {
	/**
	 * 爬取一个页面的数据，返回得到的商品对象的集合。
	 */
	public Set<Product> spiderOnepage(String keyword, int page) {
		TreeSet<Product> productSet = new TreeSet<Product>();

		/*
		 * offset=2代表页面中的按销量排序
		 */
		try {
			Document doc = Jsoup.connect(
							"http://search.jd.com/Search?enc=utf-8qrst=1&rt=1&psort=3&stop=1&vt=2&offset=2&click=0")
					.data("keyword", keyword).data("page", String.valueOf(page)).userAgent("Mozilla")
					.cookie("auth", "token").get();

			Elements goodsList = doc.select(".gl-item");

			for (Element goodsEle : goodsList) {
				// 把解析的内容封装为一个对象
				Product p = new Product();

				// 图片地址出现在src或者data-lazy-img属性上
				String img = goodsEle.select(".p-img a img").attr("src");
				if (img.equals("")) {
					img = goodsEle.select(".p-img a img").attr("data-lazy-img");
				}
				p.setImage("http:" + img); // 设置图片
				
				String price = goodsEle.select(".p-price strong").attr("data-price");
				System.out.println(price);
				if(!price.equals("0.00")){
					p.setPrice(Double.parseDouble(price)); // 设置价格
				}else{
					System.out.println( goodsEle.select(".p-price").html());
				}

				goodsEle.select(".p-name em font").unwrap(); // 先去掉<font class="skcolor_ljg"></font>
				String description = goodsEle.select(".p-name em").first().html(); // 可能会出现多个
				p.setDescription(description); // 设置描述

				String commit = goodsEle.select(".p-commit a").first().html();
				commit = commit.substring(0, commit.length() - 1);
				long commitPro = 0;
				if (commit.contains("万")) {
					commit = commit.replaceAll("万", "");
					commitPro = (long) ((Double.parseDouble(commit)) * 10000);
				} else {
					commitPro = (long) Double.parseDouble(commit);
				}
				p.setComment(commitPro); // 设置评论量

				productSet.add(p);
			}

			Iterator<Product> it = productSet.iterator();
			int num = 0;
			while (it.hasNext()) {
				Product p = it.next();
				num++;
				//留下本页销量最高的十条数据
				if (num > 10) {
					it.remove();
				}
				//System.out.println(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productSet;
	}
	
	public void spiderData(String keyword){
		
		TreeSet<Product> productSet = new TreeSet<Product>();
		//爬取代表销量最高的10页数据
		for (int i = 1; i <= 10; i++) {
			TreeSet<Product> productTemp = (TreeSet<Product>) spiderOnepage(keyword, i);
			System.out.println("-----------------------"+productTemp.size());
			productSet.addAll(productTemp);		
			
			//睡眠100毫秒，否则有的价格数据抓取不到。
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("-----------------------"+productSet.size());
		
		Iterator<Product> it = productSet.iterator();
		int num = 0;
		while (it.hasNext()) {
			Product p = it.next();
			//num++;
			//留下本页销量最高的十条数据
/*			if (num >= 10) {
				it.remove();
			}*/
			//System.out.println(p);
		}
	}
}

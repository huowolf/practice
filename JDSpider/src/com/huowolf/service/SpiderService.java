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
	 * ��ȡһ��ҳ������ݣ����صõ�����Ʒ����ļ��ϡ�
	 */
	public Set<Product> spiderOnepage(String keyword, int page) {
		TreeSet<Product> productSet = new TreeSet<Product>();

		/*
		 * offset=2����ҳ���еİ���������
		 */
		try {
			Document doc = Jsoup.connect(
							"http://search.jd.com/Search?enc=utf-8qrst=1&rt=1&psort=3&stop=1&vt=2&offset=2&click=0")
					.data("keyword", keyword).data("page", String.valueOf(page)).userAgent("Mozilla")
					.cookie("auth", "token").get();

			Elements goodsList = doc.select(".gl-item");

			for (Element goodsEle : goodsList) {
				// �ѽ��������ݷ�װΪһ������
				Product p = new Product();

				// ͼƬ��ַ������src����data-lazy-img������
				String img = goodsEle.select(".p-img a img").attr("src");
				if (img.equals("")) {
					img = goodsEle.select(".p-img a img").attr("data-lazy-img");
				}
				p.setImage("http:" + img); // ����ͼƬ
				
				String price = goodsEle.select(".p-price strong").attr("data-price");
				System.out.println(price);
				if(!price.equals("0.00")){
					p.setPrice(Double.parseDouble(price)); // ���ü۸�
				}else{
					System.out.println( goodsEle.select(".p-price").html());
				}

				goodsEle.select(".p-name em font").unwrap(); // ��ȥ��<font class="skcolor_ljg"></font>
				String description = goodsEle.select(".p-name em").first().html(); // ���ܻ���ֶ��
				p.setDescription(description); // ��������

				String commit = goodsEle.select(".p-commit a").first().html();
				commit = commit.substring(0, commit.length() - 1);
				long commitPro = 0;
				if (commit.contains("��")) {
					commit = commit.replaceAll("��", "");
					commitPro = (long) ((Double.parseDouble(commit)) * 10000);
				} else {
					commitPro = (long) Double.parseDouble(commit);
				}
				p.setComment(commitPro); // ����������

				productSet.add(p);
			}

			Iterator<Product> it = productSet.iterator();
			int num = 0;
			while (it.hasNext()) {
				Product p = it.next();
				num++;
				//���±�ҳ������ߵ�ʮ������
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
		//��ȡ����������ߵ�10ҳ����
		for (int i = 1; i <= 10; i++) {
			TreeSet<Product> productTemp = (TreeSet<Product>) spiderOnepage(keyword, i);
			System.out.println("-----------------------"+productTemp.size());
			productSet.addAll(productTemp);		
			
			//˯��100���룬�����еļ۸�����ץȡ������
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
			//���±�ҳ������ߵ�ʮ������
/*			if (num >= 10) {
				it.remove();
			}*/
			//System.out.println(p);
		}
	}
}

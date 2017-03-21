package cn.softwolf.dto;

public class Product {

	private Integer id;
	private String name;
	private Integer num;

	public Product(){}
	
	public Product(String name, Integer num) {
		this.name = name;
		this.num = num;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}

/**  
* @Title: ViewOrderDetail.java  
* @Package com.qfedu.common.vo  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Feri 
* @date 2018年5月31日  
* @version V1.0  
*/  
package com.qfedu.common.vo;
public class ViewOrderDetail {
	private String name;
	private double price;
	private int num;
	private int star;
	private String  picture;
	private String pubdate;
	private double money;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
}
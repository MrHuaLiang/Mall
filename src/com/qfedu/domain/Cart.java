/**
* @author Feri 
*/
package com.qfedu.domain;
public class Cart {
	private int id;
	private int uid;
	private double money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", uid=" + uid + ", money=" + money + "]";
	}
	
}
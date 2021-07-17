/**  
* @author Feri 
*/
package com.hualiang.domain;
public class OrderDetail {
	private int id;
	private String oid;
	private int gid;
	private int num;
	private double money;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", oid=" + oid + ", gid=" + gid + ", num=" + num + ", money=" + money + "]";
	}
	
}
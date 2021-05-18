/**  
* @Title: CartService.java  
* @Package com.qfedu.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Feri 
* @date 2018年5月30日  
* @version V1.0  
*/
package com.qfedu.service;
import java.util.List;

import com.qfedu.common.vo.ViewCart;
import com.qfedu.domain.Cart;
import com.qfedu.domain.Goods;
public interface CartService {
	// 创建购物车
	boolean createCart(Cart cart);
	// 加入购物车 详情页
	boolean add(int cid, Goods gds, int num);
	// 修改数量 购物车页面
	boolean changeNum(int cid, Goods gds, int num);
	// 获取购物车对象
	Cart queryByUid(int uid);
	// 购物车的数据
	List<ViewCart> queryCart(int cid);
	// 删除购物车的商品
	int deleteDetail(int cid,int gid);
	//删除用户购物车
	int deleteByUid(int uid);
}
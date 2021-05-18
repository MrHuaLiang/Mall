/**  
* @Title: OrderService.java  
* @Package com.qfedu.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Feri 
* @date 2018年5月31日  
* @version V1.0  
*/
package com.qfedu.service;
import java.util.List;
import com.qfedu.common.vo.ViewOrder;
import com.qfedu.domain.CartDetail;
import com.qfedu.domain.Order;
public interface OrderService {
	//下单
	public boolean save(String oid, int uid, int uaid);
	//直接下单
	public boolean insertDirect(int uid,String oid,int uaid,CartDetail cd);
	// 修改订单状态
	public boolean update(String oid, int flag);
	// 查询订单列表
	public List<Order> queryByUid(int uid);
	// 查询详情
	public ViewOrder queryOrderDetailById(String oid);
	// 查询全部订单
	public List<Order> queryAll();
	//根据用户姓名和订单的支付状态查询订单（admin）
	List<Order> selectByNameAndFlag(String username,Integer flag);
	//删除订单（admin）
	int deleteById(int id);
}
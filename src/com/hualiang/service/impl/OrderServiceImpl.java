/**  
* @Title: OrderServiceImple.java  
* @Package com.qfedu.service.imple  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Feri 
* @date 2018年5月31日  
* @version V1.0  
*/  
package com.hualiang.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hualiang.common.vo.ViewOrder;
import com.hualiang.dao.CartDao;
import com.hualiang.dao.OrderDao;
import com.hualiang.domain.CartDetail;
import com.hualiang.domain.Order;
import com.hualiang.domain.OrderDetail;
import com.hualiang.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao dao;
	@Autowired
	private CartDao cartDao;
	//新增订单
	@Override
	public boolean save(String oid, int uid, int uaid) {	
		List<CartDetail> cds=cartDao.queryByDetail(uid);
		double sum = 0;
		// 添加到订单详情表
		for (int i = 0; i < cds.size(); i++) {
			OrderDetail detail=new OrderDetail();
			detail.setGid(cds.get(i).getGid());
			detail.setOid(oid);
			detail.setNum(cds.get(i).getNum());
			detail.setMoney(cds.get(i).getMoney());
			dao.insertDetail(detail);
			sum+=cds.get(i).getMoney();
		}
		Order order=new Order();
		order.setId(oid);
		order.setUaid(uaid);
		order.setUid(uid);
		order.setMoney(sum);
		dao.insert(order);
		cartDao.deleteDetailByCid(cds.get(0).getCid());
		cartDao.updateEmpty(cds.get(0).getCid());
		return true;
	}
	//更改订单状态
	@Override
	public boolean update(String oid, int flag) {
		// TODO Auto-generated method stub
		return dao.update(oid, flag) > 0;
	}

//	@Override
//	public boolean update(String oid) {
//		return dao.update(oid,4) > 0;
//	}

	//查询用户的所有订单
	@Override
	public List<Order> queryByUid(int uid) {
		// TODO Auto-generated method stub
		return dao.queryByUid(uid);
	}
	//查询订单详情
	@Override
	public ViewOrder queryOrderDetailById(String oid) {
		// TODO Auto-generated method stub
		ViewOrder vo=dao.queryOrder(oid);
		vo.setList(dao.queryDetailList(oid));
		return vo;
	}
	//查询所有订单
	@Override
	public List<Order> queryAll() {
		// TODO Auto-generated method stub
		return dao.queryAll();
	}
	//直接下单
	@Override
	public boolean insertDirect(int uid, String oid, int uaid, CartDetail cd) {
		Order order=new Order();
		order.setId(oid);
		order.setUaid(uaid);
		order.setUid(uid);
		order.setMoney(cd.getMoney());
		dao.insert(order);
		OrderDetail detail=new OrderDetail();
		detail.setGid(cd.getGid());
		detail.setOid(oid);
		detail.setNum(cd.getNum());
		detail.setMoney(cd.getMoney());
		dao.insertDetail(detail);
		return true;		
	}
	@Override
	public List<Order> selectByNameAndFlag(String username, Integer flag) {
		if(username != null && "".equals(username)) {
			username = null;
		}
		if(flag != null && 0 == flag) {
			flag = null;
		}
		
		//根据用户姓名和订单的支付状态查询订单（admin）
		return dao.selectByNameAndFlag(username, flag);
	}
	@Override
	public int deleteById(int id) {
		// 删除订单（admin）
		return dao.deleteById(id);
	}
}
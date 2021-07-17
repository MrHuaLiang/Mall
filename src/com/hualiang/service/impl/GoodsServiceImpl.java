/** 
* @author Feri 
*/  
package com.hualiang.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hualiang.dao.GoodsDao;
import com.hualiang.domain.Goods;
import com.hualiang.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private GoodsDao dao;
	@Override
	public boolean save(Goods goods) {
		// TODO Auto-generated method stub
		goods.setPrice(goods.getPrice());
		return dao.save(goods)>0;
	}
	@Override
	public List<Goods> queryAll() {
		// TODO Auto-generated method stub
		return dao.queryAll();
	}
	@Override
	public Goods querySingle(int id) {
		// TODO Auto-generated method stub
		return dao.querySingle(id);
	}
	@Override
	public List<Goods> queryByType(String type) {
		// TODO Auto-generated method stub
		return dao.queryByType(type);
	}
	@Override
	public List<List<Goods>> queryIndex() {
		// TODO Auto-generated method stub
		List<List<Goods>> list=new ArrayList<>();
		list.add(dao.queryIndex("酒水饮料"));
		list.add(dao.queryIndex("饼干糕点"));
		list.add(dao.queryIndex("休闲零食"));
		return list;
	}
	@Override
	public List<Goods> queryByName(String name) {
		//模糊查询
		return dao.queryName(name);
	}
	@Override
	public List<Goods> queryNameAndPub(String name, String pubdate) {
		if(name != null && "".equals(name)) {
			name = null;
		}
		if(pubdate != null && "".equals(pubdate)) {
			pubdate = null;
		}
		// 根据商品名称和上架时间查询商品（admin）
		return dao.queryNameAndPub(name, pubdate);
	}
	@Override
	public int deleteById(int id) {
		// 删除商品
		return dao.deleteById(id);
	}
}
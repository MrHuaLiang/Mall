/**
* @author Feri 
*/
package com.hualiang.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hualiang.dao.GoodsTypeDao;
import com.hualiang.domain.GoodsType;
import com.hualiang.service.GoodsTypeService;
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
	@Autowired
	private GoodsTypeDao dao;
	@Override
	public boolean save(GoodsType gt) {
		// TODO Auto-generated method stub
		return dao.save(gt)>0;
	}
	@Override
	public List<GoodsType> queryByLevel() {
		// TODO Auto-generated method stub
		return dao.queryByLevel();
	}
	@Override
	public List<GoodsType> queryAll() {
		// TODO Auto-generated method stub
		return dao.queryAll();
	}
	@Override
	public List<GoodsType> queryNameAndFlag(String name,int flag) {
		// 根据商品等级和商品名称查询商品（admin）
		return dao.queryNameAndFlag(name,flag);
	}
	//根据id删除商品类型
	@Override
	public int deleteType(int tid) {
		// TODO Auto-generated method stub
		return dao.deleteType(tid);
	}
}
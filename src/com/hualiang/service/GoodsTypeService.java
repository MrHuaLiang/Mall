package com.hualiang.service;
import java.util.List;

import com.hualiang.domain.GoodsType;
public interface GoodsTypeService {
	//新增
	boolean save(GoodsType gt);
	//查询一级类型
	List<GoodsType> queryByLevel();
	//查询全部
	List<GoodsType> queryAll();
	//删除
	int deleteType(int tid);
	//根据商品等级和商品名称查询类型（admin）
	List<GoodsType> queryNameAndFlag(String name,int flag);
}
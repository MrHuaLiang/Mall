/**
* @author Feri
*/ 
package com.hualiang.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.hualiang.domain.GoodsType;
public interface GoodsTypeDao {
	//新增
	@Insert("insert into t_goodstype(name,level,parentName,flag) values(#{name},#{level},#{parentName},1)")
	int save(GoodsType gt);
	//查询一级类型
	@Select("select * from t_goodstype where level=1")
	@ResultType(GoodsType.class)
	List<GoodsType> queryByLevel();
	//查询全部
	@Select("select * from t_goodstype")
	@ResultType(GoodsType.class)
	List<GoodsType> queryAll();
	//根据商品等级和商品名称查询商品（admin）
	@Select("select * from t_goodstype where level=#{flag} and name=#{name}")
	@ResultType(GoodsType.class)
	public List<GoodsType> queryNameAndFlag(@Param("name")String name, @Param("flag")int flag);
	//根据id删除商品类型
	@Delete("delete from  t_goodstype where id=#{id}")
	public int deleteType(@Param("id")int tid);
}
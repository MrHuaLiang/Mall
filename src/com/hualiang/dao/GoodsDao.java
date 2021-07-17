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
import com.hualiang.domain.Goods;
public interface GoodsDao {
	//新增
	@Insert("insert into t_goods(name,price,pubdate,typeName,intro,picture,flag,star) values(#{name},#{price},#{pubdate},#{typeName},#{intro},#{picture},1,#{star})")
	public int save(Goods goods);
	//查询
	@Select("select * from t_goods")
	@ResultType(Goods.class)
	public List<Goods> queryAll();
	//查询单个
	@Select("select * from t_goods where id=#{id}")
	@ResultType(Goods.class)
	public Goods querySingle(int id);
	//根据商品类型查询
	@Select("select * from t_goods where flag=1 and typename=#{type}")
	@ResultType(Goods.class)
	public List<Goods> queryByType(String type);
	//查询主页的商品信息
	@Select("select * from t_goods where typename=#{type} order by star desc limit 5")
	@ResultType(Goods.class)
//	public Goods queryIndex(String type);
	public List<Goods> queryIndex(String type);
	//根据商品名模糊查询
	@Select("select * from t_goods where name LIKE CONCAT(CONCAT('%', #{name}),'%');")
	@ResultType(Goods.class)
	public List<Goods> queryName(String name);
	//根据商品名称和上架时间查询商品（admin）
	@Select("<script>" +
            "select * from t_goods" +
            "<where>" +
                "<if test='name != null'>" +
                    "and name like concat('%', #{name}, '%')"+
                "</if>"+
                "<if test='pubdate != null'>" +
                    "and pubdate = #{pubdate}"+
                "</if>"+
            "</where>" +
            "</script>")
	@ResultType(Goods.class)
	public List<Goods> queryNameAndPub(@Param("name")String name, @Param("pubdate")String pubdate);
	//删除商品
	@Delete("delete from t_goods where id=#{id} ")
	int deleteById(int id);
}
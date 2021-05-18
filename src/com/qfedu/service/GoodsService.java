/** 
* @author Feri 
*/
package com.qfedu.service;
import java.util.Date;
import java.util.List;
import com.qfedu.domain.Goods;
public interface GoodsService {
	// 新增
	boolean save(Goods goods);
	// 查询
	List<Goods> queryAll();
	//模糊查询
	List<Goods> queryByName(String name);
	//根据商品类型查询
	List<Goods> queryByType(String type);
	// 查询单个
	Goods querySingle(int id);
	List<List<Goods>> queryIndex();
	//根据商品名称和上架时间查询商品（admin）
	List<Goods> queryNameAndPub(String name,String pubdate);
	//删除商品
	int deleteById(int id);
}
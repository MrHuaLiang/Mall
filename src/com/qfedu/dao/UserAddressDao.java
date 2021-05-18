/**  
* @Title: UserAddressDao.java  
* @Package com.qfedu.dao  
* @Description: TODO(用一句话描述该文件做什么)  
* @author Feri 
* @date 2018年5月31日  
* @version V1.0  
*/  
package com.qfedu.dao;
import java.util.List;

import org.apache.ibatis.annotations.*;
import com.qfedu.domain.UserAddress;
public interface UserAddressDao {
	//新增
	@Insert("insert into t_useraddress(name,phone,detail,uid,flag ) values(#{name},#{phone},#{detail},#{uid},1)")
	int insert(UserAddress ua);
	//修改
	@Update("update t_useraddress set name=#{name},phone=#{phone},detail=#{detail} where id=#{id}")
	int update(UserAddress ua);
	//修改
	@Update("update t_useraddress set flag=#{flag} where id=#{id}")
	int updateDea(@Param("id")int id,@Param("flag")int flag);
	//查询地址
	@Select("select * from t_useraddress where uid=#{uid} order by flag desc")
	@ResultType(UserAddress.class)
	List<UserAddress> queryByUid(int uid);

	@Delete("delete from t_useraddress where id=#{id}")
	int deleteById(int id);
}
/** 
* @author Feri
*/
package com.hualiang.service;
import java.util.List;
import com.hualiang.domain.UserAddress;
public interface UserAddressService {
	// 新增
	boolean insert(UserAddress ua);
	// 修改
	boolean update(UserAddress ua);
	// 查询地址
	List<UserAddress> queryByUid(int uid);
	// 删除地址
	int delete(int id);

	int updateDea(int id,int uid);
}
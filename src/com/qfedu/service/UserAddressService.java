/** 
* @author Feri
*/
package com.qfedu.service;
import java.util.List;
import com.qfedu.domain.UserAddress;
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
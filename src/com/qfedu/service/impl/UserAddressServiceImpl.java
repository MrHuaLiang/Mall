/**
* @author Feri
*/
package com.qfedu.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qfedu.dao.UserAddressDao;
import com.qfedu.domain.UserAddress;
import com.qfedu.service.UserAddressService;
@Service
public class UserAddressServiceImpl implements UserAddressService{
	@Autowired
	private UserAddressDao dao;
	//新增收货地址
	@Override
	public boolean insert(UserAddress ua) {
		// TODO Auto-generated method stub
		return dao.insert(ua)>0;
	}
	//查询收货地址
	@Override
	public List<UserAddress> queryByUid(int uid) {
		// TODO Auto-generated method stub
		return dao.queryByUid(uid);
	}
	//删除收货地址
	@Override
	public int delete(int id) {
		return dao.deleteById(id);
	}

	@Override
	public int updateDea(int id, int uid) {
		List<UserAddress> list = dao.queryByUid(uid);
		if(list != null && !list.isEmpty()){
			for(UserAddress userAddress : list){
				if( userAddress.getFlag() == 3){
					dao.updateDea(userAddress.getId(),1);
				}
			}
		}
		return dao.updateDea(id,3);
	}

	//修改地址
	@Override
	public boolean update(UserAddress ua) {
		// TODO Auto-generated method stub
		return dao.update(ua)>0;
	}

}

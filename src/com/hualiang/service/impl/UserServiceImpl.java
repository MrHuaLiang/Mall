/**  
* @author Feri 
*/
package com.hualiang.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hualiang.common.utils.Base64Utils;
import com.hualiang.common.utils.MD5Utils;
import com.hualiang.common.utils.StrUtils;
import com.hualiang.dao.UserDao;
import com.hualiang.domain.User;
import com.hualiang.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	@Override
	public boolean save(User user) {
		// TODO Auto-generated method stub
		//密码使用密文
		user.setPassword(MD5Utils.md5(user.getPassword()));
		return dao.insert(user)>0;
	}
	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return dao.select(name);
	}
	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	/* 检查用户名
	 * true 表示存在 false不存在*/
	@Override
	public boolean checkName(String name) {
		return dao.checkName(name)>0;
	}

	/* 检查邮箱
	 *  true 表示存在 false不存在*/
	@Override
	public boolean checkEmail(String email) {
		return dao.checkEmail(email)>0;
	}
	@Override
	public boolean activateUser(String email, String code) {
		// TODO Auto-generated method stub
		if(!StrUtils.empty(email,code)){
			return dao.updateAcode(Base64Utils.decode(email), Base64Utils.decode(code))>0;
		}else {
			return false;
		}
	}
	/* @Return true存在 false不存在
	 */
	@Override
	public boolean checkLogin(String name) {
		// TODO Auto-generated method stub
		return dao.select(name)!=null;
	}
	@Override
	public int deleteByid(int id) {
		return dao.deleteByid(id);
	}
	//搜索用户（根据username和gender）
	@Override
	public List<User> usersearch(String username, String gender) {
		return dao.selectsearch(username, gender);
	}
}
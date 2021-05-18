/**
* @author Feri
*/
package com.qfedu.service;
import java.util.List;
import com.qfedu.domain.User;
public interface UserService {
	//新增
	boolean save(User user);
	//根据名称 用户名或者邮箱
	User getUserByName(String name);
	//查询全部
	List<User> selectAll();
	//校验用户名是否存在  注册页面
	boolean checkName(String name);
	//校验邮箱是否存在    注册页面
	boolean checkEmail(String email);
	//检查登录用户是否存在  可能是邮箱 也可能是用户名
	boolean checkLogin(String name);
	//激活
	boolean activateUser(String email,String code);
	//删除用户
	int deleteByid(int id);
	//用户搜索
	List<User> usersearch(String username,String gender);
}
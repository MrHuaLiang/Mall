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
import org.apache.ibatis.annotations.Update;
import com.hualiang.domain.User;
public interface UserDao {
	//新增
	@Insert("insert into t_user(role ,username,password,email,gender,createtime ,flag ,activatecode) values(1,#{username},#{password},#{email},#{gender},now(),1,#{activatecode})")
	int insert(User user);
	//登录 用户名或密码都可以使用
	@Select("select * from t_user where flag=2 and (username=#{name} or email=#{email})")
	@ResultType(User.class)
	User select(String name);
	//检查用户名是否存在
	@Select("select count(*) from t_user where username=#{username}")
	int checkName(String name);
	//检查用户名是否存在
	@Select("select count(*) from t_user where email=#{email}")
	int checkEmail(String email);
	//查询全部
	@Select("select * from t_user where flag=2 and role=1")
	@ResultType(User.class)
	List<User> selectAll();
	//激活
	@Update("update t_user set flag=2 where email=#{email} and activatecode=#{code}")
	int updateAcode(@Param("email")String email,@Param("code")String code);
	//删除用户
	@Delete("delete from t_user where id=#{id} ")
	int deleteByid(int id);
	//根据用户名和性别搜索用户
	@Select("select * from t_user where username like CONCAT('%',#{username},'%')  and gender=#{gender}")
	@ResultType(User.class)
	List<User> selectsearch(@Param("username")String username , @Param("gender")String gender);
}
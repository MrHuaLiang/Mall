package com.hualiang.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hualiang.domain.User;
import com.hualiang.domain.UserAddress;
import com.hualiang.service.UserAddressService;

/**   
* Copyright: Copyright (c) 2018
* @ClassName: UserAddressController.java
* @Description: 该类的功能描述
* @version: v1.0.0
* @author: feri
* @date: 2018年11月29日 下午8:47:36 
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年11月29日       feri           v1.0.0               修改原因
*/
@Controller
public class UserAddressController {
	@Autowired
	private UserAddressService service;
	
	@RequestMapping("userAddressAdd")
	public String add(UserAddress ua,HttpServletRequest req) {
		//获取登录信息
		ua.setUid(((User)req.getSession().getAttribute("user")).getId());
		//保存地址
		if(service.insert(ua)) {
			//保存成功 刷新地址列表
			req.setAttribute("addList", service.queryByUid(ua.getUid()));
		}
		return "self_info"; 
	}

	
	@RequestMapping("useraddressupdate")
	public String update(UserAddress ua,HttpServletRequest req) {
		//保存地址
		if(service.update(ua)) {
			//保存成功 刷新地址列表
			req.setAttribute("addList", service.queryByUid(ua.getUid()));
		}
		return "self_info"; 
	}

	@RequestMapping("userAddressDel")
	public String userAddressDel(int id,HttpServletRequest request){
		service.delete(id);
		return "redirect:userAddressShow";
	}
	
	@RequestMapping("userAddressShow")
	public String show(UserAddress ua,HttpServletRequest req) {
		req.setAttribute("addList", service.queryByUid(((User)req.getSession().getAttribute("user")).getId()));
		return "self_info";
	
	}

	@RequestMapping("userAddressDef")
	public String userAddressDef(int id,HttpServletRequest request){
		service.updateDea(id,((User)request.getSession().getAttribute("user")).getId());
		return "redirect:userAddressShow";
	}

}

package com.hualiang.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dsna.util.images.ValidateCode;

/**
 * Copyright: Copyright (c) 2018
 * 
 * @ClassName: CommonController.java
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: feri
 * @date: 2018年11月26日 下午4:48:03 Modification History: Date Author Version
 *        Description ---------------------------------------------------------*
 *        2018年11月26日 feri v1.0.0 修改原因
 */
@Controller
public class CodeController {
	// 验证码生成
	@RequestMapping("/createcode")
	public void createCode(HttpSession session, HttpServletResponse response) throws IOException {
		// 创建验证码对象 参数说明 1、宽度 2、高度 3、验证码的数量 4、干扰线
		ValidateCode vc = new ValidateCode(100, 30, 4, 40);
		// 获取验证码
		String code = vc.getCode();
		// 使用会话存储生成的验证码
		session.setAttribute("code", code);
		// System.out.println(code);
		// 响应到浏览器
		vc.write(response.getOutputStream());
	}

	// 校验验证码是否正确
	@RequestMapping("/checkcode")
	@ResponseBody
	public int checkCode(String code, HttpSession session) {
		// 获取生成的验证码
		String ccode = (String) session.getAttribute("code");
		if (ccode.compareToIgnoreCase(code) == 0) {
			return 1;
		} else {
			return 0;
		}
	}
}

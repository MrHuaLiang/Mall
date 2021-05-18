package com.qfedu.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qfedu.domain.Cart;
import com.qfedu.domain.GoodsType;
import com.qfedu.service.GoodsTypeService;
/**
 * Copyright: Copyright (c) 2018
 * 
 * @ClassName: GoodsTypeController.java
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: feri
 * @date: 2018年11月26日 上午11:43:59 Modification History: Date Author Version
 *        Description ---------------------------------------------------------*
 *        2018年11月26日 feri v1.0.0 修改原因
 */
@Controller
public class GoodsTypeController {
	@Autowired
	private GoodsTypeService service;
	// 跳转到新增页面
	@RequestMapping("goodstypeshowadd")
	public String showadd(HttpServletRequest request, Model model) {
		model.addAttribute("gtlist", service.queryByLevel());
		return "addGoodsType";
//		return "/admin/addGoodsType";
	}
	// 实现新增类型
	@RequestMapping("goodstypeadd")
	public String add(GoodsType goodsType, HttpServletRequest request, Model model) {
		if("1".equals(goodsType.getParentName())) {
			goodsType.setLevel(1);
			goodsType.setParentName(null);
		}else {
			goodsType.setLevel(2);
		}
		goodsType.setFlag(1);
		if (service.save(goodsType)) {
			return "redirect:getGoodsType";
		} else {
			model.addAttribute("msg", "服务器异常，请稍后再来");
			return "redirect:goodstypeshowadd";
		}
	}
	//实现删除商品类型
	@RequestMapping("deleteGoodsType")
	@ResponseBody
	public String deleteGoodsType(HttpServletRequest req,int count) {
		service.deleteType(count);
		return "success";
	}
	// 显示类型列表
	@RequestMapping("getGoodsType")
	public String show(HttpServletRequest request, Model model) {
		model.addAttribute("gtlist", service.queryAll());
		return "/admin/showGoodsType";
	}
	//goodstypejson
	// 显示类型
	@RequestMapping("goodstypejson")
	@ResponseBody
	public List<GoodsType> showjson() {
		return service.queryByLevel();
	}
	//根据商品等级和商品名称查询类型（admin）
	@RequestMapping("selectByNameAndFlag")
	public String queryNameAndFlag(String name,int flag, Model model) {
		model.addAttribute("gtlist", service.queryNameAndFlag(name, flag));
		return "/admin/showGoodsType";
		}
}
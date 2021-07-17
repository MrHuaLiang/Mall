package com.hualiang.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hualiang.domain.Cart;
import com.hualiang.domain.Goods;
import com.hualiang.service.CartService;
@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	//商品加入购物车
	@RequestMapping("addCart")
	public String add(int gid,double price,HttpSession session) {
		Goods goods = new Goods();
		goods.setId(gid);
		goods.setPrice(price);
		if(cartService.add(((Cart)session.getAttribute("cart")).getId(), goods, 1)) {
			//成功
			//跳转到购物车页面
			return "cartSuccess";
		}else {
			return "index";
		}
	}

	//新增购物车
	@RequestMapping("getCart")
	public String get(HttpServletRequest req) {
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		req.setAttribute("carts", cartService.queryCart(cart.getId()));
		//转发
		return "cart";
	}

	//购物车删除商品
	@RequestMapping("clearCart")
	public String clearCart(HttpServletRequest req,int gid) {
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		cartService.deleteDetail(cart.getId(),gid);
		//重定向
		return "redirect:getCart";
	}
	//新增购物车
	@RequestMapping("updateCartNum")
	@ResponseBody
	public int update(int gid,double price,int num,HttpServletRequest req) {
		//创建商品对象
		Goods gd=new Goods();
		gd.setId(gid);
		gd.setPrice(price);
		Cart cart=(Cart)req.getSession().getAttribute("cart");
		cartService.changeNum(cart.getId(), gd, num);
		return 1;
	}
}
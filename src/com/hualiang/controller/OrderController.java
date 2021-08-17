package com.hualiang.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.rabbitmq.client.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hualiang.common.utils.RandomUtils;
import com.hualiang.common.vo.ViewCart;
import com.hualiang.domain.Cart;
import com.hualiang.domain.CartDetail;
import com.hualiang.domain.Goods;
import com.hualiang.domain.User;
import com.hualiang.service.CartService;
import com.hualiang.service.OrderService;
import com.hualiang.service.UserAddressService;
import redis.clients.jedis.Jedis;
//增加了收货后的订单状态变化
@Controller

public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    private UserAddressService uaService;
    @Autowired
    private CartService cartService;
    @Autowired
    Jedis jedis;
//    @Autowired
//    Channel channel;

    // 下单
    @RequestMapping("addOrder")
    public String add(int t, Integer aid, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String key = format.format(date) + user.getEmail() + aid;
        boolean res = false;
        //地址不存在则跳转到用户信息页面(添加地址)
        if (aid == null) {
            return "self_info";
        }
//        String s = jedis.psetex(key, 1000, user.getEmail());
//        if(!"OK".equals(s)){
//            return "order";
//        }
        String oid = RandomUtils.createOrderId();
        if (t == 1) {// 直接下单
            res = service.insertDirect(user.getId(), oid, aid, (CartDetail) session.getAttribute("direct"));
        } else {// 购物车下单
            res = service.save(oid, user.getId(), aid);
        }
        if (res) {
            // 下单成功
            model.addAttribute("oid", oid);
            return "pay";
        } else {
            return "index";
        }
    }

    // 列表
    @RequestMapping("getAllOrder")
    public String all(Integer t, Integer aid, Model model) {
        model.addAttribute("orderList", service.queryAll());
        return "admin/showAllOrder";
    }

    //直接下单
    @RequestMapping("getDirectOrder")
    public String direct(Goods gs, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<ViewCart> cds = new ArrayList<>();
        ViewCart cd = new ViewCart();
        cd.setGid(gs.getId());
        cd.setMoney(gs.getPrice());
        cd.setPrice(gs.getPrice());
        cd.setNum(1);
        cd.setName(gs.getName());
        cds.add(cd);
        CartDetail detail = new CartDetail();
        detail.setGid(gs.getId());
        detail.setMoney(gs.getPrice());
        detail.setNum(1);
        session.setAttribute("direct", detail);
        model.addAttribute("cartList", cds);
        model.addAttribute("addList", uaService.queryByUid(user.getId()));
        model.addAttribute("type", 1);
        // 转发
        return "order";
    }

    // 查询用户的所有订单
    @RequestMapping("getOrderList")
    public String olist(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("orderList", service.queryByUid(user.getId()));
        return "orderList";
    }

    // 订单预览
    @RequestMapping("getOrderView")
    public String viewlist(HttpServletRequest request, Model model) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("type", 2);
        request.setAttribute("cartList", cartService.queryCart(cart.getId()));
        request.setAttribute("addList", uaService.queryByUid(user.getId()));
        return "order";
    }

    // 列表
    @RequestMapping("getOrderDetail")
    public String list(String oid, HttpSession session, Model model) {
        model.addAttribute("od", service.queryOrderDetailById(oid));
        return "orderDetail";
    }

    //根据用户姓名和订单的支付状态查询订单（admin）
    @RequestMapping("selectOrderByNameAndFlag")
    public String selectByNameAndFlag(String username, Integer status, Model model) {
        model.addAttribute("orderList", service.selectByNameAndFlag(username, status));
        return "admin/showAllOrder";
    }

    //删除订单（admin）
    @RequestMapping("deleteOrder")
    public String deleteById(int id, Model model) {
        model.addAttribute("od", service.deleteById(id));
        return "orderDetail";
    }

    //修改订单（admin）
    @RequestMapping("sendOrder")
    public String sendOrder(String oid, Model model) {
        service.update(oid, 3);
        return "redirect:getAllOrder";
    }

    @RequestMapping("changeStatus")
    public String changeStatus(String oid, Model model) {
        service.update(oid, 4);
        return "redirect:orderList";
    }

    //后续完善评价功能
    @RequestMapping("comment")
    public String comment(String oid) {
        return "comment";
    }
}
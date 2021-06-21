package com.qfedu.controller;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.qfedu.common.utils.FileUtils;
import com.qfedu.domain.Goods;
import com.qfedu.service.GoodsService;
import com.qfedu.service.GoodsTypeService;
@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsTypeService service;
	
	// 跳转到新增页面
	@RequestMapping("toAddGoods")
	public String showadd(HttpServletRequest request, Model model) {
		model.addAttribute("gtlist", service.queryByLevel());
		return "addGoods";
	}
	// 商品新增
	@RequestMapping("addGoods")
	public String save(MultipartFile file, Goods goods, HttpServletRequest request)
			throws IllegalStateException, IOException {
		//图片上传后是保存在服务器的，这里就是你在服务器上保存的路径
		File dir = FileUtils.createDir("C:\\Users\\11725\\Desktop\\chapter16\\web\\fmwimages");
//		File dir = FileUtils.createDir(request.getServletContext().getRealPath("/"));
		//目标文件
		File desFile = new File(dir, FileUtils.createFileName(file.getOriginalFilename()));
		file.transferTo(desFile);
		goods.setPicture(dir.getName() + "/" + desFile.getName());
		goods.setFlag(1);//上架
		if (goodsService.save(goods)) {
			return "addGoods";
		} else {
			request.setAttribute("msg", "添加失败，重新再来");
			return "addGoods";
		}
	}
	// 查看商品详情
	@RequestMapping("getGoodsById")
	public String goodsbyid(int id, Model model) {
		model.addAttribute("goods", goodsService.querySingle(id));
		return "goodsDetail";
	}
	// 查看商品详情
	@RequestMapping("getGoodsIndex")
	@ResponseBody
	public List<List<Goods>> goodsindex(HttpServletResponse response) throws IOException {
		return goodsService.queryIndex();
	}
	// 查看商品列表
	@RequestMapping("getGoodsListByTn")
	public String goodsbytn(String tn, Model model) {
		model.addAttribute("glist", goodsService.queryByType(tn));
		return "goodsList";
	}
	// 查看商品列表
	@RequestMapping("getGoodsList")
	public String goodslist(Model model) {
		model.addAttribute("goodsList", goodsService.queryAll());
		return "admin/showGoods";
	}
	// 模糊查询商品列表
	@RequestMapping("selectByName")
	public String selectByName(String name, Model model) {
		model.addAttribute("glist", goodsService.queryByName(name));
		return "goodsList";
		}
	// 根据商品名称和上架时间查询商品（admin）
	@RequestMapping("selectByNameAndPub")
	public String queryNameAndPub(String name,String pubdate, Model model) {
		List<Goods> goods = goodsService.queryNameAndPub(name,pubdate);
		model.addAttribute("goodsList",goods );
		return "admin/showGoods";
		}
	//删除商品
	@RequestMapping("goodsDeleteById")
	public String goodsDeleteById(Integer id ,Model model) {
		goodsService.deleteById(id);
		return "redirect:getGoodsList";
	}
}

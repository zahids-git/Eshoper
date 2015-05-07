package controller;

import java.util.List;

import interfaces.PageMapping;
import interfaces.PageTitles;
import model.ModelCategory;
import model.ModelProduct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import beanpackage.CategoryBean;
import beanpackage.ProductInfo;

@Controller
public class HomePage {
	
	@RequestMapping(value=PageMapping.HOME)
	public ModelAndView showHomePage(){
		ModelAndView view = new ModelAndView("home");
		String pageName = PageTitles.HOME_PAGE;
		view.addObject("pageName", pageName);
		
		String sql = "SELECT `pro`.* FROM `productlist` as `pro` ,`package` as `pack` WHERE `pro`.`pid` = `pack`.`pid` AND `pro`.`available` = 1 GROUP BY `pack`.`pid` LIMIT 3";
		List<ProductInfo> slideProduct = new ModelProduct().getProductBySql(sql);
		view.addObject("slideProduct", slideProduct);
		
		sql = "SELECT `pro`.* FROM `productlist` as `pro` ,`package` as `pack` WHERE `pro`.`pid` = `pack`.`pid` AND `pro`.`is_feature` = 1 AND `pro`.`available` = 1 GROUP BY `pack`.`pid` LIMIT 5";
		List<ProductInfo> productInfo = new ModelProduct().getProductBySql(sql);
		view.addObject("featureProduct", productInfo);
		
		List<CategoryBean> categoryList = new ModelCategory().getAllCategoryInfo();
		view.addObject("catMain", categoryList);
		view.addObject("catSub", categoryList);
		
		return view;
	}
	
	
	
}
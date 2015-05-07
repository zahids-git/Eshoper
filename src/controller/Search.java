package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import interfaces.PageTitles;
import model.ModelCategory;
import model.ModelProduct;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import beanpackage.CategoryBean;
import beanpackage.ProductInfo;
import beanpackage.beanpackage.SearchBean;

@Controller
public class Search {
	
	@RequestMapping(value="/search")
	public ModelAndView showResult(HttpServletRequest request){
		ModelAndView view = new ModelAndView("search");
		view.addObject("title", PageTitles.SEARCH_RESULT);
		List<CategoryBean> catForView = new ModelCategory().getAllCategoryInfo();
		view.addObject("parentCat", catForView);
		view.addObject("childCat", catForView);
		
		String pname ="",price="",category="",total="";
		
		if(request.getParameterValues("catId")!=null){
			ArrayList<Integer> catList = new ArrayList<Integer>();
			String []arrCid = request.getParameterValues("catId");
			for(String singleId : arrCid ){
				catList.add(Integer.parseInt(singleId));
			}
			String tempCat = catList.toString();
			String newCat = "("+tempCat.substring(1,tempCat.length()-1)+")";
			if(!newCat.equals("(0)")) category = " AND `pro`.`cid` in "+newCat+" ";
		}
		
		
		if(request.getParameter("productName")!=null){
			pname = "AND LOWER(`pro`.`pname`) like LOWER('%"+ request.getParameter("productName")+"%') ";
		}
		
		if(request.getParameter("priceRange")!=null){
			String getPrice = request.getParameter("priceRange").toString();
			String []split = getPrice.split(",");
			price = " AND `pack`.`total_price` BETWEEN "+split[0]+" AND "+split[1];
		}
		
		if(request.getParameter("numberOfProduct")!=null){
			String numberOfPro = request.getParameter("numberOfProduct");
			if(!numberOfPro.equals("")){
				pname = " AND `pack`.`total_number` BETWEEN 0 and "+numberOfPro+"";
			}
			
		}
		
		String sql = ("SELECT pro.pname as pname,pro.pid as pid,pack.total_price as total_price,pack.pack_id as pack_id FROM `productlist` as `pro` , `package` as `pack` WHERE `pro`.`available` = 1 AND `pro`.`pid` = `pack`.`pid` "+pname+category+total+price);
		List<SearchBean> productInfo = new ModelProduct().getListSearch(sql);
		view.addObject("result", productInfo);
		Log.showConsole(sql);
		/*if(view != null){
			Log.showConsole(productInfo.size()+"");
		}*/
		
		return view;
	}
	
	
	
	
}

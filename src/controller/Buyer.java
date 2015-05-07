package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import interfaces.PageMapping;
import interfaces.SessionNames;
import model.ModelOrderlist;
import model.ModelProduct;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.json.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import beanpackage.OrderInfo;
import beanpackage.ProductInfo;
import beanpackage.beanpackage.UserBoughtProduct;

@Controller
@RequestMapping(value=PageMapping.DASHBOARD_INDEX)
public class Buyer {
	
	public ModelAndView checkUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute(SessionNames.USER_TYPE) == null){
			ModelAndView view = new ModelAndView("redirect:"+PageMapping.USER_LOGIN);
			return view;
		}
		else return null;
	}
	
	@RequestMapping(value=PageMapping.BUYER_SHOW_BUYINGPRODUCT)
	public ModelAndView showAllBuyingProduct(HttpServletRequest request) throws JSONException {
		ModelAndView check = checkUserSession(request);
		if(check != null) return check;	
		ModelAndView view = new ModelAndView("buyer/showAll");
		
		int uid = Integer.parseInt(request.getSession().getAttribute(SessionNames.USER_ID).toString());
		String sql = "SELECT * FROM `orderlist` WHERE `uid` = "+uid;
		List<OrderInfo> orderInfo = new ModelOrderlist().getOrderInfo(sql);
		
		ArrayList<UserBoughtProduct> boughtProducts = new ArrayList<UserBoughtProduct>();
		if(orderInfo != null){
			for(int p=0;p<orderInfo.size();p++){
				UserBoughtProduct product = new UserBoughtProduct();
				JSONArray object = new JSONArray(orderInfo.get(p).getList().toString());
				String productList = new String();
				for(int x=0;x<object.length();x++){
					JSONObject jsonObject = object.getJSONObject(x);

					List<ProductInfo> productInfo = new ModelProduct().getProductBySql("SELECT * FROM `productlist` as pro,`package` as pack where pro.pid = pack.pid AND pack.`pack_id` = "+jsonObject.getInt("pack_id"));
					Log.showConsole(jsonObject.getInt("pack_id")+"");
					if(productInfo!=null){
						for (ProductInfo pro: productInfo) {
							productList += pro.getPname()+" ( "+jsonObject.getInt("total_ammount")+" piece )"+"<br/>";
						}
					}
				}
				product.setOrderId(orderInfo.get(p).getOid()+"");
				product.setDelivered(orderInfo.get(p).getDelivery()+"");
				product.setProductDesc(productList);
				product.setOrderDate(orderInfo.get(p).getDate());
				product.setNumberOfProduct(object.length()+"");
				product.setTotalPrice(orderInfo.get(p).getTotal()+"");
				boughtProducts.add(product);
			}
			view.addObject("order", boughtProducts);
		}
		else{
			// No product
		}
		
		view.addObject("showProduct", "activeMenuItem");
		return view;
	}
	
}

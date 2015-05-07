package controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import interfaces.PageMapping;
import model.ModelCategory;
import model.ModelPackage;
import model.ModelProduct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import beanpackage.CategoryBean;
import beanpackage.PackageInfo;
import beanpackage.ProductInfo;

@Controller
public class ProductDetails {
	
	@RequestMapping(value=PageMapping.PRODUCT_DETAILS,method=RequestMethod.GET)
	public ModelAndView showProductDetails(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("productDetails");
		if(request.getParameter("pid")!=null){
			int pid = Integer.parseInt(request.getParameter("pid").toString());
			List<ProductInfo> productInfo = new ModelProduct().getProductInfoCustomWhere("`pid` = "+pid);
			if(productInfo != null){
				view.addObject("title", productInfo.get(0).getPname());
				view.addObject("product", productInfo);
				if(productInfo.get(0).getAvailable() == 1) view.addObject("available", "<label class='label label-success'>Available</label>");
				else if(productInfo.get(0).getAvailable() == 0) view.addObject("available", "<label class='label label-danger'>Not Available</label>");
				if(productInfo.get(0).getIs_featured() == 1) view.addObject("featured", "<label class='label label-success'>Featured</label>");
				else if(productInfo.get(0).getIs_featured() == 0) view.addObject("featured", "<label class='label label-warning'>Not Featured</label>");
				List<CategoryBean> categoryBean = new ModelCategory().getCategoryInfoWhere("`cid` = "+productInfo.get(0).getCid());
				view.addObject("cname", categoryBean.get(0).getCname());
				List<PackageInfo> packageInfo = new ModelPackage().getAllPackageInfo("`pid` = "+pid);
				if(packageInfo!=null){
					view.addObject("packageInfo", packageInfo);
					List<ProductInfo> suggestProduct = new ModelProduct().getProductInfoCustomWhere("`uid` = "+productInfo.get(0).getUid()+" LIMIT 5");
					view.addObject("suggestProduct", suggestProduct);
				}
				else{
					Log.showConsole("Package Null");
				}
			}
			else{
				Log.showConsole("Product Null");
			}
		}
		else{
			Log.showConsole("Pid Null");
		}
		return view;
	}

	@RequestMapping(value=PageMapping.PRODUCT_DETAILS,method=RequestMethod.POST)
	public ModelAndView submitAddCart(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView("redirect:"+PageMapping.CONFIRM_CART);
		if(request.getParameterValues("packId")!=null && request.getParameterValues("numberOfItem")!=null){
			String []packId = request.getParameterValues("packId");
			String []ammountTotal = request.getParameterValues("numberOfItem");
			
			// Number Of product
			int numberOfProduct = 0;
			Cookie []totalCookie = request.getCookies();
			for(int i = 0; i < totalCookie.length; i++) { 
	            Cookie cookie1 = totalCookie[i];
	            if (cookie1.getName().equals("totalProduct")) {
	            	numberOfProduct = Integer.parseInt(cookie1.getValue().toString());
	            	break;
	            }
	        } 
			
			
			// Insert into the cookie
			for(int x=0;x<packId.length;x++){
				if(!ammountTotal[x].equals("") && !ammountTotal[x].equals("0")){
					numberOfProduct++;
					Cookie cookie = new Cookie("cart_product"+numberOfProduct , packId[x]+","+ammountTotal[x]);
					response.addCookie(cookie);
				}
				response.addCookie(new Cookie("totalProduct", numberOfProduct+""));
			}
		}
		
		return view;
	}
	
	

}

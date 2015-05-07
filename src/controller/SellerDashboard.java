package controller;

import java.util.List;

import interfaces.PageMapping;
import interfaces.SessionNames;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.ModelCategory;
import model.ModelPackage;
import model.ModelProduct;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import beanpackage.CategoryBean;
import beanpackage.PackageInfo;
import beanpackage.ProductInfo;

@Controller
@RequestMapping(value=PageMapping.DASHBOARD_INDEX)
public class SellerDashboard {
	
	public ModelAndView checkUserSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute(SessionNames.USER_TYPE) == null){
			ModelAndView view = new ModelAndView("redirect:"+PageMapping.USER_LOGIN);
			return view;
		}
		else return null;
	}
	
	private List<CategoryBean> getCategory() {		
		List<CategoryBean> categoryList = new ModelCategory().getAllCategoryInfo();
		return categoryList;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView showIndexPage(HttpServletRequest request) {
		ModelAndView view = null;
		HttpSession session = request.getSession(false);
		if(session.getAttribute(SessionNames.USER_TYPE) != null){
			String type = session.getAttribute(SessionNames.USER_TYPE).toString();
			if(type.equals("buyer")) view = new ModelAndView("redirect:"+PageMapping.DASHBOARD_INDEX+PageMapping.BUYER_SHOW_BUYINGPRODUCT);
			else if(type.equals("seller")) view = new ModelAndView("redirect:"+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_ADD_PRODUCT);
			else view = new ModelAndView("redirect:"+PageMapping.USER_LOGIN);
		}
		else view = new ModelAndView("redirect:"+PageMapping.USER_LOGIN);
		return view;
	}
	
	@RequestMapping(value=PageMapping.SELLER_ADD_PRODUCT,method =RequestMethod.GET)
	public ModelAndView showAddProduct(HttpServletRequest request){
		ModelAndView check = checkUserSession(request);
		if(check != null) return check;
		ModelAndView view = new ModelAndView("/seller/addProduct");
		List<CategoryBean> catList = getCategory();
		view.addObject("title", "Add New Product");
		view.addObject("addProduct","activeMenuItem");
		view.addObject("parentCat", catList);
		view.addObject("childCat", catList);
		return view;
	}
	
	@RequestMapping(value=PageMapping.SELLER_UPDATE_PRODUCT,method =RequestMethod.GET)
	public ModelAndView showUpdateProduct(HttpServletRequest request){
		ModelAndView check = checkUserSession(request);
		if(check != null) return check;
		if(request.getParameter("pid") != null){
			ModelAndView view = new ModelAndView("/seller/update");
			int pid = Integer.parseInt(request.getParameter("pid").toString());
			List<ProductInfo> product = new ModelProduct().getProductInfoCustomWhere("`pid` = "+pid+"");
			view.addObject("product", product);
			List<CategoryBean> catList = getCategory();
			view.addObject("parentCat", catList);
			view.addObject("childCat", catList);
			view.addObject("title", "Update Product");
			view.addObject("updateProduct","activeMenuItem");
			return view;
		}
		else{
			ModelAndView view = new ModelAndView("/seller/updateProductList");
			int uid = Integer.parseInt(request.getSession().getAttribute(SessionNames.USER_ID).toString());
			String sql = "SELECT p.* , c.cname FROM `productlist` as  p , `category` as c where p.`uid` = "+uid+" AND p.`cid` = c.`cid`";
			List<ProductInfo> list = new ModelProduct().getProductBySql(sql);
			view.addObject("product", list);
			view.addObject("title", "Update Product");
			view.addObject("updateProduct","activeMenuItem");
			return view;
		}
	}
	
	@RequestMapping(value=PageMapping.SELLER_ADD_PACKAGE,method =RequestMethod.GET)
	public ModelAndView showAddPackage(HttpServletRequest request){
		ModelAndView check = checkUserSession(request);
		int pid = 0;
		if(check != null) return check;
		ModelAndView view = new ModelAndView("/seller/package");
		int uid = Integer.parseInt(request.getSession().getAttribute(SessionNames.USER_ID).toString());
		List<ProductInfo> list = new ModelProduct().getProductInfoCustomWhere("`uid` = "+uid+" ORDER BY `pid` DESC");
		view.addObject("Allproduct", list);
		
		if(request.getParameter("pid") != null){
			pid = Integer.parseInt(request.getParameter("pid"));
		}
		else{
			if(list != null && list.size() > 0){
				pid = list.get(0).getPid();
			}
			else pid = 0;
		}
		view.addObject("pid", pid);
		
		List<PackageInfo> listPackage = new ModelPackage().getAllPackageInfo("`pid` = "+pid);
		view.addObject("packageForProduct", listPackage);
		int size = listPackage.size();
		view.addObject("totalPackage", size);
		
		view.addObject("title", "Update Product");
		view.addObject("makePackage","activeMenuItem");
		return view;
	}
	
	@RequestMapping(value=PageMapping.SELLER_REPORT,method =RequestMethod.GET)
	public ModelAndView showReport(HttpServletRequest request){
		ModelAndView check = checkUserSession(request);
		if(check != null) return check;
		ModelAndView view = new ModelAndView("/seller/report");
		view.addObject("title", "Report Product");
		view.addObject("report","activeMenuItem");
		return view;
	}
	
	@RequestMapping(value=PageMapping.DASHBOARD_LOGOUT)
	public ModelAndView sellerLogOut(HttpServletRequest request){
		ModelAndView check = checkUserSession(request);
		if(check != null) return check;
		HttpSession session = request.getSession(false) ;
		session.invalidate();
		return new ModelAndView("redirect:"+PageMapping.USER_LOGIN);
	}
	
	
	
	@RequestMapping(value=PageMapping.SELLER_ADD_PRODUCT,method=RequestMethod.POST)
	public ModelAndView submitAddProduct(@ModelAttribute ProductInfo info, BindingResult result, @RequestParam("pic") MultipartFile image, HttpServletRequest request){
		ModelAndView view = new ModelAndView("/seller/addProduct"); ;
		int uid = Integer.parseInt(request.getSession().getAttribute(SessionNames.USER_ID).toString());
		info.setUid(uid);
		List<CategoryBean> catList = getCategory();
		if(result.hasErrors() || !new ModelProduct().insertProductInfo(info)){
			view.addObject("status", "label-warning");
			view.addObject("statusMsg", "Something not going well");
		}
		else{
			List<ProductInfo> list = new ModelProduct().getProductInfoCustomWhere("`pname` = '"+info.getPname()+"' AND `uid` = "+info.getUid()+" ORDER BY `pid` DESC LIMIT 1");
			if(new UploadImage().uploadProductImages(image, list.get(0).getPid()+"")){
				view.addObject("status", "label-success");
				view.addObject("statusMsg", "Successfully added");
			}
		}
		view.addObject("title", "Add New Product");
		view.addObject("addProduct","activeMenuItem");
		view.addObject("parentCat", catList);
		view.addObject("childCat", catList);
		return view;
	}
	
	@RequestMapping(value=PageMapping.SELLER_UPDATE_PRODUCT,method=RequestMethod.POST)
	public ModelAndView submitUpdateProduct(@ModelAttribute ProductInfo info, BindingResult result ,HttpServletRequest request){
		new SellerDashboard().checkUserSession(request);	
		int uid = Integer.parseInt(request.getSession().getAttribute(SessionNames.USER_ID).toString());
		info.setUid(uid);
		if(result.hasErrors() || !new ModelProduct().updateProductInfo(info)){
			Log.showConsole("Error");
		}
		else{
			Log.showConsole("Success");
		}
		return new ModelAndView(new RedirectView(PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_UPDATE_PRODUCT));
	}
	
	@RequestMapping(value=PageMapping.SELLER_ADD_PACKAGE,method=RequestMethod.POST)
	public ModelAndView submitAddPackage(HttpServletRequest request){
		new SellerDashboard().checkUserSession(request);
		ModelAndView view = new ModelAndView("/seller/package");
		int uid = Integer.parseInt(request.getSession().getAttribute(SessionNames.USER_ID).toString());
		List<ProductInfo> list = new ModelProduct().getProductInfoCustomWhere("`uid` = "+uid+" ORDER BY pid DESC");
		
		String pid = request.getParameter("pid");
		String []total = request.getParameterValues("total_number[]");
		String []price = request.getParameterValues("total_price[]");
		String []time = request.getParameterValues("day[]");
		String []delivaryTimeType = request.getParameterValues("dayType[]");
		
		int totalPackage = total.length;
		if(price.length == totalPackage && price.length == totalPackage && time.length == totalPackage && delivaryTimeType.length == totalPackage){
			for(int x=0;x<totalPackage;x++){
				PackageInfo packageInfo = new PackageInfo();
				if(!(total[x].isEmpty()||price[x].isEmpty()||time[x].isEmpty()||delivaryTimeType[x].isEmpty())){
					packageInfo.setPid(Integer.parseInt(pid));
					packageInfo.setTotal_number(Double.parseDouble(total[x]));
					packageInfo.setTotal_price(Double.parseDouble(price[x]));
					packageInfo.setPdate(time[x]);
					packageInfo.setDayType(delivaryTimeType[x]);
					if(new ModelPackage().insertPackageInfo(packageInfo)){
						view.addObject("title", "Make Package");
						view.addObject("makePackage","activeMenuItem");
						Log.showConsole("Package Inserted");// inserted
					}
					else{
						Log.showConsole("Package Not Inserted");// Not inserted
					}
				}
				else{
					Log.showConsole("Some Fields are empty");
				}
			}
		}
		view.addObject("title", "Make Package");
		view.addObject("makePackage","activeMenuItem");
		view.addObject("Allproduct", list);		
		return view;
	}
	
	@RequestMapping(value=PageMapping.SELLER_UPDATE_PACKAGE,method=RequestMethod.POST)
	public ModelAndView updatePackage(HttpServletRequest request){
		new SellerDashboard().checkUserSession(request);
		ModelAndView view = new ModelAndView("/seller/package");
		int uid = Integer.parseInt(request.getSession().getAttribute(SessionNames.USER_ID).toString());
		List<ProductInfo> list = new ModelProduct().getProductInfoCustomWhere("`uid` = "+uid+" ORDER BY pid DESC");
		
		String pid = request.getParameter("pid");
		String []total = request.getParameterValues("total_number[]");
		String []packId = request.getParameterValues("packId[]");
		String []price = request.getParameterValues("total_price[]");
		String []time = request.getParameterValues("day[]");
		String []delivaryTimeType = request.getParameterValues("dayType[]");
		
		int totalPackage = total.length;
		if(price.length == totalPackage && price.length == totalPackage && time.length == totalPackage && delivaryTimeType.length == totalPackage){
			for(int x=0;x<totalPackage;x++){
				PackageInfo packageInfo = new PackageInfo();
				if(!(total[x].isEmpty()||price[x].isEmpty()||time[x].isEmpty()||delivaryTimeType[x].isEmpty())){
					packageInfo.setPid(Integer.parseInt(pid));
					packageInfo.setPackId(Integer.parseInt(packId[x]));
					packageInfo.setTotal_number(Double.parseDouble(total[x]));
					packageInfo.setTotal_price(Double.parseDouble(price[x]));
					packageInfo.setPdate(time[x]);
					packageInfo.setDayType(delivaryTimeType[x]);
					if(new ModelPackage().updatePackageInfo(packageInfo)){
						view.addObject("title", "Make Package");
						view.addObject("makePackage","activeMenuItem");
						Log.showConsole("Package updated");// inserted
					}
					else{
						Log.showConsole("Package Not Inserted");// Not inserted
					}
				}
				else{
					Log.showConsole("Some Fields are empty");
				}
			}
		}
		view.addObject("title", "Make Package");
		view.addObject("makePackage","activeMenuItem");
		view.addObject("Allproduct", list);		
		return view;
	}
	
	@RequestMapping(value=PageMapping.SELLER_REPORT,method=RequestMethod.POST)
	public ModelAndView submitReport(HttpServletRequest request){
		new SellerDashboard().checkUserSession(request);
		ModelAndView view = new ModelAndView("/seller/report");
		view.addObject("title", "Report Product");
		view.addObject("report","activeMenuItem");
		return view;
	}
	
	@RequestMapping(value=PageMapping.SELLER_DELETE_PRODUCT, method=RequestMethod.POST)
	public ModelAndView deleteProduct(HttpServletRequest request) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		new ModelProduct().deleteProductInfo(pid);		
		return new ModelAndView(new RedirectView(PageMapping.BASE_URL+PageMapping.DASHBOARD_INDEX+PageMapping.SELLER_UPDATE_PRODUCT));
	}
	
}

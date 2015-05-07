package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.PageMapping;
import interfaces.SessionNames;
import model.ModelOrderlist;
import model.ModelProduct;
import net.sf.json.util.JSONBuilder;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import beanpackage.OrderInfo;
import beanpackage.beanpackage.Chart;

@Controller
public class Confirm {

	@RequestMapping(value=PageMapping.CONFIRM_CART,method=RequestMethod.GET)
	public ModelAndView showConfirmPage(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("confirm");
		
		// Number Of product
		int numberOfProduct = 0;
		Cookie []totalCookie = request.getCookies();
		if(totalCookie!=null){
		for(int i = 0; i < totalCookie.length; i++) { 
            Cookie cookie1 = totalCookie[i];
            Log.showConsole(cookie1.getName());
            if (cookie1.getName().equals("totalProduct")) {
            	numberOfProduct = Integer.parseInt(cookie1.getValue().toString());
            	break;
            }
        }
				
		if(numberOfProduct == 0 ) return new ModelAndView("confirm", "msg" , "Nothing is here");
		else{
			int x = 1;
			ArrayList<Chart> chartListArray = new ArrayList<Chart>();
			Double totalPrice = 0.0;
			for(int p=0;p<totalCookie.length;p++){
				Cookie cookie1 = totalCookie[p];
				String nextCookieName = "cart_product"+x;
	            if (cookie1.getName().equals(nextCookieName)) {
	            	String getCookie = cookie1.getValue().toString();
	            	String []split = getCookie.split(",");
	            	String sql = "SELECT pro.pid as pid,pro.pname as pname,pack.pack_id as pack_id,pack.total_price as total_price,pack.total_number as total_number FROM `productlist` AS PRO, `package` AS pack WHERE pro.pid = pack.pid AND pack.pack_id = "+split[0];
	            	List<Chart> chartList = new ModelProduct().getListChart(sql);
	            	Chart chart = new Chart();
	            	chart.setTotal_ammount(split[1]);
	            	chart.setTotal_price(Double.parseDouble(chartList.get(0).getTotal_price())*Integer.parseInt(split[1])+"");
	            	chart.setPack_id(chartList.get(0).getPack_id());
	            	chart.setPid(chartList.get(0).getPid());
	            	chart.setPname(chartList.get(0).getPname().toString());
	            	chartListArray.add(chart);
	            	totalPrice += Double.parseDouble(chart.getTotal_price());
	            	if(x==numberOfProduct) break;
		            x++;
		            p=0;
		            
	            }
			}
			view.addObject("chart", chartListArray);
			view.addObject("totalPrice", totalPrice);
			view.addObject("msg", "Confirm");
		}
		}
		else{
			view.addObject("msg" , "Nothing is here");
		}
		
		return view;
	}
	
	@RequestMapping(value=PageMapping.CONFIRM_CART,method=RequestMethod.POST)
	public ModelAndView submitConfirmPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView("confirm");
		HttpSession session = request.getSession(false);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		
		String jsonProduct = "";
		
		if(session.getAttribute(SessionNames.USER_ID)!=null && session.getAttribute(SessionNames.USER_TYPE)!=null && session.getAttribute(SessionNames.USER_TYPE).equals("buyer")){
			int uid = Integer.parseInt(request.getSession().getAttribute(SessionNames.USER_ID).toString());
			if(session.getAttribute(SessionNames.USER_TYPE).equals("buyer")){
				if(request.getParameter("pack_id")!=null && request.getParameter("ammount")!=null){
					String []packId = request.getParameterValues("pack_id");
					String []ammount = request.getParameterValues("ammount");
					jsonProduct = "[";
					for(int x=0;x<packId.length;x++){
						jsonProduct += " { \"pack_id\" : "+packId[x]+" , \"total_ammount\" : "+ammount[x]+" }";
						if(x != packId.length-1) jsonProduct+=",";
					}
					jsonProduct += "]";
					OrderInfo info = new OrderInfo();
					info.setUid(uid);
					info.setList(jsonProduct);
					info.setDate(dateFormat.format(cal.getTime()));
					info.setTotal(Double.parseDouble(request.getParameter("total")));
					boolean res = new ModelOrderlist().insertOrderlist(info);
				}
			}
		}
		else{
			return new ModelAndView("redirect:"+PageMapping.USER_LOGIN+"?url="+PageMapping.CONFIRM_CART);
		}
		
		
		
		
		// Number Of product
		int numberOfProduct = 0;
		Cookie []totalCookie = request.getCookies();
		for(int i = 0; i < totalCookie.length; i++) { 
            Cookie cookie1 = totalCookie[i];
            if (cookie1.getName().equals("totalProduct")) {
            	numberOfProduct = Integer.parseInt(cookie1.getValue().toString());
            	cookie1.setMaxAge(0);
	            cookie1.setValue("");
	            response.addCookie(cookie1);
            	break;
            }
        }
		
			
		if(numberOfProduct != 0 ){
			int x = 1;
			ArrayList<Chart> chartListArray = new ArrayList<Chart>();
			Double totalPrice = 0.0;
			for(int p=0;p<totalCookie.length;p++){
				Cookie cookie1 = totalCookie[p];
				String nextCookieName = "cart_product"+x;
	            if (cookie1.getName().equals(nextCookieName)) {
	            	cookie1.setMaxAge(0);
		            cookie1.setValue("");
		            response.addCookie(cookie1);
	            	if(x==numberOfProduct) break;
		            x++;
		            p=0;
	            }
			}
		}
		return view;
	}
	
}

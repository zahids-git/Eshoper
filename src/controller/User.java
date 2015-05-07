package controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import interfaces.PageMapping;
import interfaces.SessionNames;
import model.ModelUser;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import beanpackage.UserBean;

@Controller
public class User {
	
	@RequestMapping(value=PageMapping.USER_REGISTRATION , method= RequestMethod.GET)
	public ModelAndView showAccount(){
		ModelAndView user = new ModelAndView("registration" , "title", "Registration");
		return user;
	}
	
	@RequestMapping(value=PageMapping.USER_REGISTRATION , method=RequestMethod.POST)
	public ModelAndView createAccount(@ModelAttribute("userBean") UserBean userBean, BindingResult result , HttpServletRequest request){
		ModelAndView user = new ModelAndView("registration" , "title", "Registration");
		if(!result.hasErrors()){
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Calendar cal = Calendar.getInstance();
			ModelUser modelUser = new ModelUser();
			String uname = userBean.getuName();	
			String email = userBean.getuEmail();
			String pass = userBean.getuPass();
			String accType = userBean.getAccountType();
			String address = userBean.getAddress();
			userBean.setAccountDate(dateFormat.format(cal.getTime()));
			if(!(uname.isEmpty()||email.isEmpty()||pass.isEmpty()||accType.isEmpty())){
				if(modelUser.insertUserInfo(userBean)){
					UserBean userInfo = new ModelUser().getUserInfoWhere("`uemail` = '"+email+"' AND `upass` = '"+pass+"'");
					if(userInfo == null){
						user.addObject("errorReg", "Can not process your request");
					}
					else{
						HttpSession session = request.getSession(true);
						session.setAttribute(SessionNames.USER_NAME, userInfo.getuName());
						session.setAttribute(SessionNames.USER_ID, userInfo.getUid());
						session.setAttribute(SessionNames.USER_TYPE, userInfo.getAccountType());
						return new ModelAndView("redirect:"+PageMapping.DASHBOARD_INDEX);
					}
				}
				else user.addObject("errorReg", "Can not process your request");			
			}
			else user.addObject("errorReg", "Some fields are empty");
		}
		return user;
	}
	
	
	@RequestMapping(value=PageMapping.USER_LOGIN ,method=RequestMethod.GET)
	public ModelAndView showLoginPage(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		if(session != null){
			Log.showConsole("Session is not NULL");
		}
		ModelAndView view = new ModelAndView("login");
		view.addObject("title", "Login");
		view.addObject("url", request.getParameter("url"));
		return view;
	}
	
	@RequestMapping(value=PageMapping.USER_LOGIN,method=RequestMethod.POST)
	public ModelAndView submitLoginForm(@ModelAttribute("userBean") UserBean userBean , BindingResult result , HttpServletRequest request ){
		ModelAndView view = new ModelAndView("login", "title", "Login");
		String error = null;
		if(!result.hasErrors()){
			String email = userBean.getuEmail();
			String pass = userBean.getuPass();	
			if(!(email.isEmpty() || pass.isEmpty())){
				UserBean user = new ModelUser().getUserInfoWhere("`uemail` = '"+email+"' AND `upass` = '"+pass+"'");
				if(user == null){
					error = "Email and password not same";
				}
				else{
					HttpSession session = request.getSession(true);
					session.setAttribute(SessionNames.USER_NAME, user.getuName());
					session.setAttribute(SessionNames.USER_ID, user.getUid());
					session.setAttribute(SessionNames.USER_TYPE, user.getAccountType());
					Log.showConsole(request.getParameter("url"));
					if(!request.getParameter("url").equals("")) return new ModelAndView("redirect:"+request.getParameter("url"));
					else return new ModelAndView("redirect:"+PageMapping.DASHBOARD_INDEX);
				}
			}
			else{
				error = "Some field are empty";
			}
		}
		else{
			error = "Error to buinding";
		}
		view.addObject("errorLogin", error);		
		return view;
	}
	
}

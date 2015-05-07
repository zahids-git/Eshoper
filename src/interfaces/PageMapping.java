package interfaces;

public interface PageMapping {
	
	
	public static final String BASE_URL = "/shop";
	
	/*Main Area*/
	public static final String HOME = "/";
	public static final String USER_REGISTRATION = "/reg";
	public static final String USER_LOGIN = "/login";
	
	
	/*Product Details*/
	public static final String PRODUCT_DETAILS = "/details";
	
	
	/*Seller Area*/
	public static final String PRODUCT_INDEX = "/product";
	public static final String PACKAGE_INDEX = "/package";
	public static final String PRODUCT_IMAGE_UPLOAD = "/upload";
	public static final String SELLER_ADD_PRODUCT = "/addproduct";
	public static final String SELLER_ADD_PACKAGE = "/package";
	public static final String SELLER_UPDATE_PACKAGE = "/updatepackage";
	public static final String SELLER_UPDATE_PRODUCT = "/updateproduct";
	public static final String SELLER_DELETE_PRODUCT = "/deleteproduct";
	public static final String SELLER_REPORT = "/report";
	
	
	/*Buyer Area*/
	public static final String BUYER_SHOW_BUYINGPRODUCT = "/show";
	
	
	/* Dashboard Area */
	public static final String DASHBOARD_INDEX = "/dashboard";
	public static final String DASHBOARD_LOGOUT = "/logout";
	
	
	/*Confirm*/
	public static final String CONFIRM_CART = "/confirm";
	
	
	
}

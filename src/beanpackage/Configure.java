package beanpackage;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {
	
	public static final String APPLICATION_PATH = "C:\\Users\\Md.Zahidul\\workspace\\shop\\WebContent\\resources\\";

	@Bean(name="productBean")
	public ProductInfo getPackage(){
		return new ProductInfo();
	}
	
	@Bean(name="userBean")
	public UserBean getUserBean(){
		return new UserBean();
	}
	
	@Bean(name="packageBean")
	public PackageInfo getPackageBean(){
		return new PackageInfo();
	}
	
	
}

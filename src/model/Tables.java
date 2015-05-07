package model;

import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

public class Tables {

	JdbcTemplate template;
	public Tables(JdbcTemplate template) {
		this.template = template;
		createOrder();
		createProductList();
		createPackage();
		createCategory();
		createUser();
	}

	public boolean createUser(){
		try {
			String sql = "create table IF NOT EXISTS  User ( " +
					"uid INT (10) NOT NULL AUTO_INCREMENT, " +
					"uname VARCHAR (200), " +
					"uemail VARCHAR(200), " +
					"upass VARCHAR(200), " +
					"account_date VARCHAR(30), " +
					"account_type VARCHAR(10), " +
					"card_number INT(100), " +
					"holder_name VARCHAR(200), " +
					"address VARCHAR(200), " +
					"PRIMARY KEY(uid)" +
					");";
			template.execute(sql);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	public boolean createProductList(){
		try {
			String sql = "create table IF NOT EXISTS  ProductList ( " +
					"pid INT(10) NOT NULL AUTO_INCREMENT, " +
					"pname varchar(200), " +
					"cid INT(200), " +
					"uid INT(200), " +
					"available INT(1), " +
					"pic_id VARCHAR(30), " +
					"is_feature INT(1), " +
					"brand_name VARCHAR(200) DEFAULT '', " +
					"PRIMARY KEY(pid)" +
					");";
			template.execute(sql);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	public boolean createCategory(){
		try {
			String sql = "create table IF NOT EXISTS  Category ( " +
					"cid INT(10) NOT NULL AUTO_INCREMENT, " +
					"cname VARCHAR (200), " +
					"main_catid INT (10), " +
					"PRIMARY KEY(cid)" +
					");";
			template.execute(sql);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	public boolean createPackage(){
		try {
			String sql = "create table IF NOT EXISTS Package ( " +
					"pack_id INT (10) NOT NULL AUTO_INCREMENT, " +
					"pid INT(200), " +
					"total_number DOUBLE(200,2), " +
					"total_price DOUBLE (20,2), " +
					"pdate VARCHAR(50), " +
					"total_sale INT(30), " +
					"dayType VARCHAR(30)," +
					"PRIMARY KEY(pack_id)" +
					");";
			template.execute(sql);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	public boolean createOrder(){
		try {
			String sql = "create table IF NOT EXISTS OrderList ( " +
					"oid INT (10) NOT NULL AUTO_INCREMENT, " +
					"uid INT (10), " +
					"odate VARCHAR(50), " +
					"list VARCHAR(500), " +
					"delivery INT(1), " +
					"total DOUBLE(50,2)"+
					"PRIMARY KEY(oid)" +
					");";
			template.execute(sql);
			return true;
		}
		catch (Exception e){
			System.out.print(e.getMessage());
			return false;
		}
	}
}

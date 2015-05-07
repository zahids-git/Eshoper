package model;

import beanpackage.ProductInfo;
import beanpackage.beanpackage.Chart;
import beanpackage.beanpackage.SearchBean;

import org.springframework.jdbc.core.JdbcTemplate;

import controller.Log;

import java.util.*;

public class ModelProduct{

	JdbcTemplate template;
	
	public ModelProduct() {
		template = new Database().getJdbcTemplate();
	}

	public boolean insertProductInfo(ProductInfo product){
		String sql = "INSERT INTO productlist( pname, cid, uid, available, pic_id, is_feature, brand_name) " +
				" values(?, ?, ?, ?, ?, ?, ?)";
		try {
			template.update(sql, product.getPname(),product.getCid(), product.getUid(), product.getIs_featured(), product.getPic_Id(), product.getIs_featured(), product.getBrand_name());
			return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean updateProductInfo(ProductInfo product){
		String sql = "UPDATE `productlist` SET `pname`=?,`cid`=?,`uid`=?,`available`=?,`pic_id`=?,`is_feature`=?,`brand_name`=? WHERE `pid` = ?";
		try {
			template.update(sql, product.getPname(),product.getCid(), product.getUid(), product.getAvailable(), product.getPic_Id(), product.getIs_featured(), product.getBrand_name(), product.getPid());
			return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}

	public boolean deleteProductInfo(int pid){
		String sql = "DELETE FROM `productlist` WHERE `pid` = ?";
		try {
			template.update(sql, pid);
			return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public List<ProductInfo> getProductInfo(String fieldName, String value){
		String sql = "SELECT * FROM productlist WHERE "+fieldName+" = '"+value+"'";
		return getListProduct(sql);
	}

	public List<ProductInfo> getProductInfoCustomWhere(String where){
		String sql = "SELECT * FROM productlist WHERE "+where;
		return getListProduct(sql);
	}
	
	public List<ProductInfo> getProductBySql(String sql){
		return getListProduct(sql);
	}
	
	private List<ProductInfo> getListProduct(String sql){
		try {
			List<ProductInfo> product = new ArrayList<ProductInfo>();
			List<Map<String, Object>> map = template.queryForList(sql);
			for(Map mapValue : map){
				ProductInfo productInfo = new ProductInfo();
				productInfo.setPid(Integer.parseInt(mapValue.get("pid").toString()));
				productInfo.setPname(mapValue.get("pname").toString());
				productInfo.setCid(Integer.parseInt(mapValue.get("cid").toString()));
				productInfo.setUid(Integer.parseInt(mapValue.get("uid").toString()));
				productInfo.setAvailable(Integer.parseInt(mapValue.get("available").toString()));
				productInfo.setPic_Id(Integer.parseInt(mapValue.get("pic_id").toString()));
				productInfo.setIs_featured(Integer.parseInt(mapValue.get("is_feature").toString()));
				productInfo.setBrand_name(mapValue.get("brand_name").toString());
				if(mapValue.get("cname") != null)
				productInfo.setCname(mapValue.get("cname").toString());
				product.add(productInfo);
			}
			return product;
		}
		catch(Exception e){
			System.out.println(" Error to insert data into the bean : "+e.getMessage());
			return null;
		}
	}
	
	
	public List<SearchBean> getListSearch(String sql){
		try {
			List<SearchBean> product = new ArrayList<SearchBean>();
			List<Map<String, Object>> map = template.queryForList(sql);
			for(Map mapValue : map){
				SearchBean searchbean = new SearchBean();
				searchbean.setProductId(mapValue.get("pid").toString());
				searchbean.setProductName(mapValue.get("pname").toString());
				searchbean.setPackageId(mapValue.get("pack_id").toString());
				searchbean.setProductPrice(mapValue.get("total_price").toString());
				product.add(searchbean);
			}
			return product;
		}
		catch(Exception e){
			System.out.println(" Error to insert data into the bean : "+e.getMessage());
			return null;
		}
	}
	
	public List<Chart> getListChart(String sql){
		try {
			List<Chart> Chart = new ArrayList<Chart>();
			List<Map<String, Object>> map = template.queryForList(sql);
			for(Map mapValue : map){
				Chart singleChart = new Chart();
				singleChart.setPid(mapValue.get("pid").toString());
				singleChart.setPname(mapValue.get("pname").toString());
				singleChart.setPack_id(mapValue.get("pack_id").toString());
				singleChart.setTotal_price(mapValue.get("total_price").toString());
				singleChart.setTotal_ammount(mapValue.get("total_number").toString());
				Chart.add(singleChart);
			}
			return Chart;
		}
		catch(Exception e){
			System.out.println(" Error to insert data into the bean : "+e.getMessage());
			return null;
		}
	}
		
}

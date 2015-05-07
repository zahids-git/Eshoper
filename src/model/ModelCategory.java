package model;

import beanpackage.CategoryBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelCategory {

    JdbcTemplate template;
    public ModelCategory() {
        template = new Database().getJdbcTemplate();
    }

    public boolean insertCategory(CategoryBean category){
        String sql = "INSERT INTO category(cname, main_catid) VALUES (?, ?)";

        try {
            template.update(sql, category.getCname(), category.getMain_catid());
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<CategoryBean> getCategoryInfo(String fieldName, String value){
        String sql = "SELECT * FROM category WHERE "+fieldName+" = '"+value+"'";
        return getListOfResult(sql);
    }
    
    public List<CategoryBean> getCategoryInfoWhere(String where){
        String sql = "SELECT * FROM category WHERE "+where;
        return getListOfResult(sql);
    }
    
    public List<CategoryBean> getAllCategoryInfo(){
        String sql = "SELECT * FROM category";
        return getListOfResult(sql);
    }
    
    private List<CategoryBean> getListOfResult(String sql) {
    	try {
            List<CategoryBean> categoryList = new ArrayList<CategoryBean>();
            List<Map<String, Object>> map = template.queryForList(sql);
            for(Map mapValue : map){
                CategoryBean categoryBean = new CategoryBean();
                categoryBean.setCid(Integer.parseInt(mapValue.get("cid").toString()));
                categoryBean.setCname(mapValue.get("cname").toString());
                categoryBean.setMain_catid(Integer.parseInt(mapValue.get("main_catid").toString()));
                categoryList.add(categoryBean);
            }
            return categoryList;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
	}



}

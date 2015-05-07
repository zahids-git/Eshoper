package model;

import beanpackage.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelPackage {

    JdbcTemplate template;
    public ModelPackage() {
        template = new Database().getJdbcTemplate();
    }

    public boolean insertPackageInfo(PackageInfo packageInfo){
        String sql = "INSERT INTO package(pid, total_number, total_price, pdate, total_sale ,dayType) VALUES (?, ?, ?, ?, ? , ?)";
        try {
            template.update(sql, packageInfo.getPid(), packageInfo.getTotal_number(),packageInfo.getTotal_price(),packageInfo.getPdate(),packageInfo.getTotal_Sell(), packageInfo.getDayType());
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updatePackageInfo(PackageInfo packageInfo){
        String sql = "UPDATE `package` SET `total_number`=?,`total_price`=?,`pdate`=?, `dayType`=? WHERE `pack_id`=?";
        try {
            template.update(sql, packageInfo.getTotal_number(),packageInfo.getTotal_price(),packageInfo.getPdate(),packageInfo.getDayType(),packageInfo.getPackId());
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean deletePackageInfo(PackageInfo packageInfo){
        String sql = "DELETE FROM `package` WHERE `pack_id`=?";
        try {
            template.update(sql, packageInfo.getPackId());
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<PackageInfo> getPackageInfo(String value, String fieldName){
        String sql = "SELECT * FROM package WHERE "+fieldName+" = '"+value+"'";
        return getListOfPackage(sql);
    }
    
    public List<PackageInfo> getAllPackageInfo(String where){
        String sql = "SELECT * FROM package WHERE "+where;
        return getListOfPackage(sql);
    }
    
    public List<PackageInfo> getAllPackageSql(String sql){
        return getListOfPackage(sql);
    }
    
    private List<PackageInfo> getListOfPackage(String sql) {
    	try {
            List<PackageInfo> PackageList = new ArrayList<PackageInfo>();
            List<Map<String, Object>> map = template.queryForList(sql);
            for(Map mapValue : map){
                PackageInfo packageInfo = new PackageInfo();
                packageInfo.setPackId(Integer.parseInt(mapValue.get("pack_id").toString()));
                packageInfo.setPid(Integer.parseInt(mapValue.get("pid").toString()));
                packageInfo.setTotal_number(Double.parseDouble(mapValue.get("total_number").toString()));
                packageInfo.setTotal_price(Double.parseDouble(mapValue.get("total_price").toString()));
                packageInfo.setPdate(mapValue.get("pdate").toString());
                packageInfo.setTotal_Sell(Integer.parseInt(mapValue.get("total_sale").toString()));
                packageInfo.setDayType(mapValue.get("dayType").toString());
                PackageList.add(packageInfo);
            }
            return PackageList;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }		
	}



}

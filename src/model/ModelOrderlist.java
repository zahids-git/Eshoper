package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import beanpackage.OrderInfo;
import beanpackage.PackageInfo;

import org.springframework.jdbc.core.JdbcTemplate;
    public class ModelOrderlist {

        JdbcTemplate template;
        public ModelOrderlist(){
            template = new Database().getJdbcTemplate();
        }

        public boolean insertOrderlist(OrderInfo orderInfo){
            String sql = "INSERT INTO orderlist( uid, odate, list , total) VALUES (?, ?, ?, ?)";

            try {
                template.update(sql, orderInfo.getUid(), orderInfo.getDate(), orderInfo.getList(), orderInfo.getTotal());
                return true;
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        
        public List<OrderInfo> getOrderInfo(String sql) {
			return getListOfOrder(sql);
		}
        
        
        
        private List<OrderInfo> getListOfOrder(String sql) {
        	try {
                List<OrderInfo> orderInfo = new ArrayList<OrderInfo>();
                List<Map<String, Object>> map = template.queryForList(sql);
                for(Map mapValue : map){
                	OrderInfo order = new OrderInfo();
                	order.setOid(Integer.parseInt(mapValue.get("oid").toString()));
                	order.setUid(Integer.parseInt(mapValue.get("uid").toString()));
                	order.setDate(mapValue.get("odate").toString());
                	order.setList(mapValue.get("list").toString());
                	order.setDelivery(Integer.parseInt(mapValue.get("delivery").toString()));
                	order.setTotal(Double.parseDouble(mapValue.get("total").toString()));
                    orderInfo.add(order);
                }
                return orderInfo;
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                return null;
            }		
    	}
}

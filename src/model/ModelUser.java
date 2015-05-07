package model;


import beanpackage.UserBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelUser {

    JdbcTemplate template;

    public ModelUser(){
        template = new Database().getJdbcTemplate();
    }

    public boolean insertUserInfo(UserBean user){
        String sql = "insert into user(uname,uemail,upass,account_date,account_type,card_number,holder_name,address) " +
                " values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            template.update(sql, user.getuName(),user.getuEmail(),user.getuPass(),user.getAccountDate(),user.getAccountType(),user.getCardNumber(),user.getHolderName(),user.getAddress());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public UserBean getUserInfo(Integer uid, String fieldName){
        String sql = "SELECT * FROM user WHERE uid = ?";
        try {
            UserBean data = template.queryForObject(sql, new Object[]{uid}, new UserInfo());
            return data;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public UserBean getUserInfo(String uid, String fieldName){
        String sql = "SELECT * FROM user WHERE "+fieldName+" = ?";
        try {
            UserBean data = template.queryForObject(sql, new Object[]{uid}, new UserInfo());
            return data;
        }
        catch(Exception e){
            return null;
        }
    }
    public UserBean getUserInfoWhere(String where){
        String sql = "SELECT * FROM user WHERE "+where;
        try {
            UserBean data = template.queryForObject(sql, new Object[]{}, new UserInfo());
            return data;
        }
        catch(Exception e){
            return null;
        }
    }
}

class UserInfo implements org.springframework.jdbc.core.RowMapper<UserBean>{

    @Override
    public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserBean user = new UserBean();
        user.setUid(rs.getInt("uid"));
        user.setuName(rs.getString("uname"));
        user.setuEmail(rs.getString("uemail"));
        user.setuPass(rs.getString("upass"));
        user.setAccountDate(rs.getString("account_date"));
        user.setAccountType(rs.getString("account_type"));
        user.setCardNumber(rs.getInt("card_number"));
        user.setHolderName(rs.getString("holder_name"));
        user.setAddress(rs.getString("address"));
        return user;
    }
}

package model;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Database {
	
	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/shop";
	private String username = "root";
	private String password = "";
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	
	public Database(){
		DriverManagerDataSource source = new DriverManagerDataSource(url, username, password);
		source.setDriverClassName(driverClassName);
		this.dataSource = source;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		new Tables(jdbcTemplate);
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	

}

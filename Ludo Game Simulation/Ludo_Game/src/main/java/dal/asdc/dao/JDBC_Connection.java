package dal.asdc.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import dal.asdc.dao.*;

/**
 * @author Reshma Unnikrishnan
 * */

public class JDBC_Connection implements IJDBC_Connection{
	
	public Map<String,String> read_db_property_file() {
	Properties properties = new Properties();
	String property_file_name = "src/main/java/config.properties";
	Map<String, String> db_config = new HashMap<String,String>();
	
	try {
		FileReader prop_reader = new FileReader(property_file_name);
		properties.load(prop_reader);
		db_config.put("db_url",properties.getProperty("DB_URL"));
		db_config.put("db_uname",properties.getProperty("DB_Username"));
		db_config.put("db_password",properties.getProperty("DB_Password"));
		db_config.put("db_name",properties.getProperty("DB_Database_name"));
		db_config.put("db_driver",properties.getProperty("DB_Driver"));
		}
	catch(FileNotFoundException fnf){
		fnf.printStackTrace();
		}
	catch(IOException ioe) {
		ioe.printStackTrace();
		}
	
		return db_config;
	}
	
	public Connection createDBConnection() {
		Map<String, String> db_config = new HashMap<String,String>();
		db_config = read_db_property_file();
		Connection conn = null;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			
//			conn = DriverManager.getConnection(  
//			"jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_10_DEVINT?"
//			+ "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
//			+ "&serverTimezone=UTC","CSCI5308_10_DEVINT_USER","c5SZhk8dfmzXT9eN"); 
			conn = DriverManager.getConnection("jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_10_DEVINT","CSCI5308_10_DEVINT_USER","c5SZhk8dfmzXT9eN");
			}catch(Exception e){ System.out.println(e);}  
			return conn;
			}
	
	public static void main(String args[]){  
		
		JDBC_Connection jdbc = new JDBC_Connection();
		jdbc.read_db_property_file();
		jdbc.createDBConnection();
		System.out.println("No error it runs");
}}

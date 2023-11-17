package com.EcommerceStore.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	private static Connection connect = null;
	
	public static Connection getConnection(){
		if(connect == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecom_website", "root", "Tejas@12345");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return connect;
	}
}

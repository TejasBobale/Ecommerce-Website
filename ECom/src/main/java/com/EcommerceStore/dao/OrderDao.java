package com.EcommerceStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.EcommerceStore.model.Order;

public class OrderDao {
	private Connection connect;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection connect) {
		this.connect = connect;
	}
	
	public boolean insertOrder(Order model) {
		boolean result = false;
		
		try {
			query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?, ?, ?, ?)";
			
			pst = this.connect.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getUid());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			
			pst.executeUpdate();
			
			result = true;
		} catch (Exception e) {
			e.getMessage();
		}
		
		return result ;
	}
}

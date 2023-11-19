package com.EcommerceStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.EcommerceStore.model.*;

public class ProductDao {
	private Connection connect;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection connect) {
		this.connect = connect;
	}
	
	public List<Product> getAllProduct(){
		List<Product> products = new ArrayList<Product>();
		
		try {
			query = "select * from products";
			pst = this.connect.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setAvailability(rs.getInt("availability"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);	
			}
		} catch (Exception e) {
			
		}
		
		return products;
	}
	
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<>();
		
		try {
			if(cartList.size() > 0) {
				for(Cart item: cartList) {
					query = "select * from products where id=?";
					pst = this.connect.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("Price") * item.getQuantity());
						row.setQuantity(item.getQuantity());
						
						products.add(row);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return products;
	}
	
	
	public double getTotalPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		
		try {
			if(cartList.size() > 0) {
				for(Cart item: cartList) {
					query = "select price from products where id=?";
					pst = this.connect.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						sum += rs.getDouble("price")*item.getQuantity() ;
					}
				}
			}
		} catch (Exception e) {
			
		}
		
		return sum;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

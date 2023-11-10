package com.kh.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private static final String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "khcafe";
	private static final String password = "khcafe";
	
	
	public ProductDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Product> getAllProduct() {
		List<Product> products = new ArrayList<>();
		
		try {
			//따로 뺴놔서 null값 으로 초기화 안해도 됌
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			String sql = "SELECT * FROM products";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
			
			while(resultSet.next()) {
				int productId = resultSet.getInt("product_id");
				String productName = resultSet.getString("product_name");
				String category = resultSet.getString("category");
				double price = resultSet.getDouble("price");
				int stockQuantity = resultSet.getInt("stock_Quantity");
				
				Product product = new Product(productId, productName, category, price, stockQuantity);
				products.add(product);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}
	
	public Product getProductId(int productId) {
		Product product = new Product();
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(jdbcURL, username, password);
			String sql = "SELECT * FROM PRODUCTS WHERE product_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			
			ps.setInt(1, productId);
			
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
			int getproductId = result.getInt("product_id");
			String getproductName = result.getString("product_name");
			String getcategory = result.getString("category");
			double getprice = result.getDouble("price");
			int getstockQuantity = result.getInt("stock_Quantity");
				
				
				
			product.setProductId(getproductId);
			product.setProductName(getproductName);
			product.setCategory(getcategory);
			product.setPrice(getprice);
			product.setStockQuantity(getstockQuantity);
			
				
//			product = new Product(getproductId, getproductName, getcategory, getprice, getstockQuantity);
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// select 에서 1개만 볼수 있는 쿼리 작성하고 
		// new product 이용해서 값 가지고오기.
		return product;
	}
}

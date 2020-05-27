package com.example.projects.productservice.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.projects.productservice.model.Product;

@Repository
public class ProductServiceDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int count(String id) {
		String sql = "SELECT count(*) FROM PRODUCT WHERE ID = " + id;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Map<String, Product> getProducts() {
		String sqlQuery = "SELECT ID, PRODUCT_NAME FROM PRODUCT";
		Collection<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
		Map<String, Product> productRepo = new HashMap<>();
		rows.stream().map(row -> {
			Product product = new Product();
			product.setId(String.valueOf(row.get("ID")));
			product.setName((String) row.get("PRODUCT_NAME"));
			return product;
		}).forEach(productObj -> {
			productRepo.put(productObj.getId(), productObj);
		});
		return productRepo;
	}

	public int createProduct(Product product) {
		String sql = "INSERT INTO PRODUCT (ID, PRODUCT_NAME) VALUES(?,?)";
		return jdbcTemplate.update(sql, product.getId(), product.getName());
	}

	public int updateProduct(String id, Product product) {
		String sql = "UPDATE PRODUCT SET PRODUCT_NAME = ? WHERE ID = ?";
		return jdbcTemplate.update(sql, product.getName(), id);
	}

	public int deleteProduct(String id) {
		return jdbcTemplate.update("DELETE FROM PRODUCT WHERE ID = ?", id);
	}

	public List<Product> getProductList() {
		String sqlQuery = "SELECT ID, PRODUCT_NAME FROM PRODUCT";
		Collection<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
		List<Product> productList = new ArrayList<>();
		rows.stream().map(row -> {
			Product product = new Product();
			product.setId(String.valueOf(row.get("ID")));
			product.setName((String) row.get("PRODUCT_NAME"));
			return product;
		}).forEach(productObj -> {
			productList.add(productObj);
		});
		return productList;
	}

}
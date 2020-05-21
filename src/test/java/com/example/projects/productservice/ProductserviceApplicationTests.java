package com.example.projects.productservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.projects.productservice.controller.ProductServiceController;

@SpringBootTest
class ProductserviceApplicationTests {

	@Autowired
	private ProductServiceController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}

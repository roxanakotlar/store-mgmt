package com.example.demo2;

import com.example.demo2.model.Product;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.http.MediaType;


import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest
public class StoreApplicationTests {
	private static final String API_ROOT = "http://localhost:8084/api/store-mgmt";

	@Test
	public void contextLoads() {
	}


	@Test
	public void whenHello_thenOK() {
		final Response response = RestAssured.get(API_ROOT+ "/hello");
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void  whenAddProduct_thenOK() {
		final Product product = new Product("test1", new Date());
		final Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(product)
				.post(API_ROOT + "/add-product");
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetAllProducts_thenOK() {
		final Response response = RestAssured.get(API_ROOT + "/get-all-products");
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

}

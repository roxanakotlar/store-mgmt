package com.example.demo2;

import com.example.demo2.model.Product;
import org.junit.After;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;


import io.restassured.RestAssured;
import io.restassured.response.Response;

@SpringBootTest
public class StoreApplicationTests {
	private static final String API_ROOT = "http://localhost:8084/api/store-mgmt";

	@After
	public void cleanup()
	{
		List<Product> products = RestAssured.given().auth()
				.basic("user", "password")
				.when().get(API_ROOT + "/get-all-products").
				then().statusCode(200).extract().body().jsonPath().getList(".", Product.class);
		products.forEach(product -> RestAssured.given().auth().basic("user", "password").when().
				delete(API_ROOT + "/{id}", product.getId()).then().statusCode(200));
	}


	@Test
	public void whenHello_thenOK() {
		final Response response = RestAssured.get(API_ROOT+ "/hello");
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenAddProductNotAuthenticated_thenNOK() {
		final Product product = new Product("test1", new Date());
		final Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(product)
				.post(API_ROOT + "/add-product");
		assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode());
	}

	@Test
	public void whenAddProduct_thenOK() {
		final Product product = new Product("test1", new Date());
		final Response response = RestAssured.given().auth()
				.basic("user", "password")
				.when()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(product)
				.post(API_ROOT + "/add-product");
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void  whenFindProduct_thenOK() {
		long id = addTestProduct(1);

		int getProductId = RestAssured.given().auth()
				.basic("user", "password")
				.when().get(API_ROOT + "/find-by-id/{id}", id).then().
				statusCode(HttpStatus.OK.value()).extract().path("id");

		assertEquals(id, getProductId);
	}

	@Test
	public void whenGetAllProducts_thenOK() {
		final Response response = RestAssured.given().auth()
				.basic("user", "password")
				.when().get(API_ROOT + "/get-all-products");
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		System.out.println(response.body().asString());
	}

	@Test
	public void whenGetOutOfStockProducts_thenOK() {
		addTestProduct(1);
		addTestProduct(0);
		addTestProduct(0);
		final Response response = RestAssured.given().auth()
				.basic("user", "password")
				.when().get(API_ROOT + "/find-out-of-stock");
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		ArrayList<Product> outOfStockProducts = response.body().as(ArrayList.class);
		assertEquals(2, outOfStockProducts.size());
	}

	private long addTestProduct(int quantity){
		long random = System.currentTimeMillis();
		Product product = new Product("name"+random, "brand" + random, random, "description" + random, quantity, new Date());
		String addedProductId = RestAssured.given().auth()
				.basic("user", "password")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(product)
				.when()
				.post(API_ROOT + "/add-product").then().
				statusCode(200).extract().body().asString();
		return Long.parseLong(addedProductId);
	}


}

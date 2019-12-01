package com.qa.gorest.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.pojo.Users;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

public class UpdateUserAPItest {

	String baseURI = "https://gorest.co.in/";
	String basepath = "public-api/users";
	String token = "sjry5pqfLD3_xMpkhT84ACmDlLsSWX8yMAQV";

	@Test
	@Description("Test case name: update user api with a new user")
	@Severity(SeverityLevel.CRITICAL)
	public void updateuserlistAPItest() {

		System.out.println("========creating a new user==================");
		Users user = new Users("rajeev", "sharma", "male", "02-02-1989", "rajeevsharma12@gmail.com", "91829102123",
				"http://www.rajeeshrama.com", "delhi", "active");
		Response resppost = RestClient.doPost("JSON", baseURI, basepath, token, true, user);
		System.out.println(resppost.getStatusCode());
		System.out.println(resppost.prettyPrint());
		String id = resppost.jsonPath().getString("result.id");

		System.out.println("new user id is: " + id);
		System.out.println("============");
		// update the same user: PUT call
		user = new Users("rajeev", "sharma", "male", "02-02-1989", "rajeevsharma12@gmail.com", "91829102123",
				"http://www.rajeeshrama.com", "delhi", "inactive");
		Response updateresp = RestClient.doPut("JSON", baseURI, basepath + "/" + id, token, true, user);
		System.out.println(updateresp.getStatusCode());
		System.out.println(updateresp.prettyPrint());
		String updateid = updateresp.jsonPath().getString("result.id");
		Assert.assertEquals(updateid, id);
	}

	@Test
	@Description("Test case name: update user api with a new user using setter")
	@Severity(SeverityLevel.CRITICAL)
	public void updateuserlistAPItest_withsetter() {

		System.out.println("========creating a new user==================");
		Users user = new Users();
		user.setFirst_name("arpita");
		user.setLast_name("chakraborthy");
		user.setGender("female");
		user.setDob("02-02-1989");
		user.setEmail("arpchak10@gmail.com");
		user.setPhone("91829102123");
		user.setWebsite("http://www.arpchak.com");
		user.setAddress("bangalore");
		user.setStatus("active");
		Response resppost = RestClient.doPost("JSON", baseURI, basepath, token, true, user);
		System.out.println(resppost.getStatusCode());
		System.out.println(resppost.prettyPrint());
		String id = resppost.jsonPath().getString("result.id");

		System.out.println("new user id is: " + id);
		System.out.println("============");
		// update the same user: PUT call
		user.setPhone("91829121245");
		Response updateresp = RestClient.doPut("JSON", baseURI, basepath + "/" + id, token, true, user);
		System.out.println(updateresp.getStatusCode());
		System.out.println(updateresp.prettyPrint());
		String updateid = updateresp.jsonPath().getString("result.id");
		Assert.assertEquals(updateid, id);

	}
}

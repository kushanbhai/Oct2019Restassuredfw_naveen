package com.qa.gorest.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.pojo.Users;
import com.qa.util.ExcelUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

@Epic("EPIC - CREATE USER")
@Feature("user story - define create user feature...")

public class CreateUserAPITest {
	String baseURI = "https://gorest.co.in/";
	String basepath = "public-api/users";
	String token = "sjry5pqfLD3_xMpkhT84ACmDlLsSWX8yMAQV";

	@DataProvider
	public Object[][] getuserdata() {
		Object[][] userdata = ExcelUtil.gettestData("createuserdata");
		return userdata;
	}

	@Test(dataProvider = "getuserdata")
	@Description("Test case name: verify create user api with a new user")
	@Severity(SeverityLevel.CRITICAL)
	public void createuserlistAPItestusingDataprovider(String first_name, String last_name, String gender, String dob,
			String email, String phone, String website, String address, String status) {
		Users user = new Users(first_name, last_name, gender, dob, email, phone, website, address, status);
		Response resppost = RestClient.doPost("JSON", baseURI, basepath, token, true, user);
		System.out.println(resppost.getStatusCode());
		System.out.println(resppost.prettyPrint());
	}

	@Test
	public void createuserlistAPItest() {
		Users user = new Users("rohan", "sharma", "male", "02-02-1989", "rosharm1235@gmail.com", "91829102123",
				"http://www.rosharama.com", "delhi", "active");
		Response resppost = RestClient.doPost("JSON", baseURI, basepath, token, true, user);
		System.out.println(resppost.getStatusCode());
		System.out.println(resppost.prettyPrint());
	}
}

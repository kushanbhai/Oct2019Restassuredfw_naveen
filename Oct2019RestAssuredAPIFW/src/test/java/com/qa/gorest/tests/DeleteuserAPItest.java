package com.qa.gorest.tests;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.constants.Constants;
import com.qa.pojo.Users;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;

public class DeleteuserAPItest {

	String baseURI = "https://gorest.co.in/";
	String basepath = "public-api/users";
	String token = "sjry5pqfLD3_xMpkhT84ACmDlLsSWX8yMAQV";

	@Test
	@Description("Test case name: delete user api for a new user")
	@Severity(SeverityLevel.CRITICAL)
	public void deleteuserlistAPItest() {
		System.out.println("========creating a new user==================");
		Users user = new Users("rajeev", "sharma", "male", "02-02-1989", "rajeevsharma7@gmail.com", "91829102123",
				"http://www.rajeeshrama.com", "delhi", "active");
		Response resppost = RestClient.doPost("JSON", baseURI, basepath, token, true, user);
		System.out.println(resppost.getStatusCode());
		System.out.println(resppost.prettyPrint());
		String id = resppost.jsonPath().getString("result.id");

		System.out.println("new user id is: " + id);
		System.out.println("============");
		//delete the user :DELETE API
		Response delresp = RestClient.doDelete("JSON", baseURI, basepath + "/" + id, token, true);
		System.out.println(delresp.getStatusCode());
		System.out.println(delresp.prettyPrint());
		String resultstatus = delresp.jsonPath().getString("result");
		Assert.assertNull(resultstatus);
}
	@Test
	public void deleteuserlistAPItest_usingsetter() {
		System.out.println("========creating a new user==================");
		Users user = new Users();
		user.setFirst_name("arpita");
		user.setLast_name("chakraborthy");
		user.setGender("female");
		user.setDob("02-02-1989");
		user.setEmail("arpchak6@gmail.com");
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
		//delete the user: DELETE CALL
		Response delresp = RestClient.doDelete("JSON", baseURI, basepath + "/" + id, token, true);
		System.out.println(delresp.getStatusCode());
		System.out.println(delresp.prettyPrint());
		int statuscode = delresp.jsonPath().get("_meta.code");
		Assert.assertEquals(statuscode,Constants.HTTP_STATUS_CODE_204 );
		String resultstatus = delresp.jsonPath().getString("result");
		Assert.assertNull(resultstatus);
		int limit = delresp.jsonPath().get("_meta.rateLimit.limit");
		int remaining = delresp.jsonPath().get("_meta.rateLimit.remaining");
		int reset = delresp.jsonPath().get("_meta.rateLimit.reset");
		

}
}

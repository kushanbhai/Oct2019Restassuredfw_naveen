package com.qa.gorest.tests;

import java.util.ArrayList;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetuserAPItest {
	String baseURI = "https://gorest.co.in/";
	String basepath = "public-api/users";
	String token = "sjry5pqfLD3_xMpkhT84ACmDlLsSWX8yMAQV";

	@Test
	@Description("Test case name: verify get user api with existing user")
	@Severity(SeverityLevel.CRITICAL)
	public void getuserlistAPItest() {
		Response response = RestClient.doGet("JSON", baseURI, basepath, token, true);
		System.out.println(RestClient.getstatuscode(response));
		Assert.assertEquals(RestClient.getstatuscode(response), Constants.HTTP_STATUS_CODE_200);
		RestClient.getprettyprint(response);
		JsonPath js = RestClient.getJSONpath(response);
		System.out.println("The response for meta is: "+js.getString("_meta.message"));
		ArrayList results = js.get("result");
		System.out.println(results.size());
		System.out.println(results.get(0));
		Map<String,Object> firstuserdata = (Map<String, Object>) results.get(0);
		for(Map.Entry<String, Object> entry:firstuserdata.entrySet()) {
			System.out.println("key = "+entry.getKey() + ", value = "+entry.getValue());
		}
	}
}

package com.qa.client;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.path.json.JsonPath;

public class RestClient {

//get post put delete

	public static Response doGet(String contentType, String baseURI, String basePath, String token, boolean log) {
		RestClient.getBASEURI(baseURI);
		RequestSpecification request = RestClient.createrequest(contentType, token, log);
		return RestClient.getresponse("GET", request, basePath);
	}

	public static Response doPost(String contentType, String baseURI, String basePath, String token, boolean log,
			Object obj) {
		RestClient.getBASEURI(baseURI);
		RequestSpecification request = RestClient.createrequest(contentType, token, log);
		String jsonpayload = TestUtil.getserializedJSON(obj);
		request.body(jsonpayload);
		return RestClient.getresponse("POST", request, basePath);
	}

	public static Response doPut(String contentType, String baseURI, String basePath, String token, boolean log,
			Object obj) {
		RestClient.getBASEURI(baseURI);
		RequestSpecification request = RestClient.createrequest(contentType, token, log);
		String jsonpayload = TestUtil.getserializedJSON(obj);
		request.body(jsonpayload);
		return RestClient.getresponse("PUT", request, basePath);
	}

	public static Response doDelete(String contentType, String baseURI, String basePath, String token, boolean log) {
		RestClient.getBASEURI(baseURI);
		RequestSpecification request = RestClient.createrequest(contentType, token, log);
		return RestClient.getresponse("DELETE", request, basePath);

	}

	/**
	 * this method is used to create base uri
	 * 
	 * @param baseURI
	 */
	// GENERIC methods
	public static void getBASEURI(String baseURI) {
		RestAssured.baseURI = baseURI;
	}

	/**
	 * this method is used to create request
	 * 
	 * @param contentType
	 * @param token
	 * @param log
	 * @return
	 */
	public static RequestSpecification createrequest(String contentType, String token, boolean log) {
		RequestSpecification request;

		if (log) {
			request = RestAssured.given().log().all();
		} else {
			request = RestAssured.given();
		}

		if (token != null) {
			request.header("Authorization", "Bearer " + token);
		}
		if (contentType.equalsIgnoreCase("JSON")) {
			request.contentType(ContentType.JSON);
		} else if (contentType.equalsIgnoreCase("XML")) {
			request.contentType(ContentType.XML);
		}

		else if (contentType.equalsIgnoreCase("TEXT")) {
			request.contentType(ContentType.TEXT);
		}
		return request;
	}

	public static Response getresponse(String httpmethod, RequestSpecification request, String basepath) {
		return executeAPI(httpmethod, request, basepath);
	}

	/**
	 * this method is used to execute the API on the basis of HTTP
	 * method(GET,PUT,POST,DELETE)
	 * 
	 * @param httpmethod
	 * @param request
	 * @param basepath
	 * @return
	 */
	private static Response executeAPI(String httpmethod, RequestSpecification request, String basepath) {
		Response resp = null;
		switch (httpmethod) {
		case "GET":
			resp = request.get(basepath);
			break;
		case "POST":
			resp = request.post(basepath);
			break;
		case "PUT":
			resp = request.put(basepath);
			break;
		case "DELETE":
			resp = request.delete(basepath);
			break;
		default:
			System.out.println("Please pass the correct HTTP method!");
			break;
		}

		return resp;
	}

	// generic methods for response:
	public static JsonPath getJSONpath(Response response) {
		return response.jsonPath();
	}

	public static int getstatuscode(Response response) {
		return response.getStatusCode();
	}

	public static String getheader(Response response, String headername) {
		return response.getHeader(headername);
	}

	public static int getresponseheadercount(Response response) {
		Headers headers = response.getHeaders();
		return headers.size();
	}

	public static List<Header> getresponseallHeaders(Response response) {
		Headers headers = response.getHeaders();
		List<Header> headerlist = headers.asList();
		return headerlist;
	}

	public static void getprettyprint(Response response) {
		System.out.println("========string in pretty format============");
		response.prettyPrint();
	}
}

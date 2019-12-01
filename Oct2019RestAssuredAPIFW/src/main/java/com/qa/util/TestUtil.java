package com.qa.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	/**
	 * this method is used to convert POJO to JSON string
	 * @param obj
	 * @return
	 */
	public static String getserializedJSON(Object obj) {
		String jsonstring = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonstring = mapper.writeValueAsString(obj);

		} catch (JsonProcessingException e) { 
			e.printStackTrace();
			return jsonstring;
		}
			return jsonstring;
	}
}

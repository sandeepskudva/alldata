package APITesting;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;


import io.restassured.http.ContentType;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class getAndPostExamples {

	@Test
	public void getTest() {
		
				
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("page","2");
		
		baseURI = "https://reqres.in/api";
		given().
			queryParams(reqMap).
		when().
			get("/users").
		then().
			statusCode(200).	
			body("data[4].first_name",equalTo("George")).
			body("data.first_name",hasItems("George","Rachel"));
			
		
	}
	@Test
	public void getVehicle() {
		
		String token = getToken();
		Map<String, Object> headerrequest = new HashMap<String, Object>();
		headerrequest.put("Authorization","Bearer "+token);
		headerrequest.put("Content-Type",ContentType.JSON);
		
		
		JSONObject bodyrequest = new JSONObject();
		bodyrequest.put("vehicleMake","Acura");
		bodyrequest.put("Source","WEB");
		
		baseURI = "https://ctsgtolling-test.api.crm.dynamics.com/api/data/v9.2/";
		
		ArrayList<String> models = 
		given().headers(headerrequest).
		body(bodyrequest.toJSONString()).
	when().
		post("/vector_VehicleModels").
	then().
		statusCode(200).extract().path("vehicleModelList.name");
		
		
		System.out.println(models);
		
		
	}
	
	
	public String getToken() {
		
		
		JSONObject request1 = new JSONObject();
		
		Map<String, String> request= new HashMap<String, String>();
		
		//request.put("Content-Type","application/json");
		request.put("client_id","5c95c003-b26a-4f6f-8afa-26e953ce822b");
		request.put("client_secret","_SnK6IE89bxLwD3b3_W1X~jb48-aD0j1.H");
		request.put("grant_type","client_credentials");
		request.put("resource","https://ctsgtolling-test.crm.dynamics.com");
		
		
		
		baseURI = "https://login.microsoftonline.com";
		String token = given().
			formParams(request).
		when().
			post("/d952dc1e-21e1-4202-b771-c94e8bde9e84/oauth2/token").
		then().
			statusCode(200).extract().path("access_token");
		
		
				
		return token;
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void postTest() {	
		
		
		
		JSONObject request = new JSONObject();
		request.put("name","Raghav");
		request.put("job","Teacher");
	
		
		baseURI = "https://reqres.in/api";
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).log().all();
		
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void postTest2() {	
		
		
		
		JSONObject request = new JSONObject();
		request.put("accountNumber","410003628");
		request.put("operation","Create");
		request.put("city","EMPIRE STATE");
		request.put("state","NY");
		request.put("country","USA");
		request.put("streetLine1","Litn 109012");
		request.put("streetLine2","Litre 2989");
		request.put("zipCode","10001");
		request.put("zipPlus4","9879");
		request.put("addressType","BILLING");
		request.put("source","ROV");
		request.put("aetFlag","true");
		request.put("nonaetFlag","false");
	
		System.out.println(request.toJSONString());
		
		baseURI = "https://ctsgtolling-test.crm.dynamics.com/api/data/v9.1";
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/vector_Address").
		then().
			statusCode(200).log().all();
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void postTest3() {	
		
		
		
		JSONObject request = new JSONObject();
		request.put("source","CRM");
		request.put("accountNumber","410003628");
		
		
	
		System.out.println(request.toJSONString());
		
		baseURI = "https://132.226.38.177/notifications";
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/getNotificationHistory").
		then().
			statusCode(401).log().all();
		
		
	}
}

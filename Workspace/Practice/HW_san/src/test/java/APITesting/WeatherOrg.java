package APITesting;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.hasItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;


public class WeatherOrg {

	@Test
	public void invalidKey() {	
		
		
		
		JSONObject request = new JSONObject();
		request.put("external_id", "SF_TEST001");
		request.put("latitude",37.76);
		request.put("longitude",-122.43);
		request.put("altitude",150);
		
		baseURI = "http://api.openweathermap.org";
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("data/3.0/stations").
		then().
			statusCode(401).log().all();
		
		
	}
	
	@Test
public void validkey() {	
		
		
		
		JSONObject request = new JSONObject();
		
		request.put("external_id", "DEMO_TEST002");
		request.put("name","Interview Station_02");
		request.put("latitude",33.33);
		request.put("longitude",-111.43);
		request.put("altitude",444);
		
		
		JSONObject qparam = new JSONObject();
		
		qparam.put("appid","95f8013a56bdb6ad5513e3d5fb448d39");
		//95f8013a56bdb6ad5513e3d5fb448d39 ,df08a5353b3d7a731f35a62541dc3e58
		
		baseURI = "http://api.openweathermap.org";
		given().
			queryParams(qparam).
			header("Content-Type","application/json").
			contentType(ContentType.JSON).accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("data/3.0/stations").
		then().
			statusCode(201).log().all();
		
		
	}
	
	@Test
	public void getstations() {	
			
			baseURI = "http://api.openweathermap.org";
			given().
				queryParam("appid","95f8013a56bdb6ad5513e3d5fb448d39").
				header("Content-Type","application/json").
				contentType(ContentType.JSON).accept(ContentType.JSON).
				//.body(request.toJSONString()).
			when().
				get("data/3.0/stations").
			then().
				statusCode(200).
				body("external_id",hasItems("DEMO_TEST001")).log().all();		
		}
	
	
	
	
	@Test
	public void deleteStation()
	{
		String externalid = "DEMO_TEST002";
		String IdToDelete = findstations(externalid);
		
		
		if(IdToDelete.equals("Not Found"))
		{
			System.out.println("Nothing to delete");
		}
		else {
			
			System.out.println("id found : "+IdToDelete);	
			deleteUsingId(IdToDelete);
			
		}
		
	}
	
	
	public void deleteUsingId(String id)
	{
		
		baseURI = "http://api.openweathermap.org";
		given().
			queryParam("appid","95f8013a56bdb6ad5513e3d5fb448d39").
			header("Content-Type","application/json").
			contentType(ContentType.JSON).accept(ContentType.JSON).
			
		when().
			delete("/data/3.0/stations/"+id).
		then().
			statusCode(204);
			
	}
	
	public String findstations(String stname) {		
			
		    String idfound= "Not Found";
			
			baseURI = "http://api.openweathermap.org";
			ArrayList <String> id =  given().
				queryParam("appid","95f8013a56bdb6ad5513e3d5fb448d39").
				header("Content-Type","application/json").
				contentType(ContentType.JSON).accept(ContentType.JSON).
				//.body(request.toJSONString()).W
			when().
				get("data/3.0/stations").
			then().
				extract().path("id");			
			
			ArrayList <String> Ext_id =  given().
					queryParam("appid","95f8013a56bdb6ad5513e3d5fb448d39").
					header("Content-Type","application/json").
					contentType(ContentType.JSON).accept(ContentType.JSON).
				when().
					get("data/3.0/stations").
				then().
					extract().path("external_id");			
			
			
			Map <String,String> ids = new HashMap<String, String>();
			
			for(int i =0;i< id.size();i++)
			{
				ids.put(id.get(i),Ext_id.get(i));			
			}		
			
					
			for (Map.Entry entry : ids.entrySet()) {
	                       
			if(entry.getValue().equals(stname))
			{
				idfound =  (String) entry.getKey();
			}
				
		}
			
			return idfound;
	}		
			
}

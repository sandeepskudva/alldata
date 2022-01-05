package apiDefinitions;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class commentsAPI {

	public List<String> getEmailids(String postid)
	{		
		baseURI = "https://jsonplaceholder.typicode.com/";
		
		Map<String, Object> reqParam = new HashMap<String, Object>();
		reqParam.put("postId",postid);
		
		
		List<String>  emailids =new ArrayList<String>();	
			
		Response response = 
		given().contentType(ContentType.JSON).
			queryParams(reqParam).
		when().
			get("/comments").  
		then().
			statusCode(200).extract().response();
		
		List<String> fullResponse = response.jsonPath().getList("$");	
		
		
		for(int i=0;i<fullResponse.size();i++)
		{
			emailids.add(response.jsonPath().getString("email["+i+"]"));			
			
		}
		
		return emailids;
	}
}

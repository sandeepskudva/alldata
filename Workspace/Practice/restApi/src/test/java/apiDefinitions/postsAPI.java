package apiDefinitions;

import static io.restassured.RestAssured.*;


import java.util.ArrayList;
import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class postsAPI {

	
	public List<String> getPostid(String userid)
	{		
		baseURI = "https://jsonplaceholder.typicode.com/";
		List<String>  postids =new ArrayList<String>();	
			
		Response response = 
		given().contentType(ContentType.JSON).
		when().
			get("/users/"+userid+"/posts").
		then().
			statusCode(200).extract().response();
		
		List<String> fullResponse = response.jsonPath().getList("$");	
		
		
		for(int i=0;i<fullResponse.size();i++)
		{
			postids.add(response.jsonPath().getString("id["+i+"]"));
			
		}
		
		return postids;
	}
	
}

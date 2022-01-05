package apiDefinitions;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class usersAPI {
	
	public String getUserid(String name)
	{		
		baseURI = "https://jsonplaceholder.typicode.com/";
		
		String username="";
		String userid="";
		
		Response response = 
		given().contentType(ContentType.JSON).			
		when().
			get("/users").
		then().
			statusCode(200).extract().response();
		
		List<String> fullResponse = response.jsonPath().getList("$");			
		
		for(int i=0;i<fullResponse.size();i++)
		{
			username=response.jsonPath().getString("username["+i+"]");
			if(username.equals(name))
			 userid = response.jsonPath().getString("id["+i+"]");
			
		}
		
		return userid;
	}

}

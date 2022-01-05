package APITesting;

import org.testng.annotations.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;



public class Test01_GET {

	@Test
	public void test_01()
	{
		Response res = get("https://reqres.in/api/users?page=2");
		
		System.out.println("Response is" +res.asString());
		System.out.println("Body is "+res.getBody());
		System.out.println("Status code is "+res.getStatusCode());
		System.out.println("header is "+res.getHeader("content-type"));
		System.out.println("Time taken is "+res.getTime()+" seconds");
		
		int statuscode = 	res.getStatusCode();
		Assert.assertEquals(statuscode,200);
		
		
	}
	@Test
	public void test_02()
	{
		baseURI = "https://reqres.in/api";
		when()
			.get("/users?page=2")
		.then().statusCode(200);
	}
	
	@Test
	public void test_03()
	{
		baseURI = "https://reqres.in/api";
		given()
			.get("/users?page=2").
		then().
			statusCode(200).
			body("data[1].id", equalTo(8)).
			log().all();
		
	}
	
}

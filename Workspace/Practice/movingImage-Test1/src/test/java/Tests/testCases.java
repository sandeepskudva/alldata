package Tests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoClass.*;
import common.*;

public class testCases {

	Logic L = new Logic();

	// Test to verify email address entered in the comments section by user Samantha
	// is Proper format or not
	@Test
	public void validAllUseremails() {
		String username = "Samantha";
		int userid = L.getUserid(username);
		System.out.println("User id for " + username + " is " + userid);
		List<Integer> posts = L.getPostfromUser(userid);
		System.out.println("Posts writen by user " + username + "(" + userid + ")" + " are " + posts);
		List<String> emailids = new ArrayList<String>();
		for (int i = 0; i < posts.size(); i++) {
			emailids = L.getEmailids(posts.get(i));
			System.out.println("Email validation for Comments in Postid " + posts.get(i));
			System.out.println(L.validateEmailList(emailids));
			System.out.println(
					"------------------------------------------------------------------------------------------------");
		}
	}

	// Test to verify that users api is working fine

	@Test
	public void getUserid() {
		String username = "Samantha";
		baseURI = "https://jsonplaceholder.typicode.com";

		int userid = 0;

		Response response = given().contentType(ContentType.JSON).when().get("/users");

		List<usersPOJO> pojo = Arrays.asList(response.getBody().as(usersPOJO[].class));

		for (int i = 0; i < pojo.size(); i++) {
			if (pojo.get(i).getUsername().equals(username))
				userid = pojo.get(i).getId();
		}

		assertEquals(response.getStatusCode(), 200);

		System.out.println("Userid for " + username + " is " + userid);

	}

	// Test to verify that users api is working fine
	@Test
	public void getPostsIds() {
		int userid = 3;
		baseURI = "https://jsonplaceholder.typicode.com";

		List<Integer> id = new ArrayList<Integer>();

		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("userId", userid);

		Response response = given().queryParams(reqMap).log().all().contentType(ContentType.JSON).when().get("/posts");

		List<postsPOJO> pojo = Arrays.asList(response.getBody().as(postsPOJO[].class));

		for (int i = 0; i < pojo.size(); i++) {

			id.add(pojo.get(i).getId());
		}

		assertEquals(response.getStatusCode(), 200);

		System.out.println("post ids for " + userid + " is " + id);

	}

	// Test to verify that comments api is working fine

	@Test
	public void getEmailids() {
		int postid = 21;
		baseURI = "https://jsonplaceholder.typicode.com";

		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("postID", postid);

		String emailid = new String();
		Response response = given().queryParams(reqMap).log().all().contentType(ContentType.JSON).when()
				.get("/comments");
		List<commentsPOJO> pojo = Arrays.asList(response.getBody().as(commentsPOJO[].class));

		for (int i = 0; i < pojo.size(); i++) {
			emailid = pojo.get(i).getId() + "-" + pojo.get(i).getEmail();
		}

		assertEquals(response.getStatusCode(), 200);

		System.out.println("Email address for post with Postid " + postid + " is " + emailid);

	}

}

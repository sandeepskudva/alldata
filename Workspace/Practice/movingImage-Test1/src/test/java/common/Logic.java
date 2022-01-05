package common;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojoClass.commentsPOJO;
import pojoClass.postsPOJO;
import pojoClass.usersPOJO;

public class Logic {

	public int getUserid(String username) {
		baseURI = "https://jsonplaceholder.typicode.com";

		int userid = 0;
		
		Response response = given().contentType(ContentType.JSON).when().get("/users");
		
		List<usersPOJO> pojo = Arrays.asList(response.getBody().as(usersPOJO[].class));

		for (int i = 0; i < pojo.size(); i++) {
			if (pojo.get(i).getUsername().equals(username))
				userid = pojo.get(i).getId();
		}

		return userid;
	}

	public List<Integer> getPostfromUser(int userid) {
		baseURI = "https://jsonplaceholder.typicode.com";

		List<Integer> id = new ArrayList<Integer>();
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("userId", userid);

		Response response = given().queryParams(reqMap).contentType(ContentType.JSON).when().get("/posts");

		List<postsPOJO> postpojo = Arrays.asList(response.getBody().as(postsPOJO[].class));

		for (int i = 0; i < postpojo.size(); i++) {
			id.add(postpojo.get(i).getId());
		}

		return id;

	}

	public List<String> getEmailids(int postid) {
		baseURI = "https://jsonplaceholder.typicode.com";

		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("postId", postid);

		List<String> emailid = new ArrayList<String>();

		Response response = given().queryParams(reqMap).contentType(ContentType.JSON).when().get("/comments");

		List<commentsPOJO> pojo = Arrays.asList(response.getBody().as(commentsPOJO[].class));

		for (int i = 0; i < pojo.size(); i++) {
			emailid.add(pojo.get(i).getEmail());
		}

		return emailid;

	}

	public String isValidEmail(String emailaddress) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		Pattern pattern = Pattern.compile(regex);
		String result;

		Matcher matcher = pattern.matcher(emailaddress);
		if (matcher.matches())
			result = "VALID";
		else
			result = "NOT VALID";

		return result;

	}

	public Map<String, String> validateEmailList(List<String> emailds) {

		Map<String, String> emailValResult = new HashMap<String, String>();

		for (int i = 0; i < emailds.size(); i++) {
			emailValResult.put(emailds.get(i), isValidEmail(emailds.get(i)));
		}

		return emailValResult;
	}

}

package StepDefinitions;

import io.cucumber.java.en.*;

public class loginSteps {
	
	@Given("user is on login page")
	public void user_is_on_login_page() {
	    System.out.println("User is on login page");
	}

	@When("user enters username and password")
	public void user_enters_username_and_password() {
	    System.out.println("User enters username and passowrd");
	}

	@And("clicks on Login button")
	public void clicks_on_login_button() {
		System.out.println("User clicks on Login button");
	}

	@Then("user is navigated to home page")
	public void user_is_navigated_to_home_page() {
	    System.out.println("User is on home page");
	}

}

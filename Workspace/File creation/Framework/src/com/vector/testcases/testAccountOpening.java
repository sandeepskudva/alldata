package com.vector.testcases;


import org.testng.annotations.Test;


import com.vector.func.SuperTestNG;
import com.vector.pages.BasePage;


public class testAccountOpening extends SuperTestNG {
	
	@Test
	public void accountOpen() 
	{
	
	BasePage bp = new BasePage(driver);
	bp.callaccOpentab();
	
	}

}

package com.crm.qa.TestCases;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.crm.qa.BaseClass.TestBase;
import com.crm.qa.Constants.Constants;
import com.crm.qa.Pages.HomePage;
import com.crm.qa.Pages.LoginPage;

public class LoginPageTest extends TestBase
{	
	LoginPage loginPage;
	HomePage homePage;
	public LoginPageTest()
	{
		super();
	}
	
	@Parameters("Browser")
	@BeforeMethod(alwaysRun=false)
	public void setUp(String Browser)
	{
		initialization(Browser);
		Log.info("Application Launched Successfully");
		
		loginPage = new LoginPage();
	}
	
	@Test(priority=1, enabled=true)
	public void loginPageTitleTest(Method method)
	{
		extentTest = extent.createTest(method.getName());
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "Login Page Title is not Matched");
		Log.info("Login Page Title Verified");
		extentTest.pass("Working as expected");
		extentTest.pass("Second Test case passed");
		
	}
	
	@Test(priority=2, enabled=true)
	public void crmLogoImageTest(Method method)
	{
		
		extentTest = extent.createTest(method.getName());
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
		Log.info("CRM Logo Verified");
		extentTest.fail("Not Working as expected");
	}
	
	@Test(priority=3, enabled=false, invocationCount=1) 
	public void loginTest(Method method)
	{
		extentTest = extent.createTest(method.getName());
		homePage = loginPage.login(property.getProperty("Username"),property.getProperty("Password"));
		Log.info("Successfully Logged into CRM Application");
		extentTest.skip("Skipped");
	}
}

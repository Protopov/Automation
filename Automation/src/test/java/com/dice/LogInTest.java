package com.dice;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.dice.base.BaseTest;
import com.dice.base.CsvDataProvider;
import com.dice.pages.LogInPage;
import com.dice.pages.ProfilePage;
public class LogInTest extends BaseTest {

	@Test(priority = 1, groups = { "positive" })
	public void positiveLogInTest() {
		LogInPage logInPage = new LogInPage(driver, log);
		String expectedPageTitle = "Seeker Dashboard - Profile";
		String corrextProfileName = "Eduard Protop";
		
		//Open dice login page = https://www.dice.com/dashboard/login
		logInPage.openLogInPage();
		
		
		//Fill up email and password
		logInPage.fillUpEmailAndPassword("ip192t@gmail.com", "Password000");
		
		//Push Sing In button and wait for page profile to load
		ProfilePage profilePage = logInPage.pushSignInButton();
		profilePage.waitForProfilePageToLoad(); 
		
		//Verifications
		//- Verify title of the page is correct - Sign In
		log.info("Verificstions");
		String actualTitle = profilePage.getTitle();
		Assert.assertTrue(expectedPageTitle.equals(actualTitle), "Page title is not expected.\nExpected: " + expectedPageTitle + "\nActual: " + actualTitle);
		
		
		//- Verify correct name on profile page
		Assert.assertTrue(profilePage.isCorrectProfileLoaded(corrextProfileName), "Profile name is not expected");
	}
	
	
	@Test(dataProvider =  "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 2, groups = { "negative", "broken" })
	public void negativeLogInTest(Map<String, String> testData) {
		String expectedErrorMessage = "Email and/or password incorrect.";
		String testNumber = testData.get("no");
		String email =  testData.get("email");
		String password = testData.get("password");
		String description = testData.get("description");
		
		log.info("Test No #" + testNumber + " for " + description + "Where\nEmail: " + email + "\nPassword: " + password);
		
		LogInPage logInPage = new LogInPage(driver, log);

		
		//Open dice login page = https://www.dice.com/dashboard/login
		logInPage.openLogInPage();
		
		//Fill up email and password
		logInPage.fillUpEmailAndPassword(email, password);
		
		//Push Sing In button
		logInPage.pushSignInButton();
		
		String errorMessege = logInPage.getLogInErrorMessage();
		
		Assert.assertTrue(errorMessege.contains(expectedErrorMessage), 
				"Error message is not expected. Expected: " + expectedErrorMessage + "\nActual: " + errorMessege + "." );
		
	}
}

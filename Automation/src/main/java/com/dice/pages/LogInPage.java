package com.dice.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.dice.base.BasePageObject;

public class LogInPage extends BasePageObject<LogInPage>  {

	private static final String URL = "https://www.dice.com/dashboard/login";
	
	private By emailField = By.xpath("//input[@id='email']");
	private By passwordField = By.xpath("//input[@name='password']");
	private By signInButton = By.xpath("//button[@type='submit']");
	private By errorMessage = By.xpath("//span[@data-ng-show='true']");
	
	public LogInPage(WebDriver driver, Logger log) {
		super(driver, log);
		
	}

	public void openLogInPage(){
		getPage(URL);
	}
	public void fillUpEmailAndPassword(String email, String password) {
		log.info("Filling up email and password");
		type(email, emailField);
		type(password, passwordField);
	}
	
	public ProfilePage pushSignInButton() {
		log.info("Clicking on Sing In Button");
		click(signInButton);
		return new ProfilePage(driver, log);
		
	}

	public String getLogInErrorMessage() {
		waitForVisibilityOf(errorMessage, 10);
		return getText(errorMessage);
	}
}

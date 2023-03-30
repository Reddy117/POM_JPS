package com.orange.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orange.utils.BaseClass;

public class LoginPage extends BaseClass{
	
	@FindBy(xpath="//input[@name='username']")
	WebElement userNameTxt;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passwordTxt;
	
	@FindBy(xpath="//button[text()=' Login ']")
	WebElement loginButton;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void doLogin(String userName,String password) {
		try {
			userNameTxt.sendKeys(userName);
			passwordTxt.sendKeys(password);
			loginButton.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

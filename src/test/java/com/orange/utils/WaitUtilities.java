package com.orange.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtilities extends BaseClass{

	static WebDriverWait wait;
	
	static {
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	public static void elementToBeclickable(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
	}
	
	public static void visibilityOfElement(WebElement ele,String option,String value) {
		wait.until(ExpectedConditions.visibilityOf(ele));
		if(option.equals("click")) {
			ele.click();
		}else if(option.equals("entertext")) {
			ele.sendKeys(value);
		}else if(option.equals("select")) {
			new Select(ele).selectByVisibleText(value);
		}
	}
}

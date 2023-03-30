package com.orange.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ScrollUtilities extends BaseClass{

	static JavascriptExecutor js;
	static{
		js=(JavascriptExecutor)driver;
	}
	
	public static void scrollToElementView(WebElement ele) {
		js.executeScript("",ele);
	}
}

package com.orange.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ActionUtilities extends BaseClass{
	static Actions a;
	static{
		a=new Actions(driver);
	}
	
	public static void dragDrop(WebElement src,WebElement target) {
		a.dragAndDrop(src, target).perform();
	}
	
	public static void mouseHover(WebElement src) {
		a.moveToElement(src).perform();
	}
	
	public static void doubleClick(WebElement src) {
		a.doubleClick(src).perform();
	}
}

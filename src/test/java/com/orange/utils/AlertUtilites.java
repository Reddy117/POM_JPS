package com.orange.utils;

import org.openqa.selenium.Alert;

public class AlertUtilites extends BaseClass{

	static Alert a;
	
	public static void acceptAlert() {
		a=driver.switchTo().alert();
		a.accept();
	}
	
	public static void dismissAlert() {
		a=driver.switchTo().alert();
		a.dismiss();
	}
}

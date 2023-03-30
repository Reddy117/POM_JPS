package com.orange.testcases;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.pages.AdminPage;
import com.orange.utils.BaseClass;

public class Admin extends BaseClass {

	@Test(dataProvider = "getTCData")
	public void provideAdminRights(HashMap<String, String> map) throws InterruptedException {
		AdminPage ap = new AdminPage();
		ap.provideAdminRights(map.get("UserRole"), map.get("Status"), map.get("UserName"), map.get("Password"),
				map.get("Password"));
	}

	@DataProvider
	public Object[][] getTCData() {
		return getData("TC1_ProvideAdminRights", "OrangeData");
	}

}

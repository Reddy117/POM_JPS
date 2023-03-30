package com.orange.testcases;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orange.pages.PIMPage;
import com.orange.utils.BaseClass;

public class PIM extends BaseClass {
	@Test(dataProvider = "getTCData", priority = 1)
	public void tc1_CreateEmployee(HashMap<String, String> map) throws IOException {

		PIMPage pp = new PIMPage();
		String fName = map.get("FirstName") + getRandomNum();
		String userName = fName + " " + map.get("LastName");
		pp.createEmployee(fName, map.get("LastName"), userName, map.get("Password"));

		writeTestData("OrangeData", "TC_EditEmployee", "EmployeeName", userName);
		writeTestData("OrangeData", "TC_EditEmployee", "EmployeeID", fName);
		writeTestData("OrangeData", "TC1_ProvideAdminRights", "EmployeeName", userName);
		writeTestData("OrangeData", "TC1_ProvideAdminRights", "UserName", userName);
	}

	@Test(dataProvider = "getTC2Data", priority = 2)
	public void tc3_editEmployee(HashMap<String, String> map) throws InterruptedException {
		PIMPage pp = new PIMPage();
		Thread.sleep(2000);
		pp.editEmployee(map.get("EmployeeName"), map.get("Nationality"), map.get("Marital Status"),
				map.get("BloodType"), map.get("EmployeeID"));
	}

	@DataProvider
	public Object[][] getTCData() {
		return getData("TC1_CreateEmployee", "OrangeData");
	}

	@DataProvider
	public Object[][] getTC2Data() {
		return getData("TC_EditEmployee", "OrangeData");
	}

}

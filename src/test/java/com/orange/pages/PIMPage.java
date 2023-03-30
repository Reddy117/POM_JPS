package com.orange.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orange.utils.BaseClass;
import com.orange.utils.WaitUtilities;

public class PIMPage extends BaseClass {

	public PIMPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='PIM']")
	WebElement PimLink;

	@FindBy(xpath = "//button[text()=' Add ']")
	WebElement addButton;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstNameText;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastNameText;

	@FindBy(xpath = "//label[text()='Employee Id']/following::input[1]")
	WebElement employeeIDTxt;

	@FindBy(xpath = "//p[text()='Create Login Details']/following::span[1]")
	WebElement createCredentials;

	@FindBy(xpath = "//label[text()='Username']/following::input[1]")
	WebElement userNameTxt;

	@FindBy(xpath = "(//input[@type='password'])[1]")
	WebElement passwordText;

	@FindBy(xpath = "(//input[@type='password'])[2]")
	WebElement confPassword;

	@FindBy(xpath = "//button[text()=' Save ']")
	WebElement saveButton;

	@FindBy(xpath = "//button[text()=' Search ']")
	WebElement searchButton;

	@FindBy(xpath = "//label[text()='Employee Name']/following::input[1]")
	WebElement employeeNameText;

	@FindBy(xpath = "//div[@role='listbox']")
	WebElement listbox1;
	@FindBy(xpath = "//label[text()='Nationality']/following::div[1]")
	WebElement nationalityDrop;
	@FindBy(xpath = "//label[text()='Marital Status']/following::div[1]")
	WebElement maritalStatusButton;
	@FindBy(xpath = "//label[text()='Blood Type']/following::div[1]")
	List<WebElement> BloodTypeButton;

	public void createEmployee(String fName, String lName, String userName, String password) {
		try {
			WaitUtilities.elementToBeclickable(PimLink);
			WaitUtilities.elementToBeclickable(addButton);
			WaitUtilities.visibilityOfElement(firstNameText, "entertext", fName);
			WaitUtilities.visibilityOfElement(lastNameText, "entertext", lName);
			WaitUtilities.elementToBeclickable(createCredentials);
			WaitUtilities.visibilityOfElement(userNameTxt, "entertext", userName);
			WaitUtilities.visibilityOfElement(passwordText, "entertext", password);
			WaitUtilities.visibilityOfElement(confPassword, "entertext", password);
			WaitUtilities.elementToBeclickable(saveButton);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void searchEmployee(String empName) {
		try {
			WaitUtilities.elementToBeclickable(PimLink);
			Thread.sleep(1000);
			WaitUtilities.visibilityOfElement(employeeNameText, "entertext", empName);
			WaitUtilities.elementToBeclickable(searchButton);
			// driver.findElement(By.xpath(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editEmployee(String empName, String nationality, String maritalStatus, String bloodtype, String EmpID) {
		try {
			// WaitUtilities.elementToBeclickable(PimLink);
			Thread.sleep(3000);
			searchEmployee(empName);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(text(),\"" + EmpID + "\")]/following::button[2]")).click();
			Thread.sleep(2000);
			WaitUtilities.elementToBeclickable(nationalityDrop);
			Thread.sleep(2000);
			selectValueFromDrop(listbox1, nationality);
			WaitUtilities.elementToBeclickable(maritalStatusButton);
			selectValueFromDrop(listbox1, maritalStatus);
			System.out.println(isElementAvailable(BloodTypeButton)) ;
			if (isElementAvailable(BloodTypeButton)) {
				WaitUtilities.elementToBeclickable(BloodTypeButton.get(0));
				selectValueFromDrop(listbox1, bloodtype);
			}
			Thread.sleep(5000);
			WaitUtilities.elementToBeclickable(saveButton);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.orange.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.orange.frameworkconstants.FrameworkConstants;
import com.orange.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;
	public static FileInputStream f;
	public static XSSFWorkbook w;
	public static XSSFSheet s;
	public static XSSFRow r;
	public static XSSFCell c;

	public void openBrowser(String browser) {

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(readProperty("url"));
	}

	public static String readProperty(String property) {
		try {
			f = new FileInputStream(FrameworkConstants.PROPERTYFILEPTAH);
			Properties p = new Properties();
			p.load(f);
			return p.getProperty(property);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Property file not found";

	}

	@BeforeClass
	public void setup() {
		openBrowser("edge");
	}

	@BeforeMethod
	public void login() {
		LoginPage lp = new LoginPage();
		lp.doLogin(readProperty("userName"), readProperty("password"));

	}

	@AfterMethod
	public void logOut() {
		driver.findElement(By.xpath("//img[@src='/web/index.php/pim/viewPhoto/empNumber/7']")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public static String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			f = new FileInputStream(FrameworkConstants.DATASHEETPATH);
			w = new XSSFWorkbook(f);

		} catch (Exception e) {
			e.printStackTrace();
		}

		s = w.getSheet(sheetName);
		r = s.getRow(rowNum);
		c = r.getCell(colNum);

		return c.getStringCellValue();
	}

	public static Object[][] getData(String tcName, String sheetName) {
		int tcRowNum = 0;

		while (!getCellData(sheetName, 0, tcRowNum).equals(tcName)) {
			tcRowNum++;
		}

		int cols = 0; // 4
		int colRow = tcRowNum + 1;
		while (!getCellData(sheetName, cols, colRow).equals("N")) {
			cols++;
		}

		int rows = 0; // 3
		int dataRow = tcRowNum + 2; // 2

		while (!getCellData(sheetName, 0, dataRow + rows).equals("N")) {
			rows++;
		}

		Map<String, String> map;
		Object[][] data = new Object[rows][1];
		int index = 0;

		for (int i = dataRow; i < dataRow + rows; i++) {
			map = new HashMap();
			for (int j = 0; j < cols; j++) {
				// System.out.println(getCellData(xlpath, sheetName, j, i));
				String key = getCellData(sheetName, j, colRow);
				String value = getCellData(sheetName, j, i);
				map.put(key, value);
			}

			data[index][0] = map;
			index++;

		}
		return data;
	}

	public static void writeTestData(String sheetName, String tcName, String colName, String value) throws IOException {
		FileInputStream fs = null;

		try {
			fs = new FileInputStream(FrameworkConstants.DATASHEETPATH);
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int colNum = 0;

			int tcRowNum = 0;

			while (!getCellData(sheetName, 0, tcRowNum).equals(tcName)) {
				tcRowNum++;
			}
			System.out.println(tcRowNum);
			XSSFRow r = s.getRow(tcRowNum + 1);

			for (int i = 0; i < r.getLastCellNum(); i++) {
				if (r.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
					colNum = i;
				}
			}
			System.out.println(colNum);
			System.out.println(tcRowNum + 2);
			XSSFRow row = sheet.getRow(tcRowNum + 2);
			XSSFCell cell = row.getCell(colNum);
			cell.setCellValue(value);
			FileOutputStream out = new FileOutputStream(FrameworkConstants.DATASHEETPATH);
			workbook.write(out);
			fs.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getRandomNum() {
		Random random = new Random();
		// Generates random integers 0 to 49
		int x = random.nextInt(99);
		return x;
	}

	public static void selectValueFromDrop(WebElement ele, String value) {
		List<WebElement> items = ele.findElements(By.tagName("span"));
		for (WebElement item : items) {
			if (item.getText().equals(value)) {
				item.click();
				break;
			}
		}
	}

	

	public boolean isElementAvailable(List<WebElement> ele) {
		if (ele.size() > 0) 
			return true;
		 else 
			return false;
		
	}
}

package org.sme.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {

	public static WebDriver driver;

	public static void launchBrowser(String browser) {

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		}
		if (browser.equals("ie")) {
			driver = new InternetExplorerDriver();
		}
		if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}

	}

	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public static void quitBrowser() {
		driver.quit();
	}

	public static void launchUrl(String url) {
		driver.get(url);
	}

	public static void fillTextBox(WebElement txtValue, String name) {
		txtValue.sendKeys(name);
	}

	public static void clickButton(WebElement btn) {
		btn.click();
	}

	/*
	 * public static void dateAndTime() { Date d = new Date();
	 * System.out.println(d);
	 * 
	 * }
	 */
	public static String getPageUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;

	}

	public static String getPageTitle() {

		String title = driver.getTitle();
		return title;
	}

	public static void impWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

	}

	public static Properties loginData() throws IOException {
		FileReader reader = new FileReader(
				"C:\\Users\\impelox-pc-048\\Downloads\\Sme\\Sme\\target\\DatasForDistributor\\LoginData.properties");
		Properties props = new Properties();
		props.load(reader);
		return props;

	}

	public static void fetchDataFromDatabase(String dbURL, String dbUsername, String dbPassword, String query,
			String excelFilePath, int sheetNum) throws SQLException {
		/*
		 * // Database connection details String dbURL =
		 * "jdbc:mysql://aura-uat.cwfjz6cyloxy.me-south-1.rds.amazonaws.com:3306";
		 * String dbUsername = "admin"; String dbPassword =
		 * "zFs4upwKvvpRbbXcKSTf8La3MP4ymd"; String excelFilePath =
		 * "C:\\Users\\impelox-pc-048\\eclipse-workspace\\Demo\\target\\Arshad AIAW Practice.xlsx"
		 * ;
		 */

		Connection connection = null;

		try {
			// Connect to the database
			connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			/*
			 * String query =
			 * "SELECT * FROM  7003_group_medical_aiaw_transactions.premium where plan_id=1 and status=1;"
			 * ;
			 */
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// Open the Excel file
			FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(sheetNum); // Modify index if needed

			// Remove existing rows except the first row
			int lastRow = sheet.getLastRowNum();

			for (int i = 1; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					sheet.removeRow(row);
				}
			}

			// Write new data to the Excel sheet
			int rowCount = 1; // Start writing from the second row

			while (resultSet.next()) {
				Row row = sheet.getRow(rowCount);
				if (row == null) {
					row = sheet.createRow(rowCount); // Create a new row if it doesn't exist
				}

				// Loop through each column and write data
				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
					Cell cell = row.getCell(i - 1);
					if (cell == null) {
						cell = row.createCell(i - 1);
					}

					// Preserve formulas: Skip overwriting if the cell is a formula
					if (cell.getCellType() != CellType.FORMULA) {
						cell.setCellValue(resultSet.getString(i));
						String stringCellValue = cell.getStringCellValue();
						System.out.println(stringCellValue);
					}
				}

				rowCount++; // Move to the next row
			}

			// Close the input stream
			fileInputStream.close();

			// Save the changes to the Excel file
			FileOutputStream fileOutputStream = new FileOutputStream(new File(excelFilePath));
			workbook.write(fileOutputStream);
			fileOutputStream.close();

			// Close workbook
			workbook.close();

			System.out.println("Data has been overwritten in the Excel file successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

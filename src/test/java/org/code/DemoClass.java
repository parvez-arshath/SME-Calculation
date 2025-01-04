package org.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sme.utilities.BaseClass;

public class DemoClass extends BaseClass {

	public static void main(String[] args) throws SQLException {

		/*
		 * fetchDataFromDatabase(
		 * "http://qa-database.cwfjz6cyloxy.me-south-1.rds.amazonaws.com/", "appuser",
		 * "appuseradmin",
		 * "SELECT * FROM 7003_group_medical_aiaw_transactions.group where status = 1;"
		 * );
		 */
		String dbURL = "jdbc:mysql://aura-uat.cwfjz6cyloxy.me-south-1.rds.amazonaws.com:3306";
		String dbUsername = "admin";
		String dbPassword = "zFs4upwKvvpRbbXcKSTf8La3MP4ymd";
		String excelFilePath = "C:\\Users\\impelox-pc-048\\eclipse-workspace\\Demo\\target\\demo - Copy.xlsx";

		Connection connection = null;

		try {
			// Connect to the database
			connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			String query = "SELECT * FROM 7003_group_medical_aiaw_transactions.group where status = 1;";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// Open the Excel file
			FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0); // Modify index if the data needs to go to a specific sheet
			int rowCount = sheet.getLastRowNum();

			// Write data to the Excel sheet
			while (resultSet.next()) {
				Row row = sheet.createRow(++rowCount);
				for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
					Cell cell = row.createCell(i - 1);
					cell.setCellValue(resultSet.getString(i));
					String stringCellValue = cell.getStringCellValue();
					System.out.println(stringCellValue);

				}
			}

			fileInputStream.close();

			// Save the changes to the Excel file
			FileOutputStream fileOutputStream = new FileOutputStream(new File(excelFilePath));
			workbook.write(fileOutputStream);
			fileOutputStream.close();

			System.out.println("Data has been written to the Excel file successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}

package org.code;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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

		/*
		 * // File paths String sourceFilePath =
		 * "C:\\Users\\impelox-pc-048\\Desktop\\New folder\\census_ab_automation.xlsx";
		 * // Source // Excel // file String targetFilePath =
		 * "C:\\Users\\impelox-pc-048\\eclipse-workspace\\CalculationSME\\target\\Arshad AIAW.xlsx"
		 * ; // Target // Excel // file
		 * 
		 * try { // Open the source Excel file FileInputStream sourceFile = new
		 * FileInputStream(new File(sourceFilePath)); Workbook sourceWorkbook = new
		 * XSSFWorkbook(sourceFile); Sheet sourceSheet = sourceWorkbook.getSheetAt(0);
		 * // Source sheet
		 * 
		 * // Open the target Excel file FileInputStream targetFile = new
		 * FileInputStream(new File(targetFilePath)); Workbook targetWorkbook = new
		 * XSSFWorkbook(targetFile); Sheet targetSheet = targetWorkbook.getSheetAt(6);
		 * // Target sheet (modify index as needed)
		 * 
		 * // Remove existing rows in the target sheet, except the first row int lastRow
		 * = targetSheet.getLastRowNum(); for (int i = 1; i <= lastRow; i++) { Row row =
		 * targetSheet.getRow(i); if (row != null) { targetSheet.removeRow(row); } }
		 * 
		 * // Copy data from source sheet to target sheet int rowCount = 1; // Start
		 * from the second row to preserve headers
		 * 
		 * for (int i = 1; i <= sourceSheet.getLastRowNum(); i++) { // Skip the first
		 * row Row sourceRow = sourceSheet.getRow(i); Row targetRow =
		 * targetSheet.createRow(rowCount);
		 * 
		 * if (sourceRow != null) { for (int j = 0; j < sourceRow.getLastCellNum(); j++)
		 * { Cell sourceCell = sourceRow.getCell(j); Cell targetCell =
		 * targetRow.createCell(j);
		 * 
		 * if (sourceCell != null) { // Preserve formulas: Skip overwriting if the cell
		 * is a formula if (targetCell.getCellType() == CellType.FORMULA) { continue; }
		 * 
		 * // Copy the cell value based on the type switch (sourceCell.getCellType()) {
		 * case STRING: targetCell.setCellValue(sourceCell.getStringCellValue()); break;
		 * case NUMERIC: targetCell.setCellValue(sourceCell.getNumericCellValue());
		 * break; case BOOLEAN:
		 * targetCell.setCellValue(sourceCell.getBooleanCellValue()); break; case
		 * FORMULA: targetCell.setCellFormula(sourceCell.getCellFormula()); break; case
		 * BLANK: targetCell.setBlank(); break; default: break; }
		 * 
		 * } } } rowCount++; }
		 * 
		 * // Close the input files sourceFile.close(); targetFile.close();
		 * 
		 * // Save the changes to the target Excel file FileOutputStream outputStream =
		 * new FileOutputStream(new File(targetFilePath));
		 * targetWorkbook.write(outputStream); outputStream.close();
		 * 
		 * // Close the workbooks sourceWorkbook.close(); targetWorkbook.close();
		 * 
		 * System.out.
		 * println("Data copied from source Excel to target Excel successfully.");
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 * 
		 * 
		 */

		// base
		// premium------------------------------------------------------------------
		/*
		 * fetchDataFromDatabase(
		 * "jdbc:mysql://aura-uat.cwfjz6cyloxy.me-south-1.rds.amazonaws.com:3306",
		 * "admin", "zFs4upwKvvpRbbXcKSTf8La3MP4ymd",
		 * "SELECT * FROM  7003_group_medical_aiaw_transactions.premium where plan_id=2 and status=1;"
		 * ,
		 * "C:\\Users\\impelox-pc-048\\eclipse-workspace\\Demo\\target\\Arshad AIAW Practice - Copy.xlsx"
		 * ,0);
		 * 
		 * 
		 */

	}

}

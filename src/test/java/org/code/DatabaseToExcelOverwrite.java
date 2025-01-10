package org.code;

import java.io.*;
import java.sql.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseToExcelOverwrite {

	public static void main(String[] args) {
		// Database connection details
		String dbURL = "jdbc:mysql://aura-uat.cwfjz6cyloxy.me-south-1.rds.amazonaws.com:3306";
		String dbUsername = "admin";
		String dbPassword = "zFs4upwKvvpRbbXcKSTf8La3MP4ymd";
		String excelFilePath = "C:\\Users\\impelox-pc-048\\eclipse-workspace\\CalculationSME\\target\\Arshad AIAW.xlsx";

		Connection connection = null;

		try {
			// Connect to the database
			connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			String query = "SELECT\r\n" + " gm.nationality_group_id,ng.group_name,ng.loading_discount,n.nationality\r\n"
					+ "FROM\r\n" + "      uw_rules_SMEHealth_Dubai_Mednet_transactions.nationality_group_mapping gm\r\n"
					+ "LEFT JOIN\r\n" + "      uw_rules_SMEHealth_Dubai_Mednet_transactions.nationality_group ng\r\n"
					+ "    ON ng.nationality_group_id = gm.nationality_group_id\r\n" + "LEFT JOIN\r\n"
					+ " uw_rules_SMEHealth_Dubai_Mednet_transactions.nationality n\r\n"
					+ "    ON n.nationality_id = gm.nationality_id;";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// Open the Excel file
			FileInputStream fileInputStream = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(4); // Modify index if needed

			// Remove existing rows except the first row
			int lastRow = sheet.getLastRowNum();
			for (int i = 1; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				/*
				 * if (row != null) { sheet.removeRow(row); }
				 */
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
					// Stop writing after column 'O' (15th column, index 14)
					if (i > 15) {
						break;
					}

					Cell cell = row.getCell(i - 1);
					if (cell == null) {
						cell = row.createCell(i - 1);
					}

					// Preserve formulas: Skip overwriting if the cell is a formula
					if (cell.getCellType() == CellType.FORMULA) {
						continue; // Skip cells with formulas
					}

					// Set new value from the database
					cell.setCellValue(resultSet.getString(i));
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

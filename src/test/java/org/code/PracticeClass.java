package org.code;

import org.sme.utilities.BaseClass;

public class PracticeClass extends BaseClass {

	public static void main(String[] args) throws Exception {
		System.out.println(basePremiumAIAW("Dubai", "Mednet", "Gold"));
		System.out.println(benefitsAIAW("%TEST-ABC-0125-1-00027%"));
		System.out.println(nationalityLoadingQueryAIAW("Dubai", "Mednet"));
		System.out.println(industryLoadingQueryAIAW("Dubai", "Mednet"));
		System.out.println(previousInsurerLoadingQueryAIAW("Dubai", "Mednet"));
		System.out.println(commissionAIAW("Dubai", "Mednet", "Gold"));

	
		
		
		
		
		/*
		 * // Database connection details String dbURL =
		 * "jdbc:mysql://aura-uat.cwfjz6cyloxy.me-south-1.rds.amazonaws.com:3306";
		 * String dbUsername = "admin"; String dbPassword =
		 * "zFs4upwKvvpRbbXcKSTf8La3MP4ymd";
		 * 
		 * // Variables to hold user inputs String emirateName, tpaName;
		 * 
		 * // Scanner to get user inputs Scanner scanner = new Scanner(System.in);
		 * 
		 * try (Connection connection = DriverManager.getConnection(dbURL, dbUsername,
		 * dbPassword)) { // Prompt the user for inputs
		 * System.out.print("Enter Emirate Name (e.g., Dubai): "); emirateName =
		 * scanner.nextLine();
		 * 
		 * System.out.print("Enter TPA Name (e.g., Mednet): "); tpaName =
		 * scanner.nextLine();
		 * 
		 * // Step 1: Get Active Version ID String activeVersionQuery =
		 * "WITH ActiveVersion AS (\r\n" + "		                SELECT pv.id\r\n" +
		 * "		                FROM 7003_group_medical_aiaw_transactions.product_versions pv\r\n"
		 * +
		 * "		                WHERE pv.status = 1 AND pv.effective_date <= CURDATE()\r\n"
		 * + "		                ORDER BY pv.effective_date DESC\r\n" +
		 * "		                LIMIT 1\r\n" + "		            )\r\n" +
		 * "		            SELECT id FROM ActiveVersion;";
		 * 
		 * int activeVersionId = 0; try (Statement stmt = connection.createStatement();
		 * ResultSet rs = stmt.executeQuery(activeVersionQuery)) { if (rs.next()) {
		 * activeVersionId = rs.getInt("id"); } }
		 * 
		 * // Step 2: Get Group ID based on Active Version String groupQuery =
		 * "SELECT id FROM 7003_group_medical_aiaw_transactions.group WHERE status = 1 AND version_id = ?"
		 * ; int groupId = 0; try (PreparedStatement pstmt =
		 * connection.prepareStatement(groupQuery)) { pstmt.setInt(1, activeVersionId);
		 * try (ResultSet rs = pstmt.executeQuery()) { if (rs.next()) { groupId =
		 * rs.getInt("id"); } } }
		 * 
		 * // Step 3: Get Emirate ID based on Group ID and Emirate Name String
		 * emirateQuery =
		 * "SELECT id FROM 7003_group_medical_aiaw_transactions.emirate WHERE group_id = ? AND emirate_name LIKE ?"
		 * ; int emirateId = 0; try (PreparedStatement pstmt =
		 * connection.prepareStatement(emirateQuery)) { pstmt.setInt(1, groupId);
		 * pstmt.setString(2, "%" + emirateName + "%"); try (ResultSet rs =
		 * pstmt.executeQuery()) { if (rs.next()) { emirateId = rs.getInt("id"); } } }
		 * 
		 * // Step 4: Get UW Rules Schema Name based on Group ID, Emirate ID, and TPA
		 * Name String uwRulesQuery = " SELECT uw_rules_schema_name \r\n" +
		 * "		        FROM 7003_group_medical_aiaw_transactions.tpa \r\n" +
		 * "		        WHERE group_id = ? AND emirate_id = ? AND tpa_name LIKE ?";
		 * 
		 * String uwRulesSchemaName = ""; try (PreparedStatement pstmt =
		 * connection.prepareStatement(uwRulesQuery)) { pstmt.setInt(1, groupId);
		 * pstmt.setInt(2, emirateId); pstmt.setString(3, "%" + tpaName + "%"); try
		 * (ResultSet rs = pstmt.executeQuery()) { if (rs.next()) { uwRulesSchemaName =
		 * rs.getString("uw_rules_schema_name"); } } }
		 * 
		 * // Step 5: Generate the updated Nationality Loadings query if
		 * (!uwRulesSchemaName.isEmpty()) { String nationalityLoadingsQuery =
		 * "SELECT ng.group_name, n.nationality, ng.loading_discount " + "FROM " +
		 * uwRulesSchemaName + ".nationality_group_mapping gm " + "LEFT JOIN " +
		 * uwRulesSchemaName + ".nationality_group ng " +
		 * "ON ng.nationality_group_id = gm.nationality_group_id " + "LEFT JOIN " +
		 * uwRulesSchemaName + ".nationality n " +
		 * "ON n.nationality_id = gm.nationality_id " + "WHERE gm.version_id = " +
		 * activeVersionId + ";";
		 * 
		 * System.out.println("Updated Nationality Loadings Query:");
		 * System.out.println(nationalityLoadingsQuery); } else {
		 * System.out.println("No UW Rules Schema Name found."); }
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); } finally { scanner.close();
		 * }
		 */
// to get uw rules schema name 		
		/*
		 * // Database connection details String dbURL =
		 * "jdbc:mysql://aura-uat.cwfjz6cyloxy.me-south-1.rds.amazonaws.com:3306";
		 * String dbUsername = "admin"; String dbPassword =
		 * "zFs4upwKvvpRbbXcKSTf8La3MP4ymd";
		 * 
		 * // Variables to hold user inputs String emirateName, tpaName;
		 * 
		 * // Scanner to get user inputs Scanner scanner = new Scanner(System.in);
		 * 
		 * try (Connection connection = DriverManager.getConnection(dbURL, dbUsername,
		 * dbPassword)) { // Prompt the user for inputs
		 * System.out.print("Enter Emirate Name (e.g., Dubai): "); emirateName =
		 * scanner.nextLine();
		 * 
		 * System.out.print("Enter TPA Name (e.g., Mednet): "); tpaName =
		 * scanner.nextLine();
		 * 
		 * // Step 1: Get Active Version ID String activeVersionQuery =
		 * "WITH ActiveVersion AS (\r\n" + "		                SELECT pv.id\r\n" +
		 * "		                FROM 7003_group_medical_aiaw_transactions.product_versions pv\r\n"
		 * +
		 * "		                WHERE pv.status = 1 AND pv.effective_date <= CURDATE()\r\n"
		 * + "		                ORDER BY pv.effective_date DESC\r\n" +
		 * "		                LIMIT 1\r\n" + "		            )\r\n" +
		 * "		            SELECT id FROM ActiveVersion;";
		 * 
		 * int activeVersionId = 0; try (Statement stmt = connection.createStatement();
		 * ResultSet rs = stmt.executeQuery(activeVersionQuery)) { if (rs.next()) {
		 * activeVersionId = rs.getInt("id"); } }
		 * 
		 * // Step 2: Get Group ID based on Active Version String groupQuery =
		 * "SELECT id FROM 7003_group_medical_aiaw_transactions.group WHERE status = 1 AND version_id = ?"
		 * ; int groupId = 0; try (PreparedStatement pstmt =
		 * connection.prepareStatement(groupQuery)) { pstmt.setInt(1, activeVersionId);
		 * try (ResultSet rs = pstmt.executeQuery()) { if (rs.next()) { groupId =
		 * rs.getInt("id"); } } }
		 * 
		 * // Step 3: Get Emirate ID based on Group ID and Emirate Name String
		 * emirateQuery =
		 * "SELECT id FROM 7003_group_medical_aiaw_transactions.emirate WHERE group_id = ? AND emirate_name LIKE ?"
		 * ; int emirateId = 0; try (PreparedStatement pstmt =
		 * connection.prepareStatement(emirateQuery)) { pstmt.setInt(1, groupId);
		 * pstmt.setString(2, "%" + emirateName + "%"); try (ResultSet rs =
		 * pstmt.executeQuery()) { if (rs.next()) { emirateId = rs.getInt("id"); } } }
		 * 
		 * // Step 4: Get UW Rules Schema Name based on Group ID, Emirate ID, and TPA
		 * Name String uwRulesQuery = " SELECT uw_rules_schema_name \r\n" +
		 * "		        FROM 7003_group_medical_aiaw_transactions.tpa \r\n" +
		 * "		        WHERE group_id = ? AND emirate_id = ? AND tpa_name LIKE ?";
		 * 
		 * String uwRulesSchemaName = ""; try (PreparedStatement pstmt =
		 * connection.prepareStatement(uwRulesQuery)) { pstmt.setInt(1, groupId);
		 * pstmt.setInt(2, emirateId); pstmt.setString(3, "%" + tpaName + "%"); try
		 * (ResultSet rs = pstmt.executeQuery()) { if (rs.next()) { uwRulesSchemaName =
		 * rs.getString("uw_rules_schema_name"); } } }
		 * 
		 * // Output the UW Rules Schema Name if (!uwRulesSchemaName.isEmpty()) {
		 * System.out.println("UW Rules Schema Name: " + uwRulesSchemaName); } else {
		 * System.out.println("No UW Rules Schema Name found."); }
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); } finally { scanner.close();
		 * }
		 */

		/*
		 * // Database connection details String dbURL =
		 * "jdbc:mysql://aura-uat.cwfjz6cyloxy.me-south-1.rds.amazonaws.com:3306";
		 * String dbUsername = "admin"; String dbPassword =
		 * "zFs4upwKvvpRbbXcKSTf8La3MP4ymd";
		 * 
		 * // Variables to hold user inputs String emirateName, tpaName, planName;
		 * 
		 * // Scanner to get user inputs Scanner scanner = new Scanner(System.in);
		 * 
		 * try (Connection connection = DriverManager.getConnection(dbURL, dbUsername,
		 * dbPassword)) { // Prompt the user for inputs
		 * System.out.print("Enter Emirate Name (e.g., Dubai): "); emirateName =
		 * scanner.nextLine();
		 * 
		 * System.out.print("Enter TPA Name (e.g., Mednet): "); tpaName =
		 * scanner.nextLine();
		 * 
		 * System.out.print("Enter Plan Name (e.g., Gold): "); planName =
		 * scanner.nextLine();
		 * 
		 * // Step 1: Get Active Version ID String activeVersionQuery =
		 * " WITH ActiveVersion AS (\r\n" + "                    SELECT pv.id\r\n" +
		 * "                    FROM 7003_group_medical_aiaw_transactions.product_versions pv\r\n"
		 * +
		 * "                    WHERE pv.status = 1 AND pv.effective_date <= CURDATE()\r\n"
		 * + "                    ORDER BY pv.effective_date DESC\r\n" +
		 * "                    LIMIT 1\r\n" + "                )\r\n" +
		 * "                SELECT id FROM ActiveVersion;";
		 * 
		 * int activeVersionId = 0; try (Statement stmt = connection.createStatement();
		 * ResultSet rs = stmt.executeQuery(activeVersionQuery)) { if (rs.next()) {
		 * activeVersionId = rs.getInt("id"); } }
		 * 
		 * // Step 2: Get Group ID based on Active Version String groupQuery =
		 * "SELECT id FROM 7003_group_medical_aiaw_transactions.group WHERE status = 1 AND version_id = ?"
		 * ; int groupId = 0; try (PreparedStatement pstmt =
		 * connection.prepareStatement(groupQuery)) { pstmt.setInt(1, activeVersionId);
		 * try (ResultSet rs = pstmt.executeQuery()) { if (rs.next()) { groupId =
		 * rs.getInt("id"); } } }
		 * 
		 * // Step 3: Get Emirate ID based on Group ID and Emirate Name String
		 * emirateQuery =
		 * "SELECT id FROM 7003_group_medical_aiaw_transactions.emirate WHERE group_id = ? AND emirate_name LIKE ?"
		 * ; int emirateId = 0; try (PreparedStatement pstmt =
		 * connection.prepareStatement(emirateQuery)) { pstmt.setInt(1, groupId);
		 * pstmt.setString(2, "%" + emirateName + "%"); try (ResultSet rs =
		 * pstmt.executeQuery()) { if (rs.next()) { emirateId = rs.getInt("id"); } } }
		 * 
		 * // Step 4: Get TPA ID based on Group ID, Emirate ID, and TPA Name String
		 * tpaQuery =
		 * "SELECT id FROM 7003_group_medical_aiaw_transactions.tpa WHERE group_id = ? AND emirate_id = ? AND tpa_name LIKE ?"
		 * ; int tpaId = 0; try (PreparedStatement pstmt =
		 * connection.prepareStatement(tpaQuery)) { pstmt.setInt(1, groupId);
		 * pstmt.setInt(2, emirateId); pstmt.setString(3, "%" + tpaName + "%"); try
		 * (ResultSet rs = pstmt.executeQuery()) { if (rs.next()) { tpaId =
		 * rs.getInt("id"); } } }
		 * 
		 * // Step 5: Get Plan ID based on TPA ID and Plan Name String planQuery =
		 * "SELECT id FROM 7003_group_medical_aiaw_transactions.plan WHERE tpa_id = ? AND Plan_name LIKE ?"
		 * ; int planId = 0; try (PreparedStatement pstmt =
		 * connection.prepareStatement(planQuery)) { pstmt.setInt(1, tpaId);
		 * pstmt.setString(2, "%" + planName + "%"); try (ResultSet rs =
		 * pstmt.executeQuery()) { if (rs.next()) { planId = rs.getInt("id"); } } }
		 * 
		 * // Step 6: Fetch Premium Details String premiumQuery =
		 * "SELECT * FROM 7003_group_medical_aiaw_transactions.premium WHERE plan_id = ? AND status = 1"
		 * ;
		 * 
		 * String valueOf = String.valueOf(planId);
		 * 
		 * premiumQuery = premiumQuery.replace("?", valueOf);
		 * 
		 * System.out.println(premiumQuery);
		 * 
		 * }
		 */
		/*
		 * // base premium
		 * fetchDataFromDatabase(calculatorData().getProperty("dbUrlUAT"),
		 * calculatorData().getProperty("dbUsernameUAT"),
		 * calculatorData().getProperty("dbPasswordUAT"),
		 * calculatorData().getProperty("queryAIAWBasePremium"),
		 * calculatorData().getProperty("excelCalculatorFilePath"), 0);
		 * 
		 * // benefits fetchDataFromDatabase(calculatorData().getProperty("dbUrlUAT"),
		 * calculatorData().getProperty("dbUsernameUAT"),
		 * calculatorData().getProperty("dbPasswordUAT"),
		 * "SELECT * FROM 7003_group_medical_aiaw_transactions.benefits_table where client_reference_number like 'TEST-ABC-0125-1-00020'"
		 * , calculatorData().getProperty("excelCalculatorFilePath"), 1);
		 * 
		 * // nationality loadings
		 * fetchDataFromDatabase(calculatorData().getProperty("dbUrlUAT"),
		 * calculatorData().getProperty("dbUsernameUAT"),
		 * calculatorData().getProperty("dbPasswordUAT"),
		 * calculatorData().getProperty("queryAIAWNationalityLoadings"),
		 * calculatorData().getProperty("excelCalculatorFilePath"), 4);
		 * 
		 * // industry loading
		 * fetchDataFromDatabase(calculatorData().getProperty("dbUrlUAT"),
		 * calculatorData().getProperty("dbUsernameUAT"),
		 * calculatorData().getProperty("dbPasswordUAT"),
		 * calculatorData().getProperty("queryAIAWIndustryLoadings"),
		 * calculatorData().getProperty("excelCalculatorFilePath"), 5);
		 * 
		 * // previous insurer loading
		 * fetchDataFromDatabase(calculatorData().getProperty("dbUrlUAT"),
		 * calculatorData().getProperty("dbUsernameUAT"),
		 * calculatorData().getProperty("dbPasswordUAT"),
		 * calculatorData().getProperty("queryAIAWPreviousInsurerLoadings"),
		 * calculatorData().getProperty("excelCalculatorFilePath"), 6);
		 * 
		 * // Commission fetchDataFromDatabase(calculatorData().getProperty("dbUrlUAT"),
		 * calculatorData().getProperty("dbUsernameUAT"),
		 * calculatorData().getProperty("dbPasswordUAT"),
		 * calculatorData().getProperty("queryAIAWCommission"),
		 * calculatorData().getProperty("excelCalculatorFilePath"), 9);
		 * 
		 * // Census
		 * toFetchCensusSheet("C:\\Users\\impelox-pc-048\\Desktop\\censuses sheet\\census_ab_automation.xlsx"
		 * ,
		 * "C:\\Users\\impelox-pc-048\\Downloads\\Sme\\Sme\\target\\ExcelCalculatorForDistributor\\Arshad New Calculator.xlsx"
		 * , 0, 10);
		 */
	}
}
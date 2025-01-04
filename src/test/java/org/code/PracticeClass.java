package org.code;

import java.sql.SQLException;

import org.sme.utilities.BaseClass;

public class PracticeClass extends BaseClass {

	public static void main(String[] args) throws SQLException {
	
		//base premium
		fetchDataFromDatabase("jdbc:mysql://aura-uat.cwfjz6cyloxy.me-south-1.rds.amazonaws.com:3306", "admin",
				"zFs4upwKvvpRbbXcKSTf8La3MP4ymd",
				"SELECT * FROM  7003_group_medical_aiaw_transactions.premium where plan_id=2 and status=1;",
				"C:\\Users\\impelox-pc-048\\eclipse-workspace\\Demo\\target\\Arshad AIAW Practice - Copy.xlsx",0);
		
		
	
		
		
		
		
	}

}

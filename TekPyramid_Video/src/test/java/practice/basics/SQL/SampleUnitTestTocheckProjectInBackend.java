package practice.basics.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleUnitTestTocheckProjectInBackend {


	@Test
	public void projectCheckTest() throws Throwable {
		
		String expPatientName = "Velu";
		boolean flag = false;
		
		//Step :1 -- load / register the database driver
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		//Step : 2 -- connect to database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "root");
		System.out.println("Connection Done");
		//Step : 3 -- Create sql statements
		Statement statement = con.createStatement();
		//Step : 4 -- execute select query and get result
		
//		int rusSet = statement.executeUpdate("INSERT INTO patients VALUES(7,007,'Velu',45,'velu123@gmail.com',1236547896);");
//		System.out.println(rusSet);
		ResultSet rusSet = statement.executeQuery("select * from patients");
		while(rusSet.next()) {
			String actPatientName = rusSet.getString(3);
			if(actPatientName.equals(expPatientName)) {
				flag = true;
				System.out.println(expPatientName+" is available");
			}
		}
		if(flag==false) {
			System.out.println(expPatientName+" is not available");
			Assert.fail();	
			
		}
		//Step : 5 -- close the connection
		con.close();
	}
}

package Vtiger_Basic_TestScripts;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class createContactWithOrgTest {

	public static void main(String[] args) throws Throwable {

		FileInputStream pfis = new FileInputStream("C:\\Users\\FloweR KinG\\OneDrive\\Desktop\\TekPyramid\\AdvSele\\CommonData.properties");
		Properties prop = new Properties();
		prop.load(pfis);

		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");

		// To read data from Excel file
		FileInputStream efis = new FileInputStream("C:\\Users\\FloweR KinG\\OneDrive\\Desktop\\TekPyramid\\AdvSele\\testData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);

		Random r = new Random();
		int random = r.nextInt(1000);
		String ORGNAME = wb.getSheet("contact").getRow(7).getCell(2).toString() + random;
		String LASTNAME = wb.getSheet("contact").getRow(7).getCell(3).toString();


		// AutomationScript
		//Step 1 : Launch Browser(Polymorphism)
		WebDriver driver = null;
		if(BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		//Step 2 : Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		//Creation of ORGANIZATION		
		//Step 3 : Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();

		//Step 4 : Click on create organization lookup image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		//Step 5 : Create Organization with mandetory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME);

		//Step 6 : Save and verify the org name
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		String headerMsg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerMsg.contains(ORGNAME)) {
			System.out.println(headerMsg +" ---Passed");
		}else {
			System.out.println(headerMsg +" ---Failed");
		}

		////Step 7 : Navigate to contact link
		//Creation of CONTACT			
		driver.findElement(By.linkText("Contacts")).click();

		//Step 8 : Click on create contact lookup image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		//Step 9 : Enter LASTNAME
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		//Start date and End Date
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String startDate = sdf.format(date);

		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate = sdf.format(cal.getTime());

		//Step 10 : Enter START DATE and END DATE
		driver.findElement(By.id("jscal_field_support_start_date")).clear();
		driver.findElement(By.id("jscal_field_support_start_date")).sendKeys(startDate);

		driver.findElement(By.id("jscal_field_support_end_date")).clear();
		driver.findElement(By.id("jscal_field_support_end_date")).sendKeys(endDate);

		//Step 11 : Click on '+'symbol present beside organization textfield
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();

		//Step 12 : Switch to Child Window
		Set<String> windowIds = driver.getWindowHandles();
		Iterator<String> iterator = windowIds.iterator();
		while(iterator.hasNext()) {
			String childWindowId = iterator.next();
			driver.switchTo().window(childWindowId);

			String actualUrl = driver.getCurrentUrl();
			if(actualUrl.contains("module=Accounts")) {

			}
		}

		//Step 13 : Enter ORG name and click search then click on unique org name link
		driver.findElement(By.name("search_text")).sendKeys(ORGNAME);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();

		//Step 13 : Switch to Parent Window
		Set<String> windowIds1 = driver.getWindowHandles();
		Iterator<String> iterator1 = windowIds1.iterator();
		while(iterator1.hasNext()) {
			String childWindowId = iterator1.next();
			driver.switchTo().window(childWindowId);

			String actualUrl = driver.getCurrentUrl();
			if(actualUrl.contains("module=Contacts")) {

			}
		}
         
		//Step 14 : Click on Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 15 : verify Org name in TF
		String orgNameTF = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if (orgNameTF.contains(ORGNAME)) {
			System.out.println("Org textfield have"+orgNameTF +" ---Passed");
		}else {
			System.out.println("Org textfield not have"+orgNameTF +" ---Failed");
		}
		
		//Step 16 : verify header
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname +" ---Passed");
		}else {
			System.out.println(lastname +" ---Failed");
		}

		//To verify lastname TF
		String LastnameTF = driver.findElement(By.id("mouseArea_Last Name")).getText();
		if (LastnameTF.contains(LASTNAME)) {
			System.out.println("Lastname TF have"+LastnameTF +" ---Passed");
		}else {
			System.out.println("Lastname TF have"+LastnameTF +" ---Failed");
		}

		//To Start verify date
		String startDateTF = driver.findElement(By.id("mouseArea_Support Start Date")).getText();
		if (startDateTF.contains(startDate)) {
			System.out.println("start Date TF have"+startDateTF +" ---Passed");
		}else {
			System.out.println("start Date TF have"+startDateTF +" ---Failed");
		}

		//To End verify date
		String endDateTF = driver.findElement(By.id("mouseArea_Support End Date")).getText();
		if (endDateTF.contains(endDate)) {
			System.out.println("End Date TF have "+endDateTF +" ---Passed");
		}else {
			System.out.println("End Date TF have "+endDateTF +" ---Failed");
		}

		//Step 7 : Logout of the Application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		//Step 8 : Close the browser
		driver.quit();	
	}

}

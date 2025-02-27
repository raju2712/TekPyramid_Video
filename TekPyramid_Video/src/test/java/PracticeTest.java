import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class PracticeTest {
	/**
	 * 
	 * @param args
	 * @throws Throwable 
	 * @throws IOException
	 */
	@Test
	public void createOrgTest(XmlTest test) throws Throwable  {

		String URL = test.getParameter("url");
		String  BROWSER= test.getParameter("browser");
		String  USERNAME=test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		
		FileInputStream efis = new FileInputStream("C:\\Users\\FloweR KinG\\OneDrive\\Desktop\\TekPyramid\\AdvSele\\Book1.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String ORGNAME = wb.getSheet("Sheet1").getRow(1).getCell(2).toString();

		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}else{
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		Random r = new Random();
		int random = r.nextInt(1000);
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+ random);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		//String orgname = driver.findElement(By.partialLinkText("Information")).getText();
		if(orgname.contains(ORGNAME)) {
			System.out.println("Verification of Organization name in header of the message is Passed");
		}else {
			System.out.println("Verification of Organization name in header of the message is Failed");
		}

		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a = new Actions(driver);
		a.moveToElement(logoutEle).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();
	}
}

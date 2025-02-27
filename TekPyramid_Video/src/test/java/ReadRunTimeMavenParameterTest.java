import org.testng.annotations.Test;

public class ReadRunTimeMavenParameterTest {

	@Test
	public void runTimeParameterTest() {
		String URL = System.getProperty("url");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		String BROWSER = System.getProperty("browser");

		
		System.out.println("Env Data====>URL====>"+URL);
		System.out.println("Username Data====>URL====>"+USERNAME);
		System.out.println("Password Data====>URL====>"+PASSWORD);
		System.out.println("Browser Data====>URL====>"+BROWSER);
	}

}

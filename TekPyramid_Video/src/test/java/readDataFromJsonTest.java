import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readDataFromJsonTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("C:\\Users\\FloweR KinG\\OneDrive\\Desktop\\TekPyramid\\AdvSele\\appCommonData.json"));
		
		JSONObject map =(JSONObject) obj;
		System.out.println(map.get("url"));
	}

}


public class GeneratealphaNumericRandomData {

	public static void main(String[] args) {

		int n = 20;
		String AlphaNumeric ="ABCvwDEFGHSTstUVWXYZ127890abcdefIJKLMPQRghijklmnop3456qruxyzNO";
				
		StringBuilder sb = new StringBuilder();
		for(int i=0 ; i<n ;i++) {
			int index = (int)(AlphaNumeric.length()*Math.random());
			sb.append(AlphaNumeric.charAt(index));
		}
		System.out.println(sb);
	}

}

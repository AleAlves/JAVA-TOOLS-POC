package reader;

import java.io.BufferedReader;
import java.io.FileReader;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Aleson\\Documents\\gapzero-android\\app\\src\\main\\res\\drawable\\btn_default_holo_dark_red.xml"));
			try {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
					sb.append(line);
					System.out.print(line);
					sb.append(System.lineSeparator());
					line = br.readLine();
				}
				String everything = sb.toString();
				System.out.print(everything);
			} finally {
				br.close();
			}
		} catch (Exception e) {

		}
	}

}

package TagFinder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		int linhas = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(
					"/Users/Santander/Projetos/Release17/gapzero-android/app/src/main/java/com/santander/app/menu/repository/MenuService.java"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
				if (line != null && line.length() > 0) {
					linhas++;
					System.out.println(linhas + " - " + line);
				}
			}
			String everything = sb.toString();
		} finally {
			br.close();
		}
	}

}

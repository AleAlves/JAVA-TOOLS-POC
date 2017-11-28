package TagFinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetTag {

	public GetTag() {
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getTags(String filePath){
		File file = new File(filePath);
		ArrayList<String> tags = new ArrayList<>();
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				tags.add(sc.nextLine().toLowerCase());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tags;
	}
}
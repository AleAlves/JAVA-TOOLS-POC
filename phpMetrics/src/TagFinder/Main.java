package TagFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		int medthods = 0;
		int variables = 0;
		int lines = 0;
		
		String filePath = ("/Users/Santander/file.txt");
		ArrayList<String> tagList = new ArrayList<>();
		String result ="";
		GetTag tags = new GetTag();
		tagList.addAll(tags.getTags(filePath));
		Directory directory = new Directory();
		List<File> javaDir = directory.walkDir("/Users/Santander/Desktop/codeigniter/system");
		Resource resourceList = new Resource();
		result = resourceList.searchTag(tagList, javaDir);
		System.out.println(result);
	}

}

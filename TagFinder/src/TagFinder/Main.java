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
		String filePath = ("/Users/Santander/tags.txt");
		ArrayList<String> tagList = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		GetTag tags = new GetTag();
		tagList.addAll(tags.getTags(filePath));
		Directory directory = new Directory();
		List<File> javaDir = directory.walkDir("/Users/Santander/projetos/release17/gapzero-android/app/src/main/java/");
		Resource resourceList = new Resource();
		result.addAll(resourceList.searchTag(tagList, javaDir, false));
		for(String r : result){
			System.out.println(r);
		}
	
	}

}

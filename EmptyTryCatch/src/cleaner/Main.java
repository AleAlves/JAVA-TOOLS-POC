package cleaner;

import java.awt.List;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import cleaner.domain.Directory;
import cleaner.domain.Resource;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Directory directoryClass = new Directory();

		directoryClass
				.setPath("C:/Users/Aleson/Documents/gapzero-android/app/src/main/java/com");

		FileUtils res = new FileUtils();
		FileUtils classes = new FileUtils();

		ArrayList<File> project = new ArrayList<File>();
		project.addAll(classes.walkDirs(directoryClass.getPath()));
		System.out.println("Classes: " + project.size());

		long start = System.currentTimeMillis();
		res.searchEmptyTryCatch(project);
		String time = new SimpleDateFormat("mm:ss:SSS").format((System
				.currentTimeMillis() - start));
		System.out.println("Time: " + time);
	}

}

package findlog;

import java.awt.List;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import findlog.domain.Directory;
import findlog.domain.Image;
import findlog.domain.Resource;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Directory classes = new Directory();

		classes.addPath("C:\\Users\\Aleson\\Documents\\gapzero-android\\app\\src\\main\\java\\com\\santander\\app");

		FileUtil classesUtil = new FileUtil();
		FileUtil logEUtil = new FileUtil();
		
		ArrayList<File> projectJavaFiles = new ArrayList<File>();

		ArrayList<Resource> resJava = new ArrayList<Resource>();

		long start = System.currentTimeMillis();

		for (String s : classes.getPath()) {
			projectJavaFiles.addAll(classesUtil.walkDirsJava(s));
		}
		System.out.println("Classes: " + projectJavaFiles.size());


		for (File file : projectJavaFiles) {
			Resource resource = new Resource();
			resource.setFile(file);
			resource.setName(file.getName());
			resource.setNotFound(true);
			resource.setPath(file.getAbsolutePath());
			resource.setResourceType(null);
			resJava.add(resource);
		}

		ArrayList<Resource> resources = new ArrayList<>();
		resources.addAll(resJava);
		ArrayList<Resource> uselessFiles = new ArrayList<>();
		System.out.println("Searching for useless files: ");
		uselessFiles = logEUtil.searchLogE(resources);
		System.out.println("....Done");
		System.out.println(uselessFiles.size());
		String time = new SimpleDateFormat("mm:ss:SSS").format((System.currentTimeMillis() - start));
		System.out.println("Time: " + time);
	}

}

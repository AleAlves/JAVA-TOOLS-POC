package replace;

import java.awt.List;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import replace.domain.Directory;
import replace.domain.Resource;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Directory directoryClass = new Directory();
//		"C:\\Users\\Aleson\\Documents\\GitHub\\NASA-APOD-app\\app\\src\\main\\java\\com\\aleson\\example\\nasaapodapp"
		directoryClass.setPath("C:\\Users\\Aleson\\Documents\\gapzero-android\\app\\src\\main\\java\\com\\santander\\app");
		FileUtils res = new FileUtils();
		FileUtils classes = new FileUtils();

		ArrayList<File> project = new ArrayList<File>();
		project.addAll(classes.walkDirs(directoryClass.getPath()));
		System.out.println("Classes: " + project.size());

		long start = System.currentTimeMillis();
		res.findUndeclaredModifier(project);
		String time = new SimpleDateFormat("mm:ss:SSS").format((System
				.currentTimeMillis() - start));
		System.out.println("Time: " + time);
	}

}

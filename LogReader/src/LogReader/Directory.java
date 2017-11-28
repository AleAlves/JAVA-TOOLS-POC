package LogReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class Directory implements Files {
	static List<File> files = new ArrayList();
	
	public List<File> walkDir(String path) {

		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return null;

		for (File f : list) {
			if (f.isDirectory()) {
				walkDir(f.getAbsolutePath());
			} else {
				File file = new File(f.getAbsoluteFile().toString());
				if (file.isFile()) {
					files.add(file);
				}
			}
		}
		return files;
	}
}

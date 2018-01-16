package findlog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import findlog.domain.Resource;

public class FileUtil {

	ArrayList<File> files = new ArrayList();

	public ArrayList<File> walkDirsJava(String path) {
		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return null;

		for (File f : list) {
			if (f.isDirectory()) {
				walkDirsJava(f.getAbsolutePath());
			} else {
				File file = new File(f.getAbsoluteFile().toString());
				if (file.isFile()) {
					files.add(file);
				}
			}
		}
		return files;
	}

	public ArrayList<File> walkDirs(String path) {
		if (files != null && !files.isEmpty()) {
			files.clear();
		}
		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return null;

		for (File f : list) {
			if (f.isDirectory()) {
				walkDirs(f.getAbsolutePath());
			} else {
				File file = new File(f.getAbsoluteFile().toString());
				if (file.isFile()) {
					files.add(file);
				}
			}
		}
		return files;
	}

	ArrayList<Resource> filesName = new ArrayList();

	public ArrayList<Resource> getResource(String path) {

		File root = new File(path);
		File[] list = root.listFiles();

		if (list == null)
			return null;

		for (File f : list) {
			if (f.isDirectory()) {
				getResource(f.getAbsolutePath());
			} else {
				String fileName = f.getName().toString();
				try {
					fileName = fileName.substring(0, fileName.lastIndexOf('.'));
				} catch (Exception e) {
					System.out.println(e);
				}
				if (fileName != null) {
					Resource resource = new Resource();
					resource.setPath(f.getAbsolutePath());
					resource.setName(fileName);
					resource.setNotFound(false);
					resource.setFile(f);
					filesName.add(resource);
				}
			}
		}
		return filesName;
	}

	public ArrayList<Resource> searchLogE(List<Resource> resource) {

		int i = 0;
		int checkedFiles = 0;
		int notFoundCount = 0;

		ArrayList<Resource> resourceFiles = new ArrayList<Resource>();
		for (Resource resourceFile : resource) {
			boolean found = false;
			int linha = 0;
			checkedFiles++;
			System.out.println(checkedFiles + "/" + resource.size());

			if (!found) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(resourceFile.getFile()));
					try {
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();

						while (line != null) {
							linha++;
							line = br.readLine();
							if (line.contains("Log.e(\"Error\", e.getMessage());")) {
								found = true;
								insertLog(linha, resourceFile.getFile());
								break;
							}
						}
						String everything = sb.toString();
						System.out.print(everything);
					} finally {
						br.close();
					}
				} catch (Exception e) {

				}
			}
			if (!found) {
				resourceFiles.add(resourceFile);
				notFoundCount++;
			}
		}
		
		for(Resource r : resourceFiles) {
			System.out.println(r.getName());
		}
		
		System.out.println("Checked files: " + checkedFiles + " Not found: " + notFoundCount);
		return resourceFiles;
	}

	private void insertLog(int line, File file) {
		try {
			Path path = Paths.get(file.getAbsolutePath());
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

			int position = line;
			String extraLine = "			Log.e(\"Error\", e.toString());";
			lines.remove(line);
			lines.add(position, extraLine);
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}

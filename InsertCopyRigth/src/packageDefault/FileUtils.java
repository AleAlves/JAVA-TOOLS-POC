package packageDefault;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import packageDefault.domain.Resource;

public class FileUtils {

	ArrayList<File> files = new ArrayList();

	public ArrayList<File> walkDirs(String path) {

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

	public ArrayList<Resource> search(List<Resource> resource, List<File> dir) {

		int i = 0;
		int checkedFiles = 0;
		long lines = 0;

		ArrayList<Resource> resourceFiles = new ArrayList<Resource>();

		Scanner input = new Scanner(System.in);
		for (int j = 0; j < resource.size(); j++) {

			resource.get(j).setNotFound(true);

			for (int k = 0; k < dir.size(); k++) {
				try {
					input = new Scanner(dir.get(k));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				lines = 0;
				while (input.hasNextLine()) {
					String line = input.nextLine();
					// System.out.println("Arquivo: "+dir.get(k).getName()+" Linha: "+
					// line);
					if (line.contains(resource.get(j).getName())) {
						resource.get(j).setNotFound(false);
						// System.out.println(resource.get(j).getName());
						break;
					}
					lines++;
				}
			}
			resourceFiles.add(resource.get(j));
			input.close();
			checkedFiles++;
			System.out.println("Checking files: " + checkedFiles + "/" + resource.size());
		}
		return resourceFiles;
	}

	public ArrayList<Resource> insertCopyRight(List<File> dir) {

		int i = 0;
		int checkedFiles = 0;
		int doesNotHaveCR = 0;
		boolean hasCR = false;
		long lines = 0;

		ArrayList<Resource> resourceFiles = new ArrayList<Resource>();
		ArrayList<File> filesToAddCR = new ArrayList<File>();

		Scanner input = new Scanner(System.in);

		for (int k = 0; k < dir.size(); k++) {
			try {
				input = new Scanner(dir.get(k));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			lines = 0;
			hasCR = false;
			while (input.hasNextLine()) {
				String line = input.nextLine();
				if (line.contains("Copyright")) {
					// System.out.println(dir.get(k).getName());
					hasCR = true;
//					removeHeader(dir.get(k));
					break;
				}
				lines++;
			}
			if (!hasCR) {
				doesNotHaveCR++;
				filesToAddCR.add(dir.get(k));
				System.out.println(dir.get(k).getName());
				 insertCopyright(dir.get(k));
//				removeHeader(dir.get(k));
			}
			checkedFiles++;
		}
		input.close();
		System.out.println("Checked files: " + checkedFiles + " Does not have Copy right: " + doesNotHaveCR);
		return resourceFiles;
	}

	private void insertCopyright(File file) {
		try {
			Path path = Paths.get(file.getAbsolutePath());
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			int position = 0;
			String extraLine = "\n// Copyright (c) 2018 SANTANDER BRASIL, All Rights Reserved.\n";
			lines.add(position, extraLine);
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	//
	// private void insertCopyright(int line, File file) {
	// try {
	// Path path = Paths.get(file.getAbsolutePath());
	// List<String> lines = Files.readAllLines(path,
	// StandardCharsets.UTF_8);
	//
	// int position = line;
	// String extraLine =
	// "* Copyright (c) 2018 SANTANDER BRASIL. All Rights Reserved.";
	//
	// lines.add(position, extraLine);
	// Files.write(path, lines, StandardCharsets.UTF_8);
	// } catch (Exception e) {
	// System.out.print(e.getMessage());
	// }
	// }

	private void replaceHeader(File file) {
		Scanner input = new Scanner(System.in);
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int lines = 0;
		while (input.hasNextLine()) {
			String line = input.nextLine();
			lines++;
			if (line.contains("Created by")) {
				// insertCopyright(lines, file);
				break;
			}
		}
	}

	private void removeHeader(File file) {
		try {
			Path path = Paths.get(file.getAbsolutePath());
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
			lines.remove(0);
			Files.write(path, lines, StandardCharsets.UTF_8);
			insertCopyright(file);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}

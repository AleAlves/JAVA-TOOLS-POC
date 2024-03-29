package empty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import empty.domain.Resource;

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

	public ArrayList<Resource> searchEmptyMethods(List<File> dir) {

		int i = 0;
		int checkedFiles = 0;
		int replaced = 0;
		int emptyMethods = 0;
		boolean hasCR = false;
		boolean curvyBrackets = false;
		long lines = 0;

		ArrayList<Resource> resourceFiles = new ArrayList<Resource>();
		ArrayList<File> filesToAddCR = new ArrayList<File>();

		Scanner input = new Scanner(System.in);

		for (int k = 0; k < dir.size(); k++) {
			if (!dir.get(k).getAbsolutePath().toString().contains("seguros")) {

				lines = 0;
				hasCR = false;
				curvyBrackets = false;
				String codigo = "";
				try {
					BufferedReader br = new BufferedReader(new FileReader(dir.get(k)));
					try {
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();
						while (line != null) {
							line = br.readLine();
							codigo = codigo.concat(line);
						}
						String everything = sb.toString();
						System.out.print(everything);
					} finally {
						br.close();
					}
				} catch (Exception e) {

				}

				int lastChar = 0;
				int localOcurence = 0;
				boolean empty = true;
				char[] codigoCharArray = codigo.toCharArray();
				for (int l = 0; l < codigoCharArray.length; l++) {
					// System.out.print((int) codigoCharArray[l]);
					if (lastChar == 123) {
						for (int m = l; m < codigoCharArray.length; m++) {
							char curentChar = codigoCharArray[m];
							if ((int) curentChar == 9 || (int) curentChar == 32) {
								curentChar = codigoCharArray[m + 1];
							} else if ((int) curentChar == 125) {
								localOcurence++;
								System.out.println(dir.get(k).getName() + " ocurence:" + localOcurence);
								// insertLog((int)lines,dir.get(k));
								break;
							} else {
								break;
							}
						}
					}
					lastChar = (int) codigoCharArray[l];
				}
				checkedFiles++;
			}
		}
		System.out.println("Empty methods:" + emptyMethods);
		input.close();
		return resourceFiles;
	}

	private void insertLog(int line, File file) {
		try {
			Path path = Paths.get(file.getAbsolutePath());
			List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

			int position = line;
			String extraLine = "*";
			lines.add(position, extraLine);
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
}

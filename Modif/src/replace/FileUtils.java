package replace;

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

import replace.domain.Resource;

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

	public ArrayList<Resource> findUndeclaredModifier(List<File> dir) {

		int i = 0;
		int checkedFiles = 0;
		int replaced = 0;
		int erros = 0;
		int printStackTraceCount = 0;
		long lines = 0;

		ArrayList<Resource> resourceFiles = new ArrayList<Resource>();
		ArrayList<File> filesToAddCR = new ArrayList<File>();

		for (int k = 0; k < dir.size(); k++) {
			lines = 0;
			if (!dir.get(k).getAbsolutePath().toString().contains("seguros")) {
				try {
					BufferedReader br = new BufferedReader(new FileReader(dir.get(k)));
					try {
						StringBuilder sb = new StringBuilder();
						String line = br.readLine();
						String className = "";
						boolean canRead = false;
						while (line != null) {
							lines++;
							line = br.readLine();
							if(canRead) {
								if (className != "" && line.contains(className) || line.contains("onCreate")) {
									System.out.println("-- break: " + className);
									canRead = false;
									break;
								}
								System.out.println("Sim");
								if ( 
										!line.contains("import")
										&& !line.contains("package") 
										&& !line.contains("return")
										&& !line.contains("interface") 
										&& !line.contains("this.")
										&& !line.contains("implements")
										&& !line.contains("SuppressLint"))
									if (line.contains("String") 
											|| line.contains("int") 
											|| line.contains("double")
											|| line.contains("float") 
											|| line.contains("boolean")
											|| line.contains("char")
											|| line.contains("Bitmap")
											|| line.contains("TextView")
											|| line.contains("LinearLayout")
											|| line.contains("RelativeLayout")
											|| line.contains("ImageView")) {
										if (!line.contains("private") 
												&& !line.contains("protected")
												&& !line.contains("public")) {
											System.out.println("	" + line);
											insertModifier((int)lines, line , dir.get(k));
											erros ++;
										}
									}
							}
							if (line.contains("class") || !line.contains("Activity") && !canRead) {
								String s = line;
								String[] arr = s.split(" ");
								boolean classNameFound = false;
								for (String ss : arr) {
									if (classNameFound) {
										className = ss;
										System.out.println("Class: " + className);
										break;
									}
									if (ss.equalsIgnoreCase("class")) {
										classNameFound = true;
										canRead = true;
									}
								}
							}
						}
						String everything = sb.toString();
					} finally {
						br.close();
					}
				} catch (Exception e) {

				}
			}
		}
		System.out.println(" - Erros" + erros);
		System.out.println("Checked files: " + checkedFiles + " replaced: " + printStackTraceCount);
		return resourceFiles;
	}

	private void insertModifier(int line, String oldLine, File file) {
		 try {
		 Path path = Paths.get(file.getAbsolutePath());
		 List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		
		 int position = line;
		 String extraLine = "protected " + oldLine;
		 lines.remove(line);
		 lines.add(position, extraLine);
		 Files.write(path, lines, StandardCharsets.UTF_8);
		 } catch (Exception e) {
			 System.out.print(e.getMessage());
		 }
	}
}

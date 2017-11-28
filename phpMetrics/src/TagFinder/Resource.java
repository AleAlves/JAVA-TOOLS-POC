package TagFinder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Resource implements Files {
	static List<File> files = new ArrayList();
	
	int medthods = 0;
	int variables = 0;
	int lines = 0;
	
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
	
	public String searchTag(List<String> tagList, List<File> dir) {

        int i = 0;
        int occorence = 0;
        long linesCount = 0;
        
        String aux = null;
        List<String> foundtags = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        for (int j = 0; j < tagList.size(); j++) {
        	
            for (int k = 0; k < dir.size(); k++) {
                try {
                    input = new Scanner( dir.get(k));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                linesCount = 1;
                while (input.hasNextLine()) {
                    String line = input.nextLine().toLowerCase();
                    if(line.contains("function")){
        				medthods++;
        			}
        			if(line.contains("$")){
        				variables++;
        			}
        			lines++;
                    linesCount++;
                }
            }
            input.close();
        }
        return "Methods: "+ medthods +" Variables:"+ variables +" Lines:"+lines;
    }
	
	public List<File> Search(List<File> resource, List<File> dir) {

        int i = 0;
        int occorence = 0;
        long linha = 0;
        String aux = null;
        List<File> foundFiles = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        for (int j = 0; j < resource.size(); j++) {
        	
            for (int k = 0; k < dir.size(); k++) {
                try {
                    input = new Scanner( dir.get(k));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                linha = 0;
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    aux = resource.get(j).getName();
                    if (line.contains(stripExtension( resource.get(j).getName()))) {
						foundFiles.add(resource.get(j));
						break;
                    }
                    linha++;
                }
            }
            input.close();
        }
        return foundFiles;
    }
	
	static String stripExtension(String str) {
        if (str == null)
            return null;
        int pos = str.lastIndexOf(".");
        if (pos == -1)
            return str;
        return str.substring(0, pos);
    }
	
	 public List<File> Verification(List<File> resources,List<File> foundFiles) {
	    	
	        List<File> list = new ArrayList();
	        for (File item: resources ) {
	            if(!foundFiles.contains(item)) {
	                list.add(item);
	            }
	        }
	        return list;
	    }
}

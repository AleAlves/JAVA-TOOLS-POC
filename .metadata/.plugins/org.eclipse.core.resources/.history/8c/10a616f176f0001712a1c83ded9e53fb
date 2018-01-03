package Remover;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileWalker {


    static List<File> classes = new ArrayList();
    static List<File> xml = new ArrayList();
    static List<File> images = new ArrayList();
    static List<File> foundFiles = new ArrayList<>();

    public List<File> walk(String path,int x) {

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null)
            return null;

        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath(),x);
            } else {
                File file = new File(f.getAbsoluteFile().toString());
                if (file.isFile()) {
                    switch(x){
                        case 0:
                            images.add(file);
                            break;
                        case 1:
                            classes.add(file);
                            break;
                        case 2:
                            xml.add(file);
                            break;
                    }
                }
            }
        }
        switch(x){
            case 0:
                return images;
            case 1:
                return classes;
            case 2:
                return xml;
        }
        return null;
    }

    public void Search(List<File> images, List<File> classes) {

        int size = images.size();
        int i = 0;
        int occurrence = 0;
        String aux = null;

        Scanner input = new Scanner(System.in);
        for (int j = 0; j < images.size(); j++) {
        	
            for (int k = 0; k < classes.size(); k++) {
                try {
                    input = new Scanner(classes.get(k));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                long linha = 0;
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    aux = images.get(j).getName();
                    if (line.contains(stripExtension(images.get(j).getName()))) {
                        System.out.println("found: " + images.get(j).getName()+ " at class: " + classes.get(k).getName()+" linha: "+linha);
                        foundFiles.add(images.get(j));
                        break;
                    }
                    linha++;
                }
            }
            input.close();
        }
    }
    
    public List<File> Verification() {
    	
        List<File> list = new ArrayList();
       
        for (File item: images ) {
            if(!foundFiles.contains(item)) {
                list.add(item);
            }
        }
        return list;
    }

    static String stripExtension(String str) {
        if (str == null)
            return null;
        int pos = str.lastIndexOf(".");
        if (pos == -1)
            return str;
        return str.substring(0, pos);
    }
}

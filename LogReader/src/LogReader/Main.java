package LogReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try{
		Directory directory = new Directory();
		File file = new File("/storage/emulated/0/LOG/log.txt");
		System.out.println(file.exists());
		}catch(Exception e){
			System.out.println(e);
		}
	}

}

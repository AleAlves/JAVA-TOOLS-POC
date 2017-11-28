package testeDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String d = "12/12/2017";
//		DateFormat date =  new SimpleDateFormat("MMM");
//		Date date1 = new Date();
//		System.out.println(date.format(date1));
		StringBuilder str = new StringBuilder("000010000");
		System.out.println("string = " + str);

	     // insert character value at offset 8
	
	     str.insert(str.length()-2, '.');

	     // prints StringBuilder after insertion
	     System.out.print("After insertion = ");
	     System.out.println(str.toString().replaceFirst ("^0*", ""));
		
	}

}

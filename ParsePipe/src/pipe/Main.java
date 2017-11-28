package pipe;

import java.awt.List;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String v1 = "1212121.9";
		String v2 = "21231231.3";
		double d1 = Double.parseDouble(v1);
		double d2 = Double.parseDouble(v2);
		System.out.println(d1+" " +v2);
		d1 = d1 + d2;
		NumberFormat n = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")); 
		v1 = n.format(d1);
		v1 = v1.replace("$", "$ ");
		System.out.println(v1);
		
		String myDateString = "2015-11-24 00:00:00.0";

		SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			System.out.println(outFormat.format(inFormat.parse(myDateString)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

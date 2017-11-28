package upto2decimal;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(formatTaxa("09500000"));
	}
	
	private static String formatTaxa(String indiceUnformatted) {

		try {
			if(!indiceUnformatted.equals("00000000")){
				return "0,00000";
            }
			else{
				DecimalFormatSymbols dfs = new DecimalFormatSymbols();
				dfs.setDecimalSeparator(',');
				dfs.setMinusSign('-');
				return new DecimalFormat("#,#####;-#,#####", dfs).format(Float.parseFloat(indiceUnformatted));
			}
		} catch (NumberFormatException e) {
			
		}

		return indiceUnformatted;
	}

}

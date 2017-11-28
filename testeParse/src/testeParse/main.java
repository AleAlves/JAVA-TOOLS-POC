package testeParse;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String value = "N";
		String number = "10.00";
		float y = 0.01f;
		String txt = "R$1.000.000,00";
//		System.out.println(value);
//		int x = Integer.parseInt(value);
//		System.out.println(x);
		if(value.equalsIgnoreCase("N"))
			System.out.println(001);
        else
        	System.out.println(002);
		txt = txt.replace(".","");
		txt = txt.substring(2,txt.length());
		txt = txt.replace(" ", "");
		float zx = new Float(number);
		zx++;
		System.out.println(zx+"%");
		System.out.println(y+"%");
		System.out.println(txt);
		String word = "R$950,10";
		word = word.replace(".", "");
		word = word.replace(",", "");
		word = word.replace("R$", "");
		word = word.replace(" ", "");
		System.out.println(word);
		
		String numb = "0.000003750223";
		float numbFloat = new Float(numb);
		System.out.println(numbFloat);
		
	}

}

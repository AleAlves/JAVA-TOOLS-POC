package testeURL;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
			verificar("0400");
	}
	
	private static void verificar(String cod){
		try{
			String out = new Scanner(new URL("http://www.santander.com.br/portal/pam/script/cvm/CvmAction.do?pdf=pdfLamina&canal=03&codFundo="+cod).openStream(), "UTF-8").useDelimiter("\\A").next();
			if(out.contains("('Nao existe l�mina de informa��es essenciais para este fundo")){
				System.out.println("Não há lâmina: "+ cod);
			}
			else{
				System.out.println("Há lâmina: "+ out);
			}
			}catch(Exception e){
				
			}
	}

}

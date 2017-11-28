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
			String out = new Scanner(new URL("https://owa.santander.com.br/owa/redir.aspx?C=XialCf077ezwI6DPDJKgObzhgVURf-7bsfcbBxuMF_q20Ds1Ut7UCA..&URL=https%3a%2f%2fjava-pcisrvcd-pci-pre.appls.cmpn.paas.gsnetcloud.corp%2fcarteiraDiariaPrivatePCI%2finvestment-portfolio%2f0%2f33%2f2270%2f290002307%2f19-apr-2017").openStream(), "UTF-8").useDelimiter("\\A").next();
			}catch(Exception e){
				
			}
	}

}

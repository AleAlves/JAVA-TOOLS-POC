

import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {

     
        String SOAPUrl      = "http://180.239.98.96:44446/";
        StringBuilder xmlFile2Send = new StringBuilder();
        xmlFile2Send.append("<?xml version=\"1.0\" enconding=\"utf-8\"?>")
        .append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.mbb.app.bsbr.altec.com/\">")
        .append("<soapenv:Body>")
        .append("<dlwmin:aplicarPoupancaResponse xmlns:dlwmin=\"http://webservice.mbb.app.bsbr.altec.com/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">")
		.append("<return>")
		.append("<cpf>36366188904</cpf>")
		.append("<dataHoraTransacao>20170613</dataHoraTransacao>")
		.append("<titular>SELECT TRAVA SISTEMICA ROTEIRO QUARENTA</titular>")
		.append("<valor>100.00</valor>")
		.append("<dataHash>THfdKQIpAitXwVSopSQCWT+MvkmOgu9vSVFbl6KYtqXE4yNfkfyVFch4q+uH9RQb4h7l3WAYlebTnGftvblrEcz3XB8BMLyrgegxaEcwy3c=</dataHash>")
		.append("<requestId>91e14e13-699a-4b72-bd73-2ce59fa2fdef</requestId>")
		.append("tokenTransacao>/KjjnJVYK5rBk7l1gJiwI5dstyUGVNskguZ+IeQ3eohwH3T3qXoTl6vdaXKdgBANXZTVZ3SwS76PBj2VaZKYlLIwqILrQe5Z8Z6hrjfKtC8=</tokenTransacao>")
		.append("</return>")
		.append("</dlwmin:aplicarPoupancaResponse>")
		.append("</soapenv:Body>")
		.append("</soapenv:Envelope>");
       

		  String SOAPAction = "";
        if (args.length  > 2) 
				SOAPAction = args[2];
				
        // Create the connection where we're going to send the file.
        URL url = new URL(SOAPUrl);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) connection;

        // Open the input file. After we copy it to a byte array, we can see
        // how big it is so that we can set the HTTP Cotent-Length
        // property. (See complete e-mail below for more on this.)

        FileInputStream fin = new FileInputStream(xmlFile2Send.toString());

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
    
        // Copy the SOAP file to the open connection.
        copy(fin,bout);
        fin.close();

        byte[] b = bout.toByteArray();

        // Set the appropriate HTTP parameters.
        httpConn.setRequestProperty( "Content-Length",
                                     String.valueOf( b.length ) );
        httpConn.setRequestProperty("Content-Type","text/xml; charset=utf-8");
		  httpConn.setRequestProperty("SOAPAction",SOAPAction);
        httpConn.setRequestMethod( "POST" );
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);

        // Everything's set up; send the XML that was read in to b.
        OutputStream out = httpConn.getOutputStream();
        out.write( b );    
        out.close();

        // Read the response and write it to standard out.

        InputStreamReader isr =
            new InputStreamReader(httpConn.getInputStream());
        BufferedReader in = new BufferedReader(isr);

        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);

        in.close();
    }

  // copy method from From E.R. Harold's book "Java I/O"
  public static void copy(InputStream in, OutputStream out) 
   throws IOException {

    // do not allow other threads to read from the
    // input or write to the output while copying is
    // taking place

    synchronized (in) {
      synchronized (out) {

        byte[] buffer = new byte[256];
        while (true) {
          int bytesRead = in.read(buffer);
          if (bytesRead == -1) break;
          out.write(buffer, 0, bytesRead);
        }
      }
    }
  } 
}
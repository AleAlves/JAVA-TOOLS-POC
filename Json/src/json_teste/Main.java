package json_teste;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
//		Tag model = gson.fromJson(loadJSONFromAsset(), Tag.class);
		String stg = loadJSONFromAsset();
		stg = stg.replaceAll("Família de produto e serviços Santander", "Familia_de_produto_e_servicos_Santander");
		stg = stg.replaceAll("Subfamília", "Subfamilia");
		stg = stg.replaceAll("Tipo da métrica", "Tipo_da_metrica");
		stg = stg.replaceAll("Nome da métrica", "Nome_da_metrica");
		stg = stg.replaceAll("Valor da métrica", "Valor_da_metrica");
		Tag[] tagObejtList = gson.fromJson(stg, Tag[].class);
		for(Tag t : tagObejtList){
			System.out.println(t.getNomedaMetrica());
		}
	}
	
	public static String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = new FileInputStream("/Users/Santander/tags.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
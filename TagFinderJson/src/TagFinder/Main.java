package TagFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class Main {

	public static void main(String[] args) {
		String filePath = ("/Users/Santander/tags.txt");
		ArrayList<String> tagList = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();
		GetTag tags = new GetTag();
		Directory directory = new Directory();
		List<File> javaDir = directory
				.walkDir("/Users/Santander/projetos/release17/gapzero-android/app/src/main/java/");

		Gson gson = new Gson();
		// Tag model = gson.fromJson(loadJSONFromAsset(), Tag.class);
		String stg = loadJSONFromAsset();
		stg = stg.replaceAll("Família de produto e serviços Santander", "Familia_de_produto_e_servicos_Santander");
		stg = stg.replaceAll("Subfamília", "Subfamilia");
		stg = stg.replaceAll("Tipo da métrica", "Tipo_da_metrica");
		stg = stg.replaceAll("Nome da métrica", "Nome_da_metrica");
		stg = stg.replaceAll("Valor da métrica", "Valor_da_metrica");
		Tag[] tagObejtList = gson.fromJson(stg, Tag[].class);

		Resource resourceList = new Resource();
		result.addAll(resourceList.searchTag(tagObejtList, javaDir, false));
		for (String r : result) {
			System.out.println(r);
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

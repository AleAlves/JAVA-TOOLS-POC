package TagFinder;

import com.google.gson.annotations.SerializedName;

public class Tag {
	
	@SerializedName("Familia_de_produto_e_servicos_Santander")
    private String FamiliadeProdutoServicosSantander;

    @SerializedName("Subfamilia")
    private String subfamilia;
    
    @SerializedName("Tipo_da_metrica")
    private String TipodaMetrica;

    @SerializedName("Nome_da_metrica")
    private String NomedaMetrica;
    
    @SerializedName("Valor_da_metrica")
    private String ValordaMetrica;

	public String getFamiliadeProdutoServicosSantander() {
		return FamiliadeProdutoServicosSantander;
	}

	public String getSubfamilia() {
		return subfamilia;
	}

	public String getTipodaMetrica() {
		return TipodaMetrica;
	}

	public String getNomedaMetrica() {
		return NomedaMetrica;
	}

	public String getValordaMetrica() {
		return ValordaMetrica;
	}
}


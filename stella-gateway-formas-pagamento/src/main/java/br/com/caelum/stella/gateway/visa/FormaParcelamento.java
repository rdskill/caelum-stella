package br.com.caelum.stella.gateway.visa;

/**
 * 
 * @author Alberto Pc
 *
 */
public enum FormaParcelamento {

	CREDITO_A_VISTA("1"),PARCELADO_COM_JUROS_DA_LOJA("2"),PARCELADO_COM_JUROS_DA_ADMINISTRADORA("3"),DEBITO("A");
	
	private String codigo;
	
	private FormaParcelamento(String codigo){
		this.codigo = codigo;
	}
	
	public String getCodigo(){
		return this.codigo;
	}
}

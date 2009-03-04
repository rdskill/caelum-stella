package br.com.caelum.stella.gateway.core;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe no estilo helper que carrega as configura��es que n�o devem estar no c�digo
 * para acessar os gateways. 
 * @author Alberto Pc
 *
 */
public class GatewaysConf {

	private static Properties gatewaysProperties;
	private static String fileSeparator = File.separator;
	
	static{
		gatewaysProperties = new Properties();
		try {
			gatewaysProperties.load(GatewaysConf.class.getResourceAsStream("/gateways.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("N�o foi possivel carregar o arquivo de configura��o dos gateways",e);
		}
	}
	
	private String getProperty(String key){			
		return gatewaysProperties.getProperty(key);
	}
	
	public String getUrlParaComponenteDeAutorizacaoDoVisa(){
		return getProperty("visa.urlComponenteDeAutorizacao");
	}
	
	public String getNumeroDeAfiliacaoDoVisa(){
		return getProperty("visa.numeroDeAfiliacao");
	}
	
	public String getNomeDoArquivoDeConfiguracaoDoVisa(){
		return getProperty("visa.nomeDoArquivoDeConfiguracao");
	}
	
	
	
}

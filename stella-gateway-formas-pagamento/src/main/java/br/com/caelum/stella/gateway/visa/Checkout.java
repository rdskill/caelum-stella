package br.com.caelum.stella.gateway.visa;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Cont�m as informa��es necess�rias para processar uma compra atrav�s do VISA
 * 
 * @author Alberto
 * 
 */
public class Checkout{

	/*
	 * Talvez seja bom validar todos os dados antes... mas ainda estou pensando nisso
	 */

	private String orderId;
	private String order;
	private String free;
	private BigDecimal price;		
	/*
	 * arranjar um nome em ingles para isso.
	 */
	private Parcelamento parcelamento;	
	private CartaoCredito cartao;

	
	
	
	public Checkout(final String orderId, final String order, final String free, final BigDecimal price,
			final Parcelamento parcelamento, CartaoCredito cartao) {
		super();
		this.orderId = orderId;
		this.order = order;
		this.free = free;
		this.price = price;
		this.parcelamento = parcelamento;	
		this.cartao = cartao;
	}



	public CartaoCredito getCartao() {
		return cartao;
	}



	public String getFormattedPrice(){
		return String.format("%.0f",this.price.multiply(new BigDecimal(100)));
	}
	
	
	
	/**
	 * informacoes de parcelamento do checkout
	 * @return
	 */
	public Parcelamento getParcelamento() {
		return parcelamento;
	}

		

	/**
	 * Retorna a descricao completa da compra, referente ao campo oficial order
	 * @return
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 
	 * @param descricaoDaCompra
	 * @return
	 */
	public Checkout withDescricaoDaCompra(String descricaoDaCompra) {
		this.order = descricaoDaCompra;
		return this;
	}

	
	/**
	 * 
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 
	 * @param codigoUnicoDoPedido
	 * @return
	 */
	public Checkout withCodigoUnicoDoPedido(String codigoUnicoDoPedido) {
		this.orderId = codigoUnicoDoPedido;
		return this;
	}




	/**
	 * Campo livre, qualquer informacao adicional que queria ser disponibilizada.
	 * @return
	 */
	public String getFree() {
		return free;
	}
	
	/**
	 * 
	 * @param campoLivre
	 * @return
	 */
	public Checkout withFree(String free) {
		this.free = free;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * identificador unico gerado para cada compra.
	 * @param data data que serve de referencia para o calculo do tid
	 * @return
	 */
	public String getTid(Calendar data,String numeroDeAfiliacao){		
		String dataNoFormatoJuliano = obterDataDeHojeNoFormatoJuliano(data);		
		String numeroDeAfiliacaoDoVisaFormatado = numeroDeAfiliacao.substring(4,numeroDeAfiliacao.length()-1);		
		String horaFormatadaComOMilesimoDoSegundo = String.format("%1$tH%1$tM%1$tS"+String.valueOf(data.get(Calendar.MILLISECOND)).substring(0,1),data);		
		String ultimoDigitoDoAno = getUltimoDigitoDoAno(data);			
		return numeroDeAfiliacaoDoVisaFormatado+ultimoDigitoDoAno+dataNoFormatoJuliano+horaFormatadaComOMilesimoDoSegundo+parcelamento.getCodigoDePagamento();
		
	}

	private String getUltimoDigitoDoAno(Calendar data) {
		String ano = String.valueOf(data.get(Calendar.YEAR));
		return ano.substring(ano.length()-1);
	}

	private String obterDataDeHojeNoFormatoJuliano(Calendar data) {		
        int diaDoAno = data.get(Calendar.DAY_OF_YEAR);        
        String result = String.format("%03d", diaDoAno);
        return result;
	}
	

	
}

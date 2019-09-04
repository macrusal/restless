/**
 * 
 */
package br.com.leraning.restless.services.exceptions;

/**
 * @author marcelo
 *
 */
public class PriceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PriceNotFoundException(String mensagem) {
		super(mensagem);
	}

	public PriceNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}

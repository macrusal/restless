/**
 * 
 */
package br.com.leraning.restless.services.exceptions;

/**
 * @author marcelo
 *
 */
public class ResearchNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResearchNotFoundException(String mensagem) {
		super(mensagem);
	}

	public ResearchNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}

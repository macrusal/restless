/**
 * 
 */
package br.com.leraning.restless.services.exceptions;

/**
 * @author marcelo
 *
 */
public class RoomNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RoomNotFoundException(String mensagem) {
		super(mensagem);
	}

	public RoomNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}

/**
 * 
 */
package br.com.leraning.restless.domain.enums;

/**
 * @author marcelo
 *
 */
public enum RoomType {

	STANDARD(1, "Standard"),
	DUPLO(2, "Duplo"),
	TRIPLO(3, "Triplo");

	private int codigo;
	private String descricao;

	/**
	 * @param codigo
	 * @param descricao
	 */
	private RoomType(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	public static RoomType toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
	
		for (RoomType x : RoomType.values()) {
			if(codigo.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}
	
}

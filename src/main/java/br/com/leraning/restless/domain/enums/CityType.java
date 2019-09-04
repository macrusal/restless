package br.com.leraning.restless.domain.enums;

/**
 * @author macrusal on 06/08/19
 * @project restless
 */
public enum CityType {
    PORTO_SEGURO(1032, "Porto Seguro"),
    RIO_DE_JANEIRO(7110, "Rio de Janeiro"),
    SAO_PAULO(9626, "São Paulo");

    private int codigo;
    private String descricao;

    /**
     * @param codigo
     * @param descricao
     */
    private CityType(int codigo, String descricao) {
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

    public static CityType toEnum(Integer codigo) {
        if(codigo == null) {
            return null;
        }

        for (CityType x : CityType.values()) {
            if(codigo.equals(x.getCodigo())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + codigo);
    }
}

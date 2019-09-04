package br.com.leraning.restless.dto;

import java.io.Serializable;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
public class PriceNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer priceID;

    private double adult;

    private double child;

    public PriceNewDTO() {
    }

    public Integer getPriceID() {
        return priceID;
    }

    public void setPriceID(Integer priceID) {
        this.priceID = priceID;
    }

    public double getAdult() {
        return adult;
    }

    public void setAdult(double adult) {
        this.adult = adult;
    }

    public double getChild() {
        return child;
    }

    public void setChild(double child) {
        this.child = child;
    }
}

package br.com.leraning.restless.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author macrusal on 31/07/19
 * @project opah
 */
@Entity
@Table(name = "TB_PRICE")
public class Price implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PRICE")
    private Integer priceID;

    @Column(name = "ADULT")
    private double adult;

    @Column(name = "CHILD")
    private double child;

    public Price() {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Price{");
        sb.append("adult=").append(adult);
        sb.append(", child=").append(child);
        sb.append('}');
        return sb.toString();
    }
}

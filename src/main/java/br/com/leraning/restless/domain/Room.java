package br.com.leraning.restless.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author macrusal on 31/07/19
 * @project opah
 */
@Entity
@Table(name = "TB_ROOM")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_ROOM")
    private Integer roomID;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @OneToOne
    private Price price;

    public Room() {
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public static boolean obterQuartosAdultosComValoresMenor(Room room) {
        return room.getPrice().getAdult() <= 200.0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("roomID=").append(roomID);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}

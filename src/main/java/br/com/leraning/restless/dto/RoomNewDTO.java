package br.com.leraning.restless.dto;

/**
 * @author macrusal on 31/07/19
 * @project opah
 */
public class RoomNewDTO {

    private Integer roomID;

    private String categoryName;

    private double totalPrice;

    private PriceNewDTO priceNewDTO;

    public RoomNewDTO() {
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

    public PriceNewDTO getPriceNewDTO() {
        return priceNewDTO;
    }

    public void setPriceNewDTO(PriceNewDTO priceNewDTO) {
        this.priceNewDTO = priceNewDTO;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("roomID=").append(roomID);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", price=").append(priceNewDTO);
        sb.append('}');
        return sb.toString();
    }
}

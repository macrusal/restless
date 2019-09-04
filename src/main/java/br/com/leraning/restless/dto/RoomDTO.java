package br.com.leraning.restless.dto;

/**
 * @author macrusal on 31/07/19
 * @project opah
 */
public class RoomDTO {

    private Integer roomID;

    private String categoryName;

    private double totalPrice;

    private PriceDTO priceDTO;

    public RoomDTO() {
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

    public PriceDTO getPriceDTO() {
        return priceDTO;
    }

    public void setPriceDTO(PriceDTO priceDTO) {
        this.priceDTO = priceDTO;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("roomID=").append(roomID);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", price=").append(priceDTO);
        sb.append('}');
        return sb.toString();
    }
}

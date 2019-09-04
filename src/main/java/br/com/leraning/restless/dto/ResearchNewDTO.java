package br.com.leraning.restless.dto;

import java.util.List;

/**
 * @author macrusal on 31/07/19
 * @project opah
 */
public class ResearchNewDTO {

    private Integer id;

    private String name;

    private Integer cityCode;

    private String cityName;


    private List<RoomNewDTO> roomsNewDTO;

    public ResearchNewDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<RoomNewDTO> getRoomsNewDTO() {
        return roomsNewDTO;
    }

    public void setRoomsNewDTO(List<RoomNewDTO> roomsNewDTO) {
        this.roomsNewDTO = roomsNewDTO;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Research{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", cityCode=").append(cityCode);
        sb.append(", cityName='").append(cityName).append('\'');
        sb.append(", roomsNewDTO=").append(roomsNewDTO);
        sb.append('}');
        return sb.toString();
    }
}

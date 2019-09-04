package br.com.leraning.restless.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author macrusal on 31/07/19
 * @project opah
 */
@Entity
@Table(name = "TB_RESEARCH")
public class Research implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_RESEARCH")
    private Integer id;

    @Column(name = "HOTEL_NAME")
    private String name;

    @Column(name = "CITY_CODE")
    private Integer cityCode;

    @Column(name = "CITY_NAME")
    private String cityName;

    @OneToMany
    @JoinColumn(name="research_id")
    private List<Room> rooms;

    public Research() {
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Research{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", cityCode=").append(cityCode);
        sb.append(", cityName='").append(cityName).append('\'');
        sb.append(", rooms=").append(rooms);
        sb.append('}');
        return sb.toString();
    }
}

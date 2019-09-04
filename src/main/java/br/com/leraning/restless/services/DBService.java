package br.com.leraning.restless.services;

import br.com.leraning.restless.domain.Price;
import br.com.leraning.restless.domain.Research;
import br.com.leraning.restless.domain.Room;
import br.com.leraning.restless.domain.enums.RoomType;
import br.com.leraning.restless.repositories.PriceRepository;
import br.com.leraning.restless.repositories.ResearchRepository;
import br.com.leraning.restless.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@Service
public class DBService {

    @Autowired
    ResearchRepository researchRepository;

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    RoomRepository roomRepository;

    public void instantiateTestDataBase() {

        Price price1 = new Price();
        price1.setAdult(115.0);
        price1.setChild(61.0);

        Price price2 = new Price();
        price2.setAdult(165.0);
        price2.setChild(111.0);

        Price price3 = new Price();
        price3.setAdult(245.0);
        price3.setChild(140.0);

        Price price4 = new Price();
        price4.setAdult(135.0);
        price4.setChild(91.0);

        Price price5 = new Price();
        price5.setAdult(195.0);
        price5.setChild(131.0);

        Price price6 = new Price();
        price6.setAdult(275.0);
        price6.setChild(170.0);

        Price price7 = new Price();
        price7.setAdult(185.0);
        price7.setChild(71.0);

        Price price8 = new Price();
        price8.setAdult(245.0);
        price8.setChild(121.0);

        Price price9 = new Price();
        price9.setAdult(325.0);
        price9.setChild(150.0);
        priceRepository.saveAll(Arrays.asList(price1, price2, price3, price4, price5, price6, price7, price8, price9));

        Room room1 = new Room();
        room1.setCategoryName(RoomType.STANDARD.getDescricao());
        room1.setTotalPrice(115.0 + 61.0);
        room1.setPrice(price1);

        Room room2 = new Room();
        room2.setCategoryName(RoomType.DUPLO.getDescricao());
        room2.setTotalPrice(165.0 + 111.0);
        room2.setPrice(price2);

        Room room3 = new Room();
        room3.setCategoryName(RoomType.TRIPLO.getDescricao());
        room3.setTotalPrice(245.0 + 140.0);
        room3.setPrice(price3);

        Room room4 = new Room();
        room4.setCategoryName(RoomType.STANDARD.getDescricao());
        room4.setTotalPrice(price4.getAdult() + price4.getChild());
        room4.setPrice(price4);

        Room room5 = new Room();
        room5.setCategoryName(RoomType.DUPLO.getDescricao());
        room5.setTotalPrice(price5.getAdult() + price5.getChild());
        room5.setPrice(price5);

        Room room6 = new Room();
        room6.setCategoryName(RoomType.TRIPLO.getDescricao());
        room6.setTotalPrice(price6.getAdult() + price6.getChild());
        room6.setPrice(price6);

        Room room7 = new Room();
        room7.setCategoryName(RoomType.STANDARD.getDescricao());
        room7.setTotalPrice(price7.getAdult() + price7.getChild());
        room7.setPrice(price7);

        Room room8 = new Room();
        room8.setCategoryName(RoomType.DUPLO.getDescricao());
        room8.setTotalPrice(price8.getAdult() + price8.getChild());
        room8.setPrice(price8);

        Room room9 = new Room();
        room9.setCategoryName(RoomType.TRIPLO.getDescricao());
        room9.setTotalPrice(price9.getAdult() + price9.getChild());
        room9.setPrice(price9);
        roomRepository.saveAll(Arrays.asList(room1, room2, room3, room4, room5, room6, room7, room8, room9));

        Research research1 = new Research();
        research1.setName("Hotel Prainha");
        research1.setCityCode(1);
        research1.setCityName("Ubatuba");
        research1.setRooms(Arrays.asList(room1, room2, room3));

        Research research2 = new Research();
        research2.setName("Hotel Mare Mansa");
        research2.setCityCode(2);
        research2.setCityName("Ilha Bela");
        research2.setRooms(Arrays.asList(room4, room5, room6));

        Research research3 = new Research();
        research3.setName("Hotel Maresias");
        research3.setCityCode(3);
        research3.setCityName("Maresias");
        research3.setRooms(Arrays.asList(room7, room8, room9));
        researchRepository.saveAll(Arrays.asList(research1, research2, research3));

    }
}

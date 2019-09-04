package br.com.leraning.restless.services;

import br.com.leraning.restless.domain.Room;
import br.com.leraning.restless.dto.RoomNewDTO;
import br.com.leraning.restless.repositories.RoomRepository;
import br.com.leraning.restless.services.exceptions.DataIntegrityException;
import br.com.leraning.restless.services.exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@Service
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public Room find(final Integer id) {

        Optional<Room> room = roomRepository.findById(id);

        return room.orElseThrow(() -> new RoomNotFoundException(
                "Quarto não encontrado! Id: " + id + ", Tipo: " + Room.class.getName()));
    }

    /**
     * @param room
     * @return
     */
    public Room insert(Room room) {
        room.setRoomID(null);
        room = roomRepository.save(room);
        return room;
    }

    /**
     * @param room
     * @return
     */
    public Room update(Room room) {
        Room roomNovo = find(room.getRoomID());
        updateData(roomNovo, room);
        return roomRepository.save(roomNovo);
    }

    /**
     * @param id
     * @return
     */
    public void delete(Integer id) {
        find(id);
        try {
            roomRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Preço que possui associações!");
        }
    }

    private void updateData(Room roomNovo, Room room) {
        roomNovo.setTotalPrice(room.getTotalPrice());
        roomNovo.setPrice(room.getPrice());
        roomNovo.setRoomID(room.getRoomID());
        roomNovo.setCategoryName(room.getCategoryName());
    }

    public Room fromDTO(RoomNewDTO roomDTO) {
        Room room = new Room();
        room.setCategoryName(roomDTO.getCategoryName());
        room.setRoomID(roomDTO.getRoomID());
//        room.setPrice(roomDTO.get);
        room.setTotalPrice(roomDTO.getTotalPrice());
        return room;
    }
}

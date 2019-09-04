package br.com.leraning.restless.resources;

import br.com.leraning.restless.domain.Room;
import br.com.leraning.restless.dto.RoomNewDTO;
import br.com.leraning.restless.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * @author macrusal on 04/08/19
 * @project restless
 */
@RestController
@RequestMapping(value="/restless/rooms")
public class RoomResource {

    @Autowired
    private RoomService roomService;

    @RequestMapping(value="{id}",method=RequestMethod.GET)
    public ResponseEntity<Room> find(@PathVariable final Integer id) {

        Room room = roomService.find(id);
        return ResponseEntity.ok().body(room);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody RoomNewDTO roomDTO) {
        Room room = roomService.fromDTO(roomDTO);
        room = roomService.insert(room);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(room.getRoomID()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody RoomNewDTO roomDTO, @PathVariable final Integer id) {
        Room room = roomService.fromDTO(roomDTO);
        room.setRoomID(id);
        room = roomService.update(room);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable final Integer id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

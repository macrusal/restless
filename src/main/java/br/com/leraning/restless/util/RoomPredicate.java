package br.com.leraning.restless.util;

import br.com.leraning.restless.domain.Room;

import java.util.function.Predicate;

/**
 * @author macrusal on 06/08/19
 * @project restless
 */
public class RoomPredicate implements Predicate<Room> {

    @Override
    public boolean test(Room room) {
        return room.getPrice().getAdult() <= 200.0;
    }
}

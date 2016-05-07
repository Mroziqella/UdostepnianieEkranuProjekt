package pl.mroziqella.repository;

import pl.mroziqella.domain.Room;

import java.util.ArrayList;


/**
 * Created by Kamil on 03/05/2016.
 */
public interface RoomRepository {
    boolean save(Room room);
    ArrayList<Room> getAllRoomsFromUser(String userName);
}

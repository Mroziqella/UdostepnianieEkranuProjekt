package pl.mroziqella.service;

import pl.mroziqella.domain.Room;

import java.util.ArrayList;


/**
 * Created by Kamil on 03/05/2016.
 */
public interface RoomService {
    boolean save(Room room);
    ArrayList<Room> getAllRoomsFromUser(String userName);

}

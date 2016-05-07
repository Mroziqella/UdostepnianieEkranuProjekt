package pl.mroziqella.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mroziqella.domain.Room;
import pl.mroziqella.repository.RoomRepository;
import pl.mroziqella.service.RoomService;

import java.util.ArrayList;

/**
 * Created by Kamil on 03/05/2016.
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public boolean save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public ArrayList<Room> getAllRoomsFromUser(String userName) {
        return roomRepository.getAllRoomsFromUser(userName);
    }
}

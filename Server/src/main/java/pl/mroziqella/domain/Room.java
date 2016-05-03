package pl.mroziqella.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Kamil on 03/05/2016.
 */
@Entity
@Table(name = "pokoj")
public class Room {
    @Id
    private String roomName;
    private String roomPassword;
    @ManyToOne
    @JoinColumn(name = "login")
    private User user;


    public String getRoomPassword() {
        return roomPassword;
    }

    public void setRoomPassword(String roomPassword) {
        this.roomPassword = roomPassword;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

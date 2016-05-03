package pl.mroziqella.domain;

import javax.persistence.*;

/**
 * Created by Kamil on 03/05/2016.
 */
@Entity
@Table(name = "rola")
public class Role {
    @Id
    @GeneratedValue
    private long roleId;
    @ManyToOne
    @JoinColumn(name = "login")
    private User user;
    private String role;




    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

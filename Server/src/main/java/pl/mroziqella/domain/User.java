package pl.mroziqella.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Kamil on 25/03/2016.
 */
@Entity
@Table(name="uzytkownik")
public class User {
    @Id
    @Size(min = 3,max=15,message = "{validation.register.login.label}")
    private String login;
    @NotNull(message = "{validation.register.password.label}")
    private String password;
    private String password2;
    @Email(message = "{validation.register.email.label}")
    private String eMail;


    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}


package br.furb.eventos.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    private String email;
    private String lastname;
    private String login;
    private String pwd;
    private String photoUrl;
    private Profile profile;
    
    //@OneToOne
    //private Profile profile;

    public User() {
    }
    
    public User(String name, String email, String lastname, String login, String pwd) {
        this.name  = name;
        this.email = email;
        this.lastname = lastname;
        this.login = login;
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Profile getProfile() {
        return null;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        // Retorna uma imagem padrão caso não tenha.
        return (photoUrl != null && photoUrl.length() > 0) ? photoUrl : "img/photo.jpg";
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}


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
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the profile
     */
    public Profile getProfile() {
        return null;
    }

    /**
     * @param profile the profile to set
     */
    public void setProfile(Profile profile) {
        //this.profile = profile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

package br.furb.eventos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author •srtª.prii
 */
@Entity
public class Guest {

    @Id
    @GeneratedValue
    private long id;
    
    private User guest;
    private UserStatus status;
    
    public Guest()
    {
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public enum UserStatus 
    {
        YES,
        MAYBE,
        NO,
        INVITED,
    }

}

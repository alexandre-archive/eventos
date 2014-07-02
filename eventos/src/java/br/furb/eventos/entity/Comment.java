package br.furb.eventos.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

@Entity
public class Comment {
    
    @Id
    @GeneratedValue
    private long id;
    
    private User user;
    private String comment;
    @Temporal(DATE)
    private Date date;

    public Comment() {
        this.id = 0;
    }
    
    public Comment(String comment) {
        this.comment = comment;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}

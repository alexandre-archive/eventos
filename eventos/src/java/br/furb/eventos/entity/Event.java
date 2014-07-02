package br.furb.eventos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(Date initialdate) {
        this.initialdate = initialdate;
    }

    public Date getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(Date finaldate) {
        this.finaldate = finaldate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public HashMap<User, UserStatus> getGuests() {
        return guests;
    }

    public void setGuests(HashMap<User, UserStatus> guests) {
        this.guests = guests;
    }

    public String getCoverImage() {
        return (coverImage != null && !coverImage.isEmpty()) ? coverImage : "img/event.jpg";
    }

    public ArrayList<String> getImages() {
        return images;
    }
    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public List<User> getShares() {
        return shares;
    }

    public void setShares(List<User> shares) {
        this.shares = shares;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
    
    public enum UserStatus
    {
        YES,
        MAYBE,
        NO,
        INVITED,
    }
    
    public enum EventStatus
    {
        Open,
        Done,
        Canceled,
        Undefined,
    }
    
    public enum EventType
    {
        Private,
        Public
    }
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;
    private String description;
    
    private String address;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date initialdate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date finaldate;

    private User owner;
    private HashMap<User, UserStatus> guests;
    
    private List<User> likes;
    
    private List<User> shares;
    
    private List<Comment> comments;
    
    private String coverImage;
    
    private ArrayList<String> images;
 
    private EventStatus status;
    
    private EventType type;
    
    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public Event () {
        this.id = 0;
    }
    
    /*
    
    Só o owner pode excluir o evento.
    
    */
}
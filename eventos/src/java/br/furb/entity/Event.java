/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Event {

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * @return the guests
     */
    public HashMap<User, UserStatus> getGuests() {
        return guests;
    }

    /**
     * @param guests the guests to set
     */
    public void setGuests(HashMap<User, UserStatus> guests) {
        this.guests = guests;
    }

    /**
     * @return the coverImage
     */
    public String getCoverImage() {
        return coverImage;
    }

    /**
     * @return the images
     */
    public ArrayList<String> getImages() {
        return images;
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
    private long id;
    
    private String name;
    private String description;
    
    private String address;
    private Date date;
    private User owner;
    private HashMap<User, UserStatus> guests;
    
    private String coverImage;
    
    private ArrayList<String> images;
    
    private ArrayList<User> likes;
 
    private EventStatus status;
    
    private EventType type;
    
    /*
    
    Só o owner pode excluir o evento.
    
    */
}

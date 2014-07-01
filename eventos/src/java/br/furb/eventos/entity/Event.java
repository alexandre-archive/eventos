/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.eventos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;


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
        return coverImage;
    }

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
    @GeneratedValue
    private long id;
    
    private String name;
    private String description;
    
    private String address;
    @Temporal(DATE)
    private Date initialdate;
    @Temporal(DATE)
    private Date finaldate;

    private User owner;
    private HashMap<User, UserStatus> guests;
    
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
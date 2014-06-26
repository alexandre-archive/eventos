/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.furb.entity;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author marcospaulo
 */
@Entity
public class Profile {
    
    @Id
    private long id;
    
    private String name;
    
    private ArrayList<Permission> permissions;

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
     * @return the permissions
     */
    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     */
    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}

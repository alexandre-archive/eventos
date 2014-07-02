
package br.furb.eventos.entity;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profile {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String name;

    public Profile() {
    }
    
    public Profile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Permission> getPermissions() {
        return null;
    }

    public void setPermissions(ArrayList<Permission> permissions) {
        
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}

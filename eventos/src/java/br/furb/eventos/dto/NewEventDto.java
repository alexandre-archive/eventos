package br.furb.eventos.dto;

import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

public class NewEventDto {

    private long id;
    
    private long owner;
    
    private String coverUrl;
    
    @NotNull(message="Campo Título não pode ser vazio.")
    @NotEmpty(message="Campo Título não pode ser vazio.")
    @MinLength(value=3, message="Campo Título deve possuir no mínimo 3 caracteres.")
    private String title;
    
    private String initialdate;
    
    private String finaldate;
    
    @NotNull(message="Campo Localização não pode ser vazio.")
    @NotEmpty(message="Campo Localização não pode ser vazio.")
    @MinLength(value=3, message="Campo Localização deve possuir no mínimo 3 caracteres.")
    private String location;
    
    @NotNull(message="Campo Detalhes não pode ser vazio.")
    @NotEmpty(message="Campo Detalhes não pode ser vazio.")
    @MinLength(value=3, message="Campo Detalhes deve possuir no mínimo 3 caracteres.")
    private String detail;
    
    private String guests;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(String initialdate) {
        this.initialdate = initialdate;
    }

    public String getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(String finaldate) {
        this.finaldate = finaldate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(long owner) {
        this.owner = owner;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}

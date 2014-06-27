/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.furb.eventos.dto;

/**
 *
 * @author Alexandre
 */
public class NewEventDto {

    private String title;
    private String initialdate;
    private String finaldate;
    private String location;
    private String detail;
    private String guests;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the initialdate
     */
    public String getInitialdate() {
        return initialdate;
    }

    /**
     * @param initialdate the initialdate to set
     */
    public void setInitialdate(String initialdate) {
        this.initialdate = initialdate;
    }

    /**
     * @return the finaldate
     */
    public String getFinaldate() {
        return finaldate;
    }

    /**
     * @param finaldate the finaldate to set
     */
    public void setFinaldate(String finaldate) {
        this.finaldate = finaldate;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * @return the guests
     */
    public String getGuests() {
        return guests;
    }

    /**
     * @param guests the guests to set
     */
    public void setGuests(String guests) {
        this.guests = guests;
    }
}

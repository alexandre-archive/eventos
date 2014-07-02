package br.furb.eventos.dto;

import java.util.ArrayList;

/**
 * @author Alexandre
 */
public class EventDto {
    
    private long id;
    private UserDto owner;
    private String coverUrl;
    private String title;
    private String initialDate;
    private String finalDate;
    private String date; // datas concatenadas.
    private String location;
    private String detail;
    private String guests;
    private String totalGuests;
    // TODO: usar enum.
    private int answer;
    private boolean due;
    // Usuario logado curtiu;
    private boolean like;
    // total de likes.
    private long likes;
    // usuario compartilhou o evento.
    private boolean shared;
    private ArrayList<CommentDto> comments;

    public String getTotalGuests() {
        return totalGuests;
    }

    public void setTotalGuests(String totalGuests) {
        this.totalGuests = totalGuests;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isDue() {
        return due;
    }

    public void setDue(boolean due) {
        this.due = due;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public ArrayList<CommentDto> getComments() {
        return comments;
    }

    public void setComments(ArrayList<CommentDto> comments) {
        this.comments = comments;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }
    
    
}

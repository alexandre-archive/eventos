package br.furb.eventos.dto;

/**
 *
 * @author priscila.assini
 */
public class NewCommentDto {
    
    private long id;
    private long user;
    private String comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }    
}

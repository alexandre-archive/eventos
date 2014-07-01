package br.furb.eventos.dto;

/**
 * @author Alexandre
 */
public class CommentDto {
    
    private long id;
    private UserDto user;
    private String comment;
    // usuario logado curtiu.
    private boolean like;
    private long likes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    
    
}

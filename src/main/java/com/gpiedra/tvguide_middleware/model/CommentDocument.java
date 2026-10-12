package com.gpiedra.tvguide_middleware.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//aqui si costea
@Document("comments")
public class CommentDocument {
    @Id
    private String id;
    @Indexed
    private Long showId;
    private String comment;
    private int rating;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Long getShowId() {
        return showId;
    }
    public void setShowId(Long showId) {
        this.showId = showId;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

}

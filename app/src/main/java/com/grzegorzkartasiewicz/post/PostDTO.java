package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.CommentDTO;
import com.grzegorzkartasiewicz.user.UserDTO;
import com.grzegorzkartasiewicz.user.vo.UserId;

import java.util.List;

public class PostDTO {
    private int id;

    private String description;

    private UserId user;
    private String authorName;
    private String authorSurname;
    private List<CommentDTO> comments;

    public PostDTO() {
    }

    public PostDTO(int id, String description, UserId user, String authorName, String authorSurname, List<CommentDTO> comments) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
    public String getAuthorSurname() { return authorSurname; }
    public void setAuthorSurname(String authorSurname) { this.authorSurname = authorSurname; }
    public List<CommentDTO> getComments() { return comments; }
    public void setComments(List<CommentDTO> comments) { this.comments = comments; }

    static PostDTO toDTO(Post post, UserDTO user, List<CommentDTO> commentDTOs) {
        PostSnapshot postSnapshot = post.getSnapshot();
        return new PostDTO(postSnapshot.getId(), postSnapshot.getDescription(), postSnapshot.getUserId(),
                user.getName(), user.getSurname(), commentDTOs);
    }
}

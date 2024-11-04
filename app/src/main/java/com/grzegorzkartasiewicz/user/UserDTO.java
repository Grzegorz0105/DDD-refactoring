package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.login.vo.LoginId;
import com.grzegorzkartasiewicz.post.vo.PostId;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private int age;
    private List<PostId> posts = new ArrayList<PostId>();
    private List<CommentId> comments = new ArrayList<CommentId>();
    private LoginId login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<PostId> getPosts() {
        return posts;
    }

    public void setPosts(List<PostId> posts) {
        this.posts = posts;
    }

    public List<CommentId> getComments() {
        return comments;
    }

    public void setComments(List<CommentId> comments) {
        this.comments = comments;
    }

    public LoginId getLogin() {
        return login;
    }

    public void setLogin(LoginId login) {
        this.login = login;
    }

    static UserDTO toDTO(User user) {
        UserSnapshot userSnapshot = user.getSnapshot();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userSnapshot.getId());
        userDTO.setName(userSnapshot.getName());
        userDTO.setSurname(userSnapshot.getSurname());
        userDTO.setAge(userSnapshot.getAge());
        userDTO.setPosts(userSnapshot.getPostIds());
        userDTO.setComments(userSnapshot.getCommentIds());
        userDTO.setLogin(userSnapshot.getLoginId());
        return userDTO;
    }
}

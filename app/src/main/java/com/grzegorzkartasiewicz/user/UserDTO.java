package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.login.Login;
import com.grzegorzkartasiewicz.post.Post;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private int age;
    private List<Post> posts = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private Login login;

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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setAge(user.getAge());
        userDTO.setPosts(user.getPosts());
        userDTO.setComments(user.getComments());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.getId());
        user.setAge(this.getAge());
        user.setName(this.getName());
        user.setSurname(this.getSurname());
        user.setLogin(this.getLogin());
        return user;
    }
}

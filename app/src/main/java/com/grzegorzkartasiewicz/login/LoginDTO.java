package com.grzegorzkartasiewicz.login;

import com.grzegorzkartasiewicz.user.vo.UserId;

public class LoginDTO {
    private int id;
    private String nick;
    private String password;
    private String email;
    private UserId user;

    public LoginDTO() {
    }

    public LoginDTO(int id, String nick, String password, String email, UserId user) {
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.email = email;
        this.user = user;
    }

    public static LoginDTO toDto(Login save) {
        return  new LoginDTO();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserId getUser() {
        return user;
    }

    public void setUser(UserId user) {
        this.user = user;
    }
}

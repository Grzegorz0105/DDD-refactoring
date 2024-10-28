package com.grzegorzkartasiewicz.login;

import com.grzegorzkartasiewicz.user.UserId;

public class LoginDTO {
    private int id;
    private String nick;
    private String password;
    private String email;
    private UserId user;

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

    public Login toEntity() {
        return new Login(id, nick, password, email, user);
    }
}

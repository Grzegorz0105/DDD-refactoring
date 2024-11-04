package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.login.vo.LoginId;

class UserSnapshot {

    private int id;

    private String name;

    private String surname;
    private int age;

    private LoginId loginId;

    protected UserSnapshot() {
    }

    UserSnapshot(int id, String name, String surname, int age, LoginId loginId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.loginId = loginId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public LoginId getLoginId() {
        return loginId;
    }
}

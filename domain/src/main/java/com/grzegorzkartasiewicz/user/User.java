package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.login.vo.LoginId;
import com.grzegorzkartasiewicz.user.vo.UserCreator;


class User {

    static User restore(UserSnapshot snapshot) {
        return new User(
                snapshot.getId(),
                snapshot.getName(),
                snapshot.getSurname(),
                snapshot.getAge(),
                snapshot.getLoginId()
        );
    }

    static User createFrom(final UserCreator source) {
        return new User(
                0,
                source.name(),
                source.surname(),
                source.age(),
                source.loginId()
        );
    }

    private int id;

    private String name;

    private String surname;
    private int age;

    private LoginId loginId;

    private User(int id, String name, String surname, int age, LoginId loginId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.loginId = loginId;
    }

    UserSnapshot getSnapshot() {
        return new UserSnapshot(id, name, surname, age, loginId);
    }

}

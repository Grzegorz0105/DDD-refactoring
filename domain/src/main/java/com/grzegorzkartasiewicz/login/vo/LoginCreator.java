package com.grzegorzkartasiewicz.login.vo;

import com.grzegorzkartasiewicz.user.vo.UserId;

public record LoginCreator(int id, String nick, String password, String email) {
}

package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.comment.vo.CommentId;
import com.grzegorzkartasiewicz.login.vo.LoginId;
import com.grzegorzkartasiewicz.post.vo.PostId;

import java.util.ArrayList;
import java.util.List;

class UserSnapshot {

    private int id;

    private String name;

    private String surname;
    private int age;

    private List<PostId> postIds = new ArrayList<>();

    private List<CommentId> commentIds = new ArrayList<>();

    private LoginId loginId;

    protected UserSnapshot() {
    }

    UserSnapshot(int id, String name, String surname, int age, List<PostId> postIds, List<CommentId> commentIds, LoginId loginId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.postIds = postIds;
        this.commentIds = commentIds;
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

    public List<PostId> getPostIds() {
        return postIds;
    }

    public List<CommentId> getCommentIds() {
        return commentIds;
    }

    public LoginId getLoginId() {
        return loginId;
    }
}

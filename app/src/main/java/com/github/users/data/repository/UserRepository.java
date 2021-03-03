package com.github.users.data.repository;



import com.github.users.data.model.Response;
import com.github.users.data.model.User;
import com.github.users.data.service.UserService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;


@Singleton
public class UserRepository {

    private final UserService userService;

    @Inject
    public UserRepository(UserService userService) {
        this.userService = userService;
    }

    public Single<Response> searchUsers(String query) {
        return userService.searchUsers(query);
    }

}

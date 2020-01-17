package com.serheev.app.model;

import com.serheev.app.entities.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static Model instance = new Model();

    private List<UserEntity> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(UserEntity user) {
        model.add(user);
    }

    public List<String> list() {
        return model.stream()
                .map(UserEntity::getName)
                .collect(Collectors.toList());
    }
}

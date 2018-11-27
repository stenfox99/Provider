package by.training.provider.dao.impl;

import by.training.provider.connection.ProxyConnection;
import by.training.provider.dao.Dao;
import by.training.provider.entity.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserTypeDao implements Dao<UserType> {
    public boolean add(UserType element) {
        return false;
    }

    public boolean remove(UserType element) {
        return false;
    }

    public boolean update(UserType element) {
        return false;
    }

    public List<UserType> findUserTypeByType(){
        List<UserType> userTypes = new ArrayList<>();

        return userTypes;
    }
}

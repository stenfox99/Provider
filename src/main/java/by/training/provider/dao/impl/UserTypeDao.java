package by.training.provider.dao.impl;

import by.training.provider.dao.Dao;
import by.training.provider.entity.UserType;
import by.training.provider.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

public class UserTypeDao implements Dao<UserType> {
    public void add(UserType element) {

    }

    public void remove(UserType element) {

    }

    public void update(UserType element) {

    }

    @Override
    public List<UserType> findAll() throws DaoException {
        return null;
    }

    public List<UserType> findUserTypeByType(){
        List<UserType> userTypes = new ArrayList<>();

        return userTypes;
    }
}

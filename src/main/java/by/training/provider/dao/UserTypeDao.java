package by.training.provider.dao;

import by.training.provider.entity.UserType;

public interface UserTypeDao extends DaoBase<UserType> {
    @Override
    default void add(UserType element) {
        throw new UnsupportedOperationException("Method is unsupported");
    }

    @Override
    default void remove(UserType element) {
        throw new UnsupportedOperationException("Method is unsupported");
    }

    @Override
    default void update(UserType element) {
        throw new UnsupportedOperationException("Method is unsupported");
    }
}

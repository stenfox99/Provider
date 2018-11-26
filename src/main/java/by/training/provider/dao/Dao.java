package by.training.provider.dao;

public interface Dao<T> {

    boolean add(T element);
    boolean remove(T element);
    boolean update(T element);
}

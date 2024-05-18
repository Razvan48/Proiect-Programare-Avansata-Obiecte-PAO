package Crud;

import Facilities.Facility;

import java.sql.SQLException;

public interface CRUD<T> {

    public int create(T obj) throws SQLException;
    public T read(T obj) throws SQLException;
    public int update(T obj) throws SQLException;
    public int delete(T obj) throws SQLException;
}

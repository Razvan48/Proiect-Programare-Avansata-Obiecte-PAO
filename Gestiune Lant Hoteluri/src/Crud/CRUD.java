package Crud;

import Facilities.Facility;

import java.sql.SQLException;

public interface CRUD<T> {

    public int create() throws SQLException;
    public T read() throws SQLException;
    public int update() throws SQLException;
    public int delete() throws SQLException;
    public void inputForCreate();
    public void inputForRead();
    public void inputForUpdate();
    public void inputForDelete();
}

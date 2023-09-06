package lk.ijse.pos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T>{
    ArrayList<T> getAll(Connection connection) throws ClassNotFoundException , SQLException;
    boolean save(T data, Connection connection) throws ClassNotFoundException , SQLException;
    boolean delete(String id,Connection connection) throws ClassNotFoundException , SQLException;
    boolean update(T data,Connection connection) throws ClassNotFoundException , SQLException;
}

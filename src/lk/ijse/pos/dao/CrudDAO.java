package lk.ijse.pos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO{
    ArrayList<T> getAll(Connection connection) throws SQLException;
    boolean save(T data, Connection connection) throws SQLException;
    boolean delete(String id,Connection connection) throws SQLException;
    boolean update(T data,Connection connection) throws SQLException;
}

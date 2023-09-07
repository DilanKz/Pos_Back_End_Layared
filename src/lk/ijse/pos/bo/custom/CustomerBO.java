package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
    ArrayList<CustomerDTO> getAll(Connection connection) throws SQLException, ClassNotFoundException;

    boolean save(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException;

    boolean update(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException;

    boolean delete(String id, Connection connection) throws SQLException, ClassNotFoundException;


}

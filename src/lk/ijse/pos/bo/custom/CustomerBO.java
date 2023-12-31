package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAll(Connection connection) throws SQLException;

    boolean save(CustomerDTO customerDTO, Connection connection) throws SQLException;

    boolean update(CustomerDTO customerDTO, Connection connection) throws SQLException;

    boolean delete(String id, Connection connection) throws SQLException;


}

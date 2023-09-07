package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    ArrayList<ItemDTO> getAll(Connection connection) throws SQLException, ClassNotFoundException;

    boolean save(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException;

    boolean update(ItemDTO itemDTO, Connection connection) throws SQLException, ClassNotFoundException;

    boolean delete(String id, Connection connection) throws SQLException, ClassNotFoundException;

}

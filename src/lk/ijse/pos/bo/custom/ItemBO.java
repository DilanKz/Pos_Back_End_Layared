package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<ItemDTO> getAll(Connection connection) throws SQLException;

    boolean save(ItemDTO itemDTO, Connection connection) throws SQLException;

    boolean update(ItemDTO itemDTO, Connection connection) throws SQLException;

    boolean delete(String id, Connection connection) throws SQLException;

}

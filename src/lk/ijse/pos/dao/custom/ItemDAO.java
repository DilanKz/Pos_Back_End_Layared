package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    Item getItem(String id, Connection connection) throws SQLException;
    boolean updateQty(Item item, Connection connection) throws SQLException;
}

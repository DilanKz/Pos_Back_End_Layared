package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    Item getItem(String id, Connection connection) throws SQLException;

    boolean updateQty(String id, int qty, Connection connection) throws SQLException;

    boolean updateAllItems(ArrayList<OrderDetails> orderDetails, Connection connection) throws SQLException;
}

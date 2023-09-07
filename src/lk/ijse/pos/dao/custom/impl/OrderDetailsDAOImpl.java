package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetails> getAll(Connection connection) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean save(OrderDetails data, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetails data, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }
}

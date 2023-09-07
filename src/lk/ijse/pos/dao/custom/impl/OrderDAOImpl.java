package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrdersDAO;
import lk.ijse.pos.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<Orders> getAll(Connection connection) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean save(Orders data, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Orders data, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }
}

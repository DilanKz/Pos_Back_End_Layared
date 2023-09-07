package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrdersDAO;
import lk.ijse.pos.entity.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrdersDAO {
    @Override
    public ArrayList<Orders> getAll(Connection connection) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean save(Orders data, Connection connection) throws ClassNotFoundException, SQLException {

        PreparedStatement pstm = connection.prepareStatement("insert into orders values(?,?,?,?)");
        pstm.setObject(1, data.getId());
        pstm.setObject(2, data.getDate());
        pstm.setObject(3, data.getTotal());
        pstm.setObject(4, data.getCusID());

        return pstm.executeUpdate()>0;
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

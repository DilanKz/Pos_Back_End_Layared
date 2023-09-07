package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetails> getAll(Connection connection) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean save(OrderDetails data, Connection connection) throws ClassNotFoundException, SQLException {
        PreparedStatement pstm = connection.prepareStatement("insert into ordersdetails values(?,?,?,?)");
        pstm.setObject(1,data.getOrderID());
        pstm.setObject(2,data.getCode());
        pstm.setObject(3,data.getItemQty());
        pstm.setObject(4,data.getUnitPrice());

        return pstm.executeUpdate()>0;
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

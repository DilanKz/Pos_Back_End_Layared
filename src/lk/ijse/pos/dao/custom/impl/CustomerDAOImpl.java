package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll(Connection connection) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public boolean save(Customer data, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String id, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(Customer data, Connection connection) throws ClassNotFoundException, SQLException {
        return false;
    }
}

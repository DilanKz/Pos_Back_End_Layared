package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll(Connection connection) throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

        PreparedStatement pstm = connection.prepareStatement("select * from customerinfo");
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            customers.add(
                    new Customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)

                    )
            );
        }

        return customers;
    }

    @Override
    public boolean save(Customer customer, Connection connection) throws SQLException {

        PreparedStatement pstm = connection.prepareStatement("insert into customerinfo values(?,?,?,?)");
        pstm.setObject(1, customer.getCusID());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getContact());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id, Connection connection) throws  SQLException {
        PreparedStatement pstm = connection.prepareStatement("delete from customerinfo where cusID=?");
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Customer customer, Connection connection) throws  SQLException {
        PreparedStatement pstm = connection.prepareStatement("update customerinfo set name=?,address=?,contact=? where cusID=?");
        pstm.setObject(4, customer.getCusID());
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getContact());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public Customer getCustomer(String id, Connection connection) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("select * from customerinfo where cusID=?");
        pstm.setObject(1, id);
        ResultSet rst = pstm.executeQuery();
        Customer customer = new Customer();
        if (rst.next()) {

            customer.setCusID(rst.getString(1));
            customer.setName(rst.getString(2));
            customer.setAddress(rst.getString(3));
            customer.setContact(rst.getString(4));

        }
        return customer;
    }
}

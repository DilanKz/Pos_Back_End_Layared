package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Customer);
    @Override
    public ArrayList<CustomerDTO> getAll(Connection connection) throws SQLException ,ClassNotFoundException {
        ArrayList<Customer> daoAll = customerDAO.getAll(connection);
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : daoAll) {
            customerDTOS.add(
                    new CustomerDTO(
                            customer.getCusID(),
                            customer.getName(),
                            customer.getAddress(),
                            customer.getContact()
                    )
            );
        }

        return customerDTOS;
    }

    @Override
    public boolean save(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCusID(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getContact()),connection);

    }

    @Override
    public boolean update(CustomerDTO customerDTO, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCusID(),customerDTO.getName(),customerDTO.getAddress(),customerDTO.getContact()),connection);
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id,connection);
    }
}

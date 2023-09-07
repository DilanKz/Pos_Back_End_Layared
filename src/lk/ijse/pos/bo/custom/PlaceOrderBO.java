package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public interface PlaceOrderBO {
    ItemDTO getItem(String id, Connection connection) throws SQLException, ClassNotFoundException;

    CustomerDTO getCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException;
}

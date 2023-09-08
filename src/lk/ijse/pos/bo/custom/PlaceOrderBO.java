package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.SavedOrdersDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {
    ItemDTO getItem(String id, Connection connection) throws SQLException;

    CustomerDTO getCustomer(String id, Connection connection) throws SQLException;

    boolean saveOrder(OrderDTO orderDTO, Connection connection) throws SQLException;
    ArrayList<SavedOrdersDTO> getAllOrders(Connection connection) throws SQLException;
}

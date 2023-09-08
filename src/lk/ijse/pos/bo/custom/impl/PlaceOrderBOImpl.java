package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.*;
import lk.ijse.pos.dto.*;
import lk.ijse.pos.entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Customer);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Items);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Orders);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.OrderDetails);
    QuarryDAO quarryDAO = (QuarryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Quarry);


    @Override
    public ItemDTO getItem(String id, Connection connection) throws SQLException {
        Item item = itemDAO.getItem(id, connection);
        return new ItemDTO(item.getCode(), item.getDesc(), item.getUnitPrice(), item.getItemQty());
    }

    @Override
    public CustomerDTO getCustomer(String id, Connection connection) throws SQLException {
        Customer customer = customerDAO.getCustomer(id, connection);
        return new CustomerDTO(customer.getCusID(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO, Connection connection) throws SQLException {
        //getting all the necessary data
        Orders orders = new Orders(orderDTO.getId(), orderDTO.getDate(), orderDTO.getTotal(), orderDTO.getCusID());

        ArrayList<OrderDetails> orderDetails = new ArrayList<>();

        for (OrderDetailsDTO dto : orderDTO.getList()) {
            orderDetails.add(
                    new OrderDetails(
                            dto.getOrderID(),
                            dto.getCode(),
                            dto.getItemQty(),
                            dto.getUnitPrice()
                    )
            );
        }

        //transaction
        try {
            connection.setAutoCommit(false);

            //save order
            if (ordersDAO.save(orders, connection)) {
                //save details
                if (orderDetailsDAO.saveDetails(orderDetails, connection)) {
                    //update items
                    if (itemDAO.updateAllItems(orderDetails, connection)) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<SavedOrdersDTO> getAllOrders(Connection connection) throws SQLException {

        ArrayList<CustomEntity> orders = quarryDAO.getAllOrders(connection);
        ArrayList<SavedOrdersDTO> ordersDTOS = new ArrayList<>();
        for (CustomEntity order : orders) {
            ordersDTOS.add(
                    new SavedOrdersDTO(
                            order.getOrderID(),
                            order.getDate(),
                            order.getName(),
                            order.getTotal()
                    )
            );
        }

        return ordersDTOS;
    }
}

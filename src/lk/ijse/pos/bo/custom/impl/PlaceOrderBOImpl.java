package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dao.custom.OrdersDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Customer);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Items);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Orders);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.OrderDetails);

    @Override
    public ItemDTO getItem(String id, Connection connection) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.getItem(id, connection);
        return new ItemDTO(item.getCode(), item.getDesc(), item.getUnitPrice(), item.getItemQty());
    }

    @Override
    public CustomerDTO getCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.getCustomer(id, connection);
        return new CustomerDTO(customer.getCusID(), customer.getName(), customer.getAddress(), customer.getContact());
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO, Connection connection) throws SQLException, ClassNotFoundException {
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
            if (ordersDAO.save(orders,connection)) {
                //save details
                if (orderDetailsDAO.saveDetails(orderDetails,connection)) {
                    //update items
                    if (itemDAO.updateAllItems(orderDetails,connection)) {
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
}

package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll(Connection connection) throws  SQLException {
        ArrayList<Item> items = new ArrayList<>();

        PreparedStatement pstm = connection.prepareStatement("select * from iteminfo");
        ResultSet rst = pstm.executeQuery();

        while (rst.next()){
            items.add(
                    new Item(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getDouble(4),
                            rst.getInt(3)
                    )
            );
        }

        return items;
    }

    @Override
    public boolean save(Item data, Connection connection) throws SQLException {

        PreparedStatement pstm = connection.prepareStatement("insert into iteminfo values(?,?,?,?)");
        pstm.setObject(1, data.getCode());
        pstm.setObject(2, data.getDesc());
        pstm.setObject(3, data.getItemQty());
        pstm.setObject(4, data.getUnitPrice());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {

        PreparedStatement pstm = connection.prepareStatement("delete from iteminfo where itemID=?");
        pstm.setObject(1, id);

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Item data, Connection connection) throws  SQLException {
        PreparedStatement pstm = connection.prepareStatement("update iteminfo set itemDesc=?,itemQty=?,unitPrice=? where itemID=?");

        pstm.setObject(1, data.getDesc());
        pstm.setObject(2, data.getItemQty());
        pstm.setObject(3, data.getUnitPrice());
        pstm.setObject(4, data.getCode());

        return pstm.executeUpdate()>0;
    }

    @Override
    public Item getItem(String id, Connection connection) throws SQLException {
        Item item = new Item();
        PreparedStatement pstm = connection.prepareStatement("select * from iteminfo where itemID=?");
        pstm.setObject(1, id);
        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            item.setCode(rst.getString(1));
            item.setItemQty(rst.getInt(4));
            item.setDesc(rst.getString(2));
            item.setUnitPrice(rst.getDouble(3));
        }


        return item;
    }

    @Override
    public boolean updateQty(String id, int qty, Connection connection) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("update iteminfo set itemQty = itemQty - ? where itemID=?");
        pstm.setObject(1,qty);
        pstm.setObject(2,id);
        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean updateAllItems(ArrayList<OrderDetails> orderDetails, Connection connection) throws SQLException {

        for (OrderDetails orderDetail : orderDetails) {
            if (!updateQty(orderDetail.getCode(), Integer.parseInt(orderDetail.getItemQty()),connection)) {
                return false;
            }
        }

        return true;
    }
}

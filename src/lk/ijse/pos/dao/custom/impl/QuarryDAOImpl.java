package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.QuarryDAO;
import lk.ijse.pos.entity.CustomEntity;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuarryDAOImpl implements QuarryDAO {
    @Override
    public ArrayList<CustomEntity> getAllOrders(Connection connection) throws SQLException {
        ArrayList<CustomEntity> list = new ArrayList<>();

        PreparedStatement pstm = connection.prepareStatement("select orders.orderID,orders.date,c.name ,orders.total  from orders join customerinfo c on c.cusID = orders.customerID");
        ResultSet rst = pstm.executeQuery();

        while (rst.next()) {

            String id = rst.getString(1);
            String date = rst.getString(2);
            String name = rst.getString(3);
            String total = rst.getString(4);
            list.add(new CustomEntity(id,date,name,total));
        }

        return list;
    }
}

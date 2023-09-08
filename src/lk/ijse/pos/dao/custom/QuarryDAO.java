package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.entity.CustomEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QuarryDAO extends SuperDAO {
    ArrayList<CustomEntity> getAllOrders(Connection connection) throws SQLException;
}

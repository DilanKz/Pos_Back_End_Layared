package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO= (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Items);
    @Override
    public ArrayList<ItemDTO> getAll(Connection connection) throws SQLException {
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ArrayList<Item> all = itemDAO.getAll(connection);

        for (Item item : all) {
            itemDTOS.add(
                    new ItemDTO(
                            item.getCode(),
                            item.getDesc(),
                            item.getUnitPrice(),
                            item.getItemQty()
                    )
            );
        }

        return itemDTOS;
    }

    @Override
    public boolean save(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.save(new Item(itemDTO.getCode(), itemDTO.getDesc(), itemDTO.getUnitPrice(), itemDTO.getItemQty()),connection);
    }

    @Override
    public boolean update(ItemDTO itemDTO, Connection connection) throws SQLException {
        return itemDAO.update(new Item(itemDTO.getCode(), itemDTO.getDesc(), itemDTO.getUnitPrice(), itemDTO.getItemQty()),connection);
    }

    @Override
    public boolean delete(String id, Connection connection) throws SQLException {
        return itemDAO.delete(id,connection);
    }
}

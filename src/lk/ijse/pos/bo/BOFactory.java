package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos.bo.custom.impl.ItemBOImpl;
import lk.ijse.pos.bo.custom.impl.PlaceOrderBOImpl;
import lk.ijse.pos.dao.SuperDAO;
import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailsDAOImpl;

public class BOFactory {
    private static BOFactory factory;

    private BOFactory(){

    }
    public static BOFactory getInstance(){
        if (factory==null){
            factory=new BOFactory();
        }
        return factory;
    }



    public enum DAOTypes{
        Customer,Items,placeOrder
    }
    public SuperBO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case Customer:
                return new CustomerBOImpl();
            case Items:
                return new ItemBOImpl();
            case placeOrder:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}

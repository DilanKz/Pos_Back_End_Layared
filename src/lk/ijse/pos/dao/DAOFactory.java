package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory factory;

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        if (factory==null){
            factory=new DAOFactory();
        }
        return factory;
    }



    public enum DAOTypes{
        Customer,Items,Orders,OrderDetails,Quarry
    }
    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case Customer:
                return new CustomerDAOImpl();
            case Items:
                return new ItemDAOImpl();
            case Orders:
                return new OrderDAOImpl();
            case OrderDetails:
                return new OrderDetailsDAOImpl();
            case Quarry:
                return new QuarryDAOImpl();
            default:
                return null;
        }
    }
}

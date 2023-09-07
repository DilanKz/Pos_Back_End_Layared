package lk.ijse.pos.entity;

public class OrderDetails {
    private String orderID;
    private String code;
    private String itemQty;
    private String unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(String orderID, String code, String itemQty, String unitPrice) {
        this.orderID = orderID;
        this.code = code;
        this.itemQty = itemQty;
        this.unitPrice = unitPrice;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderID='" + orderID + '\'' +
                ", code='" + code + '\'' +
                ", itemQty='" + itemQty + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }
}

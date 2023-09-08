package lk.ijse.pos.dto;

public class SavedOrdersDTO {
    private String orderID;
    private String date;
    private String name;
    private String total;

    public SavedOrdersDTO() {
    }

    public SavedOrdersDTO(String orderID, String date, String name, String total) {
        this.orderID = orderID;
        this.date = date;
        this.name = name;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}

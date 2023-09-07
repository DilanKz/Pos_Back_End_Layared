package lk.ijse.pos.entity;

public class Orders {
    private String id;
    private String date;
    private String total;
    private String cusID;

    public Orders() {
    }

    public Orders(String id, String date, String total, String cusID) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.cusID = cusID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", total='" + total + '\'' +
                ", cusID='" + cusID + '\'' +
                '}';
    }
}

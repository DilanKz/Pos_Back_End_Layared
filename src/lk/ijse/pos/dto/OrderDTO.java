package lk.ijse.pos.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
    private String id;
    private String date;
    private String total;
    private String cusID;
    private List<OrderDetailsDTO> list;

    public OrderDTO() {
    }

    public OrderDTO(String id, String date, String total, String cusID, List<OrderDetailsDTO> list) {
        this.id = id;
        this.date = date;
        this.total = total;
        this.cusID = cusID;
        this.list = list;
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

    public List<OrderDetailsDTO> getList() {
        return list;
    }

    public void setList(List<OrderDetailsDTO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", total='" + total + '\'' +
                ", cusID='" + cusID + '\'' +
                ", list=" + list +
                '}';
    }
}

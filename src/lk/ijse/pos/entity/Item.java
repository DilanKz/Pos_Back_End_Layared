package lk.ijse.pos.entity;

public class Item {
    private String code;
    private String desc;
    private double unitPrice;
    private int itemQty;

    public Item() {
    }

    public Item(String code, String desc, double unitPrice, int itemQty) {
        this.code = code;
        this.desc = desc;
        this.unitPrice = unitPrice;
        this.itemQty = itemQty;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", unitPrice=" + unitPrice +
                ", itemQty=" + itemQty +
                '}';
    }
}

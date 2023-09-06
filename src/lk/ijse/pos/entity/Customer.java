package lk.ijse.pos.entity;

public class Customer {
    private String cusID;
    private String name;
    private String address;
    private String contact;

    public Customer() {
    }
    public Customer(String cusID, String name, String address, String contact) {
        this.cusID = cusID;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusID='" + cusID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}

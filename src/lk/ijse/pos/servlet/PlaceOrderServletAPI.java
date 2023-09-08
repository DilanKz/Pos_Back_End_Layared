package lk.ijse.pos.servlet;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.PlaceOrderBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/placeOrder")
public class PlaceOrderServletAPI extends HttpServlet {

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.placeOrder);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-type", "application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");

        String option = req.getParameter("option");
        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        switch (option) {
            case "customer":

                String cusID = req.getParameter("id");


                try (Connection connection = pool.getConnection()) {
                    resp.getWriter().print(getCustomer(cusID, connection));
                } catch (SQLException e) {
                    //response
                }

                break;
            case "items":

                String itemID = req.getParameter("id");

                try (Connection connection = pool.getConnection()) {
                    resp.getWriter().print(getItem(itemID, connection));
                } catch (SQLException e) {
                    //response
                }
                break;
            case "orders":

                try (Connection connection = pool.getConnection()) {
                    PreparedStatement pstm = connection.prepareStatement("select orders.orderID,orders.date,c.name ,orders.total  from orders join customerinfo c on c.cusID = orders.customerID");
                    ResultSet rst = pstm.executeQuery();

                    JsonArrayBuilder allOrders = Json.createArrayBuilder();

                    while (rst.next()) {

                        String id = rst.getString(1);
                        String date = rst.getString(2);
                        String name = rst.getString(3);
                        String total = rst.getString(4);

                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("id", id);
                        objectBuilder.add("date", date);
                        objectBuilder.add("name", name);
                        objectBuilder.add("total", total);

                        allOrders.add(objectBuilder.build());
                    }
                    resp.getWriter().print(allOrders.build());


                } catch (SQLException e) {

                    resp.setStatus(400);
                    resp.getWriter().print(addJSONObject(e.getMessage(), "error"));

                }

                break;
        }

    }

    public JsonObject getCustomer(String customerId, Connection connection) throws SQLException {

        CustomerDTO customer = placeOrderBO.getCustomer(customerId, connection);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("id", customer.getCusID());
        objectBuilder.add("name", customer.getName());
        objectBuilder.add("address", customer.getAddress());
        objectBuilder.add("contact", customer.getContact());

        return objectBuilder.build();
    }

    public JsonObject getItem(String customerId, Connection connection) throws SQLException {

        ItemDTO item = placeOrderBO.getItem(customerId, connection);

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("id", item.getCode());
        objectBuilder.add("desc", item.getDesc());
        objectBuilder.add("price", item.getUnitPrice());
        objectBuilder.add("qty", item.getItemQty());

        return objectBuilder.build();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-type", "application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject readObject = reader.readObject();

        String orderID = readObject.getString("orderID");
        String date = readObject.getString("date");
        int amount = readObject.getInt("amount");
        String customerID = readObject.getString("customer");
        JsonArray details = readObject.getJsonArray("details");

        ArrayList<OrderDetailsDTO> detailsDTOS = new ArrayList<>();
        for (JsonValue item : details) {

            detailsDTOS.add(
                    new OrderDetailsDTO(
                            item.asJsonObject().getString("id"),
                            item.asJsonObject().getString("desc"),
                            item.asJsonObject().getString("qty"),
                            item.asJsonObject().getString("price")
                    )
            );

        }

        OrderDTO orderDTO = new OrderDTO(orderID, date, String.valueOf(amount), customerID, detailsDTOS);

        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()) {
            placeOrderBO.saveOrder(orderDTO, connection);

        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getWriter().print(addJSONObject(e.getMessage(), "error"));
        }

    }


    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private JsonObject addJSONObject(String message, String state) {

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("state", state);
        objectBuilder.add("message", message);
        objectBuilder.add("data", "[]");

        return objectBuilder.build();
    }
}

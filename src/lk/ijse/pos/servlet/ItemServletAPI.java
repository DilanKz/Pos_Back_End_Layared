package lk.ijse.pos.servlet;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dto.ItemDTO;
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

@WebServlet(urlPatterns = "/item")
public class ItemServletAPI extends HttpServlet {

    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Items);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()){

            ArrayList<ItemDTO> all = itemBO.getAll(connection);

            JsonArrayBuilder allItems = Json.createArrayBuilder();
            for (ItemDTO dto : all) {

                JsonObjectBuilder objectBuilder= Json.createObjectBuilder();
                objectBuilder.add("code",dto.getCode());
                objectBuilder.add("name",dto.getDesc());
                objectBuilder.add("qtyOnHand",dto.getItemQty());
                objectBuilder.add("unitPrice",dto.getUnitPrice());

                allItems.add(objectBuilder.build());
            }
            resp.getWriter().print(allItems.build());

        } catch (SQLException e) {

            resp.setStatus(400);
            resp.getWriter().print(addJSONObject(e.getMessage(), "error"));

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");
        String itemName = req.getParameter("description");
        String qty = req.getParameter("qty");
        String unitPrice = req.getParameter("unitPrice");

        ItemDTO dto = new ItemDTO(code, itemName, Double.parseDouble(unitPrice), Integer.parseInt(qty));

        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()){

            if (itemBO.save(dto,connection)) {
                //response
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
            //resp.setStatus(400);
            //resp.getWriter().print(addJSONObject(e.getMessage(), "error"));

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("itemID");
        System.out.println("delete"+code);

        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()){

            if (itemBO.delete(code,connection)) {
                //response
            }

        } catch (SQLException e) {

            resp.setStatus(400);
            resp.getWriter().print(addJSONObject(e.getMessage(), "error"));

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject customerOB = reader.readObject();

        String code = customerOB.getString("code");
        String desc = customerOB.getString("name");
        String qty = customerOB.getString("qty");
        String price = customerOB.getString("price");

        ItemDTO dto = new ItemDTO(code, desc, Double.parseDouble(price), Integer.parseInt(qty));

        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()){

            if (itemBO.update(dto,connection)) {
                //response
            }

        } catch (SQLException e) {

            resp.setStatus(400);
            resp.getWriter().print(addJSONObject(e.getMessage(), "error"));

        }

    }

    private JsonObject addJSONObject(String message, String state) {

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("state", state);
        objectBuilder.add("message", message);
        objectBuilder.add("data", "[]");


        return objectBuilder.build();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

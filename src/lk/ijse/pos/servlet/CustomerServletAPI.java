package lk.ijse.pos.servlet;

import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.util.ResponseUtil;
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

@WebServlet(urlPatterns = {"/customer"})
public class CustomerServletAPI extends HttpServlet {
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Customer);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        try (Connection connection = pool.getConnection()){

            ArrayList<CustomerDTO> all = customerBO.getAll(connection);
            System.out.println(all);

            JsonArrayBuilder allCustomers = Json.createArrayBuilder();

            for (CustomerDTO dto : all) {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id", dto.getCusID());
                objectBuilder.add("name", dto.getName());
                objectBuilder.add("address", dto.getAddress());
                objectBuilder.add("contact", dto.getContact());

                allCustomers.add(objectBuilder.build());
            }
            resp.getWriter().print(allCustomers.build());


        } catch (SQLException e) {

            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.genJson("error",e.getMessage()));

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        String cusID = req.getParameter("cusID");
        String cusName = req.getParameter("cusName");
        String cusAddress = req.getParameter("cusAddress");
        String cusSalary = req.getParameter("cusSalary");


        try (Connection connection = pool.getConnection()){
            CustomerDTO dto = new CustomerDTO(cusID,cusName,cusAddress,cusSalary);
            if (customerBO.save(dto,connection)) {
                resp.getWriter().print(ResponseUtil.genJson("ok","Customer Saved"));
            }

        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.genJson("error",e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject customerOB = reader.readObject();

        String cusID = customerOB.getString("id");
        String cusName = customerOB.getString("name");
        String cusAddress = customerOB.getString("address");
        String cusSalary = customerOB.getString("salary");


        System.out.println(cusID + " - " + cusName+ " - " + cusAddress+ " - " +cusSalary);
        try (Connection connection = pool.getConnection()){

            CustomerDTO dto = new CustomerDTO(cusID,cusName,cusAddress,cusSalary);
            if (customerBO.update(dto,connection)) {
                resp.getWriter().print(ResponseUtil.genJson("ok","Customer updated"));
            }

        } catch (SQLException e) {

            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.genJson("error",e.getMessage()));

        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        BasicDataSource pool = (BasicDataSource) context.getAttribute("dbcp");

        String cusID = req.getParameter("cusID");
        System.out.println(cusID);

        try (Connection connection = pool.getConnection()){

            if (customerBO.delete(cusID,connection)) {
                resp.getWriter().print(ResponseUtil.genJson("ok","Customer Deleted"));
            }

        } catch (SQLException e) {

            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.genJson("error",e.getMessage()));

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

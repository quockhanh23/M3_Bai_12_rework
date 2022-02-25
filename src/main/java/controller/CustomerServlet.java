package controller;

import model.Customer;
import service.CustomerDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    CustomerDAO iCustomerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";

        }
        switch (action) {
            default:
                showList(request, response);
                break;
            case "create":
                showCreate(request, response);
                break;
            case "edit":
                showEdit(request, response);
                break;
            case "delete":
                showDelete(request, response);
                break;
//            case "findByName":
//                showFindName(request, response);
//                break;
            case "showListByOrder":
                showListByOrder(request, response);
                break;
        }
    }

//    private void showFindName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/findByName1.jsp");
//        dispatcher.forward(request, response);
//    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = iCustomerDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/delete.jsp");
        request.setAttribute("aloDelete", customer);
        dispatcher.forward(request, response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = iCustomerDAO.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/customer/edit.jsp");
        request.setAttribute("aloEdit", customer);
        dispatcher.forward(request, response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customer/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customer/list.jsp");
        String key = request.getParameter("key");
        List<Customer> customers;
        if (key== null) {
            customers = iCustomerDAO.findAll();
        } else {
            customers = iCustomerDAO.findByName(key);
        }
        request.setAttribute("alo", customers);
        requestDispatcher.forward(request, response);
    }
    private void showListByOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/customer/list.jsp");
        List<Customer> customers = iCustomerDAO.orderByAge();
        request.setAttribute("alo", customers);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    saveCreate(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    saveEdit(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    saveDelete(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
//            case "findByName":
//                findByName(request, response);
//                break;
        }
    }

//    private void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        RequestDispatcher dispatcher =request.getRequestDispatcher("customer/findByName2.jsp");
//        String name = request.getParameter("name");
//        List<Customer> customers = iCustomerDAO.findByName(name);
//        request.setAttribute("list", customers);
//        dispatcher.forward(request, response);
//    }

    private void saveDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        iCustomerDAO.delete(id);
        response.sendRedirect("/customers");
    }

    private void saveCreate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int id = Integer.parseInt(request.getParameter("id"));
        iCustomerDAO.add(new Customer(id, name, age));
        response.sendRedirect("/customers");
    }

    private void saveEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        int age = Integer.parseInt(request.getParameter("age"));
        String name = request.getParameter("name");
        Customer customer = new Customer(id, name, age);
        iCustomerDAO.update(customer);
        response.sendRedirect("/customers");
    }

}

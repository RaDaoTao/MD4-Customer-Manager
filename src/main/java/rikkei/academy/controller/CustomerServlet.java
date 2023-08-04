package rikkei.academy.controller;

import rikkei.academy.model.Customer;
import rikkei.academy.service.CustomerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    protected CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "GETALL":
                    break;
                case "DELETE":
                    int idDel = Integer.parseInt(request.getParameter("id"));
                    customerService.deleteById(idDel);
                    break;
                case "EDIT":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    Customer customer = customerService.findById(idEdit);
                    request.setAttribute("customer", customer);
                    request.getRequestDispatcher("/WEB-INF/editCustomer.jsp").forward(request, response);
                    break;
                case "CREATE":
                    request.getRequestDispatcher("/WEB-INF/newCustomer.jsp").forward(request, response);
                    break;
                case "SEARCH":
                    String searchName = request.getParameter("search-name");
                    String sort = request.getParameter("sort");
                    String by = request.getParameter("by");
                    // lọc dữ liệu cần tim kiếm
                    List<Customer> listSearch = searchAndSort(searchName, sort, by);
                    request.setAttribute("searchname", searchName);
                    request.setAttribute("sort", sort);
                    request.setAttribute("by", by);
                    showListCustomers(listSearch, request, response);
                    break;
            }
            showListCustomers(customerService.findAll(), request, response);
        }
    }

    protected List<Customer> searchAndSort(String name, String sort, String by) {
        // lọc dữ liệu
        List<Customer> listSearch = new ArrayList<>();
        for (Customer c : customerService.findAll()) {
            if (c.getName().toLowerCase().contains(name.toLowerCase())) {
                listSearch.add(c);
            }
        }
        // sắp xếp
        switch (sort) {
            case "name":
                if (by.equalsIgnoreCase("ASC")) {
                    listSearch.sort(Comparator.comparing(Customer::getName));
                } else {
                    listSearch.sort(Comparator.comparing(Customer::getName).reversed());
                }
                break;
            case "email":
                if (by.equalsIgnoreCase("ASC")) {
                    listSearch.sort(Comparator.comparing(Customer::getEmail));
                } else {
                    listSearch.sort(Comparator.comparing(Customer::getEmail).reversed());
                }
                break;
            case "address":
                if (by.equalsIgnoreCase("ASC")) {
                    listSearch.sort(Comparator.comparing(Customer::getAddress));
                } else {
                    listSearch.sort(Comparator.comparing(Customer::getAddress).reversed());
                }
                break;
        }
        return listSearch;
    }

    protected void showListCustomers(List<Customer> list, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers", list);
        request.getRequestDispatcher("/WEB-INF/listCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "UPDATE":
                    int id = Integer.parseInt(request.getParameter("id"));
                    String name = request.getParameter("name");
                    String email = request.getParameter("email");
                    String address = request.getParameter("address");
                    String phone = request.getParameter("phone");
                    boolean sex = Boolean.parseBoolean(request.getParameter("sex"));
                    int age = Integer.parseInt(request.getParameter("age"));
                    customerService.save(new Customer(id,name,email,address,phone,sex,age));
                    break;
                case "ADD":

                    String nameNew = request.getParameter("name");
                    String emailNew = request.getParameter("email");
                    String addressNew = request.getParameter("address");
                    String phoneNew = request.getParameter("phone");
                    boolean sexNew = Boolean.parseBoolean(request.getParameter("sex"));
                    int ageNew = Integer.parseInt(request.getParameter("age"));
                    customerService.save(new Customer(0, nameNew, emailNew, addressNew,phoneNew,sexNew,ageNew));
                    break;

            }
            showListCustomers(customerService.findAll(), request, response);
        }
    }
}
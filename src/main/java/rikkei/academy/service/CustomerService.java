package rikkei.academy.service;

import rikkei.academy.model.Customer;
import rikkei.academy.util.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IGenericService<Customer,Integer>{
    private final String FIND_ALL ="SELECT * from Customers";
    private final String INSERT_CUSTOMER ="INSERT INTO CUSTOMERS(name,email,address,phone,sex,age) VALUES(?,?,?,?,?,?)";
    private final String UPDATE_CUSTOMER ="UPDATE CUSTOMERS SET name =?,email=? ,address=?, sex =?, phone=? ,age=? WHERE id =?";
    private final String FIND_BY_ID ="SELECT * from Customers Where id = ?";
    private final String DELETE_BY_ID = "DELETE FROM CUSTOMERS WHERE id = ?";
    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        Connection conn =null;
        try{
            conn = ConnectDB.getConnection();
            PreparedStatement preSt = conn.prepareStatement(FIND_ALL);
            ResultSet rs =preSt.executeQuery();

            while (rs.next()){
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));
                c.setPhone(rs.getString("phone"));
                c.setSex(rs.getBoolean("sex"));
                c.setAge(rs.getInt("age"));
                list.add(c);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    @Override
    public void save(Customer customer) {

        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            PreparedStatement preSt = null;
            if(customer.getId()==0){
                // chức năng thêm mới
               preSt = conn.prepareStatement(INSERT_CUSTOMER);
               preSt.setString(1,customer.getName());
               preSt.setString(2,customer.getEmail());
               preSt.setString(3,customer.getAddress());
               preSt.setString(4,customer.getPhone());
               preSt.setBoolean(5,customer.isSex());
               preSt.setInt(6,customer.getAge());
                // thực thi câu lệnh sql
                preSt.executeUpdate();
            }else {
                // cập nhật
                preSt = conn.prepareStatement(UPDATE_CUSTOMER);
                preSt.setString(1,customer.getName());
                preSt.setString(2,customer.getEmail());
                preSt.setString(3,customer.getAddress());
                preSt.setString(5,customer.getPhone());
                preSt.setBoolean(4,customer.isSex());
                preSt.setInt(6,customer.getAge());
                preSt.setInt(7,customer.getId());
                // thực thi câu lệnh sql
                preSt.executeUpdate();
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }

    }

    @Override
    public Customer findById(Integer integer) {
            Customer customer =null;
            Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            PreparedStatement preSt = conn.prepareStatement(FIND_BY_ID);
            // truyền đối số
            preSt.setInt(1,integer);
            // thực thi câu lệnh xóa
            ResultSet rs = preSt.executeQuery();
            while (rs.next()){
                customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setPhone(rs.getString("phone"));
                customer.setSex(rs.getBoolean("sex"));
                customer.setAge(rs.getInt("age"));
            }

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return customer;
    }
    @Override
    public void deleteById(Integer integer){
        Connection conn =null;
        try {
            // mỏ kết nối
            conn = ConnectDB.getConnection();

            // chuẩn bị câu lệnh
            PreparedStatement preSt = conn.prepareStatement(DELETE_BY_ID);
            // truyền đối số
            preSt.setInt(1,integer);
            // thực thi câu lệnh xóa
            preSt.executeUpdate();

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}

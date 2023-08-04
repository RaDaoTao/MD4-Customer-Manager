package rikkei.academy.util;

import rikkei.academy.model.Customer;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection con = ConnectDB.getConnection();
        System.out.println(con);
        // đã mở kết nối
        // sử dụng Statement
        try {
            String sql = "SELECT * FROM CUSTOMERS id = 3";
//            Statement query = con.createStatement();
//            ResultSet rs = query.executeQuery(sql);

//            PreparedStatement preSt = con.prepareStatement("SELECT * FROM CUSTOMERS WHERE id = ?");
//            preSt.setInt(1,2);
//            ResultSet rs = preSt.executeQuery();

            CallableStatement callSt = con.prepareCall("{CALL PROC_INSERT_CUSTOMER(:name,:email,:a,:b,:c,:d)}");
            callSt.setString("name","Duy Nến 1");
            callSt.setString("email","duynen1@mom.com");
            callSt.setString("a","Thanh Hóa 1");
            callSt.setString("b","0943864378");
            callSt.setBoolean("c",false);
            callSt.setInt("d",24);
            callSt.executeUpdate();
//            System.out.println(rs);
//            Customer c = new Customer();
//            while (rs.next()){
//                c.setId(rs.getInt("id"));
//                c.setName(rs.getString("name"));
//                c.setEmail(rs.getString("email"));
//                c.setAddress(rs.getString("address"));
//                System.out.println(c);
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.Customer;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yue
 */
public class CustomerModel {

    public CustomerModel() {

    }

    //Thêm thông tin khách hàng và trả về mã khách hàng vừa thêm
    public int add(Customer customer) throws SQLException {
        String SQL = "INSERT INTO khachhang (hotenkh, gioitinh, cmnd, quoctich, diachi, sdt) "
                + "VALUES (? ,? ,? ,? ,?, ?)";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, customer.getHotenkh());
        ps.setBoolean(2, customer.isGioitinh());
        ps.setString(3, customer.getCmnd());
        ps.setString(4, customer.getQuoctich());
        ps.setString(5, customer.getDiachi());
        ps.setString(6, customer.getSdt());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int result = -1;
        if (rs.next()) {
            result = rs.getInt(1);
        }
        ps.close();

        return result;
    }

    //Gán thông tin từ ResulSet vào Class khách hàng
    public Customer getInfoCustomer(ResultSet rs) {
        try {
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setHotenkh(rs.getString("hotenkh"));
                customer.setMakh(rs.getInt("makh"));
                customer.setCmnd(rs.getString("cmnd"));
                customer.setGioitinh(rs.getBoolean("gioitinh"));
                customer.setQuoctich(rs.getString("quoctich"));
                customer.setDiachi(rs.getString("diachi"));
                customer.setSdt(rs.getString("sdt"));

                return customer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Lấy thông tin khách hàng dựa trên căn cước
    public Customer getCustomerByIdCard(String cmndString) throws SQLException {

        Statement stmt = DbConn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM khachhang WHERE cmnd = '" + cmndString + "'");
        Customer customer = getInfoCustomer(rs);

        rs.close();
        stmt.close();
        return customer;
    }

    //Lấy thông tin khách hàng theo mã KH
    public Customer getCustomerById(int id) throws SQLException {
        Statement stmt = DbConn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM khachhang WHERE makh = '" + id + "'");
        Customer customer = getInfoCustomer(rs);

        rs.close();
        stmt.close();
        return customer;
    }

}

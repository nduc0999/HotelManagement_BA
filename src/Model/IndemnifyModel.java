/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.Customer;
import BasicClass.IndemnifyRoom;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class IndemnifyModel {

    // Lấy các giá trị ở bảng quy định bồi thường
    public void getIndemnify(DefaultTableModel defaultTableModel) {
        try {
            String sql = "SELECT * FROM qdboithuong;";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] col = new Object[3];
                col[0] = rs.getInt("manoidung");
                col[1] = rs.getString("noidung");
                col[2] = rs.getLong("giatri");
                defaultTableModel.addRow(col);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(IndemnifyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // lấy tên khách hàng dựa theo mã thuê phòng
    public Customer getNameCustomer(int mathuephong) {
        try {
            String sql = "select hotenkh from hosothuephong inner join (dondatphong inner join khachhang on dondatphong.makh = khachhang.makh ) "
                    + "on hosothuephong.madatphong = dondatphong.madatphong "
                    + "where mathuephong = ? ";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ps.setInt(1, mathuephong);
            ResultSet rs = ps.executeQuery();
            Customer cus1 = new Customer();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setHotenkh(rs.getString("hotenkh"));
                cus1.setHotenkh(customer.getHotenkh());
            }
            rs.close();
            ps.close();
            return cus1;
        } catch (SQLException ex) {
            Logger.getLogger(IndemnifyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // thêm thông tin vào phiếu bồi thường
    public void addPhieuboithuong(IndemnifyRoom indemnifyRoom) {
        try {
            String sql = "INSERT INTO phieuboithuong (loai,ngaylap,mathuephong,chitiet,chiphi) VALUES (?,?,?,?,?)";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ps.setString(1, indemnifyRoom.getLoai());
            ps.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
            ps.setInt(3, indemnifyRoom.getMathuephong());
            ps.setString(4, indemnifyRoom.getNoidung());
            ps.setLong(5, indemnifyRoom.getChiphi());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(IndemnifyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pickMathuephong(JComboBox comboBox) {
        try {
            String sql = "SELECT mathuephong FROM hosothuephong WHERE ngaythanhtoan is null";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comboBox.addItem(rs.getInt("mathuephong"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(IndemnifyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getIndemnifyList(DefaultTableModel tableModel, int mathuephong) {

        try {
            tableModel.setRowCount(0);
            String sql = "select chitiet, ngaylap,loai,chiphi from hotelmanagement.phieuboithuong where mathuephong = ?";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ps.setInt(1, mathuephong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Object[] col = new Object[4];
                col[0] = rs.getString("chitiet");
                col[1] = rs.getTimestamp("ngaylap");
                col[2] = rs.getString("loai");
                col[3] = rs.getInt("chiphi");
                tableModel.addRow(col);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(IndemnifyModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

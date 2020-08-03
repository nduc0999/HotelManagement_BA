/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.Dichvu;
import BasicClass.Dichvuphong;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ServiceModel {

    public ArrayList<Dichvu> getDichvu() throws SQLException {
        ArrayList<Dichvu> ListServices = new ArrayList<>();
        String sql = "Select * from hotelmanagement.dichvu";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Dichvu dichvu;
        while (rs.next()) {
            dichvu = new Dichvu(rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4));
            ListServices.add(dichvu);
        }
        rs.close();
        ps.close();
        return ListServices;
    }

    public ArrayList<Dichvuphong> getDichvuphong(int maphong$, int mathuephong$) throws SQLException {
        ArrayList<Dichvuphong> dvphonglist = new ArrayList<>();
        String sql = " Select * from hotelmanagement.dichvuphong inner join hotelmanagement.dichvu on dichvuphong.madv = dichvu.madv "
                + "where maphong  = ? and mathuephong = ? order by ngaydung asc";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setInt(1, maphong$);
        ps.setInt(2, mathuephong$);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // formatter.parse(rs.getDate(sql))
            Dichvuphong dichvuphong = new Dichvuphong(rs.getInt("mathuephong"), rs.getInt("maphong"), rs.getTimestamp("ngaydung"),
                    rs.getString("tendv"), rs.getInt("dongia"), rs.getInt("soluong"));
            dvphonglist.add(dichvuphong);
        }
        rs.close();
        ps.close();
        return dvphonglist;
    }

    public void addService(Dichvu dichvu) throws SQLException {
        String sql = "INSERT INTO dichvu VALUES (?,?,?,?);";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setInt(1, dichvu.getMadv());
        ps.setString(2, dichvu.getTendv());
        ps.setString(3, dichvu.getDvtinh());
        ps.setInt(4, dichvu.getDongia());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteService(int madv) throws SQLException {
        String sql = "DELETE FROM dichvu WHERE madv = ? ";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setInt(1, madv);
        ps.executeUpdate();
        ps.close();
    }

    public void updateService(Dichvu dichvu) throws SQLException {
        String sql = "UPDATE dichvu set tendv = ?, dvtinh = ?, dongia = ? "
                + "WHERE madv = ?";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
        ps.setString(1, dichvu.getTendv());
        ps.setString(2, dichvu.getDvtinh());
        ps.setInt(3, dichvu.getDongia());
        ps.setInt(4, dichvu.getMadv());
        ps.executeUpdate();
        ps.close();
    }

    public void deleteRoomService(Dichvuphong dichvuphong) {
        try {
            String sql = "DELETE FROM dichvuphong  WHERE \n"
                    + " mathuephong = ? and madv = (select madv from dichvu where tendv = ? ) and ngaydung =? and maphong =?";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ps.setInt(1, dichvuphong.getMathuephong());
            ps.setString(2, dichvuphong.getTendichvu());
            ps.setString(3, String.valueOf(dichvuphong.getNgaydung()));
            ps.setInt(4, dichvuphong.getMaphong());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertRoomService(Dichvuphong dichvuphong) {
        try {
            String sql = " INSERT INTO hotelmanagement.dichvuphong (mathuephong, madv, dongia, ngaydung, maphong, soluong)"
                    + " VALUES (?, ?, ?, ?, ?,?);";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ps.setInt(1, dichvuphong.getMathuephong());
            ps.setInt(2, dichvuphong.getMadv());
            ps.setInt(3, dichvuphong.getDongia());
            ps.setString(4, String.valueOf(dichvuphong.getNgaydung()));
            ps.setInt(5, dichvuphong.getMaphong());
            ps.setInt(6, dichvuphong.getSoluong());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateRoomService(Dichvuphong dichvuphong) {
        try {
            String sql = "UPDATE hotelmanagement.dichvuphong SET soluong = ? \n"
                    + "WHERE mathuephong = ? \n"
                    + " and madv = (select madv from dichvu where tendv = ? )  \n"
                    + "and ngaydung = ? \n"
                    + "and maphong = ?";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ps.setInt(1, dichvuphong.getSoluong());
            ps.setInt(2, dichvuphong.getMathuephong());
            ps.setString(3, dichvuphong.getTendichvu());
            ps.setString(4, String.valueOf(dichvuphong.getNgaydung()));
            ps.setInt(5, dichvuphong.getMaphong());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

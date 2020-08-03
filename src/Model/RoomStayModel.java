/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.Chitietthuephong;
import BasicClass.Dichvu;
import BasicClass.Hosothuephong;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yue
 */
public class RoomStayModel {

    //Lập hồ sơ thuê phòng theo mã đặt phòng
    int addProfile(int id) throws SQLException {
        int mathuephong = 0;
        try {
            Statement stmt = DbConn.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO hosothuephong "
                    + "(madatphong) VALUES (" + id + ")", Statement.RETURN_GENERATED_KEYS);
            ResultSet addOrder = stmt.getGeneratedKeys();
            addOrder.next();
            mathuephong = addOrder.getInt(1);
            ResultSet roomStay = stmt.executeQuery("SELECT * FROM chitietdatphong WHERE madatphong = " + id);
            while (roomStay.next()) {
                addRoomToProfile(mathuephong, roomStay.getInt("maphong"), roomStay.getInt("dongiadat"));
            }
            addOrder.close();
            roomStay.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(RoomStayModel.class.getName()).log(Level.SEVERE, null, ex);
            DbConn.getConnection().rollback();
        }
        return mathuephong;
    }

    //Thêm phòng vào hồ sơ
    public void addRoomToProfile(int id, int room, int price) throws SQLException {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        PreparedStatement ps = DbConn.getConnection().prepareStatement("INSERT INTO chitietthuephong "
                + "(mathuephong, maphong, thucnhan, dongiatt) "
                + "VALUES (?, ?, ?, ?)");
        ps.setInt(1, id);
        ps.setInt(2, room);
        ps.setTimestamp(3, currentTimestamp);
        ps.setInt(4, price);
        ps.executeUpdate();
        ps.close();
    }

    public ArrayList<Hosothuephong> getHosothuephong() throws SQLException {
        ArrayList<Hosothuephong> arrayList = new ArrayList<>();
        ResultSet rs = DbConn.getConnection().createStatement().executeQuery("SELECT mathuephong, hotenkh, ngaythanhtoan, tongcoc "
                + "FROM hosothuephong "
                + "INNER JOIN (dondatphong INNER JOIN khachhang ON dondatphong.makh = khachhang.makh) "
                + "ON hosothuephong.madatphong = dondatphong.madatphong "
                + "ORDER BY IF(isnull(ngaythanhtoan),1,0) DESC, ngaythanhtoan DESC, ngaytra ASC");
        Hosothuephong hoso = null;
        while (rs.next()) {
            hoso = new Hosothuephong();
            hoso.setMathuephong(rs.getInt("mathuephong"));
            hoso.setTenkh(rs.getString("hotenkh"));
            hoso.setNgaythanhtoan(rs.getTimestamp("ngaythanhtoan"));
            hoso.setTongcoc(rs.getInt("tongcoc"));
            arrayList.add(hoso);
        }
        rs.close();
        return arrayList;
    }

    public ArrayList<Chitietthuephong> getChitietthuephong(int mathuephong) throws SQLException {
        ArrayList<Chitietthuephong> arrayList = new ArrayList<>();
        ResultSet rs = DbConn.getConnection().createStatement().executeQuery("SELECT tenphong, tenloaiphong, thucnhan, thuctra, dongiatt "
                + "FROM chitietthuephong "
                + "INNER JOIN (phong INNER JOIN loaiphong ON phong.maloaiphong = loaiphong.maloaiphong) "
                + "ON chitietthuephong.maphong = phong.maphong "
                + "Where mathuephong = " + mathuephong);
        Chitietthuephong thuephong = null;
        while (rs.next()) {
            thuephong = new Chitietthuephong();
            thuephong.setTenphong(rs.getString("tenphong"));
            thuephong.setLoaiphong(rs.getString("tenloaiphong"));
            thuephong.setNgaythucnhan(rs.getTimestamp("thucnhan"));
            thuephong.setNgaythuctra(rs.getTimestamp("thuctra"));
            thuephong.setThanhtien(rs.getInt("dongiatt"));
            arrayList.add(thuephong);
        }
        rs.close();
        return arrayList;
    }

    public ArrayList<Dichvu> getDichvu(String tenphong, int mathuephong) throws SQLException {
        Integer roomId = Integer.valueOf(tenphong.substring(1));
        ArrayList<Dichvu> arrayList = new ArrayList<>();
        ResultSet rs = DbConn.getConnection().createStatement().executeQuery("SELECT * FROM dichvu "
                + "INNER JOIN dichvuphong ON dichvu.madv = dichvuphong.madv "
                + "WHERE maphong = " + roomId + " and mathuephong = " + mathuephong);
        while (rs.next()) {
            Dichvu dichvu = new Dichvu();
            dichvu.setMadv(rs.getInt("madv"));
            dichvu.setNgaydung(rs.getTimestamp("ngaydung"));
            dichvu.setTendv(rs.getString("tendv"));
            dichvu.setDvtinh(rs.getString("dvtinh"));
            dichvu.setDongia(rs.getInt("dongia"));
            dichvu.setSoluong(rs.getInt("soluong"));
            arrayList.add(dichvu);
        }
        rs.close();
        return arrayList;
    }

    public void checkOutRoom(int stayId, String roomString) throws SQLException {
        Integer roomId = Integer.valueOf(roomString.substring(1));
        PreparedStatement ps = DbConn.getConnection().prepareStatement("UPDATE chitietthuephong SET thuctra = ? "
                + "WHERE mathuephong = ? AND maphong = ?");
        ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        ps.setInt(2, stayId);
        ps.setInt(3, roomId);
        ps.executeUpdate();

        Statement stmt = DbConn.getConnection().createStatement();
        stmt.executeUpdate("UPDATE phong SET trangthai = 0 WHERE maphong = " + roomId);

        ps.close();
        stmt.close();
    }

    public ArrayList<Dichvu> getServiceStayID(int stayId) throws SQLException {

        ArrayList<Dichvu> arrayList = new ArrayList<>();
        ResultSet rs = DbConn.getConnection().createStatement().executeQuery("SELECT * FROM dichvu "
                + "INNER JOIN dichvuphong ON dichvu.madv = dichvuphong.madv "
                + "WHERE mathuephong = " + stayId);
        while (rs.next()) {
            Dichvu dichvu = new Dichvu();
            dichvu.setMadv(rs.getInt("madv"));
            dichvu.setMaphong(rs.getInt("maphong"));
            dichvu.setNgaydung(rs.getTimestamp("ngaydung"));
            dichvu.setTendv(rs.getString("tendv"));
            dichvu.setDvtinh(rs.getString("dvtinh"));
            dichvu.setDongia(rs.getInt("dongia"));
            dichvu.setSoluong(rs.getInt("soluong"));
            arrayList.add(dichvu);
        }
        rs.close();
        return arrayList;
    }

    public void checkOutProfile(int stayId, int tongthanhtoan) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String sql = "UPDATE hosothuephong SET ngaythanhtoan = ?, tongthanhtoan = ? WHERE mathuephong =? ";
            PreparedStatement ps = DbConn.getConnection().prepareStatement(sql);
            ps.setTimestamp(1, timestamp);
            ps.setInt(2, tongthanhtoan);
            ps.setInt(3, stayId);
            ps.executeUpdate();
//            PreparedStatement ps2 = DbConn.getConnection().prepareStatement("UPDATE phong SET tranhthai = 0 WHERE trangthai = ?");
//            ps2.setInt(1, stayId);
//            ps2.executeUpdate();
            ps.close();
//            ps2.close();
        } catch (SQLException ex) {
            Logger.getLogger(RoomStayModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

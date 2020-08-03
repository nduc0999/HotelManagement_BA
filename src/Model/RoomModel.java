/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.Room;
import BasicClass.RoomInfo;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Yue
 */
public class RoomModel {

    public ArrayList<Room> getRoomState() throws SQLException {
        Statement stmt = DbConn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM phong WHERE phong.trangthai != 0");
        ArrayList<Room> rooms = new ArrayList<>();
        while (rs.next()) {
            Room room = new Room();
            room.setMaphong(rs.getInt("maphong"));
            room.setTrangthai(rs.getInt("trangthai"));
            rooms.add(room);
        }
        rs.close();
        stmt.close();

        return rooms;
    }

    //Lấy thông tin phòng theo mã thuê phòng
    public RoomInfo getRoomById(int id) throws SQLException {
        Statement stmt = DbConn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT hotenkh, hosothuephong.madatphong, chitietthuephong.mathuephong, chitietthuephong.thucnhan, sdt "
                + "FROM ((khachhang INNER JOIN dondatphong ON khachhang.makh = dondatphong.makh) "
                + "INNER JOIN hosothuephong ON dondatphong.madatphong = hosothuephong.madatphong) "
                + "INNER JOIN chitietthuephong ON hosothuephong.mathuephong = chitietthuephong.mathuephong "
                + "WHERE chitietthuephong.maphong = " + id
                + " ORDER BY chitietthuephong.thucnhan desc");
        RoomInfo roomInfo = null;
        if (rs.next()) {
            roomInfo = new RoomInfo(rs.getString("hotenkh"),
                    rs.getInt("madatphong"),
                    rs.getInt("mathuephong"),
                    rs.getTimestamp("thucnhan"),
                    rs.getString("sdt"
                    ));
        }

        return roomInfo;
    }

    //Đổi phòng
    public void changeRoom(int stayId, int oldRoom, int newRoom) throws SQLException {
        PreparedStatement checkOutOldRoom = DbConn.getConnection().prepareStatement(
                "UPDATE chitietthuephong SET thuctra = ? WHERE mathuephong = ? AND maphong = ?");
        checkOutOldRoom.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        checkOutOldRoom.setInt(2, stayId);
        checkOutOldRoom.setInt(3, oldRoom);
        checkOutOldRoom.executeUpdate();

        Statement stmt = DbConn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT dongia "
                + "FROM phong INNER JOIN loaiphong ON phong.maloaiphong = loaiphong.maloaiphong "
                + "WHERE maphong = " + newRoom);
        if (rs.next()) {
            new RoomStayModel().addRoomToProfile(stayId, newRoom, rs.getInt("dongia"));
        }

        stmt.executeUpdate("UPDATE phong SET trangthai = " + stayId + " WHERE maphong = " + newRoom);
        stmt.executeUpdate("UPDATE phong SET trangthai = 0 WHERE maphong = " + oldRoom);

        checkOutOldRoom.close();
        rs.close();
        stmt.close();
    }
}

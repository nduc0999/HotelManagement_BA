/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.Order;
import BasicClass.OrderRoom;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author Yue
 */
public class OrderModel {

    //Tạo đơn đặt phòng và trả về mã đặt phòng
    public int addOrder(Order order) throws SQLException {
        String SQL = "INSERT INTO dondatphong (makh, ngaydat, ngaynhan, ngaytra, tongcoc, trangthai) "
                + "VALUES (? ,? ,? ,? ,? ,?)";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, order.getMakh());
        ps.setTimestamp(2, order.getNgaydat());
        ps.setTimestamp(3, order.getNgaynhan());
        ps.setTimestamp(4, order.getNgaytra());
        ps.setInt(5, order.getTongcoc());
        ps.setInt(6, order.getTrangthai());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        int result = -1;
        if (rs.next()) {
            result = rs.getInt(1);
        }
        rs.close();
        ps.close();

        return result;
    }

    //Lưu các phòng được đặt trong đơn đặt phòng
    public void addOrderRoomsByOrderId(int orderId, OrderRoom orderRoom) throws SQLException {
        String SQL = "INSERT INTO chitietdatphong (madatphong, maphong, dongiadat) "
                + "VALUES (?, ?, ?)";
        PreparedStatement ps = DbConn.getConnection().prepareStatement(SQL);
        ps.setInt(1, orderId);
        ps.setInt(2, orderRoom.getMaphong());
        ps.setInt(3, orderRoom.getDongiadat());
        ps.executeUpdate();

        ps.close();
    }

    //Kiểm tra lịch đặt phòng theo thời gian (String)
    public ArrayList<Integer> getOrderedRoomsByString(String ngayNhan, String ngayTra) throws SQLException, ParseException {
        Timestamp checkin = new Timestamp(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(ngayNhan.concat(" 14:00:01")).getTime());
        Timestamp checkout = new Timestamp(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(ngayTra.concat(" 11:59:59")).getTime());

        return getOrderedRoomsByTimestamp(checkin, checkout);
    }

    //Kiểm tra lịch đặt phòng theo thời gian (Timestamp)
    public ArrayList<Integer> getOrderedRoomsByTimestamp(Timestamp checkin, Timestamp checkout) throws SQLException {
        PreparedStatement ps = DbConn.getConnection().prepareStatement(
                "SELECT chitietdatphong.maphong "
                + "FROM dondatphong INNER JOIN chitietdatphong ON dondatphong.madatphong = chitietdatphong.madatphong "
                + "WHERE  ngaytra >= ? AND ngaynhan <= ?  AND dondatphong.trangthai != 3"
                + " GROUP BY chitietdatphong.maphong"
        );
        ps.setTimestamp(1, checkin);
        ps.setTimestamp(2, checkout);
        ResultSet rs = ps.executeQuery();
        ArrayList<Integer> maphongList = new ArrayList<>();
        while (rs.next()) {
            maphongList.add(rs.getInt(1));
        }
        rs.close();
        ps.close();

        return maphongList;
    }

    //Lấy danh sách đơn đặt phòng
    public ArrayList<Order> getOrdersList() throws SQLException {
        Statement stmt = DbConn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM dondatphong ORDER BY trangthai ASC");
        ArrayList<Order> ordersList = new ArrayList<>();

        while (rs.next()) {
            ordersList.add(new Order(
                    rs.getInt("madatphong"),
                    rs.getInt("makh"),
                    rs.getTimestamp("ngaydat"),
                    rs.getTimestamp("ngaynhan"),
                    rs.getTimestamp("ngaytra"),
                    rs.getInt("tongcoc"),
                    rs.getInt("trangthai")
            ));
        }
        rs.close();
        stmt.close();

        return ordersList;
    }

    //Lấy danh sách đơn đặt phòng theo mã phòng
    public ArrayList<Order> getOrderByRoomId(int id) throws SQLException {
        PreparedStatement ps = DbConn.getConnection().prepareStatement(
                "SELECT dondatphong.madatphong, ngaynhan, ngaytra, trangthai "
                + "FROM dondatphong INNER JOIN chitietdatphong "
                + "ON dondatphong.madatphong = chitietdatphong.madatphong "
                + "WHERE maphong = ? AND (trangthai = 0 OR trangthai = 1) "
                + "ORDER BY ngaynhan ASC");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        ArrayList<Order> orders = new ArrayList<>();
        while (rs.next()) {
            Order order = new Order();
            order.setMadatphong(rs.getInt("madatphong"));
            order.setNgaynhan(rs.getTimestamp("ngaynhan"));
            order.setNgaytra(rs.getTimestamp("ngaytra"));
            order.setTrangthai(rs.getInt("trangthai"));
            orders.add(order);
        }
        rs.close();
        ps.close();

        return orders;
    }

    //Lấy danh sách các phòng trong đơn đặt
    public ArrayList<OrderRoom> getOrderDetailByOrderId(int orderId) throws SQLException {
        PreparedStatement ps = DbConn.getConnection().prepareStatement("SELECT * FROM chitietdatphong "
                + "WHERE madatphong = ?");
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        ArrayList<OrderRoom> orderRooms = new ArrayList<>();
        while (rs.next()) {
            orderRooms.add(new OrderRoom(
                    rs.getInt("maphong"),
                    rs.getInt("dongiadat")
            ));

        }
        rs.close();
        ps.close();

        return orderRooms;
    }

    //Hủy phòng
    public void cancelOrder(int id) throws SQLException {
        Statement stmt = DbConn.getConnection().createStatement();
        stmt.executeUpdate("UPDATE dondatphong SET trangthai = 3 WHERE madatphong = " + id);

    }

    //Cập nhật tiền cọc
    public void updateDeposit(int id) throws SQLException {
        Statement stmt = DbConn.getConnection().createStatement();
        stmt.executeUpdate("UPDATE dondatphong SET trangthai = 1 WHERE madatphong = " + id);

    }

    //Nhận phòng
    public void checkInOrder(int id) throws SQLException {
        Statement stmt = DbConn.getConnection().createStatement();
        int mathuephong = new RoomStayModel().addProfile(id);
        stmt.executeUpdate("UPDATE dondatphong SET trangthai = 2 WHERE madatphong = " + id);
        stmt.executeUpdate("UPDATE phong "
                + "SET trangthai = " + mathuephong
                + " WHERE maphong IN(SELECT maphong "
                + "FROM chitietdatphong "
                + "WHERE madatphong = " + id + ")");

        stmt.close();
    }

    //Lấy ngày trả theo mã đặt phòng
    public Timestamp getCheckOutById(int id) throws SQLException {
        Statement stmt = DbConn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT ngaytra FROM dondatphong WHERE madatphong = " + id);
        Timestamp timestamp = null;
        if (rs.next()) {
            timestamp = rs.getTimestamp("ngaytra");
        }
        return timestamp;
    }

    //Đặt phòng tính tới vấn đề đổi phòng
    public ArrayList<Integer> getRoomsInOrder(String ngayNhan, String ngayTra) throws SQLException, ParseException {
        Timestamp checkin = new Timestamp(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(ngayNhan.concat(" 14:00:01")).getTime());
        Timestamp checkout = new Timestamp(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(ngayTra.concat(" 11:59:59")).getTime());
        ArrayList<Integer> roomList = new ArrayList<>();
        //chọn các đơn đã nhận
        PreparedStatement ps1 = DbConn.getConnection().prepareStatement("select maphong"
                + " from chitietthuephong inner join hosothuephong "
                + "on chitietthuephong.mathuephong = hosothuephong.mathuephong "
                + "inner join dondatphong on hosothuephong.madatphong = dondatphong.madatphong "
                + "where thuctra is null and ngaytra >= ?");
        ps1.setTimestamp(1, checkin);
        ResultSet checked_in = ps1.executeQuery();
        while (checked_in.next()) {
            roomList.add(checked_in.getInt(1));
        }
        //chọn các đơn chưa nhận
        PreparedStatement ps2 = DbConn.getConnection().prepareStatement("SELECT chitietdatphong.maphong "
                + "FROM dondatphong INNER JOIN chitietdatphong ON dondatphong.madatphong = chitietdatphong.madatphong "
                + "WHERE  ngaytra >= ? AND ngaynhan <= ?  AND dondatphong.trangthai < 2"
                + " GROUP BY chitietdatphong.maphong");
        ps2.setTimestamp(1, checkin);
        ps2.setTimestamp(2, checkout);
        ResultSet non_check_in = ps2.executeQuery();
        while (non_check_in.next()) {
            roomList.add(non_check_in.getInt(1));
        }
        ps1.close();
        ps2.close();

        return roomList;
    }
}

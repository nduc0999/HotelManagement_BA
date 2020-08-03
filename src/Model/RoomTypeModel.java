/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.RoomType;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yue
 */
public class RoomTypeModel {

    //Tạo danh sách các loại phòng
    public ArrayList<RoomType> getRoomTypeList() throws SQLException {
        ArrayList<RoomType> roomTypeList = new ArrayList<>();
        Statement stmt = DbConn.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM loaiphong");
        while (rs.next()) {
            roomTypeList.add(new RoomType(
                    rs.getInt("maloaiphong"),
                    rs.getString("tenloaiphong"),
                    rs.getInt("dongia"),
                    rs.getInt("soluong")
            ));

        }
        return roomTypeList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.Thongtinnhanphong;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class CheckInModel {

    public CheckInModel() {
    }

    public Thongtinnhanphong getThongtinnhanphongs(int madatphong) throws SQLException {
        Statement st = DbConn.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT hotenkh, cmnd, quoctich,ngaynhan,ngaytra,tongcoc,dondatphong.trangthai "
                + "FROM dondatphong INNER JOIN khachhang on dondatphong.makh = khachhang.makh "
                + "INNER JOIN (chitietdatphong inner join phong "
                + "ON chitietdatphong.maphong=phong.maphong) "
                + "ON dondatphong.madatphong = chitietdatphong.madatphong "
                + "WHERE dondatphong.madatphong = " + madatphong + ";"
        );
        Thongtinnhanphong thongtin = null;
        if (rs.next()) {

            thongtin = new Thongtinnhanphong();
            thongtin.setHotenkh(rs.getString("hotenkh"));
            thongtin.setCmnd(rs.getString("cmnd"));
            thongtin.setQuoctich(rs.getString("quoctich"));
            thongtin.setNgaynhan(rs.getString("ngaynhan"));
            thongtin.setNgaytra(rs.getString("ngaytra"));
            thongtin.setTongcoc(rs.getInt("tongcoc"));
            thongtin.setTrangthai(rs.getInt("trangthai"));
            return thongtin;
        }
        rs.close();
        st.close();

        return thongtin;
    }

}

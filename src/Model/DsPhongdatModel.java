/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BasicClass.DsPhongdat;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DsPhongdatModel {

    private ArrayList<DsPhongdat> arrayList = null;

    public ArrayList<DsPhongdat> getdsPhongdat(int madatphong) throws SQLException {
        Statement st = DbConn.getConnection().createStatement();
        ResultSet rs = st.executeQuery("SELECT  chitietdatphong.maphong, tenphong, tenloaiphong, dongia\n"
                + "FROM chitietdatphong INNER JOIN (phong INNER JOIN loaiphong ON phong.maloaiphong = loaiphong.maloaiphong)\n"
                + "ON chitietdatphong.maphong = phong.maphong \n"
                + "WHERE madatphong =" + madatphong + ";"
        );
        arrayList = new ArrayList<>();
        DsPhongdat dsPhongdat = null;
        while (rs.next()) {
            dsPhongdat = new DsPhongdat();
            dsPhongdat.setMaphong(rs.getInt("maphong"));
            dsPhongdat.setTenphong(rs.getString("tenphong"));
            dsPhongdat.setLoaiphong(rs.getString("tenloaiphong"));
            dsPhongdat.setDongia(rs.getInt("dongia"));
            arrayList.add(dsPhongdat);
        }
        rs.close();
        st.close();

        return arrayList;
    }
}

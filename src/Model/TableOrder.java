/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
import BasicClass.Phieudat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TableOrder {

    private ArrayList<Phieudat> arrayList;

    public ArrayList<Phieudat> getorderList() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Statement st = null;
        arrayList = new ArrayList<>();
        try {
            st = DbConn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM dondatphong ORDER BY trangthai ASC");
            while (rs.next()) {
                Phieudat phieudatphong1 = new Phieudat();
                phieudatphong1.setMadatphong(rs.getInt("madatphong"));
                phieudatphong1.setMakh(rs.getInt("makh"));
                phieudatphong1.setNgaydat(simpleDateFormat.format(rs.getDate("ngaydat")));
                phieudatphong1.setNgaynhan(rs.getString("ngaynhan"));
                phieudatphong1.setNgaytra(rs.getString("ngaytra"));
                phieudatphong1.setTongcoc(rs.getInt("tongcoc"));
                phieudatphong1.setTrangthai(rs.getInt("trangthai"));
                arrayList.add(phieudatphong1);
            }
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arrayList;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicClass;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class Hosothuephong {

    private int mathuephong;
    private String tenkh;
    private Timestamp ngaythanhtoan;
    private int tongcoc;

    public Hosothuephong() {

    }

    public Hosothuephong(int mathuephong, String tenkh, Timestamp ngaythanhtoan, int tongcoc) {
        this.mathuephong = mathuephong;
        this.tenkh = tenkh;
        this.ngaythanhtoan = ngaythanhtoan;
        this.tongcoc = tongcoc;
    }

    public int getMathuephong() {
        return mathuephong;
    }

    public void setMathuephong(int mathuephong) {
        this.mathuephong = mathuephong;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public Timestamp getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setNgaythanhtoan(Timestamp ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public int getTongcoc() {
        return tongcoc;
    }

    public void setTongcoc(int tongcoc) {
        this.tongcoc = tongcoc;
    }

}

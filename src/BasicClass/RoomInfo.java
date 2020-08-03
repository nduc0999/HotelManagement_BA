/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicClass;

import java.sql.Timestamp;

/**
 *
 * @author Yue
 */
public class RoomInfo {

    private String hotenkh;
    private int madatphong;
    private int mathuephong;
    private Timestamp ngaynhan;
    private String sdt;

    public RoomInfo() {
    }

    public RoomInfo(String hotenkh, int madatphong, int mathuephong, Timestamp ngaynhan, String sdt) {
        this.hotenkh = hotenkh;
        this.madatphong = madatphong;
        this.mathuephong = mathuephong;
        this.ngaynhan = ngaynhan;
        this.sdt = sdt;
    }

    public String getHotenkh() {
        return hotenkh;
    }

    public void setHotenkh(String hotenkh) {
        this.hotenkh = hotenkh;
    }

    public int getMadatphong() {
        return madatphong;
    }

    public void setMadatphong(int madatphong) {
        this.madatphong = madatphong;
    }

    public int getMathuephong() {
        return mathuephong;
    }

    public void setMathuephong(int mathuephong) {
        this.mathuephong = mathuephong;
    }

    public Timestamp getNgaynhan() {
        return ngaynhan;
    }

    public void setNgaynhan(Timestamp ngaynhan) {
        this.ngaynhan = ngaynhan;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}

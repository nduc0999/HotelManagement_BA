/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicClass;

/**
 *
 * @author Yue
 */
public class Room {

    private int maphong;
    private String tenphong;
    private int loaiphong;
    private String sdtphong;
    private int trangthai;

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public int getMaloaiphong() {
        return loaiphong;
    }

    public void setMaloaiphong(int loaiphong) {
        this.loaiphong = loaiphong;
    }

    public String getSdtphong() {
        return sdtphong;
    }

    public void setSdtphong(String sdtphong) {
        this.sdtphong = sdtphong;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public Room() {
    }

    public Room(int maphong, String tenphong, int loaiphong, String sdtphong, int trangthai) {
        this.maphong = maphong;
        this.tenphong = tenphong;
        this.loaiphong = loaiphong;
        this.sdtphong = sdtphong;
        this.trangthai = trangthai;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BasicClass;

/**
 *
 * @author Admin
 */
public class DsPhongdat {

    private int maphong;
    private String Tenphong;
    private String loaiphong;
    private int dongia;

    public DsPhongdat() {

    }

    public DsPhongdat(int maphong, String Tenphong, String loaiphong, int dongia) {
        this.maphong = maphong;
        this.Tenphong = Tenphong;
        this.loaiphong = loaiphong;
        this.dongia = dongia;
    }

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public String getTenphong() {
        return Tenphong;
    }

    public void setTenphong(String Tenphong) {
        this.Tenphong = Tenphong;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

}

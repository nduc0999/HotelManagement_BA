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
public class Chitietthuephong {

    private int mathuephong;
    private String tenphong;
    private String loaiphong;
    private Timestamp ngaythucnhan;
    private Timestamp ngaythuctra;
    private int thanhtien;

    public Chitietthuephong() {

    }

    public Chitietthuephong(String tenphong, String loaiphong, Timestamp ngaythucnhan, Timestamp ngaythuctra, int thanhtien) {
        this.tenphong = tenphong;
        this.loaiphong = loaiphong;
        this.ngaythucnhan = ngaythucnhan;
        this.ngaythuctra = ngaythuctra;
        this.thanhtien = thanhtien;
    }

    public int getMathuephong() {
        return mathuephong;
    }

    public void setMathuephong(int mathuephong) {
        this.mathuephong = mathuephong;
    }

    public String getTenphong() {
        return tenphong;
    }

    public void setTenphong(String tenphong) {
        this.tenphong = tenphong;
    }

    public String getLoaiphong() {
        return loaiphong;
    }

    public void setLoaiphong(String loaiphong) {
        this.loaiphong = loaiphong;
    }

    public Timestamp getNgaythucnhan() {
        return ngaythucnhan;
    }

    public void setNgaythucnhan(Timestamp ngaythucnhan) {
        this.ngaythucnhan = ngaythucnhan;
    }

    public Timestamp getNgaythuctra() {
        return ngaythuctra;
    }

    public void setNgaythuctra(Timestamp ngaythuctra) {
        this.ngaythuctra = ngaythuctra;
    }

    public int getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

}

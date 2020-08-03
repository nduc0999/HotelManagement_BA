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
public class IndemnifyRoom {

    private int manoidung;
    private String noidung;
    private Timestamp ngaylap;
    private long chiphi;
    private String loai;
    private int mathuephong;

    public int getMathuephong() {
        return mathuephong;
    }

    public void setMathuephong(int mathuephong) {
        this.mathuephong = mathuephong;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public IndemnifyRoom() {

    }

    public int getManoidung() {
        return manoidung;
    }

    public void setManoidung(int manoidung) {
        this.manoidung = manoidung;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public Timestamp getNgaylap() {
        return ngaylap;
    }

    public void setNgaylap(Timestamp ngaylap) {
        this.ngaylap = ngaylap;
    }

    public long getChiphi() {
        return chiphi;
    }

    public void setChiphi(long chiphi) {
        this.chiphi = chiphi;
    }

    public IndemnifyRoom(String noidung, Timestamp ngaylap, long chiphi) {
        this.noidung = noidung;
        this.ngaylap = ngaylap;
        this.chiphi = chiphi;
    }

}

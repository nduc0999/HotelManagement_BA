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
public class Phieudat {

    private int madatphong;
    private int makh;
    private String ngaydat;
    private String ngaynhan;
    private String ngaytra;
    private int tongcoc;
    private int trangthai;

    public Phieudat() {

    }

    public Phieudat(int madatphong, int makh, String ngaydat, String ngaynhan, String ngaytra, int tongcoc, int trangthai) {
        this.madatphong = madatphong;
        this.makh = makh;
        this.ngaydat = ngaydat;
        this.ngaynhan = ngaynhan;
        this.ngaytra = ngaytra;
        this.tongcoc = tongcoc;
        this.trangthai = trangthai;
    }

    public int getMadatphong() {
        return madatphong;
    }

    public void setMadatphong(int madatphong) {
        this.madatphong = madatphong;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(String ngaydat) {
        this.ngaydat = ngaydat;
    }

    public String getNgaynhan() {
        return ngaynhan;
    }

    public void setNgaynhan(String ngaynhan) {
        this.ngaynhan = ngaynhan;
    }

    public String getNgaytra() {
        return ngaytra;
    }

    public void setNgaytra(String ngaytra) {
        this.ngaytra = ngaytra;
    }

    public int getTongcoc() {
        return tongcoc;
    }

    public void setTongcoc(int tongcoc) {
        this.tongcoc = tongcoc;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

}

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
public class Thongtinnhanphong {

    private int maphieudat;
    private String hotenkh;
    private String cmnd;
    private String quoctich;
    private String ngaynhan;
    private String ngaytra;
    private int tongcoc;
    private int trangthai;

    public Thongtinnhanphong() {

    }

    public Thongtinnhanphong(int maphieudat, String hotenkh, String cmnd, String quoctich, String ngaynhan, String ngaytra, int tongcoc, int trangthai) {
        this.maphieudat = maphieudat;
        this.hotenkh = hotenkh;
        this.cmnd = cmnd;
        this.quoctich = quoctich;
        this.ngaynhan = ngaynhan;
        this.ngaytra = ngaytra;
        this.tongcoc = tongcoc;
        this.trangthai = trangthai;
    }

    public int getMaphieudat() {
        return maphieudat;
    }

    public void setMaphieudat(int maphieudat) {
        this.maphieudat = maphieudat;
    }

    public String getHotenkh() {
        return hotenkh;
    }

    public void setHotenkh(String hotenkh) {
        this.hotenkh = hotenkh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
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

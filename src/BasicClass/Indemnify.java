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
public class Indemnify {

    private int manoidung;
    private String noidung;
    private long giatri;

    public Indemnify() {

    }

    public Indemnify(int manoidung, String noidung, long giatri) {
        this.manoidung = manoidung;
        this.noidung = noidung;
        this.giatri = giatri;
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

    public long getGiatri() {
        return giatri;
    }

    public void setGiatri(long giatri) {
        this.giatri = giatri;
    }

}

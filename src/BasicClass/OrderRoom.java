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
public class OrderRoom {

    private static int madatphong;
    private int maphong;
    private int dongiadat;

    public OrderRoom() {
    }

    public OrderRoom(int maphong, int dongiadat) {
        this.maphong = maphong;
        this.dongiadat = dongiadat;
    }

    public static int getMadatphong() {
        return madatphong;
    }

    public static void setMadatphong(int madatphong) {
        OrderRoom.madatphong = madatphong;
    }

    public int getMaphong() {
        return maphong;
    }

    public void setMaphong(int maphong) {
        this.maphong = maphong;
    }

    public int getDongiadat() {
        return dongiadat;
    }

    public void setDongiadat(int dongiadat) {
        this.dongiadat = dongiadat;
    }

}

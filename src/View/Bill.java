package View;

import BasicClass.Dichvu;
import Model.RoomStayModel;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Model.IndemnifyModel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class Bill extends javax.swing.JDialog {

    private JTable roomList;
    private int stayId;
    private String customerName;
    private DefaultTableModel roomListModel = new DefaultTableModel() {
    };
    private DefaultTableModel serviceListModel;
    private int tienCoc;
    private int tongTiendv = 0;
    private int tongTienphong = 0;
    private RoomStayModel roomStayModel = new RoomStayModel();
    private IndemnifyModel indemnifyModel = new IndemnifyModel();
    private int tongthu = 0, tongchi = 0;

    public Bill(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    public Bill(java.awt.Frame parent, boolean modal, JTable roomList, int stayId, String customerName, int tienCoc) {
        super(parent, modal);
        this.roomList = roomList;

        this.stayId = stayId;
        this.customerName = customerName;
        this.tienCoc = tienCoc;
        initComponents();
        initializeRoomList();
        initializeServiceList();
        initializeImdemnifyList();
        thuChiBoiThuong();
        tenkh.setText(customerName);
        mathuephong.setText(String.format("%07d", stayId));
        tiencoc.setText(new DecimalFormat("#,###.0 vnd").format(tienCoc));
        jtFTongtienphong.setText(new DecimalFormat("#,###.0 vnd").format(tongTienphong));
        jtFTongtiendv.setText(new DecimalFormat("#,###.0 vnd").format(tongTiendv));
        jTFTongcong.setText(new DecimalFormat("#,###.0 vnd").format(tongTienphong + tongTiendv));
        jTFTongthanhtoan.setText(new DecimalFormat("#,###.0 vnd").format(tongTienphong + tongTiendv + tongthu - tienCoc));
        jTFTongThu.setText(new DecimalFormat("#,###.0 vnd").format(tongthu));
        jTFTongChi.setText(new DecimalFormat("#,###.0 vnd").format(tongchi));
    }

    private void initializeRoomList() {
        roomListModel = (DefaultTableModel) dsthuephong.getModel();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < dsthuephong.getColumnCount(); i++) {
            dsthuephong.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            dsthuephong.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        for (int i = 0; i < roomList.getRowCount(); i++) {
            Object[] col = new Object[7];
            col[0] = roomList.getValueAt(i, 0);
            col[1] = roomList.getValueAt(i, 1);
            col[2] = roomList.getValueAt(i, 2);
            col[3] = roomList.getValueAt(i, 3);
            col[4] = roomList.getValueAt(i, 4);
            col[5] = PhuThu((int) col[4], (Timestamp) col[2], (Timestamp) col[3]);
            if (col[3] == null) {
                col[6] = 0;
            } else {
                col[6] = Thanhtien((int) col[4], Timestamp.valueOf(String.valueOf(col[2])), Timestamp.valueOf(String.valueOf(col[3])));
            }
            tongTienphong += (int) col[6];
            roomListModel.addRow(col);
        }
    }

    private long calculateStayTime(Timestamp thucnhan, Timestamp thuctra) {
        long HOUR = 1000 * 60 * 60;
        long DAY = HOUR * 24;

        Timestamp checkIn;
        Timestamp checkOut;

        if (thucnhan.getHours() >= 5
                && thucnhan.getHours() < 14) {
            checkIn = new Timestamp(thucnhan.getTime());
        } else {
            checkIn = new Timestamp(thucnhan.getTime() - 14 * HOUR);
        }
        checkIn.setHours(14);
        checkIn.setMinutes(0);
        checkIn.setSeconds(0);

        if (thucnhan.getHours() >= 12
                && thucnhan.getHours() < 18) {
            checkOut = new Timestamp(thuctra.getTime());
        } else {
            checkOut = new Timestamp(thuctra.getTime() + 6 * HOUR);
        }
        checkOut.setHours(12);
        checkOut.setMinutes(0);
        checkOut.setSeconds(0);

        return (checkOut.getTime() - checkIn.getTime()) / DAY + 1;
    }

    private int Thanhtien(int dongia, Timestamp thucnhan, Timestamp thuctra) {
        int pt = PhuThu(dongia, thucnhan, thuctra);
        long songay = calculateStayTime(thucnhan, thuctra);
        int tien = (int) (dongia * songay + pt);
        return tien;
    }

    private int PhuThu(int dongia, Timestamp thucnhan, Timestamp thuctra) {
        int phuthu = 0;
        if (thucnhan.getHours() >= 5 && thucnhan.getHours() < 14) {
            phuthu += (int) (dongia * 0.15);
        }
        if (thuctra.getHours() >= 12 && thuctra.getHours() < 18) {
            phuthu += (int) (dongia * 0.15);
        }
        return phuthu;
    }

    private int calculateMoney(int dongia, Object thucnhan, Object thuctra) {
        return 0;
    }

    Object[] columnsname1 = {"maph", "A", "B", "C", "D", "E"};

    public Class getaClass1(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return Timestamp.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
        }
        return null;
    }

    ;
    private void initializeServiceList() {
        try {
            ArrayList<Dichvu> dichvu = new RoomStayModel().getServiceStayID(stayId);
            DefaultTableModel defaultTableModel = new DefaultTableModel() {

                public Class getColumnClass1(int column) {
                    return getaClass1(column);
                }
            };
            dsDichvu.setModel(defaultTableModel);
            defaultTableModel.addColumn("Dịch vụ");
            defaultTableModel.addColumn("Ngày dùng");
            defaultTableModel.addColumn("Phòng");
            defaultTableModel.addColumn("Đơn giá");
            defaultTableModel.addColumn("Số lượng");
            defaultTableModel.addColumn("Thành tiền");

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < dsDichvu.getColumnCount(); i++) {
                dsDichvu.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
                dsDichvu.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
            for (int i = 0; i < dichvu.size(); i++) {
                Object[] col = new Object[6];
                col[0] = dichvu.get(i).getTendv();
                col[1] = dichvu.get(i).getNgaydung();
                col[2] = dichvu.get(i).getMaphong();
                col[3] = dichvu.get(i).getDongia();
                col[4] = dichvu.get(i).getSoluong();
                col[5] = calculateMoneyService((int) col[3], (int) col[4]);
                tongTiendv += (int) col[5];
                defaultTableModel.addRow(col);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bill.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int calculateMoneyService(int dongia, int Soluong) {
        return dongia * Soluong;
    }

    private void initializeImdemnifyList() {
        indemnifyModel.getIndemnifyList((DefaultTableModel) indemnifyList.getModel(), stayId);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < indemnifyList.getColumnCount(); i++) {
            indemnifyList.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            indemnifyList.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void thuChiBoiThuong() {
        JTextField loai = new JTextField();
        loai.setText("Thu");
        for (int i = 0; i < indemnifyList.getRowCount(); i++) {
            if (indemnifyList.getValueAt(i, 2).toString().equals(loai.getText())) {
                tongthu += Integer.parseInt(indemnifyList.getValueAt(i, 3).toString());
            } else {
                tongchi += Integer.parseInt(indemnifyList.getValueAt(i, 3).toString());
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        tieude = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        main = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        mathuephong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tenkh = new javax.swing.JTextField();
        jpanel123 = new javax.swing.JPanel();
        jlableTongcong = new javax.swing.JLabel();
        jTFTongcong = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tiencoc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTFTongthanhtoan = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        dichvu = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dsthuephong = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jtFTongtienphong = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dsDichvu = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jtFTongtiendv = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        indemnifyList = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jTFTongChi = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTFTongThu = new javax.swing.JTextField();
        Chot = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        jLabel16.setText("jLabel16");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 255));
        setModal(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 700));

        jPanel3.setLayout(new java.awt.BorderLayout(0, 8));

        tieude.setPreferredSize(new java.awt.Dimension(886, 120));
        tieude.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/architecture-and-city2.png"))); // NOI18N
        jPanel1.add(jLabel1);

        tieude.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setEnabled(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tên khách sạn/ Hotel name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        jPanel2.add(jLabel2, gridBagConstraints);

        jTextField1.setText("Khách sạn KHODU");
        jTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));
        jTextField1.setEnabled(false);
        jTextField1.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel2.add(jTextField1, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Địa chỉ/Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        jPanel2.add(jLabel3, gridBagConstraints);

        jTextField2.setText("Hà Nội");
        jTextField2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField2.setEnabled(false);
        jTextField2.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel2.add(jTextField2, gridBagConstraints);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Số điện thoại/Tel:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        jPanel2.add(jLabel4, gridBagConstraints);

        jTextField3.setText("09696969696");
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField3.setEnabled(false);
        jTextField3.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel2.add(jTextField3, gridBagConstraints);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Email:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.5;
        jPanel2.add(jLabel5, gridBagConstraints);

        jTextField4.setText("Hotel@gmail.com");
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTextField4.setEnabled(false);
        jTextField4.setOpaque(false);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 50);
        jPanel2.add(jTextField4, gridBagConstraints);

        tieude.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.add(tieude, java.awt.BorderLayout.PAGE_START);

        main.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        main.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel4.setRequestFocusEnabled(false);
        jPanel4.setVerifyInputWhenFocusTarget(false);
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel6.setText("Hồ sơ thuê phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jLabel6, gridBagConstraints);

        mathuephong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel4.add(mathuephong, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel7.setText("Chốt hóa đơn");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 1.4;
        jPanel4.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Họ tên khách hàng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jLabel8, gridBagConstraints);

        tenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 50);
        jPanel4.add(tenkh, gridBagConstraints);

        main.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jpanel123.setLayout(new java.awt.GridBagLayout());

        jlableTongcong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlableTongcong.setForeground(new java.awt.Color(255, 0, 51));
        jlableTongcong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlableTongcong.setText("Tổng cộng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jlableTongcong, gridBagConstraints);

        jTFTongcong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFTongcong.setForeground(new java.awt.Color(255, 0, 51));
        jTFTongcong.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jTFTongcong, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Đã cọc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jLabel12, gridBagConstraints);

        tiencoc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tiencoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(tiencoc, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 51));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Tổng thanh toán:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jLabel13, gridBagConstraints);

        jTFTongthanhtoan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTFTongthanhtoan.setForeground(new java.awt.Color(255, 0, 51));
        jTFTongthanhtoan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jpanel123.add(jTFTongthanhtoan, gridBagConstraints);

        main.add(jpanel123, java.awt.BorderLayout.PAGE_END);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(50, 291));
        jPanel7.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Phòng");
        jPanel7.add(jLabel9);

        dichvu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dichvu.setText("Dịch vụ");
        jPanel7.add(dichvu);

        jLabel15.setText("<html>   Bồi<br>thường</html>");
        jPanel7.add(jLabel15);

        jPanel6.add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 10, 10));

        jPanel5.setLayout(new java.awt.BorderLayout());

        dsthuephong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Phòng", "Loại phòng", "Thực nhận", "Thực trả", "Đơn giá", "Phụ thu", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dsthuephong.setShowGrid(true);
        jScrollPane1.setViewportView(dsthuephong);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel11.setPreferredSize(new java.awt.Dimension(10, 30));
        jPanel11.setLayout(new java.awt.BorderLayout());

        jtFTongtienphong.setBackground(new java.awt.Color(255, 255, 153));
        jtFTongtienphong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtFTongtienphong.setForeground(new java.awt.Color(102, 102, 255));
        jtFTongtienphong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtFTongtienphong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jtFTongtienphong.setOpaque(false);
        jtFTongtienphong.setPreferredSize(new java.awt.Dimension(167, 19));
        jPanel11.add(jtFTongtienphong, java.awt.BorderLayout.LINE_END);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Tổng tiền phòng:");
        jLabel10.setPreferredSize(new java.awt.Dimension(500, 13));
        jPanel11.add(jLabel10, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel11, java.awt.BorderLayout.PAGE_END);

        jPanel8.add(jPanel5);

        jPanel10.setLayout(new java.awt.BorderLayout());

        dsDichvu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dịch vụ", "Ngày dùng", "Phòng", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dsDichvu.setShowGrid(true);
        jScrollPane2.setViewportView(dsDichvu);

        jPanel10.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel12.setPreferredSize(new java.awt.Dimension(10, 30));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jtFTongtiendv.setBackground(new java.awt.Color(255, 255, 153));
        jtFTongtiendv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jtFTongtiendv.setForeground(new java.awt.Color(102, 102, 255));
        jtFTongtiendv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtFTongtiendv.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jtFTongtiendv.setOpaque(false);
        jtFTongtiendv.setPreferredSize(new java.awt.Dimension(167, 19));
        jPanel12.add(jtFTongtiendv, java.awt.BorderLayout.LINE_END);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Tổng tiền dịch vụ:");
        jLabel11.setPreferredSize(new java.awt.Dimension(500, 13));
        jPanel12.add(jLabel11, java.awt.BorderLayout.CENTER);

        jPanel10.add(jPanel12, java.awt.BorderLayout.PAGE_END);

        jPanel8.add(jPanel10);

        jPanel13.setLayout(new java.awt.BorderLayout());

        indemnifyList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chi tiết", "Ngày lập", "Loại", "Chi phí"
            }
        ));
        jScrollPane3.setViewportView(indemnifyList);

        jPanel13.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel14.setPreferredSize(new java.awt.Dimension(100, 30));
        jPanel14.setLayout(new java.awt.BorderLayout());

        jTFTongChi.setBackground(new java.awt.Color(255, 255, 153));
        jTFTongChi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTFTongChi.setForeground(new java.awt.Color(102, 102, 255));
        jTFTongChi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFTongChi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTFTongChi.setOpaque(false);
        jTFTongChi.setPreferredSize(new java.awt.Dimension(167, 19));
        jPanel14.add(jTFTongChi, java.awt.BorderLayout.LINE_END);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Tổng thu:");
        jLabel18.setPreferredSize(new java.awt.Dimension(100, 13));
        jPanel15.add(jLabel18, java.awt.BorderLayout.LINE_START);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Tổng chi (Không tính vào tổng hóa đơn):");
        jLabel19.setPreferredSize(new java.awt.Dimension(290, 13));
        jPanel15.add(jLabel19, java.awt.BorderLayout.LINE_END);

        jTFTongThu.setBackground(new java.awt.Color(255, 255, 153));
        jTFTongThu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTFTongThu.setForeground(new java.awt.Color(102, 102, 255));
        jTFTongThu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTFTongThu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        jTFTongThu.setOpaque(false);
        jPanel15.add(jTFTongThu, java.awt.BorderLayout.CENTER);

        jPanel14.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel13.add(jPanel14, java.awt.BorderLayout.PAGE_END);

        jPanel8.add(jPanel13);

        jPanel6.add(jPanel8, java.awt.BorderLayout.CENTER);

        main.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel3.add(main, java.awt.BorderLayout.CENTER);

        Chot.setBackground(new java.awt.Color(255, 153, 153));
        Chot.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        Chot.setForeground(new java.awt.Color(255, 0, 51));
        Chot.setText("Thanh Toán");
        Chot.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        Chot.setPreferredSize(new java.awt.Dimension(63, 50));
        Chot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChotActionPerformed(evt);
            }
        });
        jPanel3.add(Chot, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        filler1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, new java.awt.Color(0, 0, 0)));
        getContentPane().add(filler1, java.awt.BorderLayout.LINE_START);

        filler2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(0, 0, 0)));
        getContentPane().add(filler2, java.awt.BorderLayout.LINE_END);

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(0, 0, 0)));
        jPanel9.setPreferredSize(new java.awt.Dimension(900, 30));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_delete_24px.png"))); // NOI18N
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel14, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel9, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void ChotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChotActionPerformed
        roomStayModel.checkOutProfile(stayId, tongTienphong + tongTiendv - tienCoc);
        JOptionPane.showMessageDialog(null, "Đã cập nhật thanh toán!");

    }//GEN-LAST:event_ChotActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Bill dialog = new Bill(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Chot;
    private javax.swing.JLabel dichvu;
    private javax.swing.JTable dsDichvu;
    private javax.swing.JTable dsthuephong;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTable indemnifyList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFTongChi;
    private javax.swing.JTextField jTFTongThu;
    private javax.swing.JTextField jTFTongcong;
    private javax.swing.JTextField jTFTongthanhtoan;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel jlableTongcong;
    private javax.swing.JPanel jpanel123;
    private javax.swing.JTextField jtFTongtiendv;
    private javax.swing.JTextField jtFTongtienphong;
    private javax.swing.JPanel main;
    private javax.swing.JTextField mathuephong;
    private javax.swing.JTextField tenkh;
    private javax.swing.JTextField tiencoc;
    private javax.swing.JPanel tieude;
    // End of variables declaration//GEN-END:variables
}

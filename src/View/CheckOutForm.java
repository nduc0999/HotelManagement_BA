/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BasicClass.Chitietthuephong;
import BasicClass.Dichvu;
import BasicClass.Hosothuephong;
import Model.RoomStayModel;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Model.IndemnifyModel;

/**
 *
 * @author Admin
 */
public class CheckOutForm extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    private int currentStayId;
    private String customerName;
    private int tienCoc;
    private IndemnifyModel indemnifyModel = new IndemnifyModel();

    /**
     * Creates new form CheckOutForm
     */
    public CheckOutForm() {
        initComponents();
        initProfile();

    }

    public void searchProfile(int stayId) {
        for (int i = 0; i < profileList.getRowCount(); i++) {
            if ((int) profileList.getValueAt(i, 0) == stayId) {
                profileList.setRowSelectionInterval(i, i);
                break;
            }
        }
        profileListMouseClicked(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton2 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jpdichvusuco = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        roomlist = new javax.swing.JTable();
        jpDichvu = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        serviceList = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        indemnifyList = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        traPhongBtn = new javax.swing.JButton();
        thanhToanBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        option = new javax.swing.JPanel();
        _1 = new javax.swing.JRadioButton();
        _2 = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        searchTxf = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 0), new java.awt.Dimension(3, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        profileList = new javax.swing.JTable();

        jButton2.setText("jButton2");

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(1006, 604));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(10, 0));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông tin trả phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setMinimumSize(new java.awt.Dimension(194, 97));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel5.setMinimumSize(new java.awt.Dimension(286, 100));
        jPanel5.setName(""); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rsz_19132_1_wide700.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        jLabel7.setOpaque(true);
        jLabel7.setPreferredSize(new java.awt.Dimension(300, 100));
        jLabel7.setRequestFocusEnabled(false);
        jPanel5.add(jLabel7, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jpdichvusuco.setBackground(jPanel7.getBackground());
        jpdichvusuco.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jpdichvusuco.setDoubleBuffered(false);
        jpdichvusuco.setMaximumSize(new java.awt.Dimension(500, 600));
        jpdichvusuco.setMinimumSize(new java.awt.Dimension(300, 50));
        jpdichvusuco.setName(""); // NOI18N
        jpdichvusuco.setOpaque(false);
        jpdichvusuco.setPreferredSize(new java.awt.Dimension(300, 450));
        jpdichvusuco.setRequestFocusEnabled(false);
        jpdichvusuco.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        jPanel7.setBackground(new java.awt.Color(153, 153, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Danh sách phòng trong hồ sơ");
        jPanel7.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        roomlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phòng", "Loại phòng", "Ngày thực nhận ", "Ngày thực trả", "Đơn giá"
            }
        ));
        roomlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roomlistMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(roomlist);

        jPanel7.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jpdichvusuco.add(jPanel7);

        jpDichvu.setBackground(jPanel7.getBackground());
        jpDichvu.setPreferredSize(new java.awt.Dimension(100, 200));
        jpDichvu.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Dịch vụ");
        jLabel9.setPreferredSize(new java.awt.Dimension(60, 13));
        jpDichvu.add(jLabel9, java.awt.BorderLayout.PAGE_START);

        serviceList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ngày dùng", "Tên dịch vụ", "Đơn vị", "Đơn giá"
            }
        ));
        serviceList.setShowGrid(true);
        jScrollPane3.setViewportView(serviceList);

        jpDichvu.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jpdichvusuco.add(jpDichvu);

        jPanel8.setBackground(jPanel1.getBackground());
        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Thu/chi bồi thường");
        jPanel8.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        indemnifyList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chi tiết", "Ngày lập", "Loại", "Chi phí"
            }
        ));
        jScrollPane2.setViewportView(indemnifyList);

        jPanel8.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jpdichvusuco.add(jPanel8);

        jPanel1.add(jpdichvusuco, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(jPanel7.getBackground());
        jPanel6.setPreferredSize(new java.awt.Dimension(10, 50));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        traPhongBtn.setBackground(new java.awt.Color(255, 153, 153));
        traPhongBtn.setForeground(new java.awt.Color(255, 0, 51));
        traPhongBtn.setText("Trả phòng");
        traPhongBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traPhongBtnActionPerformed(evt);
            }
        });
        jPanel6.add(traPhongBtn);

        thanhToanBtn.setBackground(new java.awt.Color(255, 153, 153));
        thanhToanBtn.setForeground(new java.awt.Color(255, 0, 51));
        thanhToanBtn.setText("Thanh toán");
        thanhToanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thanhToanBtnActionPerformed(evt);
            }
        });
        jPanel6.add(thanhToanBtn);

        jPanel1.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        add(jPanel1, java.awt.BorderLayout.LINE_END);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Danh sách thuê phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rsz_cung_hoi_nghi_ariyana_da_nang_3.jpg"))); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(1635, 100));
        jLabel8.setRequestFocusEnabled(false);
        jPanel3.add(jLabel8, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(jPanel2.getBackground());
        jPanel4.setPreferredSize(new java.awt.Dimension(10, 90));
        jPanel4.setLayout(new java.awt.BorderLayout(10, 0));

        option.setBackground(jPanel2.getBackground());
        option.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm theo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12), new java.awt.Color(102, 102, 255))); // NOI18N
        option.setEnabled(false);
        option.setPreferredSize(new java.awt.Dimension(150, 100));
        option.setRequestFocusEnabled(false);
        option.setLayout(new java.awt.GridBagLayout());

        buttonGroup1.add(_1);
        _1.setSelected(true);
        _1.setText("Mã Thuê phòng");
        _1.setOpaque(false);
        _1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _1ActionPerformed(evt);
            }
        });
        option.add(_1, new java.awt.GridBagConstraints());

        buttonGroup1.add(_2);
        _2.setText("Tên khách hàng");
        _2.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        option.add(_2, gridBagConstraints);

        jPanel4.add(option, java.awt.BorderLayout.LINE_START);

        jPanel9.setBackground(jPanel2.getBackground());
        jPanel9.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 13;
        gridBagConstraints.weightx = 0.1;
        jPanel9.add(searchTxf, gridBagConstraints);

        searchBtn.setIcon(new javax.swing.ImageIcon("D:\\icon\\tools-and-utensils1.png")); // NOI18N
        searchBtn.setToolTipText("");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel9.add(searchBtn, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        jPanel9.add(filler2, gridBagConstraints);

        jPanel4.add(jPanel9, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel4, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        profileList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        profileList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thuê phòng", "Tên Khách hàng", "Ngày thanh toán", "Tiền cọc"
            }
        ));
        profileList.setShowGrid(true);
        profileList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(profileList);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        if (this.isShowing()) {
            try {
                updateTable();
            } catch (SQLException ex) {
                Logger.getLogger(CheckOutForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formHierarchyChanged

    private void _1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__1ActionPerformed

    Object[] columnsname2 = {"maph", "A", "B", "C", "D"};

    public Class getaClass2(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Timestamp.class;
            case 3:
                return Timestamp.class;
            case 4:
                return Integer.class;
        }
        return null;
    }

    // Chọn các hồ sơ để xem khách thuê những phòng nào
    private void profileListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileListMouseClicked
        try {
            currentStayId = (int) profileList.getValueAt(profileList.getSelectedRow(), 0);
            customerName = (String) profileList.getValueAt(profileList.getSelectedRow(), 1);
            tienCoc = (int) profileList.getValueAt(profileList.getSelectedRow(), 3);
            ArrayList<Chitietthuephong> thuephong = new RoomStayModel().getChitietthuephong(currentStayId);
            if (thuephong != null) {
                DefaultTableModel defaultTableModel = new DefaultTableModel() {

                    public Class getColumnClass2(int column) {
                        return getaClass2(column);
                    }
                };
                roomlist.setModel(defaultTableModel);
                defaultTableModel.addColumn("Tên phòng");
                defaultTableModel.addColumn("Loại phòng");
                defaultTableModel.addColumn("Ngày thực nhận");
                defaultTableModel.addColumn("Ngày thực trả");
                defaultTableModel.addColumn("Thành tiền");
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                for (int i = 0; i < roomlist.getColumnCount(); i++) {
                    roomlist.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
                    roomlist.getColumnModel().getColumn(i).setCellRenderer(renderer);
                }
                for (int i = 0; i < thuephong.size(); i++) {
                    Object[] col = new Object[5];
                    col[0] = thuephong.get(i).getTenphong();
                    col[1] = thuephong.get(i).getLoaiphong();
                    col[2] = thuephong.get(i).getNgaythucnhan();
                    col[3] = thuephong.get(i).getNgaythuctra();
                    col[4] = thuephong.get(i).getThanhtien();
                    defaultTableModel.addRow(col);
                }
            }
            if (profileList.getValueAt(profileList.getSelectedRow(), 2) == null) {
                traPhongBtn.setEnabled(true);
                thanhToanBtn.setEnabled(true);
            } else {
                traPhongBtn.setEnabled(false);
                thanhToanBtn.setEnabled(false);
            }
            int mathuephong = Integer.parseInt(profileList.getValueAt(profileList.getSelectedRow(), 0).toString());
            indemnifyModel.getIndemnifyList((DefaultTableModel) indemnifyList.getModel(), mathuephong);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < indemnifyList.getRowCount(); i++) {
                indemnifyList.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
                indemnifyList.getColumnModel().getColumn(i).setCellRenderer(renderer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_profileListMouseClicked

    Object[] columnsname3 = {"maph", "A", "B", "C", "D"};

    public Class getaClass3(int column) {
        switch (column) {
            case 0:
                return Timestamp.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            case 4:
                return Integer.class;
        }
        return null;
    }
    private void traPhongBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traPhongBtnActionPerformed
        new CheckOutRoom(null, true, currentStayId, roomlist).setVisible(true);
    }//GEN-LAST:event_traPhongBtnActionPerformed

    // chọn các phòng mà khách thuê để xem dịch vụ dùng ở các phòng
    private void roomlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roomlistMouseClicked
        try {
            String currentRoom = (String) roomlist.getValueAt(roomlist.getSelectedRow(), 0);
            String mthuephong = profileList.getValueAt(profileList.getSelectedRow(), 0).toString();
            ArrayList<Dichvu> dichvu = new RoomStayModel().getDichvu(currentRoom, Integer.parseInt(mthuephong));
            if (dichvu != null) {
                DefaultTableModel defaultTableModel = new DefaultTableModel() {

                    public Class getColumnClass3(int column) {
                        return getaClass3(column);
                    }
                };
                serviceList.setModel(defaultTableModel);
                defaultTableModel.addColumn("Mã dịch vụ");
                defaultTableModel.addColumn("Tên dịch vụ");
                defaultTableModel.addColumn("Đơn vị");
                defaultTableModel.addColumn("Đơn giá");
                defaultTableModel.addColumn("Số lượng");
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                for (int i = 0; i < serviceList.getColumnCount(); i++) {
                    serviceList.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
                    serviceList.getColumnModel().getColumn(i).setCellRenderer(renderer);
                }
                for (int i = 0; i < dichvu.size(); i++) {
                    Object[] col = new Object[5];
                    col[0] = dichvu.get(i).getNgaydung();
                    col[1] = dichvu.get(i).getTendv();
                    col[2] = dichvu.get(i).getDvtinh();
                    col[3] = dichvu.get(i).getDongia();
                    col[4] = dichvu.get(i).getSoluong();
                    defaultTableModel.addRow(col);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_roomlistMouseClicked

    private void thanhToanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thanhToanBtnActionPerformed
        boolean x = true;
        for (int i = 0; i < roomlist.getRowCount(); i++) {
            if (roomlist.getValueAt(i, 3) == null) {
                x = false;
            }
        }
        if (x) {
            new Bill(null, true, roomlist, currentStayId, customerName, tienCoc).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Chưa trả hết phòng!!");
        }
    }//GEN-LAST:event_thanhToanBtnActionPerformed

    private int getCol() {
        if (buttonGroup1.getSelection().equals(_1.getModel())) {
            return 0;
        } else if (buttonGroup1.getSelection().equals(_2.getModel())) {
            return 1;
        }
        return -1;
    }
    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        for (int i = 0; i < profileList.getRowCount(); i++) {
            if (profileList.getValueAt(i, getCol()).toString().equalsIgnoreCase(searchTxf.getText())) {
                profileList.setRowSelectionInterval(i, i);
                profileListMouseClicked(null);
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Không tồn tại thông tin cần tìm kiếm!!");
    }//GEN-LAST:event_searchBtnActionPerformed
    Object[] columnsname1 = {"maph", "A", "B", "C"};

    public Class getaClass1(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Timestamp.class;
            case 3:
                return Integer.class;
        }
        return null;
    }
    //Lấy các hồ sơ thuê phòng từ sql

    private void initProfile() {
        defaultTableModel = new DefaultTableModel() {

            public Class getColumnClass(int column) {
                return getaClass1(column);
            }
        };
        profileList.setModel(defaultTableModel);
        defaultTableModel.addColumn("Mã thuê phòng");
        defaultTableModel.addColumn("Tên khách hàng");
        defaultTableModel.addColumn("Ngày thanh toán");
        defaultTableModel.addColumn("Tổng cọc");
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < profileList.getColumnCount(); i++) {
            profileList.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            profileList.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void updateTable() throws SQLException {
        int rowCount = defaultTableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            defaultTableModel.removeRow(0);
        }
        ArrayList<Hosothuephong> array = new RoomStayModel().getHosothuephong();
        for (int i = 0; i < array.size(); i++) {
            Object[] col = new Object[4];
            col[0] = array.get(i).getMathuephong();
            col[1] = array.get(i).getTenkh();
            col[2] = array.get(i).getNgaythanhtoan();
            col[3] = array.get(i).getTongcoc();
            defaultTableModel.addRow(col);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton _1;
    private javax.swing.JRadioButton _2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JTable indemnifyList;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel jpDichvu;
    private javax.swing.JPanel jpdichvusuco;
    private javax.swing.JPanel option;
    private javax.swing.JTable profileList;
    private javax.swing.JTable roomlist;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTxf;
    private javax.swing.JTable serviceList;
    private javax.swing.JButton thanhToanBtn;
    private javax.swing.JButton traPhongBtn;
    // End of variables declaration//GEN-END:variables
}
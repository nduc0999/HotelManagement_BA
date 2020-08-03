/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import BasicClass.DsPhongdat;
import BasicClass.Phieudat;
import BasicClass.Thongtinnhanphong;
import Model.CheckInModel;
import Model.DsPhongdatModel;
import Model.OrderModel;
import Model.TableOrder;
import java.security.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class CheckInForm extends javax.swing.JPanel {

    DefaultTableModel defaultTableModel;
    TableRowSorter<TableModel> rowSorter;

    public CheckInForm() {
        initComponents();
        InitTable();
        jLabel3.setText("<html>Hồ sơ thuê phòng<br/>Thông tin đơn đặt</html>");
        rowSorter = new TableRowSorter<>(defaultTableModel);
        orderList.setRowSorter(rowSorter);
//        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                String OwO = txtSearch.getText();
//                if (OwO.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("?i" + OwO));
//                }
//            }
//
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                String OwO = txtSearch.getText();
//                if (OwO.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("?i" + OwO));
//                }
//            }
//
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//
//            }
//        });
    }
    Object[] columnsname = {"mapd", "A", "B", "C", "D", "E", "F"};

    public Class getaClass(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            case 1:
                return Integer.class;
            case 5:
                return Integer.class;
            case 2:
                return Timestamp.class;
            case 3:
                return Timestamp.class;
            case 4:
                return Timestamp.class;
            case 6:
                return String.class;

        }
        return null;
    }
    ;
    Object[] columnsname1 = {"maph", "A", "B", "C"};

    public Class getaClass1(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
        }
        return null;
    }

    ;
    private void InitTable() {

        defaultTableModel = new DefaultTableModel() {

            public Class getColumnClass(int column) {
                return getaClass(column);
            }
        };
        orderList.setModel(defaultTableModel);
        defaultTableModel.addColumn("Mã phiếu đặt");
        defaultTableModel.addColumn("Mã khách hàng");
        defaultTableModel.addColumn("Ngày đặt");
        defaultTableModel.addColumn("Ngày nhận");
        defaultTableModel.addColumn("Ngày trả");
        defaultTableModel.addColumn("Tổng cọc");
        defaultTableModel.addColumn("Trạng thái");
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < orderList.getColumnCount(); i++) {
            orderList.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
            orderList.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    private void updateTable() {
        int rowCount = defaultTableModel.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            defaultTableModel.removeRow(0);
        }
        ArrayList<Phieudat> array = new TableOrder().getorderList();
        for (int i = 0; i < array.size(); i++) {
            Object[] col = new Object[7];
            col[0] = array.get(i).getMadatphong();
            col[1] = array.get(i).getMakh();
            col[2] = array.get(i).getNgaydat();
            col[3] = array.get(i).getNgaynhan();
            col[4] = array.get(i).getNgaytra();
            col[5] = array.get(i).getTongcoc();
            switch (array.get(i).getTrangthai()) {
                case 0:
                    col[6] = "Chưa cọc";
                    break;
                case 1:
                    col[6] = "Đã cọc";
                    break;
                case 2:
                    col[6] = "Đã nhận";
                    break;
                case 3:
                    col[6] = "Đã hủy";
                    break;
                default:
                    break;
            }
            defaultTableModel.addRow(col);
        }
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

        groupsearch = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        _1 = new javax.swing.JRadioButton();
        _3 = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jScrollPane1 = new javax.swing.JScrollPane();
        orderList = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        hotenkh = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmnd = new javax.swing.JTextField();
        quoctich = new javax.swing.JTextField();
        ngaynhan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ngaytra = new javax.swing.JTextField();
        tongcoc = new javax.swing.JTextField();
        trangthai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jScrollPane2 = new javax.swing.JScrollPane();
        dsphongdat = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        huyphong = new javax.swing.JButton();
        nhancoc = new javax.swing.JButton();
        nhanphong = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(10, 0));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Danh sách đơn đặt", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 22), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(696, 604));
        jPanel2.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel3.setBackground(jPanel2.getBackground());
        jPanel3.setLayout(new java.awt.BorderLayout(10, 0));

        jPanel5.setBackground(jPanel2.getBackground());
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm theo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 3, 12), new java.awt.Color(102, 102, 255))); // NOI18N
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 100));
        jPanel5.setRequestFocusEnabled(false);
        jPanel5.setLayout(new java.awt.GridBagLayout());

        groupsearch.add(_1);
        _1.setSelected(true);
        _1.setLabel("Mã đặt phòng");
        _1.setOpaque(false);
        _1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 13;
        jPanel5.add(_1, gridBagConstraints);

        groupsearch.add(_3);
        _3.setLabel("Ngày đặt");
        _3.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 35;
        jPanel5.add(_3, gridBagConstraints);

        jPanel3.add(jPanel5, java.awt.BorderLayout.LINE_START);

        jPanel6.setBackground(jPanel2.getBackground());
        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWidths = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel6Layout.rowHeights = new int[] {0};
        jPanel6.setLayout(jPanel6Layout);

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 23;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 2;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(txtSearch, gridBagConstraints);

        jButton1.setText("Tìm kiếm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 28;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.weighty = 1.0;
        jPanel6.add(jButton1, gridBagConstraints);

        jPanel3.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);
        jPanel2.add(filler2, java.awt.BorderLayout.LINE_START);

        orderList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Phiếu đặt", "Mã khách hàng", "Ngày đặt", "Ngày nhận", "Ngày trả", "Tổng cọc", "Trạng thái"
            }
        ));
        orderList.setShowGrid(true);
        orderList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(orderList);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(153, 153, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông tin nhận phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 3, 22), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(350, 100));
        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 1, 0), "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 604));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("<html>Khách hàng/<br>Đại diện:</html>");
        jLabel2.setMinimumSize(new java.awt.Dimension(30, 26));
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        hotenkh.setBackground(jPanel1.getBackground());
        hotenkh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        hotenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(hotenkh, gridBagConstraints);

        jLabel4.setText("CMND:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel4, gridBagConstraints);

        cmnd.setBackground(jPanel1.getBackground());
        cmnd.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmnd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(cmnd, gridBagConstraints);

        quoctich.setBackground(jPanel1.getBackground());
        quoctich.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quoctich.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(quoctich, gridBagConstraints);

        ngaynhan.setBackground(jPanel1.getBackground());
        ngaynhan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ngaynhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(ngaynhan, gridBagConstraints);

        jLabel5.setText("Quốc tịch:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel5, gridBagConstraints);

        jLabel6.setText("Thời gian nhận:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel6, gridBagConstraints);

        ngaytra.setBackground(jPanel1.getBackground());
        ngaytra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ngaytra.setToolTipText("");
        ngaytra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(ngaytra, gridBagConstraints);

        tongcoc.setBackground(jPanel1.getBackground());
        tongcoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tongcoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(tongcoc, gridBagConstraints);

        trangthai.setBackground(jPanel1.getBackground());
        trangthai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        trangthai.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(trangthai, gridBagConstraints);

        jLabel7.setText("Thời gian trả:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel8.setText("Tiền cọc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setText("Trạng thái:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel9, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 11;
        jPanel1.add(filler1, gridBagConstraints);

        jScrollPane2.setMinimumSize(new java.awt.Dimension(300, 100));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 150));

        dsphongdat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phòng", "Tên phòng", "Loại phòng", "Đơn giá"
            }
        ));
        dsphongdat.setShowGrid(true);
        jScrollPane2.setViewportView(dsphongdat);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 2.0;
        jPanel1.add(jScrollPane2, gridBagConstraints);

        jPanel8.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel9.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel9.setRequestFocusEnabled(false);
        jPanel9.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/9132_1_wide700.jpg"))); // NOI18N
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel9.add(jLabel3, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(jPanel8.getBackground());
        jPanel7.setPreferredSize(new java.awt.Dimension(466, 0));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 3, 0));
        jPanel9.add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel8.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        jPanel10.setBackground(jPanel8.getBackground());
        jPanel10.setPreferredSize(new java.awt.Dimension(300, 50));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        huyphong.setBackground(new java.awt.Color(255, 153, 153));
        huyphong.setForeground(new java.awt.Color(255, 0, 51));
        huyphong.setText("Hủy phòng");
        huyphong.setToolTipText("");
        huyphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                huyphongActionPerformed(evt);
            }
        });
        jPanel10.add(huyphong);

        nhancoc.setBackground(new java.awt.Color(255, 153, 153));
        nhancoc.setForeground(new java.awt.Color(255, 0, 51));
        nhancoc.setText("Nhận cọc");
        nhancoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhancocActionPerformed(evt);
            }
        });
        jPanel10.add(nhancoc);

        nhanphong.setBackground(new java.awt.Color(255, 153, 153));
        nhanphong.setForeground(new java.awt.Color(255, 0, 51));
        nhanphong.setText("Nhận phòng");
        nhanphong.setToolTipText("");
        nhanphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nhanphongActionPerformed(evt);
            }
        });
        jPanel10.add(nhanphong);

        jPanel8.add(jPanel10, java.awt.BorderLayout.PAGE_END);

        add(jPanel8, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void _1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event__1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event__1ActionPerformed

    private void huyphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_huyphongActionPerformed
        try {
            new OrderModel().cancelOrder((int) orderList.getValueAt(orderList.getSelectedRow(), 0));
            updateTable();
        } catch (SQLException ex) {
            Logger.getLogger(CheckInForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_huyphongActionPerformed

    //chọn phiếu đặt
    private void orderListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderListMouseClicked

        try {
            int select = (int) orderList.getValueAt(orderList.getSelectedRow(), 0);
            Thongtinnhanphong thongtin = new CheckInModel().getThongtinnhanphongs(select);
            if (thongtin != null) {
                hotenkh.setText(thongtin.getHotenkh());
                cmnd.setText(thongtin.getCmnd());
                quoctich.setText(thongtin.getQuoctich());
                ngaynhan.setText(thongtin.getNgaynhan());
                ngaytra.setText(thongtin.getNgaynhan());
                ngaytra.setText(thongtin.getNgaytra());
                tongcoc.setText(String.valueOf(thongtin.getTongcoc()));
                switch (thongtin.getTrangthai()) {
                    case 0:
                        trangthai.setText("Chưa cọc");
                        break;
                    case 1:
                        trangthai.setText("Đã cọc");
                        break;
                    case 2:
                        trangthai.setText("Đã nhận");
                        break;
                    case 3:
                        trangthai.setText("Đã hủy");
                        ;
                        break;
                    default:
                        break;
                }
            }
            ArrayList<DsPhongdat> phongdat = new DsPhongdatModel().getdsPhongdat(select);
            if (phongdat != null) {
                DefaultTableModel defaultTableModel = new DefaultTableModel() {

                    public Class getColumnClass1(int column) {
                        return getaClass1(column);
                    }
                };
                dsphongdat.setModel(defaultTableModel);
                defaultTableModel.addColumn("Mã phòng");
                defaultTableModel.addColumn("Tên Phòng");
                defaultTableModel.addColumn("Loại phòng");
                defaultTableModel.addColumn("Đơn giá");
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(JLabel.CENTER);
                for (int i = 0; i < dsphongdat.getColumnCount(); i++) {
                    dsphongdat.getColumnModel().getColumn(i).setHeaderRenderer(renderer);
                    dsphongdat.getColumnModel().getColumn(i).setCellRenderer(renderer);
                }
                for (int i = 0; i < phongdat.size(); i++) {
                    Object[] col = new Object[4];
                    col[0] = phongdat.get(i).getMaphong();
                    col[1] = phongdat.get(i).getTenphong();
                    col[2] = phongdat.get(i).getLoaiphong();
                    col[3] = phongdat.get(i).getDongia();
                    defaultTableModel.addRow(col);
                }
            }
            if (orderList.getValueAt(orderList.getSelectedRow(), 6) == "Đã nhận"
                    || orderList.getValueAt(orderList.getSelectedRow(), 6) == "Đã hủy") {
                huyphong.setEnabled(false);
                nhancoc.setEnabled(false);
                nhanphong.setEnabled(false);
            } else {
                huyphong.setEnabled(true);
                nhancoc.setEnabled(true);
                nhanphong.setEnabled(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckInForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_orderListMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void nhancocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhancocActionPerformed
        try {
            new OrderModel().updateDeposit((int) orderList.getValueAt(orderList.getSelectedRow(), 0));
            updateTable();
        } catch (SQLException ex) {
            Logger.getLogger(CheckInForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_nhancocActionPerformed

    private void nhanphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nhanphongActionPerformed
        try {
            new OrderModel().checkInOrder((int) orderList.getValueAt(orderList.getSelectedRow(), 0));
            JOptionPane.showMessageDialog(null, "Nhận phòng thành công");
            updateTable();
        } catch (SQLException ex) {
            Logger.getLogger(CheckInForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_nhanphongActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        if (this.isShowing()) {
            updateTable();
        }
    }//GEN-LAST:event_formHierarchyChanged

    private int getCol() {
        if (groupsearch.getSelection().equals(_1.getModel())) {
            return 0;
        } else if (groupsearch.getSelection().equals(_3.getModel())) {
            return 2;
        }
        return -1;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        for (int i = 0; i < orderList.getRowCount(); i++) {
            if (orderList.getValueAt(i, getCol()).toString().equalsIgnoreCase(txtSearch.getText())) {
                orderList.setRowSelectionInterval(i, i);
                orderListMouseClicked(null);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Không tìm thấy nội dung cần tìm!");
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton _1;
    private javax.swing.JRadioButton _3;
    private javax.swing.JTextField cmnd;
    private javax.swing.JTable dsphongdat;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.ButtonGroup groupsearch;
    private javax.swing.JTextField hotenkh;
    private javax.swing.JButton huyphong;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField ngaynhan;
    private javax.swing.JTextField ngaytra;
    private javax.swing.JButton nhancoc;
    private javax.swing.JButton nhanphong;
    private javax.swing.JTable orderList;
    private javax.swing.JTextField quoctich;
    private javax.swing.JTextField tongcoc;
    private javax.swing.JTextField trangthai;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}

package View;

import BasicClass.Order;
import BasicClass.Room;
import BasicClass.RoomInfo;
import Model.OrderModel;
import Model.RoomModel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yue
 */
public class ManagementForm extends javax.swing.JPanel {

    private final MainForm mainForm;
    private int currentRoom;
    private DefaultTableModel tableModel;
    IndemnifyForm indemnifyForm = new IndemnifyForm();

    private class RoomMng extends JButton {

        private int state;
        int i, j;

        public void setState(int state) {
            this.state = state;
        }

        public int getState() {
            return this.state;
        }

        public RoomMng(int i, int j, int mathuephong) {
            super();
            this.i = i;
            this.j = j;
            this.state = mathuephong;
        }

    }
    RoomMng[][] room = new RoomMng[5][10];
    //
    CardLayout infoCard;
    Color brokenRoomColor = new Color(255, 204, 0);

    //
    //
    public ManagementForm(MainForm mainForm) {
        this.mainForm = mainForm;
        initComponents();
        infoCard = (CardLayout) roomInfo.getLayout();
        initializeRoomOrder();
        initializeRoom();
        checkStayRoom();
        room[2][2].setBackground(Color.red);
        room[2][2].setState(-1);
    }

    private void initializeRoomOrder() {
        tableModel = new DefaultTableModel(new Object[]{"Mã đặt", "Ngày nhận", "Ngày trả", "Trạng thái"}, 0);
        dsphongdat.setModel(tableModel);
        dsphongdat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        dsphongdat.getColumnModel().getColumn(0).setMinWidth(50);
        dsphongdat.getColumnModel().getColumn(1).setMinWidth(90);
        dsphongdat.getColumnModel().getColumn(2).setMinWidth(90);
        dsphongdat.getColumnModel().getColumn(3).setMinWidth(50);

    }

    private void initializeRoom() {
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                room[i][j] = new RoomMng(i + 1, j, 99);
                dsphong.add(room[i][j]);
                room[i][j].addActionListener((ActionEvent evt) -> {
                    RoomMng room_temp = (RoomMng) evt.getSource();
                    roomName.setText("P" + (room_temp.i) + "0" + room_temp.j);
                    currentRoom = room_temp.i * 100 + room_temp.j;
                    switch (room_temp.getState()) {
                        case 0:
                            infoCard.show(roomInfo, "emptyRoom");
                            getOrdersByRoom();
                            break;
                        case -1:
                            infoCard.show(roomInfo, "brokenRoom");
                            break;
                        default:
                            infoCard.show(roomInfo, "stayedRoom");
                            getInfoRoom();
                            break;
                    }
                });
            }
        }
    }

    private void refreshRoom() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (room[i][j].getState() != 0) {
                    room[i][j].setText("P" + (i + 1) + "0" + j);
                    room[i][j].setState(0);
                    room[i][j].setBackground(Color.green);
                    room[i][j].setForeground(Color.black);
                }
            }
        }
    }

    public void checkStayRoom() {
        refreshRoom();
        int i, j;
        try {
            ArrayList<Room> rooms = new RoomModel().getRoomState();
            for (Room a : rooms) {
                i = a.getMaphong() / 100 - 1;
                j = a.getMaphong() % 100;
                room[i][j].setState(a.getTrangthai());
                if (a.getTrangthai() == -1) {
                    room[i][j].setText("<html>" + room[i][j].getText() + "<br>Hỏng</html>");
                    room[i][j].setBackground(brokenRoomColor);
                } else {
                    room[i][j].setText("<html><p style='text-align: center'>"
                            + room[i][j].getText() + "<br>#"
                            + String.format("%07d", a.getTrangthai())
                            + "</p></html>");
                    room[i][j].setBackground(Color.red);
                    room[i][j].setForeground(Color.white);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getOrdersByRoom() {
        try {
            for (int i = 0; i < dsphongdat.getRowCount(); i++) {
                tableModel.removeRow(0);
            }

            ArrayList<Order> orders = new OrderModel().getOrderByRoomId(currentRoom);
            for (int i = 0; i < orders.size(); i++) {
                tableModel.addRow(new Object[]{
                    orders.get(i).getMadatphong(),
                    orders.get(i).getNgaynhan(),
                    orders.get(i).getNgaytra(),
                    orders.get(i).getTrangthai()
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void getInfoRoom() {
        try {
            RoomInfo roomInfo = new RoomModel().getRoomById(currentRoom);
            hotenkh.setText(roomInfo.getHotenkh());
            madatphong.setText(String.format("%07d", roomInfo.getMadatphong()));
            mathuephong.setText(String.format("%07d", roomInfo.getMathuephong()));
            ngaynhan.setText(roomInfo.getNgaynhan().toString());
            sdt.setText(roomInfo.getSdt());
        } catch (SQLException ex) {
            Logger.getLogger(ManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        danhsachphong = new javax.swing.JPanel();
        loaiphong = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dsphong = new javax.swing.JPanel();
        thongtinphong = new javax.swing.JPanel();
        roomInfo = new javax.swing.JPanel();
        stayedRoom = new javax.swing.JPanel();
        info = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hotenkh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        mathuephong = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ngaynhan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        sdt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        madatphong = new javax.swing.JTextField();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        buttonList = new javax.swing.JPanel();
        buttons = new javax.swing.JPanel();
        dichvuphong = new javax.swing.JButton();
        doiphong = new javax.swing.JButton();
        baocaohuhong = new javax.swing.JButton();
        traphong = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        emptyRoom = new javax.swing.JPanel();
        datphong = new javax.swing.JButton();
        bangphongdat = new javax.swing.JScrollPane();
        dsphongdat = new javax.swing.JTable();
        brokenRoom = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        updateState = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 50), new java.awt.Dimension(0, 50), new java.awt.Dimension(32767, 50));
        roomName = new javax.swing.JLabel();

        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        danhsachphong.setBackground(new java.awt.Color(204, 204, 255));
        danhsachphong.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Danh sách phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Calligraphy", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        danhsachphong.setLayout(new java.awt.BorderLayout(5, 10));

        loaiphong.setBackground(danhsachphong.getBackground());
        loaiphong.setPreferredSize(new java.awt.Dimension(20, 100));
        loaiphong.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        jLabel8.setBackground(new java.awt.Color(152, 235, 255));
        jLabel8.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("<HTML><p style=\"text-align: center\">Q<br>U<br>A<br>D<br>R<br>A</p></HTML>");
        jLabel8.setOpaque(true);
        loaiphong.add(jLabel8);

        jLabel9.setBackground(new java.awt.Color(255, 255, 153));
        jLabel9.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("<html><p style=\"text-align: center\">T<br>R<br>I<br>P<br>L<br>E</p></html>");
        jLabel9.setOpaque(true);
        loaiphong.add(jLabel9);

        jLabel11.setBackground(new java.awt.Color(204, 255, 204));
        jLabel11.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("<html><p style=\"text-align: center\">T<br>W<br>I<br>N</p></html>");
        jLabel11.setOpaque(true);
        loaiphong.add(jLabel11);

        jLabel10.setBackground(new java.awt.Color(255, 153, 255));
        jLabel10.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("<html><p style=\"text-align: center\">D<br>O<br>U<br>B<br>L<br>E</p></html>");
        jLabel10.setOpaque(true);
        loaiphong.add(jLabel10);

        jLabel7.setBackground(new java.awt.Color(195, 195, 195));
        jLabel7.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("<html><p style=\"text-align: center\">S<br>I<br>N<br>G<br>L<br>E</p></html>");
        jLabel7.setOpaque(true);
        loaiphong.add(jLabel7);

        danhsachphong.add(loaiphong, java.awt.BorderLayout.LINE_START);

        dsphong.setBackground(danhsachphong.getBackground());
        dsphong.setLayout(new java.awt.GridLayout(5, 10, 5, 10));
        danhsachphong.add(dsphong, java.awt.BorderLayout.CENTER);

        add(danhsachphong, java.awt.BorderLayout.CENTER);

        thongtinphong.setBackground(new java.awt.Color(153, 153, 255));
        thongtinphong.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông tin phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Calligraphy", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        thongtinphong.setPreferredSize(new java.awt.Dimension(350, 565));
        thongtinphong.setLayout(new java.awt.BorderLayout(0, 5));

        roomInfo.setLayout(new java.awt.CardLayout());

        stayedRoom.setLayout(new java.awt.BorderLayout());

        info.setBackground(thongtinphong.getBackground());
        info.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("<html>Khách hàng/<br>Đại diện:</html>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        info.add(jLabel1, gridBagConstraints);

        hotenkh.setBackground(thongtinphong.getBackground());
        hotenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        hotenkh.setPreferredSize(new java.awt.Dimension(1, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 10);
        info.add(hotenkh, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Mã thuê phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        info.add(jLabel2, gridBagConstraints);

        mathuephong.setBackground(thongtinphong.getBackground());
        mathuephong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        mathuephong.setPreferredSize(new java.awt.Dimension(1, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 10);
        info.add(mathuephong, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Ngày nhận:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        info.add(jLabel3, gridBagConstraints);

        ngaynhan.setBackground(thongtinphong.getBackground());
        ngaynhan.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        ngaynhan.setPreferredSize(new java.awt.Dimension(1, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 10);
        info.add(ngaynhan, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Số điện thoại:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        info.add(jLabel5, gridBagConstraints);

        sdt.setBackground(thongtinphong.getBackground());
        sdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        sdt.setPreferredSize(new java.awt.Dimension(1, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 10);
        info.add(sdt, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Mã đặt phòng:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        info.add(jLabel6, gridBagConstraints);

        madatphong.setBackground(thongtinphong.getBackground());
        madatphong.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        madatphong.setPreferredSize(new java.awt.Dimension(1, 18));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 10);
        info.add(madatphong, gridBagConstraints);

        filler4.setBackground(thongtinphong.getBackground());
        filler4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        info.add(filler4, gridBagConstraints);

        stayedRoom.add(info, java.awt.BorderLayout.CENTER);

        buttonList.setPreferredSize(new java.awt.Dimension(286, 200));
        buttonList.setLayout(new java.awt.BorderLayout());

        buttons.setBackground(thongtinphong.getBackground());
        buttons.setPreferredSize(new java.awt.Dimension(300, 150));
        buttons.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        dichvuphong.setBackground(new java.awt.Color(255, 153, 153));
        dichvuphong.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        dichvuphong.setForeground(new java.awt.Color(255, 0, 51));
        dichvuphong.setText("Dịch vụ phòng");
        dichvuphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dichvuphongActionPerformed(evt);
            }
        });
        buttons.add(dichvuphong);

        doiphong.setBackground(new java.awt.Color(255, 153, 153));
        doiphong.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        doiphong.setForeground(new java.awt.Color(255, 0, 51));
        doiphong.setText("Đổi phòng");
        doiphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doiphongActionPerformed(evt);
            }
        });
        buttons.add(doiphong);

        baocaohuhong.setBackground(new java.awt.Color(255, 153, 153));
        baocaohuhong.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        baocaohuhong.setForeground(new java.awt.Color(255, 0, 51));
        baocaohuhong.setText("Báo cáo hư hỏng");
        baocaohuhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baocaohuhongActionPerformed(evt);
            }
        });
        buttons.add(baocaohuhong);

        traphong.setBackground(new java.awt.Color(255, 153, 153));
        traphong.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        traphong.setForeground(new java.awt.Color(255, 0, 51));
        traphong.setText("Trả phòng");
        traphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traphongActionPerformed(evt);
            }
        });
        buttons.add(traphong);

        buttonList.add(buttons, java.awt.BorderLayout.CENTER);

        filler1.setBackground(thongtinphong.getBackground());
        filler1.setOpaque(true);
        buttonList.add(filler1, java.awt.BorderLayout.LINE_START);

        filler2.setBackground(thongtinphong.getBackground());
        filler2.setOpaque(true);
        buttonList.add(filler2, java.awt.BorderLayout.LINE_END);

        filler3.setBackground(thongtinphong.getBackground());
        filler3.setOpaque(true);
        buttonList.add(filler3, java.awt.BorderLayout.PAGE_END);

        stayedRoom.add(buttonList, java.awt.BorderLayout.PAGE_END);

        roomInfo.add(stayedRoom, "stayedRoom");

        emptyRoom.setBackground(thongtinphong.getBackground());
        emptyRoom.setLayout(new java.awt.BorderLayout());

        datphong.setBackground(new java.awt.Color(255, 153, 153));
        datphong.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        datphong.setForeground(new java.awt.Color(255, 0, 51));
        datphong.setText("Cập nhật tình trạng");
        datphong.setPreferredSize(new java.awt.Dimension(73, 70));
        emptyRoom.add(datphong, java.awt.BorderLayout.PAGE_END);

        dsphongdat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        dsphongdat.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        bangphongdat.setViewportView(dsphongdat);

        emptyRoom.add(bangphongdat, java.awt.BorderLayout.CENTER);

        roomInfo.add(emptyRoom, "emptyRoom");

        brokenRoom.setBackground(thongtinphong.getBackground());
        brokenRoom.setLayout(new java.awt.GridBagLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("<html><p style=\"text-align: center\">Phòng hiện không đủ đáp ứng để cho thuê</p></html>");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        brokenRoom.add(jLabel12, gridBagConstraints);

        updateState.setBackground(new java.awt.Color(255, 153, 153));
        updateState.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateState.setForeground(new java.awt.Color(255, 0, 51));
        updateState.setText("Cập nhật");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = 25;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 2.0;
        brokenRoom.add(updateState, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 3.0;
        brokenRoom.add(filler5, gridBagConstraints);

        roomInfo.add(brokenRoom, "brokenRoom");

        thongtinphong.add(roomInfo, java.awt.BorderLayout.CENTER);

        roomName.setFont(new java.awt.Font("Lucida Calligraphy", 1, 16)); // NOI18N
        roomName.setForeground(new java.awt.Color(255, 255, 255));
        roomName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roomName.setText("Phòng");
        thongtinphong.add(roomName, java.awt.BorderLayout.PAGE_START);

        add(thongtinphong, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void dichvuphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dichvuphongActionPerformed
        int roomId = Integer.valueOf(roomName.getText().substring(1));
        int stayId = Integer.valueOf(mathuephong.getText());
        ServiceForm serviceForm = new ServiceForm(null, true, roomId, stayId);
        serviceForm.setVisible(true);
    }//GEN-LAST:event_dichvuphongActionPerformed

    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        if (this.isShowing()) {
            checkStayRoom();
        }
    }//GEN-LAST:event_formHierarchyChanged

    private void doiphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doiphongActionPerformed
        int orderId = Integer.valueOf(madatphong.getText());
        int stayId = Integer.valueOf(mathuephong.getText());
        new ChangingRoom((MainForm) this.getTopLevelAncestor(), true, orderId, stayId, currentRoom).setVisible(true);
    }//GEN-LAST:event_doiphongActionPerformed

    private void traphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traphongActionPerformed
        //if (mathuephong.getText().equals("")) {
        mainForm.goToCheckOut(Integer.valueOf(mathuephong.getText()));
        //}
    }//GEN-LAST:event_traphongActionPerformed

    private void baocaohuhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baocaohuhongActionPerformed
        mainForm.gotoIndemnify();
    }//GEN-LAST:event_baocaohuhongActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane bangphongdat;
    private javax.swing.JButton baocaohuhong;
    private javax.swing.JPanel brokenRoom;
    private javax.swing.JPanel buttonList;
    private javax.swing.JPanel buttons;
    private javax.swing.JPanel danhsachphong;
    private javax.swing.JButton datphong;
    private javax.swing.JButton dichvuphong;
    private javax.swing.JButton doiphong;
    private javax.swing.JPanel dsphong;
    private javax.swing.JTable dsphongdat;
    private javax.swing.JPanel emptyRoom;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JTextField hotenkh;
    private javax.swing.JPanel info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel loaiphong;
    private javax.swing.JTextField madatphong;
    private javax.swing.JTextField mathuephong;
    private javax.swing.JTextField ngaynhan;
    private javax.swing.JPanel roomInfo;
    private javax.swing.JLabel roomName;
    private javax.swing.JTextField sdt;
    private javax.swing.JPanel stayedRoom;
    private javax.swing.JPanel thongtinphong;
    private javax.swing.JButton traphong;
    private javax.swing.JButton updateState;
    // End of variables declaration//GEN-END:variables
}

package View;

import BasicClass.Customer;
import BasicClass.RoomType;
import Model.CustomerModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import BasicClass.Order;
import BasicClass.OrderRoom;
import Model.OrderModel;
import Model.RoomTypeModel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Yue
 */
public class OrderForm extends javax.swing.JPanel {

    //50 nút phòng tạo ra nhằm tương tác với đơn đặt phòng từ cơ sở dữ liệu
    private class RoomChecker extends JToggleButton {

        private boolean available;

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public RoomChecker() {
            this.available = true;
        }
    }
    private RoomChecker[][] room;
    //Biến Lưu trữ thông tin khách hàng hiện đang đặt phòng
    private Customer customer;

    //
    public OrderForm() {
        initComponents();
        //Lấy hôm nay và ngày mai gán vào ngày nhận và ngày trả
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String homnay = simpleDateFormat.format(calendar.getTime());
        ngaynhan.setText(homnay);
        calendar.add(Calendar.DATE, 1);
        String ngaymai = simpleDateFormat.format(calendar.getTime());
        ngaytra.setText(ngaymai);
        //
        initializeRoom();
        try {
            initializeRoomTypes();
            checkOrder(homnay, ngaymai);
        } catch (SQLException ex) {
            Logger.getLogger(OrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshButton.doClick();
    }

    //Khởi tạo bảng 50 nút phòng
    private void initializeRoom() {
        room = new RoomChecker[5][10];
        for (int i = 4; i >= 0; i--) {
            for (int j = 0; j < 10; j++) {
                room[i][j] = new RoomChecker();
                room[i][j].setText("P" + (i + 1) + "0" + (j));
                roomList.add(room[i][j]);
                room[i][j].setCursor(new Cursor(Cursor.HAND_CURSOR));
                final int ii = i;
                final int jj = j;
                //Nếu chọn phòng thuê được thì màu sẽ đậm hơn phòng không chọn
                room[i][j].addItemListener((ItemEvent evt) -> {
                    if (room[ii][jj].isAvailable()) {
                        if (evt.getStateChange() == ItemEvent.SELECTED) {
                            room[ii][jj].setBackground(new Color(0, 150, 0));
                            dsloaiphong.setValueAt((int) dsloaiphong.getValueAt(ii, 3) + 1, ii, 3);
                        } else {
                            room[ii][jj].setBackground(Color.green);
                            dsloaiphong.setValueAt((int) dsloaiphong.getValueAt(ii, 3) - 1, ii, 3);
                        }
                    } else {
                        room[ii][jj].setSelected(false);
                    }
                });
            }
        }
    }

    //Hàm reset trạng thái 50 nút phòng
    private void setEnabledRoom() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (room[i][j].getBackground() != Color.green) {
                    room[i][j].setAvailable(true);
                    room[i][j].setBackground(Color.green);
                    room[i][j].setSelected(false);
                }
            }
        }
    }

    //Khởi tạo danh sách loại phòng
    private void initializeRoomTypes() throws SQLException {
        ArrayList<RoomType> roomTypeList = new RoomTypeModel().getRoomTypeList();
        bangloaiphong.setModel(dsloaiphong);
        dsloaiphong.setColumnIdentifiers(new Object[]{"Mã loại", "Tên loại phòng", "Đơn giá", "Số lượng"});
        for (int i = 0; i < roomTypeList.size(); i++) {
            dsloaiphong.addRow(new Object[]{
                roomTypeList.get(i).getMaloaiphong(),
                roomTypeList.get(i).getTenloaiphong(),
                roomTypeList.get(i).getDongia(),
                0
            });
        }
        dsloaiphong.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int sum = 0;
                for (int i = 0; i < bangloaiphong.getRowCount(); i++) {
                    sum += Integer.parseInt(bangloaiphong.getValueAt(i, 2).toString())
                            * Integer.parseInt(bangloaiphong.getValueAt(i, 3).toString());
                }
                tongcoc.setText(String.valueOf(sum));
            }
        });
    }

    //Kiểm tra các phòng đã được đặt từ ngày nhận và ngày trả
    private void checkOrder(String checkin, String checkout) {
        setEnabledRoom();
        try {
            ArrayList<Integer> sophong;
            //sophong = new OrderModel().getOrderedRoomsByString(checkin, checkout);
            sophong = new OrderModel().getRoomsInOrder(checkin, checkout);
            int i, j;
            for (int a = 0; a < sophong.size(); a++) {
                i = sophong.get(a) / 100 - 1;
                j = sophong.get(a) % 100;
                room[i][j].setAvailable(false);
                room[i][j].setBackground(Color.red);
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(OrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Phòng đã đặt sẽ có màu đỏ
    //
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        customerInfo = new javax.swing.JPanel();
        infoFill = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hotenkh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmnd = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        diachi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        quoctich = new javax.swing.JTextField();
        roomType = new javax.swing.JScrollPane();
        bangloaiphong = new javax.swing.JTable();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jLabel6 = new javax.swing.JLabel();
        sdt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        gioitinh = new javax.swing.JComboBox<>();
        tongcoc = new javax.swing.JTextField();
        nhancoc = new javax.swing.JCheckBox();
        buttons = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        refreshButton = new javax.swing.JButton();
        orderButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        reminder = new javax.swing.JLabel();
        roomsInfo = new javax.swing.JPanel();
        in_and_out = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        checkinLabel = new javax.swing.JLabel();
        ngaynhan = new javax.swing.JFormattedTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 0), new java.awt.Dimension(50, 32767));
        checkoutLabel = new javax.swing.JLabel();
        ngaytra = new javax.swing.JFormattedTextField();
        roomList = new javax.swing.JPanel();
        loaiphong = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                formHierarchyChanged(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        customerInfo.setBackground(new java.awt.Color(153, 153, 255));
        customerInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Calligraphy", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        customerInfo.setForeground(new java.awt.Color(255, 255, 255));
        customerInfo.setPreferredSize(new java.awt.Dimension(350, 600));
        customerInfo.setLayout(new java.awt.BorderLayout());

        infoFill.setBackground(customerInfo.getBackground());
        infoFill.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setLabelFor(hotenkh);
        jLabel1.setText("Họ tên KH:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        infoFill.add(jLabel1, gridBagConstraints);

        hotenkh.setBackground(infoFill.getBackground());
        hotenkh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        hotenkh.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 0);
        infoFill.add(hotenkh, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setLabelFor(gioitinh);
        jLabel2.setText("Giới tính:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        infoFill.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setLabelFor(cmnd);
        jLabel3.setText("CMND:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        infoFill.add(jLabel3, gridBagConstraints);

        cmnd.setBackground(infoFill.getBackground());
        cmnd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmnd.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        cmnd.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmndFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 0);
        infoFill.add(cmnd, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setLabelFor(diachi);
        jLabel4.setText("Địa chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        infoFill.add(jLabel4, gridBagConstraints);

        diachi.setBackground(infoFill.getBackground());
        diachi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        diachi.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 0);
        infoFill.add(diachi, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setLabelFor(quoctich);
        jLabel5.setText("Quốc tịch:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        infoFill.add(jLabel5, gridBagConstraints);

        quoctich.setBackground(infoFill.getBackground());
        quoctich.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quoctich.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 0);
        infoFill.add(quoctich, gridBagConstraints);

        roomType.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        roomType.setMinimumSize(new java.awt.Dimension(500, 115));

        bangloaiphong.setModel(dsloaiphong);
        roomType.setViewportView(bangloaiphong);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.weighty = 3.0;
        infoFill.add(roomType, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 8;
        infoFill.add(filler3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 8;
        infoFill.add(filler4, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setLabelFor(sdt);
        jLabel6.setText("Số điện thoại:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        infoFill.add(jLabel6, gridBagConstraints);

        sdt.setBackground(infoFill.getBackground());
        sdt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        sdt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 0);
        infoFill.add(sdt, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 51));
        jLabel7.setLabelFor(tongcoc);
        jLabel7.setText("Tổng cọc:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        infoFill.add(jLabel7, gridBagConstraints);

        gioitinh.setBackground(customerInfo.getBackground());
        gioitinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gioitinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 0);
        infoFill.add(gioitinh, gridBagConstraints);

        tongcoc.setEditable(false);
        tongcoc.setBackground(customerInfo.getBackground());
        tongcoc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tongcoc.setForeground(new java.awt.Color(255, 0, 51));
        tongcoc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tongcoc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 0, 51)));
        tongcoc.setCaretColor(new java.awt.Color(255, 0, 51));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, -10, 0, 0);
        infoFill.add(tongcoc, gridBagConstraints);

        nhancoc.setBackground(customerInfo.getBackground());
        nhancoc.setText("Nhận cọc");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        infoFill.add(nhancoc, gridBagConstraints);

        customerInfo.add(infoFill, java.awt.BorderLayout.CENTER);

        buttons.setBackground(customerInfo.getBackground());
        buttons.setPreferredSize(new java.awt.Dimension(336, 80));
        buttons.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel1.setBackground(customerInfo.getBackground());
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        refreshButton.setBackground(new java.awt.Color(255, 153, 153));
        refreshButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(255, 0, 51));
        refreshButton.setText("Làm mới");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jPanel1.add(refreshButton);

        orderButton.setBackground(new java.awt.Color(255, 153, 153));
        orderButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        orderButton.setForeground(new java.awt.Color(255, 0, 51));
        orderButton.setText("Đặt phòng");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });
        jPanel1.add(orderButton);

        buttons.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(customerInfo.getBackground());
        jPanel2.setMinimumSize(new java.awt.Dimension(10, 15));
        jPanel2.setPreferredSize(new java.awt.Dimension(10, 15));
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0);
        flowLayout2.setAlignOnBaseline(true);
        jPanel2.setLayout(flowLayout2);

        reminder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reminder.setForeground(java.awt.Color.red);
        reminder.setText("Chưa điền đầy đủ thông tin");
        jPanel2.add(reminder);

        buttons.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        customerInfo.add(buttons, java.awt.BorderLayout.PAGE_END);

        add(customerInfo, java.awt.BorderLayout.LINE_END);

        roomsInfo.setBackground(getBackground());
        roomsInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Danh sách phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lucida Calligraphy", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        roomsInfo.setForeground(new java.awt.Color(255, 255, 255));
        roomsInfo.setLayout(new java.awt.BorderLayout(0, 10));

        in_and_out.setBackground(getBackground());
        in_and_out.setPreferredSize(new java.awt.Dimension(788, 40));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 10);
        flowLayout1.setAlignOnBaseline(true);
        in_and_out.setLayout(flowLayout1);
        in_and_out.add(filler2);

        checkinLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkinLabel.setLabelFor(ngaynhan);
        checkinLabel.setText("Ngày nhận:");
        in_and_out.add(checkinLabel);

        ngaynhan.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ngaynhan.setMinimumSize(new java.awt.Dimension(150, 30));
        ngaynhan.setPreferredSize(new java.awt.Dimension(200, 30));
        ngaynhan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkRoom(evt);
            }
        });
        in_and_out.add(ngaynhan);
        in_and_out.add(filler1);

        checkoutLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkoutLabel.setLabelFor(ngaytra);
        checkoutLabel.setText("Ngày trả:");
        in_and_out.add(checkoutLabel);

        ngaytra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        ngaytra.setMinimumSize(new java.awt.Dimension(150, 30));
        ngaytra.setPreferredSize(new java.awt.Dimension(200, 30));
        ngaytra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkRoom(evt);
            }
        });
        in_and_out.add(ngaytra);

        roomsInfo.add(in_and_out, java.awt.BorderLayout.PAGE_START);

        roomList.setBackground(getBackground());
        roomList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        roomList.setLayout(new java.awt.GridLayout(5, 10, 5, 10));
        roomsInfo.add(roomList, java.awt.BorderLayout.CENTER);

        loaiphong.setOpaque(false);
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

        jLabel12.setBackground(new java.awt.Color(195, 195, 195));
        jLabel12.setFont(new java.awt.Font("Corbel", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("<html><p style=\"text-align: center\">S<br>I<br>N<br>G<br>L<br>E</p></html>");
        jLabel12.setOpaque(true);
        loaiphong.add(jLabel12);

        roomsInfo.add(loaiphong, java.awt.BorderLayout.LINE_START);

        add(roomsInfo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private JComponent checkInfoFilled() {
        if (cmnd.getText().equals("")) {
            return cmnd;
        }
        if (hotenkh.getText().equals("")) {
            return hotenkh;
        }
        if (quoctich.getText().equals("")) {
            return quoctich;
        }
        if (diachi.getText().equals("")) {
            return diachi;
        }
        if (sdt.getText().equals("")) {
            return sdt;
        }
        return null;
    }

    //Đặt phòng
    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderButtonActionPerformed
        try {
            //Kiểm tra thông tin đã điền đủ chưa
            if (checkInfoFilled() != null) {
                reminder.setVisible(true);
                checkInfoFilled().requestFocus();
            } else {
                reminder.setVisible(false);
                if (customer == null) {
                    customer = new Customer();
                    customer.setHotenkh(hotenkh.getText());
                    customer.setGioitinh((gioitinh.getSelectedIndex() == 1));
                    customer.setCmnd(cmnd.getText());
                    customer.setQuoctich(quoctich.getText());
                    customer.setDiachi(diachi.getText());
                    customer.setSdt(sdt.getText());
                    //Nếu khách hàng chưa có trong database thì thêm vào
                    customer.setMakh(new CustomerModel().add(customer));
                }
                //Lập đơn đặt phòng lưu vào hệ thống
                OrderModel orderModel = new OrderModel();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Timestamp checkin, checkout;
                checkin = new Timestamp(format.parse(ngaynhan.getText()).getTime());
                checkin.setHours(14);
                checkout = new Timestamp(format.parse(ngaytra.getText()).getTime());
                checkout.setHours(12);
                OrderRoom.setMadatphong(orderModel.addOrder(new Order(
                        customer.getMakh(),
                        new Timestamp(System.currentTimeMillis()),
                        checkin,
                        checkout,
                        Integer.parseInt(tongcoc.getText()),
                        nhancoc.isSelected() ? 1 : 0
                )));
                //Thêm thông tin các phòng được đặt trong đơn đặt phòng
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (room[i][j].isSelected()) {
                            orderModel.addOrderRoomsByOrderId(OrderRoom.getMadatphong(),
                                    new OrderRoom((i + 1) * 100 + j,
                                            (int) bangloaiphong.getValueAt(i, 2)));
                        }
                    }
                }
                JOptionPane.showInternalMessageDialog(null, "Đặt phòng thành công");
                checkOrder(ngaynhan.getText(), ngaytra.getText());
            }
        } catch (ParseException | SQLException ex) {
            Logger.getLogger(OrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_orderButtonActionPerformed

    //Làm mới
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        hotenkh.setText("");
        gioitinh.setSelectedIndex(0);
        cmnd.setText("");
        quoctich.setText("");
        diachi.setText("");
        sdt.setText("");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (room[i][j].getBackground() != Color.red) {
                    room[i][j].setBackground(Color.green);
                    room[i][j].setSelected(false);
                }
            }
        }
        customer = null;
        reminder.setVisible(false);
        checkOrder(ngaynhan.getText(), ngaytra.getText());
    }//GEN-LAST:event_refreshButtonActionPerformed

    //Nếu khách hàng đã tồn tại trong database thì lấy ra
    private void cmndFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmndFocusLost
        try {
            customer = new CustomerModel().getCustomerByIdCard(cmnd.getText());
            if (customer != null) {
                hotenkh.setText(customer.getHotenkh());
                diachi.setText(customer.getDiachi());
                quoctich.setText(customer.getQuoctich());
                sdt.setText(customer.getSdt());
                gioitinh.setSelectedIndex(customer.isGioitinh() ? 1 : 0);
            } else {
                customer = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cmndFocusLost

    //Hàm sự kiện của ngày nhận và trả
    private void checkRoom(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkRoom
        checkOrder(ngaynhan.getText(), ngaytra.getText());
    }//GEN-LAST:event_checkRoom

    //Cập nhật lại lịch khi chuyển sang form này
    private void formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formHierarchyChanged
        if (this.isShowing()) {
            checkOrder(ngaynhan.getText(), ngaytra.getText());
        }
    }//GEN-LAST:event_formHierarchyChanged

    DefaultTableModel dsloaiphong = new DefaultTableModel();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bangloaiphong;
    private javax.swing.JPanel buttons;
    private javax.swing.JLabel checkinLabel;
    private javax.swing.JLabel checkoutLabel;
    private javax.swing.JTextField cmnd;
    private javax.swing.JPanel customerInfo;
    private javax.swing.JTextField diachi;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.JComboBox<String> gioitinh;
    private javax.swing.JTextField hotenkh;
    private javax.swing.JPanel in_and_out;
    private javax.swing.JPanel infoFill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel loaiphong;
    private javax.swing.JFormattedTextField ngaynhan;
    private javax.swing.JFormattedTextField ngaytra;
    private javax.swing.JCheckBox nhancoc;
    private javax.swing.JButton orderButton;
    private javax.swing.JTextField quoctich;
    private javax.swing.JButton refreshButton;
    private javax.swing.JLabel reminder;
    private javax.swing.JPanel roomList;
    private javax.swing.JScrollPane roomType;
    private javax.swing.JPanel roomsInfo;
    private javax.swing.JTextField sdt;
    private javax.swing.JTextField tongcoc;
    // End of variables declaration//GEN-END:variables
}

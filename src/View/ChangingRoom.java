package View;

import BasicClass.RoomType;
import Model.OrderModel;
import Model.RoomModel;
import Model.RoomTypeModel;
import java.awt.Frame;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Yue
 */
public class ChangingRoom extends javax.swing.JDialog {

    private int orderId;
    private int stayId;
    private int room;
    ArrayList<RoomType> roomTypes;
    ArrayList<Integer> rooms;

    public ChangingRoom(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public ChangingRoom(Frame parent, boolean modal, int orderId, int stayId, int room) {
        super(parent, modal);
        this.orderId = orderId;
        this.stayId = stayId;
        this.room = room;
        initComponents();
        initializeAllRoom();
        getRoomTypes();
        getRoomByRoomType(1);
    }

    private void initializeAllRoom() {
        rooms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                rooms.add((i + 1) * 100 + j);
            }
        }
    }

    private ArrayList<Integer> getAvailableRooms() {
        ArrayList<Integer> availableRoom = (ArrayList<Integer>) rooms.clone();
        try {
            OrderModel orderModel = new OrderModel();
            Timestamp checkin = new Timestamp(System.currentTimeMillis());
            Timestamp checkout = orderModel.getCheckOutById(orderId);
            //ArrayList<Integer> orderedRooms = orderModel.getOrderedRoomsByTimestamp(checkin, checkout);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            ArrayList<Integer> orderedRooms = orderModel.getRoomsInOrder(formatter.format(checkin),
                    formatter.format(checkout));
            for (int i = 0; i < orderedRooms.size(); i++) {
                availableRoom.remove(orderedRooms.get(i));
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(ChangingRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return availableRoom;
    }

    private void getRoomTypes() {
        try {
            roomTypes = new RoomTypeModel().getRoomTypeList();
            for (int i = 0; i < roomTypes.size(); i++) {
                newRoomType.addItem(roomTypes.get(i).getTenloaiphong());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangingRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getRoomByRoomType(int type) {
        newRoom.removeAllItems();
        ArrayList<Integer> avalRooms = getAvailableRooms();
        for (int i = 0; i < avalRooms.size(); i++) {
            int t = avalRooms.get(i) / 100;
            if (t == type) {
                newRoom.addItem("P" + avalRooms.get(i).toString());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        cancel = new javax.swing.JButton();
        changeRoom = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        newRoomType = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        newRoom = new javax.swing.JComboBox<>();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 32767));
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setModal(true);
        setName("doiphong"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(300, 300));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 1, new java.awt.Color(255, 204, 102)));
        jPanel1.setPreferredSize(new java.awt.Dimension(775, 50));
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        cancel.setBackground(new java.awt.Color(255, 153, 153));
        cancel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancel.setForeground(new java.awt.Color(255, 0, 51));
        cancel.setText("Hủy");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        jPanel1.add(cancel);

        changeRoom.setBackground(new java.awt.Color(255, 153, 153));
        changeRoom.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        changeRoom.setForeground(new java.awt.Color(255, 0, 51));
        changeRoom.setText("Đổi phòng");
        changeRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeRoomActionPerformed(evt);
            }
        });
        jPanel1.add(changeRoom);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 1, new java.awt.Color(255, 204, 102)));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setText("Loại phòng mới");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel2.add(jLabel2, gridBagConstraints);

        newRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        newRoomType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                newRoomTypeItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel2.add(newRoomType, gridBagConstraints);

        jLabel3.setText("Phòng có thể đổi:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel2.add(jLabel3, gridBagConstraints);

        newRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        jPanel2.add(newRoom, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        jPanel2.add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        jPanel2.add(filler2, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 204, 102));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 30));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_delete_24px.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel4, java.awt.BorderLayout.LINE_END);

        jLabel5.setFont(new java.awt.Font(".VnAristote", 0, 30)); // NOI18N
        jLabel5.setText("§æi phßng");
        jPanel3.add(jLabel5, java.awt.BorderLayout.CENTER);
        jPanel3.add(filler3, java.awt.BorderLayout.LINE_START);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void newRoomTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_newRoomTypeItemStateChanged
        getRoomByRoomType(newRoomType.getSelectedIndex() + 1);
    }//GEN-LAST:event_newRoomTypeItemStateChanged

    private void changeRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeRoomActionPerformed
        try {
            String str = (String) newRoom.getSelectedItem();
            new RoomModel().changeRoom(stayId, room, Integer.valueOf(str.substring(1)));
            JOptionPane.showMessageDialog(null, "Đổi phòng thành công");
            MainForm parent = (MainForm) this.getParent();
            parent.managementForm.checkStayRoom();
            dispose();
        } catch (SQLException ex) {
            Logger.getLogger(ChangingRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_changeRoomActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ChangingRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChangingRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChangingRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChangingRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChangingRoom dialog = new ChangingRoom(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton cancel;
    private javax.swing.JButton changeRoom;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox<String> newRoom;
    private javax.swing.JComboBox<String> newRoomType;
    // End of variables declaration//GEN-END:variables
}

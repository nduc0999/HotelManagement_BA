/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.RoomStayModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class CheckOutRoom extends javax.swing.JDialog {

    private int stayId;
    private JTable roomList;
    private CheckOutForm checkOutForm;
    Object[] columnsname1 = {"chon", "tenphong"};

    public Class<?> getaClass1(int column) {
        switch (column) {
            case 0:
                return Boolean.class;
            case 1:
                return String.class;
        }
        return null;
    }

    public CheckOutRoom(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public CheckOutRoom(java.awt.Frame parent, boolean modal, int stayId, JTable roomList) {
        super(parent, modal);
        this.stayId = stayId;
        this.roomList = roomList;
        initComponents();
        initializeRoomList();
    }

    private void initializeRoomList() {
        DefaultTableModel model = (DefaultTableModel) dstraphong.getModel();
        for (int i = 0; i < roomList.getRowCount(); i++) {
            if (roomList.getValueAt(i, 3) == null) {
                model.addRow(new Object[]{roomList.getValueAt(i, 0), false});
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        traphong = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dstraphong = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(300, 300));

        traphong.setText("Trả phòng");
        traphong.setPreferredSize(new java.awt.Dimension(79, 50));
        traphong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traphongActionPerformed(evt);
            }
        });
        getContentPane().add(traphong, java.awt.BorderLayout.PAGE_END);

        dstraphong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phòng", "check"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dstraphong);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(603, 30));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_delete_24px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, java.awt.BorderLayout.LINE_END);

        jLabel2.setText("Trả phòng");
        jPanel2.add(jLabel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void traphongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traphongActionPerformed
        RoomStayModel roomStayModel = new RoomStayModel();
        for (int i = 0; i < dstraphong.getRowCount(); i++) {
            Boolean check = (Boolean) dstraphong.getValueAt(i, 1);
            if (check) {
                try {
                    roomStayModel.checkOutRoom(stayId, dstraphong.getValueAt(i, 0).toString());
                } catch (SQLException ex) {
                    Logger.getLogger(CheckOutRoom.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Trả phòng thành công");
        dispose();
    }//GEN-LAST:event_traphongActionPerformed

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
            java.util.logging.Logger.getLogger(CheckOutRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckOutRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckOutRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckOutRoom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CheckOutRoom dialog = new CheckOutRoom(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable dstraphong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton traphong;
    // End of variables declaration//GEN-END:variables
}

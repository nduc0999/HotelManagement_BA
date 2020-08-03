package View;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Yue
 */
public class MainForm extends javax.swing.JFrame {

    //Rút ngắn code làm hover effect cho các nút
    private final Color defaultColor;
    private final Color choosenColor = new Color(153, 255, 255);
    JLabel currentChoosen;
    CardLayout card;
    //4 form phụ
    ManagementForm managementForm = new ManagementForm(this);
    OrderForm orderForm = new OrderForm();
    CheckInForm checkInForm = new CheckInForm();
    CheckOutForm checkOutForm = new CheckOutForm();
    IndemnifyForm indemnifyForm = new IndemnifyForm();

    public MainForm() {
        initComponents();
        //Set kích thước toàn màn hình
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setResizable(false);

        //Thêm các form chức năng
        card = (CardLayout) tabDetails.getLayout();
        managerDetail.add(managementForm);
        orderDetail.add(orderForm);
        checkinDetail.add(checkInForm);
        checkoutDetail.add(checkOutForm);
        reportDetail.add(indemnifyForm);
        //Khởi tạo native ban đầu
        defaultColor = tabs.getBackground();
        quanlyphong.setBackground(choosenColor);
        currentChoosen = quanlyphong;
        quanlyphongMouseClicked(null);
        boithuong.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nav = new javax.swing.JPanel();
        title = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jLabel2 = new javax.swing.JLabel();
        tabs = new javax.swing.JPanel();
        quanlyphong = new javax.swing.JLabel();
        datphong = new javax.swing.JLabel();
        nhanphong = new javax.swing.JLabel();
        traphong = new javax.swing.JLabel();
        boithuong = new javax.swing.JLabel();
        tabDetails = new javax.swing.JPanel();
        orderDetail = new javax.swing.JPanel();
        checkinDetail = new javax.swing.JPanel();
        checkoutDetail = new javax.swing.JPanel();
        reportDetail = new javax.swing.JPanel();
        managerDetail = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        nav.setBackground(new java.awt.Color(153, 204, 255));
        nav.setPreferredSize(new java.awt.Dimension(60, 70));
        nav.setLayout(new java.awt.BorderLayout());

        title.setBackground(new java.awt.Color(255, 204, 102));
        title.setPreferredSize(new java.awt.Dimension(1300, 30));
        title.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/architecture-and-city.png"))); // NOI18N
        jLabel1.setText("QUẢN LÝ KHÁCH SẠN");
        jLabel1.setMaximumSize(new java.awt.Dimension(196, 30));
        jLabel1.setMinimumSize(new java.awt.Dimension(196, 30));
        jLabel1.setPreferredSize(new java.awt.Dimension(300, 30));
        title.add(jLabel1, java.awt.BorderLayout.LINE_START);

        jPanel1.setBackground(title.getBackground());
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_subtract_24px.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3);
        jPanel1.add(filler1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_delete_24px.png"))); // NOI18N
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.setPreferredSize(new java.awt.Dimension(30, 30));
        jLabel2.setVerifyInputWhenFocusTarget(false);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2);

        title.add(jPanel1, java.awt.BorderLayout.CENTER);

        nav.add(title, java.awt.BorderLayout.PAGE_START);

        tabs.setBackground(new java.awt.Color(153, 204, 255));
        tabs.setPreferredSize(new java.awt.Dimension(1320, 1320));
        tabs.setLayout(new java.awt.GridLayout(1, 0));

        quanlyphong.setBackground(new java.awt.Color(153, 255, 255));
        quanlyphong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        quanlyphong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quanlyphong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/circled_menu_24px.png"))); // NOI18N
        quanlyphong.setText("Danh sách phòng");
        quanlyphong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        quanlyphong.setOpaque(true);
        quanlyphong.setPreferredSize(new java.awt.Dimension(150, 70));
        quanlyphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanlyphongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        tabs.add(quanlyphong);

        datphong.setBackground(nav.getBackground());
        datphong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        datphong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        datphong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/create_24px.png"))); // NOI18N
        datphong.setText("Đặt phòng");
        datphong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        datphong.setOpaque(true);
        datphong.setPreferredSize(new java.awt.Dimension(150, 70));
        datphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datphongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        tabs.add(datphong);

        nhanphong.setBackground(nav.getBackground());
        nhanphong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nhanphong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nhanphong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/tick_box_24px.png"))); // NOI18N
        nhanphong.setText("Nhận phòng");
        nhanphong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        nhanphong.setOpaque(true);
        nhanphong.setPreferredSize(new java.awt.Dimension(150, 70));
        nhanphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nhanphongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        tabs.add(nhanphong);

        traphong.setBackground(nav.getBackground());
        traphong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        traphong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traphong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/money_24px.png"))); // NOI18N
        traphong.setText("Trả phòng");
        traphong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        traphong.setOpaque(true);
        traphong.setPreferredSize(new java.awt.Dimension(150, 70));
        traphong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                traphongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        tabs.add(traphong);

        boithuong.setBackground(nav.getBackground());
        boithuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boithuong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        boithuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/edit_graph_report_32px.png"))); // NOI18N
        boithuong.setText("Thu/Chi bồi thường");
        boithuong.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        boithuong.setOpaque(true);
        boithuong.setPreferredSize(new java.awt.Dimension(150, 70));
        boithuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boithuongMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                HoverButton(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutHoverButton(evt);
            }
        });
        tabs.add(boithuong);

        nav.add(tabs, java.awt.BorderLayout.CENTER);

        getContentPane().add(nav, java.awt.BorderLayout.PAGE_START);

        tabDetails.setLayout(new java.awt.CardLayout());

        orderDetail.setLayout(new java.awt.GridLayout(1, 0));
        tabDetails.add(orderDetail, "datphong");

        checkinDetail.setLayout(new java.awt.GridLayout(1, 0));
        tabDetails.add(checkinDetail, "nhanphong");

        checkoutDetail.setLayout(new java.awt.GridLayout(1, 0));
        tabDetails.add(checkoutDetail, "traphong");

        reportDetail.setLayout(new java.awt.GridLayout(1, 0));
        tabDetails.add(reportDetail, "boithuong");

        managerDetail.setLayout(new java.awt.GridLayout(1, 0));
        tabDetails.add(managerDetail, "quanlyphong");

        getContentPane().add(tabDetails, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Hàm thay đổi màu khi active các nút

    private void changeColorChoosen(JLabel label) {
        currentChoosen.setBackground(defaultColor);
        label.setBackground(choosenColor);
        currentChoosen = label;
    }

    public void goToCheckOut(int stayId) {
        card.show(tabDetails, "traphong");
        checkOutForm.searchProfile(stayId);
    }

    public void gotoIndemnify() {
        card.show(tabDetails, "boithuong");
    }
    private void datphongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datphongMouseClicked
        changeColorChoosen(datphong);
        card.show(tabDetails, "datphong");
    }//GEN-LAST:event_datphongMouseClicked

    private void quanlyphongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanlyphongMouseClicked
        changeColorChoosen(quanlyphong);
        card.show(tabDetails, "quanlyphong");
    }//GEN-LAST:event_quanlyphongMouseClicked

    private void boithuongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boithuongMouseClicked
        changeColorChoosen(boithuong);
        card.show(tabDetails, "boithuong");
        indemnifyForm.mathuephongComboBox.removeAllItems();
        indemnifyForm.showMathuephong();
    }//GEN-LAST:event_boithuongMouseClicked

    //Hover
    private void HoverButton(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoverButton
        if (evt.getComponent().getBackground() != choosenColor) {
            evt.getComponent().setBackground(new Color(185, 233, 255));
        }
    }//GEN-LAST:event_HoverButton

    private void OutHoverButton(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OutHoverButton
        if (evt.getComponent().getBackground() != choosenColor) {
            evt.getComponent().setBackground(defaultColor);
        }
    }//GEN-LAST:event_OutHoverButton

    ///Hover
    private void nhanphongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nhanphongMouseClicked
        changeColorChoosen(nhanphong);
        card.show(tabDetails, "nhanphong");
    }//GEN-LAST:event_nhanphongMouseClicked

    private void traphongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_traphongMouseClicked
        changeColorChoosen(traphong);
        card.show(tabDetails, "traphong");

    }//GEN-LAST:event_traphongMouseClicked

    //Thoát chương trình
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    //Hạ chương trình xuống thanh tác vụ
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_jLabel3MouseClicked

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel boithuong;
    private javax.swing.JPanel checkinDetail;
    private javax.swing.JPanel checkoutDetail;
    private javax.swing.JLabel datphong;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel managerDetail;
    private javax.swing.JPanel nav;
    private javax.swing.JLabel nhanphong;
    private javax.swing.JPanel orderDetail;
    private javax.swing.JLabel quanlyphong;
    private javax.swing.JPanel reportDetail;
    private javax.swing.JPanel tabDetails;
    private javax.swing.JPanel tabs;
    private javax.swing.JPanel title;
    private javax.swing.JLabel traphong;
    // End of variables declaration//GEN-END:variables
}

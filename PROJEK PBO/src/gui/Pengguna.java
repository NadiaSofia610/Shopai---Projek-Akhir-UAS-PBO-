/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import method.login.Enkripsi;
import method.login.datapengguna;
import method.login.penggunacontrol;

/**
 *
 * @author User
 */
public class Pengguna extends javax.swing.JInternalFrame {
    int x = 0;
    private DefaultTableModel model;
    penggunacontrol pc = new penggunacontrol();
    datapengguna dp = new datapengguna();
    List<datapengguna> listpengguna = new ArrayList<datapengguna>();

    /**
     * Creates new form Pengguna
     */
    public Pengguna() {
        initComponents();
        buattable();
        showtable();
    }
    
    private void buattable() {
        model = new DefaultTableModel();
        model.addColumn("id pengguna");
        model.addColumn("username");
        model.addColumn("password");
        model.addColumn("hak akses");
        jTable1.setModel(model);
    }
    
    private void showtable(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        listpengguna.clear();
        listpengguna=pc.tampil();
        for(x = 0; x < listpengguna.size(); x++) {
            Object[] data = new Object[4];
            data[0] = listpengguna.get(x).getidpengguna();
            data[1] = listpengguna.get(x).getusername();
            data[2] = listpengguna.get(x).getpassword();
            data[3] = listpengguna.get(x).gethakakses();
            model.addRow(data);
        }
    }
    
    private void bersih() {
        txtuser.setText(null);
        txtpass.setText(null);
        txtupass.setText(null);
        cmbhak.setSelectedItem("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtuser = new javax.swing.JTextField();
        txtpass = new javax.swing.JPasswordField();
        txtupass = new javax.swing.JPasswordField();
        cmbhak = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(170, 0, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel1.setText("DATA PENGGUNA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(jLabel1)
                .addContainerGap(347, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 100));

        jPanel2.setBackground(new java.awt.Color(44, 62, 80));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ID Pengguna");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Username");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Password");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ulangi Password");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("HakAkses");

        txtid.setBackground(new java.awt.Color(44, 62, 80));
        txtid.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtid.setForeground(new java.awt.Color(255, 255, 255));
        txtid.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtuser.setBackground(new java.awt.Color(44, 62, 80));
        txtuser.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtuser.setForeground(new java.awt.Color(255, 255, 255));
        txtuser.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtpass.setBackground(new java.awt.Color(44, 62, 80));
        txtpass.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtpass.setForeground(new java.awt.Color(255, 255, 255));
        txtpass.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtupass.setBackground(new java.awt.Color(44, 62, 80));
        txtupass.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtupass.setForeground(new java.awt.Color(255, 255, 255));
        txtupass.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbhak.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbhak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Administrator", "Kasir" }));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Batal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbhak, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtuser, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(txtpass)
                            .addComponent(txtupass)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(61, 61, 61)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(568, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtupass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbhak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1030, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (txtid.getText().isEmpty() || txtuser.getText().isEmpty() || txtpass.getText().isEmpty() || cmbhak.toString().equals(null)) {
        JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "pesan", JOptionPane.WARNING_MESSAGE);
        } else {
            // validasi password dan ketik ulang password
            if (txtpass.getText() == null ? txtupass.getText() != null : !txtpass.getText().equals(txtupass.getText())) {
                JOptionPane.showMessageDialog(null, "Password tidak sama periksa kembali", "pesan", JOptionPane.WARNING_MESSAGE);
                txtpass.requestFocus();
            } else {
                //validasi idpengguna
                listpengguna=pc.cariid(Integer.parseInt(txtid.getText()));
                if (listpengguna.size()==1) {
                JOptionPane.showMessageDialog(null, "Pengguna telah digunakan user lain", "pesan", JOptionPane.WARNING_MESSAGE);
                txtid.requestFocus();
                } else {
                    try{
                        dp.setidpengguna(Integer.parseInt(txtid.getText()));
                        dp.setusername(txtuser.getText());
                        dp.setpassword(Enkripsi.getSHA1(txtpass.getText()));
                        dp.sethakakses(cmbhak.getSelectedItem().toString());
                        if (pc.tambah(dp)==1) {
                            JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                            buattable();
                            showtable();    
                        } else {
                            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
                        }
                        bersih();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Tidak dapat enkripsi password");
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    dp.setidpengguna(Integer.parseInt(txtid.getText()));
    int pesan = JOptionPane.showConfirmDialog(null, "Data akan dihapus ?", "Konfirmasi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (pesan==JOptionPane.YES_NO_OPTION) {
            if (pc.deletepengguna(dp)==1) {
                buattable();
                showtable();
                bersih();
            }else {
                JOptionPane.showMessageDialog(null, "Hapus data gagal");
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    int baris = jTable1.getSelectedRow();
    txtid.setText(jTable1.getModel().getValueAt(baris, 0).toString());
    txtuser.setText(jTable1.getModel().getValueAt(baris, 1).toString());
    txtpass.setText(jTable1.getModel().getValueAt(baris, 2).toString());
    cmbhak.setSelectedItem(jTable1.getModel().getValueAt(baris, 3).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    bersih();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbhak;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtid;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JPasswordField txtupass;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}

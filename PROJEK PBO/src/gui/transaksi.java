/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.sun.javafx.image.impl.IntArgb;
import com.sun.prism.PresentableState;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import apotek.koneksi;

/**
 *
 * @author Fajar
 */
public class transaksi extends javax.swing.JFrame {
private DefaultTableModel model;

    /**
     * Creates new form transaksi
     */
    public transaksi() {
       
        initComponents();
        ImageIcon icon = new ImageIcon("src/gambar/unnamed.PNG");
        setIconImage(icon.getImage());
        this.setLocationRelativeTo(null);
        auto_key();
        txtNofa.disable();
        
        //variabel pembantu di tutup
        TxtStock.hide();
        TxtDateTime.hide();
        
        model =new DefaultTableModel();
        TblDetail.setModel(model);
        model.addColumn("ID Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Qry");
        model.addColumn("Sub Total");
        model.addColumn("Jual Time");
        
        //START fungsi tidak menampilkan column ID barang(0) dan jual time (5)
        TblDetail.getColumnModel().getColumn(5).setMinWidth(0);
        TblDetail.getColumnModel().getColumn(5).setMaxWidth(0);
        TblDetail.getColumnModel().getColumn(5).setWidth(0);
        
        TblDetail.getColumnModel().getColumn(0).setMinWidth(0);
        TblDetail.getColumnModel().getColumn(0).setMaxWidth(0);
        TblDetail.getColumnModel().getColumn(0).setWidth(0);
        
        loadData();
        Date date = new Date();
        JdateJual.setDate(date);
    }
    
    public void Batal(){
    int x, y, z;
    x = Integer.parseInt(TxtStock.getText());
    y = Integer.parseInt(TxtQty.getText());
    z = x+y;
    
    String Barang_ID=this.TxtKode.getText();
     try{
       Connection c= koneksi.getConnection();  
       String sql ="UPDATE tbdataobat set jumlah_stok=? WHERE idobat=?";  
       PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
           p.setInt(1,z);
           p.setString(2,Barang_ID);//yang kode atau id letakkan di nomor terakhir  
           p.executeUpdate();  
           p.close();  
     }catch(SQLException e){  
       System.out.println("Terjadi Kesalahan");  
     }finally{   
       //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");  
     }
   
      //Proses mengahapus data dari tabel detail
     try {
        Connection c= koneksi.getConnection();
        String sql="DELETE FROM t_jualdetail WHERE jual_nofa='"+this.txtNofa.getText()+"' AND  jual_time ='"+this.TxtDateTime.getText()+"'";
        PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);
        p.executeUpdate();
        p.close();
    }catch(SQLException e){
        System.out.println("Terjadi Kesalahan");
    }finally{
        loadData();
        JOptionPane.showMessageDialog(this,"Sukses Hapus Data...");
    }  
   }
    
    public void Cari_Kode(){
    int i=TblDetail.getSelectedRow();  
    if(i==-1)  
    { return; }  
    String ID=(String)model.getValueAt(i, 0); 
    TxtKode.setText(ID);
    }
    
    public void ShowData(){
    try {
        Connection c=koneksi.getConnection();
        String sql="Select * from t_jualdetail, tbdataobat WHERE t_jualdetail.jual_barangid = tbdataobat.idobat AND t_jualdetail.jual_barangid='"+this.TxtKode.getText()+"'"; 
        Statement st = koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            this.TxtQty.setText(rs.getString("jual_qty"));
            this.TxtNama.setText(rs.getString("namabarang"));
            this.TxtHJual.setText(rs.getString("jual_harga"));
            this.TxtSubTotal.setText(rs.getString("jual_subtotal"));
            this.TxtDateTime.setText(rs.getString("jual_time"));
        }
    rs.close(); st.close();
    } 
    catch (Exception e) {
        System.out.println(e.getMessage());
    }
 }
    public void ShowSisa(){
   try {
        Connection c=koneksi.getConnection();
        String sql="Select * from  tbdataobat WHERE idobat ='"+this.TxtKode.getText()+"'"; 
        Statement st = koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
        this.TxtStock.setText(rs.getString("jumlah_stok"));      
        }
        rs.close(); st.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
 }
    
    public void UpdateStock(){
    int x, y, z;
    x = Integer.parseInt(TxtStock.getText());
    y = Integer.parseInt(TxtQty.getText());
    z = x-y;
    
    String Barang_ID=this.TxtKode.getText();
     try{
       Connection c= koneksi.getConnection();  
       String sql ="UPDATE tbdataobat set jumlah_stok=? WHERE idobat=?";  
       PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
           p.setInt(1,z);
           p.setString(2,Barang_ID);//yang kode atau id letakkan di nomor terakhir  
           p.executeUpdate();  
           p.close();  
     }catch(SQLException e){  
       System.out.println("Terjadi Kesalahan");  
     }finally{   
       //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");  
     }
}
    
    public final void loadData(){
   model.getDataVector().removeAllElements();
   model.fireTableDataChanged();
   try{  
     Connection c= koneksi.getConnection();
       Statement s= c.createStatement();
     String sql="Select * from t_jualdetail, tbdataobat WHERE t_jualdetail.jual_barangid = tbdataobat.idobat AND t_jualdetail.jual_nofa='"+this.txtNofa.getText()+"'"; 
       ResultSet r=s.executeQuery(sql);
     while(r.next()){
       Object[]o=new Object[6];
       o[0]=r.getString("jual_barangid");
       o[1]=r.getString("namabarang");
       o[2]=r.getString("jual_harga");
       o[3]=r.getString("jual_qty");
       o[4]=r.getString("jual_subtotal");
       o[5]=r.getString("jumlah_stok");
       model.addRow(o);
     }  
     r.close();  
     s.close();  
     //ShowData();  
   }catch(SQLException e){  
     System.out.println("Terjadi Kesalahan");  
   }



   //menjumlahkan isi colom ke 4 dalam tabel
   int total = 0;
   for (int i =0; i< TblDetail.getRowCount(); i++){
       int amount = Integer.parseInt((String)TblDetail.getValueAt(i, 4));
       total += amount;
   }
   TxtTotal.setText(""+total);
 }  
    
    public void AutoSum() {     
        int a, b, c;
        a = Integer.parseInt(TxtHJual.getText());
        b = Integer.parseInt(TxtQty.getText());
        c = a*b;
        TxtSubTotal.setText(""+c);
    }
    
    public void HitungKembali() {     
        int d, e, f;
        d = Integer.parseInt(TxtTotal.getText());
        e = Integer.parseInt(TxtCash.getText());
        f = e-d;
        TxtKembali.setText(""+f);
    }

    public void auto_key(){  
   try {  
   java.util.Date tgl = new java.util.Date();  
   java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyMMdd");  
   java.text.SimpleDateFormat tanggal = new java.text.SimpleDateFormat("yyyyMMdd");  
     Connection c=koneksi.getConnection();  
     String sql = "select max(jual_nofa) from t_jual WHERE jual_tgl ="+tanggal.format(tgl);   
     Statement st = koneksi.getConnection().createStatement();  
     ResultSet rs = st.executeQuery(sql);  
     while(rs.next()){  
     Long a =rs.getLong(1); //mengambil nilai tertinggi  
       if(a == 0){  
         this.txtNofa.setText(kal.format(tgl)+"0000"+(a+1));  
       }else{  
         this.txtNofa.setText(""+(a+1));  
       }  
   }  
   rs.close(); st.close();}  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalahan");  
   }  
 }  
    
    public void Selesai(){   
    String jual_nofa =this.txtNofa.getText();     
    String jual_total=this.TxtTotal.getText();
    String jual_cash =this.TxtCash.getText();
    String jual_kembali =this.TxtKembali.getText();
    String nama_pembeli = this.Txtnamapem.getText();
   
   //Date date = new Date();
   //JdateJual.setDate(date);
        
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
   Date tanggal = new Date(); 
   tanggal = JdateJual.getDate(); 
   String jual_tgl = dateFormat.format(tanggal);

        
        
   
   try{  
     Connection c=koneksi.getConnection();  
     String sql="Insert into t_jual (jual_nofa,jual_tgl,jual_total,jual_cash,jual_kembali) values (?,?,?,?,?)";  
     PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
     p.setString(1,jual_nofa);
     p.setString(2,jual_tgl);
     p.setString(3,jual_total);
     p.setString(4,jual_cash);
     p.setString(5,jual_kembali);
     p.executeUpdate();
     p.close();
   }catch(SQLException e){ 
   System.out.println(e);  
   }finally{  
   //loadData();
       JOptionPane.showMessageDialog(this,"Data Telah Tersimpan");  
  }
   
  auto_key();
  loadData();
 }  
      
      public void TambahDetail(){
   Date HariSekarang = new Date( );
   SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
      
   String jual_nofa =this.txtNofa.getText();  
   String jual_barangid =this.TxtKode.getText();  
   String jual_harga=this.TxtHJual.getText();  
   String jual_qty=this.TxtQty.getText();
   String jual_subtotal =this.TxtSubTotal.getText();
   String DateTime = ft.format(HariSekarang);
   String nama_pembeli = this.Txtnamapem.getText();
   String nama_barang= this.TxtNama.getText();
   
   try{  
     Connection c=koneksi.getConnection();  
     String sql="Insert into t_jualdetail (jual_nofa,jual_barangid,jual_harga,jual_qty,jual_subtotal,jual_time,nama_pembeli,namabarang) values (?,?,?,?,?,?,?,?)";  
     PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
     p.setString(1,jual_nofa);
     p.setString(2,jual_barangid);
     p.setString(3,jual_harga);
     p.setString(4,jual_qty);
     p.setString(5,jual_subtotal);
     p.setString(6,DateTime);
     p.setString(7,nama_pembeli);
     p.setString(8,nama_barang);
     p.executeUpdate();
     p.close();
   }catch(SQLException e){ 
   System.out.println(e);  
   }finally{  
   //loadData();
       //JOptionPane.showMessageDialog(this,"Data Telah Tersimpan");  
  }
 }
      
       public void cari_id(){
        try {
        Connection c=koneksi.getConnection();
        String sql = "select * from tbdataobat where idobat='"+this.TxtKode.getText()+"'"; 
        Statement st = koneksi.getConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
        this.TxtNama.setText(rs.getString("nama_obat"));
        this.TxtHJual.setText(rs.getString("harga"));
        this.TxtStock.setText(rs.getString("jumlah_stok"));
        }
        rs.close(); st.close();}
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
}
   
        public  void bersihkan(){
        TxtKode.setText("");
        TxtNama.setText("");
        TxtHJual.setText("");
        TxtQty.setText("");
        TxtCash.setText("");
        TxtSubTotal.setText("");
        TxtKembali.setText("");
       
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Nama = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNofa = new javax.swing.JTextField();
        JdateJual = new com.toedter.calendar.JDateChooser();
        TxtKode = new javax.swing.JTextField();
        TxtNama = new javax.swing.JTextField();
        TxtHJual = new javax.swing.JTextField();
        TxtQty = new javax.swing.JTextField();
        TxtSubTotal = new javax.swing.JTextField();
        BtnAdd = new javax.swing.JButton();
        BtnBatal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDetail = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        TxtCash = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtKembali = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        TxtStock = new javax.swing.JTextField();
        TxtDateTime = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TxtTotal = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Txtnamapem = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        laporan = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Transaksi");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 60));

        jPanel2.setBackground(new java.awt.Color(255, 0, 204));

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel2.setText("No Faktur");

        jLabel3.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel3.setText("Tanggal");

        jLabel5.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel5.setText("Kode Barang");

        Nama.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        Nama.setText("Nama");

        jLabel6.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel6.setText("Harga");

        jLabel7.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel7.setText("Qty");

        jLabel8.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel8.setText("Sub Total");

        TxtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeActionPerformed(evt);
            }
        });
        TxtKode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtKodeKeyPressed(evt);
            }
        });

        TxtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtQtyKeyPressed(evt);
            }
        });

        BtnAdd.setText("ADD");
        BtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddActionPerformed(evt);
            }
        });

        BtnBatal.setText("Batal");
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });

        TblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Barang", "Nama Barang", "Harga", "Qty", "Sub Total", "jual_time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblDetail);
        if (TblDetail.getColumnModel().getColumnCount() > 0) {
            TblDetail.getColumnModel().getColumn(1).setResizable(false);
            TblDetail.getColumnModel().getColumn(2).setResizable(false);
            TblDetail.getColumnModel().getColumn(3).setResizable(false);
            TblDetail.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel9.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel9.setText("Cash");

        TxtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCashKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel10.setText("Kembali");

        jButton3.setText("Simpan");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel12.setText("Total Pembelian (Rp)");

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 0, 14)); // NOI18N
        jLabel4.setText("Nama Pembeli");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/pri.PNG"))); // NOI18N
        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtHJual, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(TxtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(BtnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Nama, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jLabel6)
                                .addGap(71, 71, 71)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel8))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TxtKembali, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(TxtCash))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtStock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtDateTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(153, 153, 153))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNofa, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(JdateJual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(248, 248, 248))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Txtnamapem)
                        .addGap(211, 211, 211)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(72, 72, 72))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNofa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(JdateJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(Txtnamapem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(TxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(Nama)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(BtnBatal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtHJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAdd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(TxtCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(TxtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(TxtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(TxtDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton3)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 770, 400));

        jMenu1.setText("File");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/home.PNG"))); // NOI18N
        jMenuItem2.setText("Home");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/db.PNG"))); // NOI18N
        jMenuItem3.setText("Data Barang");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/acc.PNG"))); // NOI18N
        jMenuItem4.setText("Log Out");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gambar/logut.PNG"))); // NOI18N
        jMenuItem5.setText("Keluar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        laporan.setText("Help");
        laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanMouseClicked(evt);
            }
        });
        laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laporanActionPerformed(evt);
            }
        });
        jMenuBar1.add(laporan);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtKodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtKodeKeyPressed
   if (evt.getKeyCode() == KeyEvent.VK_ENTER) {     
            cari_id();
        }
    }//GEN-LAST:event_TxtKodeKeyPressed

    private void TxtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtQtyKeyPressed
if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        AutoSum();
        }
    }//GEN-LAST:event_TxtQtyKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Batal();
        bersihkan();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddActionPerformed
        TambahDetail();
        UpdateStock();
        loadData();
        bersihkan();
       
    }//GEN-LAST:event_BtnAddActionPerformed

    private void TblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblDetailMouseClicked
    this.Cari_Kode();
        this.ShowData();
        this.ShowSisa();
    }//GEN-LAST:event_TblDetailMouseClicked

    private void TxtCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCashKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        HitungKembali();
        }
    }//GEN-LAST:event_TxtCashKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Selesai();
        bersihkan();
     
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
     new menuutama1().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         new databarang().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
         new login().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laporanActionPerformed
        new laporan().setVisible(true);
        dispose();
    }//GEN-LAST:event_laporanActionPerformed

    private void laporanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanMouseClicked
         JOptionPane.showMessageDialog(this, "Lihatlah Data Base Bila Terjadi Kesalahan"+"\n");
    }//GEN-LAST:event_laporanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String reportSource = null;
        String reportDest = null;
        
        try{
           com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) koneksi.getConnection();
           reportSource = System.getProperty("user.dir") + "/src/laporan/nota.jrxml";
           reportDest = System.getProperty("user.dir") + "/src/laporan/nota.jasper";
           System.out.println(reportDest);
           
            HashMap param = new HashMap(2);
            param.put("nama",Txtnamapem.getText());
           JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
           JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, c);
           JasperExportManager.exportReportToPdfFile(jasperPrint, reportDest);
           JasperViewer.viewReport(jasperPrint, false);
           
       } catch (JRException e) {
           e.printStackTrace();
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TxtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtKodeActionPerformed

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
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdd;
    private javax.swing.JButton BtnBatal;
    private com.toedter.calendar.JDateChooser JdateJual;
    private javax.swing.JLabel Nama;
    private javax.swing.JTable TblDetail;
    private javax.swing.JTextField TxtCash;
    private javax.swing.JTextField TxtDateTime;
    private javax.swing.JTextField TxtHJual;
    private javax.swing.JTextField TxtKembali;
    private javax.swing.JTextField TxtKode;
    private javax.swing.JTextField TxtNama;
    private javax.swing.JTextField TxtQty;
    private javax.swing.JTextField TxtStock;
    private javax.swing.JTextField TxtSubTotal;
    private javax.swing.JTextField TxtTotal;
    private javax.swing.JTextField Txtnamapem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu laporan;
    private javax.swing.JTextField txtNofa;
    // End of variables declaration//GEN-END:variables
}

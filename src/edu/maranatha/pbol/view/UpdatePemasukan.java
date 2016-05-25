/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.view;

import edu.maranatha.pbol.controller.PemasukanTableController;
import edu.maranatha.pbol.controller.PengeluaranTableController;
import edu.maranatha.pbol.model.pojo.Pemasukan;
import edu.maranatha.pbol.model.pojo.Pengeluaran;
import edu.maranatha.pbol.presistence.HibernateUtil;
import edu.maranatha.pbol.util.Cache;
import edu.maranatha.pbol.util.DateLabelFormatter;
import edu.maranatha.pbol.util.Validation;
import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.swing.ImageIcon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Didit Velliz
 */
public class UpdatePemasukan extends javax.swing.JFrame {

    private final JDatePickerImpl datePicker2;
    private final PemasukanTableController dtmPemasukan;

    private final NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    private int id = 0;
    Pemasukan pem = null;

    /**
     * Creates new form UpdatePemasukan
     *
     * @param id
     */
    public UpdatePemasukan(int id) {
        this.id = id;
        initComponents();

        setIconImage(new ImageIcon("money.png").getImage());
        setLocationRelativeTo(null);

        dtmPemasukan = new PemasukanTableController();
        setSisaSaldo();
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Pemasukan> dataMasuk = session.createQuery("from Pemasukan WHERE idpemasukan = " + this.id).list();
        for (Pemasukan p : dataMasuk) {
            this.pem = p;
            break;
        }

        UtilDateModel model = new UtilDateModel(pem.getTanggalpemasukan());
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);

        datePicker2 = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        panelDatePickerPemasukan.setLayout(new CardLayout());
        panelDatePickerPemasukan.add(datePicker2);

        pemasukanNominal.setText(String.valueOf(pem.getNominal()));
        pemasukanKeterangan.setText(pem.getKeterangan());

    }

    public final void setSisaSaldo() {
        String sisaKas = String.valueOf(formatter.format(Cache.getKas()));
        saldo2.setText(sisaKas);
    }
    
        @Override
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            new MoneyManager().setVisible(true);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pemasukanKeterangan = new javax.swing.JTextArea();
        DoSimpanPemasukan = new javax.swing.JButton();
        DoResetPemasukan = new javax.swing.JButton();
        panelDatePickerPemasukan = new javax.swing.JPanel();
        pemasukanNominal = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        saldo2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Pemasukan"));

        jLabel5.setText("Tanggal Pengeluaran");

        jLabel6.setText("Nominal");

        jLabel7.setText("Keterangan");

        pemasukanKeterangan.setColumns(20);
        pemasukanKeterangan.setRows(5);
        jScrollPane3.setViewportView(pemasukanKeterangan);

        DoSimpanPemasukan.setText("Simpan");
        DoSimpanPemasukan.setOpaque(false);
        DoSimpanPemasukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoSimpanPemasukanActionPerformed(evt);
            }
        });

        DoResetPemasukan.setText("Reset");
        DoResetPemasukan.setOpaque(false);
        DoResetPemasukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoResetPemasukanActionPerformed(evt);
            }
        });

        panelDatePickerPemasukan.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout panelDatePickerPemasukanLayout = new javax.swing.GroupLayout(panelDatePickerPemasukan);
        panelDatePickerPemasukan.setLayout(panelDatePickerPemasukanLayout);
        panelDatePickerPemasukanLayout.setHorizontalGroup(
            panelDatePickerPemasukanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDatePickerPemasukanLayout.setVerticalGroup(
            panelDatePickerPemasukanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Sisa Saldo"));

        saldo2.setEditable(false);
        saldo2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(saldo2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saldo2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(DoSimpanPemasukan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DoResetPemasukan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(panelDatePickerPemasukan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pemasukanNominal))
                        .addGap(0, 43, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(panelDatePickerPemasukan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(pemasukanNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DoSimpanPemasukan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(DoResetPemasukan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DoSimpanPemasukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoSimpanPemasukanActionPerformed
        String datePattern = "dd MMMM yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, new Locale("en", "US"));
        String tanggal = dateFormatter.format(datePicker2.getModel().getValue());
        int nominal = Integer.parseInt(pemasukanNominal.getText().replace(",", ""));
        String keterangan = pemasukanKeterangan.getText();

        if (Validation.Validate(tanggal, nominal, keterangan)) {
            Pemasukan baru = new Pemasukan(Cache.user, nominal, (Date) datePicker2.getModel().getValue(), keterangan);
            baru.setIdpemasukan(this.id);

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(baru);
            transaction.commit();

            dtmPemasukan.add(baru);

            Validation.infoDialouge(this, "Data pemasukan berhasil disimpan");
        }
          java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MoneyManager().setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_DoSimpanPemasukanActionPerformed

    private void DoResetPemasukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoResetPemasukanActionPerformed
        datePicker2.getModel().setValue(null);
        pemasukanNominal.setText("");
        pemasukanKeterangan.setText("");
    }//GEN-LAST:event_DoResetPemasukanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DoResetPemasukan;
    private javax.swing.JButton DoSimpanPemasukan;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel panelDatePickerPemasukan;
    private javax.swing.JTextArea pemasukanKeterangan;
    private javax.swing.JTextField pemasukanNominal;
    private javax.swing.JTextField saldo2;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.view;

import edu.maranatha.pbol.controller.PengeluaranTableController;
import edu.maranatha.pbol.model.pojo.Pengeluaran;
import edu.maranatha.pbol.presistence.HibernateUtil;
import edu.maranatha.pbol.util.Cache;
import edu.maranatha.pbol.util.DateLabelFormatter;
import edu.maranatha.pbol.util.Validation;
import java.awt.CardLayout;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Didit Velliz
 */
public class UpdatePengeluaran extends javax.swing.JFrame {

    private final JDatePickerImpl datePicker;
    private final PengeluaranTableController dtmPengeluaran;

    private final NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    private int id = 0;

    /**
     * Creates new form UpdatePengeluaran
     * @param id
     */
    public UpdatePengeluaran(int id) {
        this.id = id;
        initComponents();

        setIconImage(new ImageIcon("money.png").getImage());
        setLocationRelativeTo(null);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        panelDatePickerPengeluaran.setLayout(new CardLayout());
        panelDatePickerPengeluaran.add(datePicker);

        dtmPengeluaran = new PengeluaranTableController();
        setSisaSaldo();
    }

    public final void setSisaSaldo() {
        String sisaKas = String.valueOf(formatter.format(Cache.getKas()));
        saldo1.setText(sisaKas);
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
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panelDatePickerPengeluaran = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pengeluaranKeterangan = new javax.swing.JTextArea();
        pengeluaranJenis = new javax.swing.JComboBox();
        DoSavePengeluaran = new javax.swing.JButton();
        DoResetPengeluaran = new javax.swing.JButton();
        pengeluaranNominal = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        saldo1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Pengeluaran"));

        jLabel1.setText("Tanggal Pengeluaran");

        jLabel2.setText("Nominal");

        jLabel3.setText("Keterangan");

        jLabel4.setText("Jenis");

        panelDatePickerPengeluaran.setBackground(new java.awt.Color(255, 255, 0));
        panelDatePickerPengeluaran.setPreferredSize(new java.awt.Dimension(0, 20));

        javax.swing.GroupLayout panelDatePickerPengeluaranLayout = new javax.swing.GroupLayout(panelDatePickerPengeluaran);
        panelDatePickerPengeluaran.setLayout(panelDatePickerPengeluaranLayout);
        panelDatePickerPengeluaranLayout.setHorizontalGroup(
            panelDatePickerPengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDatePickerPengeluaranLayout.setVerticalGroup(
            panelDatePickerPengeluaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        pengeluaranKeterangan.setColumns(20);
        pengeluaranKeterangan.setRows(5);
        jScrollPane1.setViewportView(pengeluaranKeterangan);

        pengeluaranJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tersier", "Sekunder", "Primer", "Darurat" }));

        DoSavePengeluaran.setText("Simpan");
        DoSavePengeluaran.setOpaque(false);
        DoSavePengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoSavePengeluaranActionPerformed(evt);
            }
        });

        DoResetPengeluaran.setText("Reset");
        DoResetPengeluaran.setOpaque(false);
        DoResetPengeluaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoResetPengeluaranActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Sisa Saldo"));

        saldo1.setEditable(false);
        saldo1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(saldo1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saldo1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(DoSavePengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DoResetPengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(pengeluaranJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDatePickerPengeluaran, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                            .addComponent(pengeluaranNominal))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDatePickerPengeluaran, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pengeluaranNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pengeluaranJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DoSavePengeluaran)
                    .addComponent(DoResetPengeluaran))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void DoSavePengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoSavePengeluaranActionPerformed
        try {
            String datePattern = "dd MMMM yyyy";
            SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, new Locale("en", "US"));
            String tanggal = dateFormatter.format(datePicker.getModel().getValue());

            int nominal = Integer.parseInt(pengeluaranNominal.getText().replace(",", ""));

            String keterangan = pengeluaranKeterangan.getText();
            String status = pengeluaranJenis.getModel().getSelectedItem().toString();

            if (Validation.Validate(tanggal, nominal, keterangan, status)) {

                if (Cache.getKas() < nominal) {
                    Validation.dangerDialouge(this, "Pengeluaran anda bulan ini sudah melebihi pemasukan.!");
                    return;
                }

                if ((nominal + Cache.getPengeluaran(tanggal)) > Cache.getAgenda(tanggal)) {
                    if (Validation.confirmationDialouge(this, "Pengeluaran anda melebihi anggaran " + formatter.format((nominal + Cache.getPengeluaran(tanggal)) - Cache.getAgenda(tanggal)) + ". Tetap lanjutkan.?") == JOptionPane.NO_OPTION) {
                        return;
                    }
                }

                Pengeluaran baru = new Pengeluaran(Cache.user, nominal, (Date) datePicker.getModel().getValue(), keterangan, status);
                baru.setIdpengeluaran(this.id);
                
                Session session = HibernateUtil.getSessionFactory().openSession();
                Transaction transaction = session.beginTransaction();
                session.saveOrUpdate(baru);
                transaction.commit();

                dtmPengeluaran.add(baru);
                setSisaSaldo();
                Validation.infoDialouge(this, "Data pengeluaran berhasil disimpan");
            }

        } catch (NumberFormatException ex) {
            Validation.infoDialouge(this, "Nominal harus angka positif");
        }
    }//GEN-LAST:event_DoSavePengeluaranActionPerformed

    private void DoResetPengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoResetPengeluaranActionPerformed
        datePicker.getModel().setValue(null);
        pengeluaranNominal.setText("");
        pengeluaranKeterangan.setText("");
        pengeluaranJenis.setSelectedIndex(0);
    }//GEN-LAST:event_DoResetPengeluaranActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DoResetPengeluaran;
    private javax.swing.JButton DoSavePengeluaran;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDatePickerPengeluaran;
    private javax.swing.JComboBox pengeluaranJenis;
    private javax.swing.JTextArea pengeluaranKeterangan;
    private javax.swing.JTextField pengeluaranNominal;
    private javax.swing.JTextField saldo1;
    // End of variables declaration//GEN-END:variables
}

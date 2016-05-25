/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.view;

import edu.maranatha.pbol.controller.AgendaTableController;
import edu.maranatha.pbol.controller.PemasukanTableController;
import edu.maranatha.pbol.controller.PengeluaranTableController;
import edu.maranatha.pbol.model.pojo.Agenda;
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
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Didit Velliz
 */
public class UpdateAgenda extends javax.swing.JFrame {

    private final JDatePickerImpl datePicker3;
    private final AgendaTableController dtmAgenda;
    
    private final NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    
    private int id = 0;
            
    /**
     * Creates new form UpdateAgenda
     * @param id
     */
    public UpdateAgenda(int id) {
        this.id = id;
        initComponents();
        setIconImage(new ImageIcon("money.png").getImage());
        setLocationRelativeTo(null);
        
        UtilDateModel model3 = new UtilDateModel();
        Properties p3 = new Properties();
        p3.put("text.today", "Today");
        p3.put("text.month", "Month");
        p3.put("text.year", "Year");
        JDatePanelImpl datePanel3 = new JDatePanelImpl(model3, p3);
        datePicker3 = new JDatePickerImpl(datePanel3, new DateLabelFormatter());
        panelDatePickerAgenda.setLayout(new CardLayout());
        panelDatePickerAgenda.add(datePicker3);

        dtmAgenda = new AgendaTableController();
        setSisaSaldo();
    }
    
    public final void setSisaSaldo() {
        String sisaKas = String.valueOf(formatter.format(Cache.getKas()));
        saldo3.setText(sisaKas);
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
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        agendaKeterangan = new javax.swing.JTextArea();
        DoSimpanAgenda = new javax.swing.JButton();
        DoResetAgenda = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        agendaOtoritas = new javax.swing.JComboBox();
        panelDatePickerAgenda = new javax.swing.JPanel();
        agendaNominal = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        saldo3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Agenda"));

        jLabel9.setText("Tanggal Pengeluaran");

        jLabel10.setText("Nominal");

        jLabel11.setText("Keterangan");

        agendaKeterangan.setColumns(20);
        agendaKeterangan.setRows(5);
        jScrollPane5.setViewportView(agendaKeterangan);

        DoSimpanAgenda.setText("Simpan");
        DoSimpanAgenda.setOpaque(false);
        DoSimpanAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoSimpanAgendaActionPerformed(evt);
            }
        });

        DoResetAgenda.setText("Reset");
        DoResetAgenda.setOpaque(false);
        DoResetAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoResetAgendaActionPerformed(evt);
            }
        });

        jLabel12.setText("Otoritas");

        agendaOtoritas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Penting", "Penting" }));

        panelDatePickerAgenda.setBackground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout panelDatePickerAgendaLayout = new javax.swing.GroupLayout(panelDatePickerAgenda);
        panelDatePickerAgenda.setLayout(panelDatePickerAgendaLayout);
        panelDatePickerAgendaLayout.setHorizontalGroup(
            panelDatePickerAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelDatePickerAgendaLayout.setVerticalGroup(
            panelDatePickerAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Sisa Saldo"));

        saldo3.setEditable(false);
        saldo3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(saldo3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(saldo3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(DoSimpanAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DoResetAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(agendaOtoritas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDatePickerAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(agendaNominal))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(panelDatePickerAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(agendaNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agendaOtoritas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DoSimpanAgenda)
                    .addComponent(DoResetAgenda))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DoSimpanAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoSimpanAgendaActionPerformed
        String datePattern = "dd MMMM yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, new Locale("en", "US"));
        String tanggal = dateFormatter.format(datePicker3.getModel().getValue());
        int nominal = Integer.parseInt(agendaNominal.getText().replace(",", ""));
        String keterangan = agendaKeterangan.getText();
        String otoritas = agendaOtoritas.getModel().getSelectedItem().toString();

        boolean authority = false;
        if (otoritas.equals("Penting")) {
            authority = true;
        }

        if (Validation.Validate(tanggal, nominal, keterangan, otoritas)) {

            Agenda baru = new Agenda(Cache.user, nominal, (Date) datePicker3.getModel().getValue(), keterangan, authority);
            baru.setIdagenda(this.id);
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(baru);
            transaction.commit();

            dtmAgenda.add(baru);

            Validation.infoDialouge(this, "Data pemasukan berhasil disimpan");
        }
        
    }//GEN-LAST:event_DoSimpanAgendaActionPerformed

    private void DoResetAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoResetAgendaActionPerformed
        datePicker3.getModel().setValue(null);
        agendaNominal.setText("");
        agendaKeterangan.setText("");
        agendaOtoritas.setSelectedIndex(0);
    }//GEN-LAST:event_DoResetAgendaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DoResetAgenda;
    private javax.swing.JButton DoSimpanAgenda;
    private javax.swing.JTextArea agendaKeterangan;
    private javax.swing.JTextField agendaNominal;
    private javax.swing.JComboBox agendaOtoritas;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel panelDatePickerAgenda;
    private javax.swing.JTextField saldo3;
    // End of variables declaration//GEN-END:variables
}
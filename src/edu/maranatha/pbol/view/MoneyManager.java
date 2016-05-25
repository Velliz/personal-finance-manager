/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.view;

import edu.maranatha.pbol.controller.AgendaTableController;
import edu.maranatha.pbol.controller.PemasukanTableController;
import edu.maranatha.pbol.controller.PengeluaranTableController;
import edu.maranatha.pbol.conventional.DBI;
import edu.maranatha.pbol.model.pojo.Agenda;
import edu.maranatha.pbol.model.pojo.Pemasukan;
import edu.maranatha.pbol.model.pojo.Pengeluaran;
import edu.maranatha.pbol.presistence.HibernateUtil;
import edu.maranatha.pbol.util.Cache;
import edu.maranatha.pbol.util.DateLabelFormatter;
import edu.maranatha.pbol.util.Validation;
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author didit
 */
public class MoneyManager extends javax.swing.JFrame {

    private final JDatePickerImpl datePicker, datePicker2, datePicker3;
    private final PengeluaranTableController dtmPengeluaran;
    private final PemasukanTableController dtmPemasukan;
    private final AgendaTableController dtmAgenda;

    private final NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    private final DBI dbuser = new DBI("user");
    private final DBI dbpemasukan = new DBI("pemasukan");
    private final DBI dbpengeluaran = new DBI("pengeluaran");
    private final DBI dbagenda = new DBI("agenda");

    private final JPopupMenu popupMenuPengeluaran = new JPopupMenu();
    private final JPopupMenu popupMenuPemasukan = new JPopupMenu();
    private final JPopupMenu popupMenuAgenda = new JPopupMenu();

    /**
     * Creates new form MoneyManager
     */
    public MoneyManager() {

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

        UtilDateModel model2 = new UtilDateModel();
        Properties p2 = new Properties();
        p2.put("text.today", "Today");
        p2.put("text.month", "Month");
        p2.put("text.year", "Year");
        JDatePanelImpl datePanel2 = new JDatePanelImpl(model2, p2);
        datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
        panelDatePickerPemasukan.setLayout(new CardLayout());
        panelDatePickerPemasukan.add(datePicker2);

        UtilDateModel model3 = new UtilDateModel();
        Properties p3 = new Properties();
        p3.put("text.today", "Today");
        p3.put("text.month", "Month");
        p3.put("text.year", "Year");
        JDatePanelImpl datePanel3 = new JDatePanelImpl(model3, p3);
        datePicker3 = new JDatePickerImpl(datePanel3, new DateLabelFormatter());
        panelDatePickerAgenda.setLayout(new CardLayout());
        panelDatePickerAgenda.add(datePicker3);

        dtmPengeluaran = new PengeluaranTableController();
        dtmPemasukan = new PemasukanTableController();
        dtmAgenda = new AgendaTableController();

        // if use hibernate connection schema
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Pengeluaran> dataKeluar = session.createQuery("from Pengeluaran WHERE iduser = " + Cache.user.getId()).list();
        for (Object peng : dataKeluar) {
            dtmPengeluaran.add(peng);
        }
        List<Pemasukan> dataMasuk = session.createQuery("from Pemasukan WHERE iduser = " + Cache.user.getId()).list();
        for (Pemasukan pem : dataMasuk) {
            dtmPemasukan.add(pem);
        }
        List<Agenda> dataAgenda = session.createQuery("from Agenda WHERE iduser = " + Cache.user.getId()).list();
        for (Agenda agen : dataAgenda) {
            dtmAgenda.add(agen);
        }
        // end if use hibernate connection schema

        // if use traditional connection schema
        //fetchPengeluaran();
        //fetchPemasukan();
        //fetchAgenda();
        // end if use traditional connection schema
        
        jTablePengeluaran.setModel(dtmPengeluaran);
        jTablePemasukan.setModel(dtmPemasukan);
        jTableAgenda.setModel(dtmAgenda);

        setSisaSaldo();
        
        //region menu tabel pengeluaran
        JMenuItem updateItemPengeluaran = new JMenuItem("Perbarui Data");
        JMenuItem deleteItemPengeluaran = new JMenuItem("Hapus");
        
        updateItemPengeluaran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Right-click performed on table and choose UPDATE");
                System.out.println(jTablePengeluaran.getValueAt(jTablePengeluaran.getSelectedRow(), 0));
            }
        });
        deleteItemPengeluaran.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Right-click performed on table and choose DELETE");
                System.out.println(jTablePengeluaran.getValueAt(jTablePengeluaran.getSelectedRow(), 0));
            }
        });
        
        popupMenuPengeluaran.add(updateItemPengeluaran);
        popupMenuPengeluaran.add(deleteItemPengeluaran);
        jTablePengeluaran.setComponentPopupMenu(popupMenuPengeluaran);
        
        popupMenuPengeluaran.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = jTablePengeluaran.rowAtPoint(SwingUtilities.convertPoint(popupMenuPengeluaran, new Point(0, 0), jTablePengeluaran));
                        if (rowAtPoint > -1) {
                            jTablePengeluaran.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                            
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}

        });
        //end region menu tabel pengeluaran
        
        //region menu tabel pemasukan
        JMenuItem updateItemPemasukan = new JMenuItem("Perbarui Data");
        JMenuItem deleteItemPemasukan = new JMenuItem("Hapus");
        
        updateItemPemasukan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Right-click performed on table and choose UPDATE");
                System.out.println(jTablePemasukan.getValueAt(jTablePemasukan.getSelectedRow(), 0));
            }
        });
        deleteItemPemasukan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Right-click performed on table and choose DELETE");
                System.out.println(jTablePemasukan.getValueAt(jTablePemasukan.getSelectedRow(), 0));
            }
        });
        
        popupMenuPemasukan.add(updateItemPemasukan);
        popupMenuPemasukan.add(deleteItemPemasukan);
        jTablePemasukan.setComponentPopupMenu(popupMenuPemasukan);
        
        popupMenuPemasukan.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = jTablePemasukan.rowAtPoint(SwingUtilities.convertPoint(popupMenuPemasukan, new Point(0, 0), jTablePemasukan));
                        if (rowAtPoint > -1) {
                            jTablePemasukan.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                            
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}

        });
        //end region menu tabel pemasukan
        
        //region menu tabel agenda
        JMenuItem updateItemAgenda = new JMenuItem("Perbarui Data");
        JMenuItem deleteItemAgenda = new JMenuItem("Hapus");
        
        updateItemAgenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Right-click performed on table and choose UPDATE");
                System.out.println(jTableAgenda.getValueAt(jTableAgenda.getSelectedRow(), 0));
            }
        });
        deleteItemAgenda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Right-click performed on table and choose DELETE");
                System.out.println(jTableAgenda.getValueAt(jTableAgenda.getSelectedRow(), 0));
            }
        });
        
        popupMenuAgenda.add(updateItemAgenda);
        popupMenuAgenda.add(deleteItemAgenda);
        jTableAgenda.setComponentPopupMenu(popupMenuAgenda);
        
        popupMenuAgenda.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        int rowAtPoint = jTableAgenda.rowAtPoint(SwingUtilities.convertPoint(popupMenuAgenda, new Point(0, 0), jTableAgenda));
                        if (rowAtPoint > -1) {
                            jTableAgenda.setRowSelectionInterval(rowAtPoint, rowAtPoint);
                            
                        }
                    }
                });
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}

        });
        //end region menu tabel agenda
        
    }

    public final void setSisaSaldo() {
        String sisaKas = String.valueOf(formatter.format(Cache.getKas()));
        saldo1.setText(sisaKas);
        saldo2.setText(sisaKas);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePengeluaran = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablePemasukan = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
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
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableAgenda = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Tema = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Manager Pengeluaran Personal v1.0 Beta");
        setResizable(false);

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

        jTablePengeluaran.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTablePengeluaran);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Pengeluaran", jPanel2);

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
                        .addGap(0, 0, Short.MAX_VALUE))
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

        jTablePemasukan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTablePemasukan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Pemasukan", jPanel3);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTableAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTableAgenda);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Agenda", jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenu1.setText("Berkas");

        Tema.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        Tema.setText("Tema");
        Tema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TemaActionPerformed(evt);
            }
        });
        jMenu1.add(Tema);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Logout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Keluar Aplikasi");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Laporan");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Cetak Laporan");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

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
                .addGap(0, 7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (Validation.infoDialouge(null, "Yakin ingin keluar?") == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void TemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TemaActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Settings().setVisible(true);
            }
        });
    }//GEN-LAST:event_TemaActionPerformed

    private void DoResetPengeluaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoResetPengeluaranActionPerformed
        datePicker.getModel().setValue(null);
        pengeluaranNominal.setText("");
        pengeluaranKeterangan.setText("");
        pengeluaranJenis.setSelectedIndex(0);
    }//GEN-LAST:event_DoResetPengeluaranActionPerformed

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

    private void DoResetPemasukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoResetPemasukanActionPerformed
        datePicker2.getModel().setValue(null);
        pemasukanNominal.setText("");
        pemasukanKeterangan.setText("");
    }//GEN-LAST:event_DoResetPemasukanActionPerformed

    private void DoSimpanPemasukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoSimpanPemasukanActionPerformed
        String datePattern = "dd MMMM yyyy";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, new Locale("en", "US"));
        String tanggal = dateFormatter.format(datePicker2.getModel().getValue());
        int nominal = Integer.parseInt(pemasukanNominal.getText().replace(",", ""));
        String keterangan = pemasukanKeterangan.getText();

        if (Validation.Validate(tanggal, nominal, keterangan)) {
            Pemasukan baru = new Pemasukan(Cache.user, nominal, (Date) datePicker2.getModel().getValue(), keterangan);

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(baru);
            transaction.commit();

            dtmPemasukan.add(baru);

            Validation.infoDialouge(this, "Data pemasukan berhasil disimpan");
        }
        setSisaSaldo();
    }//GEN-LAST:event_DoSimpanPemasukanActionPerformed

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

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(baru);
            transaction.commit();

            dtmAgenda.add(baru);

            Validation.infoDialouge(this, "Data pemasukan berhasil disimpan");
        }
        setSisaSaldo();
    }//GEN-LAST:event_DoSimpanAgendaActionPerformed

    private void DoResetAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoResetAgendaActionPerformed
        datePicker3.getModel().setValue(null);
        agendaNominal.setText("");
        agendaKeterangan.setText("");
        agendaOtoritas.setSelectedIndex(0);
    }//GEN-LAST:event_DoResetAgendaActionPerformed

    @Override
    protected void processWindowEvent(final WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (Validation.infoDialouge(null, "Yakin ingin keluar?") == 0) {
                System.exit(0);
            }
        }
    }

    private void fetchPengeluaran() {
        try {
            ResultSet kas = dbpengeluaran.select("select * from pengeluaran where iduser = " + Cache.user.getId());

            while (kas.next()) {
                Pengeluaran u = new Pengeluaran(
                        Cache.user,
                        kas.getInt("nominal"),
                        kas.getDate("tanggalpengeluaran"),
                        kas.getString("keterangan"),
                        kas.getString("status"));
                u.setIdpengeluaran(kas.getInt("idpengeluaran"));
                dtmPengeluaran.add(u);
            }
        } catch (SQLException ex) {
            Validation.infoDialouge(null, "Terjadi kesalahan saat mengeksekusi query database");
        }
    }

    private void fetchPemasukan() {
        try {
            ResultSet kas = dbpemasukan.select("select * from pemasukan where iduser = " + Cache.user.getId());

            while (kas.next()) {
                Pemasukan u = new Pemasukan(
                        Cache.user,
                        kas.getInt("nominal"),
                        kas.getDate("tanggalpemasukan"),
                        kas.getString("keterangan"));
                u.setIdpemasukan(kas.getInt("idpemasukan"));
                dtmPemasukan.add(u);
            }
        } catch (SQLException ex) {
            Validation.infoDialouge(null, "Terjadi kesalahan saat mengeksekusi query database");
        }
    }

    private void fetchAgenda() {
        try {
            ResultSet kas = dbagenda.select("select * from agenda where iduser = " + Cache.user.getId());

            while (kas.next()) {
                Agenda u = new Agenda(
                        Cache.user,
                        kas.getInt("nominalanggaran"),
                        kas.getDate("tanggal"),
                        kas.getString("keterangan"),
                        kas.getBoolean("otoritas"));
                u.setIdagenda(kas.getInt("idagenda"));
                dtmAgenda.add(u);
            }
        } catch (SQLException ex) {
            Validation.infoDialouge(null, "Terjadi kesalahan saat mengeksekusi query database");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DoResetAgenda;
    private javax.swing.JButton DoResetPemasukan;
    private javax.swing.JButton DoResetPengeluaran;
    private javax.swing.JButton DoSavePengeluaran;
    private javax.swing.JButton DoSimpanAgenda;
    private javax.swing.JButton DoSimpanPemasukan;
    private javax.swing.JMenuItem Tema;
    private javax.swing.JTextArea agendaKeterangan;
    private javax.swing.JTextField agendaNominal;
    private javax.swing.JComboBox agendaOtoritas;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAgenda;
    private javax.swing.JTable jTablePemasukan;
    private javax.swing.JTable jTablePengeluaran;
    private javax.swing.JPanel panelDatePickerAgenda;
    private javax.swing.JPanel panelDatePickerPemasukan;
    private javax.swing.JPanel panelDatePickerPengeluaran;
    private javax.swing.JTextArea pemasukanKeterangan;
    private javax.swing.JTextField pemasukanNominal;
    private javax.swing.JComboBox pengeluaranJenis;
    private javax.swing.JTextArea pengeluaranKeterangan;
    private javax.swing.JTextField pengeluaranNominal;
    private javax.swing.JTextField saldo1;
    private javax.swing.JTextField saldo2;
    private javax.swing.JTextField saldo3;
    // End of variables declaration//GEN-END:variables
}

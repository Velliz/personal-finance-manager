/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.controller;

import edu.maranatha.pbol.model.pojo.Pengeluaran;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author didit
 */
public class PengeluaranReportController extends AbstractTableModel {

    private String[] columnNames = {"idpengeluaran", "nominal", "tanggal", "keterangan", "status"};

    Object[][] data;

    public PengeluaranReportController(List<Pengeluaran> pengeluaran) {
        data = new Object[pengeluaran.size()][5];
        for (int i = 0; i < pengeluaran.size(); i++) {
            data[i][0] = pengeluaran.get(i).getIdpengeluaran();
            data[i][1] = pengeluaran.get(i).getNominal();
            data[i][2] = pengeluaran.get(i).getTanggalpengeluaran();
            data[i][3] = pengeluaran.get(i).getKeterangan();
            data[i][4] = pengeluaran.get(i).getStatus();
        }
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return this.columnNames[columnIndex];
    }

    @Override
    public int getRowCount() {
        return this.data.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }
}

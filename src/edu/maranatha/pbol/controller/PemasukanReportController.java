/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.maranatha.pbol.controller;

import edu.maranatha.pbol.model.pojo.Pemasukan;
import edu.maranatha.pbol.model.pojo.Pengeluaran;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author didit
 */
public class PemasukanReportController extends AbstractTableModel{
    
     private String[] columnNames = {"idpemasukan", "nominal", "tanggalpemasukan", "keterangan"};

    Object[][] data;
    public PemasukanReportController(List<Pemasukan> pemasukan) {
        data = new Object[pemasukan.size()][4];
        for (int i = 0; i < pemasukan.size(); i++) {
            data[i][0] = pemasukan.get(i).getIdpemasukan();
            data[i][1] = pemasukan.get(i).getNominal();
            data[i][2] = pemasukan.get(i).getTanggalpemasukan();
            data[i][3] = pemasukan.get(i).getKeterangan();
            
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.maranatha.pbol.controller;

import edu.maranatha.pbol.model.pojo.Agenda;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author didit
 */
public class AgendaReportController extends AbstractTableModel{
       private String[] columnNames = {"idagenda", "tanggal", "nominalanggaran", "keterangan"};

    Object[][] data;
    public AgendaReportController(List<Agenda> agenda) {
        data = new Object[agenda.size()][4];
        for (int i = 0; i < agenda.size(); i++) {
            data[i][0] = agenda.get(i).getIdagenda();
            data[i][1] = agenda.get(i).getNominalanggaran();
            data[i][2] = agenda.get(i).getTanggal();
            data[i][3] = agenda.get(i).getKeterangan();
            
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

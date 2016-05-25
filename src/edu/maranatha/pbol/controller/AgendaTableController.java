/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.controller;

import edu.maranatha.pbol.model.pojo.Agenda;
import edu.maranatha.pbol.util.Column;
import edu.maranatha.pbol.util.MoneyManagerTableModel;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author didit
 */
public class AgendaTableController extends MoneyManagerTableModel {

    private final List<Agenda> data = new ArrayList<>();
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        public final Column idColumn = new Column("ID", Integer.class) {

        @Override
        public Object get(int row) {
            return data.get(row).getIdagenda();
        }

    };
    public final Column nominalColumn = new Column("Nominal", Integer.class, true) {

        @Override
        public Object get(int row) {
            return formatter.format(data.get(row).getNominalanggaran());
        }

        @Override
        public void set(Object value, int row) {
            data.set(row, (Agenda) value);
            fireTableRowsUpdated(row, row);
        }
    };
    public final Column tanggalColumn = new Column("Tanggal", Date.class) {

        @Override
        public Object get(int row) {
            return data.get(row).getTanggal();
        }

    };
    public final Column keteranganColumn = new Column("Keterangan", String.class) {

        @Override
        public Object get(int row) {
            return data.get(row).getKeterangan();
        }

    };
    public final Column otoritasColumn = new Column("Otoritas", Boolean.class) {

        @Override
        public Object get(int row) {
            return data.get(row).isOtoritas();
        }

    };

    public AgendaTableController() {
        addColumns(idColumn, nominalColumn, tanggalColumn, keteranganColumn, otoritasColumn);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    public void add(Object element) {
        Objects.requireNonNull(element);
        data.add((Agenda)element);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}

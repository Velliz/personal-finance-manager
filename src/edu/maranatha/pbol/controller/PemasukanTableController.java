/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.controller;

import edu.maranatha.pbol.model.pojo.Pemasukan;
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
public class PemasukanTableController extends MoneyManagerTableModel {

    private final List<Pemasukan> data = new ArrayList<>();
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public final Column idColumn = new Column("ID", Integer.class) {

        @Override
        public Object get(int row) {
            return data.get(row).getIdpemasukan();
        }

    };
    public final Column nominalColumn = new Column("Nominal", Integer.class, true) {

        @Override
        public Object get(int row) {
            return formatter.format(data.get(row).getNominal());
        }

        @Override
        public void set(Object value, int row) {
            data.set(row, (Pemasukan) value);
            fireTableRowsUpdated(row, row);
        }
    };
    public final Column tanggalColumn = new Column("Tanggal", Date.class) {

        @Override
        public Object get(int row) {
            return data.get(row).getTanggalpemasukan();
        }

    };
    public final Column keteranganColumn = new Column("Keterangan", String.class) {

        @Override
        public Object get(int row) {
            return data.get(row).getKeterangan();
        }

    };

    public PemasukanTableController() {
        addColumns(idColumn, nominalColumn, tanggalColumn, keteranganColumn);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    public void add(Object element) {
        Objects.requireNonNull(element);
        data.add((Pemasukan) element);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}

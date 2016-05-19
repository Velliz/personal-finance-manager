/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.controller;

import edu.maranatha.pbol.util.Column;
import edu.maranatha.pbol.util.MoneyManagerTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author didit
 */
public class AgendaTableController extends MoneyManagerTableModel {

    private final List<String> data = new ArrayList<>();

    public final Column indexColumn = new Column("Index", Integer.class) {
        @Override
        public Object get(int row) {
            return row + 1;
        }
    };
    public final Column stringColumn = new Column("Value", String.class, true) {
        @Override
        public Object get(int row) {
            return data.get(row);
        }

        @Override
        public void set(Object value, int row) {
            data.set(row, (String) value);
            fireTableRowsUpdated(row, row);
        }
    };
    public final Column hashColumn = new Column("Hash", Integer.class) {
        @Override
        public Object get(int row) {
            return data.get(row).hashCode();
        }
    };

    public AgendaTableController() {
        addColumns(indexColumn, stringColumn, hashColumn);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    public void add(String element) {
        Objects.requireNonNull(element);
        data.add(element);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}

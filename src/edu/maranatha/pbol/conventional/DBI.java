/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.maranatha.pbol.conventional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dwiPa
 */
public class DBI {
    
    private DataModel mm;


    public DBI(String table) {
        mm = new DataModel(table);
    }

    public ArrayList<Object[]> Get(int colLength, String query) throws SQLException {
        ArrayList<Object[]> data = new ArrayList<>();
        ResultSet rs = mm.Get(query);
        while (rs.next()) {
            Object[] temp = new Object[colLength];
            for (int i = 0; i < colLength; i++) {
                temp[i] = rs.getString(i + 1);
            }
            data.add(temp);
        }
        return data;
    }

    public boolean Delete(String col, String val) {
        return mm.Delete(col, val);
    }
    
    public boolean Update(String kue){
        return mm.Update(kue);
    }
    
    public ResultSet select(String query){
        return mm.Get(query);
    }
}
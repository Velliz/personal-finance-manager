/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.conventional;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Deprecated
 * @author PRG2
 */
public class DataModel {

    private String table = null;

    public DataModel(String tablename) {
        table = tablename;
    }

    public ResultSet Get() {
        String kueri = "SELECT * FROM " + table;
        MysqliConnector.getInstance().ambil(kueri);

        if (MysqliConnector.LOG) {
            System.out.println("LOG QUERY GENERATOR : " + kueri);
        }

        return MysqliConnector.getInstance().getResult();
    }

    public ResultSet Get(String column, String key) {
        String kueri = "SELECT * FROM " + table + " WHERE " + column + " LIKE '%" + key + "%'";
        MysqliConnector.getInstance().ambil(kueri);

        if (MysqliConnector.LOG) {
            System.out.println("LOG QUERY GENERATOR : " + kueri);
        }

        return MysqliConnector.getInstance().getResult();
    }

    public boolean Save(ArrayList<String> col, HashMap<String, String> arrData) {
        String queryBuilder = "INSERT INTO " + table + " (";
        for (int i = 0; i < col.size(); i++) {
            if (i != (col.size() - 1)) {
                queryBuilder += "`" + col.get(i) + "`,";
            } else {
                queryBuilder += "`" + col.get(i) + "`)";
            }
        }
        queryBuilder += " VALUES (";
        for (int i = 0; i < col.size(); i++) {
            if (i != (col.size() - 1)) {
                queryBuilder += "'" + arrData.get(col.get(i)) + "',";
            } else {
                queryBuilder += "'" + arrData.get(col.get(i)) + "');";
            }
        }

        if (MysqliConnector.LOG) {
            System.out.println("LOG QUERY GENERATOR : " + queryBuilder);
        }

        return MysqliConnector.getInstance().kirim(queryBuilder);
    }

    public boolean Delete(String column, String key) {
        String kueri = "DELETE FROM " + table + " WHERE " + column + " = '" + key + "'";

        if (MysqliConnector.LOG) {
            System.out.println("LOG QUERY GENERATOR : " + kueri);
        }

        return MysqliConnector.getInstance().kirim(kueri);
    }

    public boolean Update(String kuer) {
        return MysqliConnector.getInstance().kirim(kuer);
    }

}

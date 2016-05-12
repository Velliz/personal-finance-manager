/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.model.conventional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author PRG2
 */
public class MysqliConnector implements DataConfig {

    private static MysqliConnector MysqliConn;

    private ResultSet result;
    private Connection koneksi;
    private Statement statement;
    private PreparedStatement ps;

    private MysqliConnector() {
        try {
            Class.forName(DRIVERNAME);
            koneksi = DriverManager.getConnection("jdbc:mysql://" + HOST + "/" + DATABASE, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private static MysqliConnector createKoneksi() {
        if (MysqliConn == null) {
            synchronized (MysqliConnector.class) {
                MysqliConn = new MysqliConnector();
            }
        }
        return MysqliConn;
    }

    public static MysqliConnector getInstance() {
        return createKoneksi();
    }

    public ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }

    public Connection getKoneksi() {
        return koneksi;
    }

    public void setKoneksi(Connection koneksi) {
        this.koneksi = koneksi;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public boolean kirim(String queri) {
        boolean result =  false;
        try {
            statement = koneksi.createStatement();
            result = statement.execute(queri);
            JOptionPane.showMessageDialog(null, "Operasi Berhasil", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }

    public ResultSet ambil(String queri) {
        try {
            statement = koneksi.createStatement();
            result = statement.executeQuery(queri);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public PreparedStatement getPrepStatement() {
        return ps;
    }

    public void setPrepStatement(PreparedStatement ps) {
        this.ps = ps;
    }
}

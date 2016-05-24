/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.util;

import edu.maranatha.pbol.conventional.DBI;
import edu.maranatha.pbol.model.pojo.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Didit Velliz
 */
public class Cache {

    public static User user;
    public static int themePos = 1;
    public static String username = null;
    public static String password = null;

    public static int getPengeluaran(String tanggal) {
        DBI dbagenda = new DBI("pengeluaran");
        int kasagenda = 0;
        try {
            ResultSet kas = dbagenda.select("select SUM(nominal) nominal from pengeluaran where iduser = " + user.getId()
                    + " AND DATE(tanggalpengeluaran) = STR_TO_DATE('" + tanggal + "','%d %M %Y')"
                    + " GROUP BY DATE(tanggalpengeluaran)");

            while (kas.next()) {
                kasagenda = kas.getInt("nominal");
            }

        } catch (SQLException ex) {
            Validation.infoDialouge(null, "Terjadi kesalahan saat mengambil data agenda");
        }
        System.err.println("PENGELUARAN : " + kasagenda);
        return kasagenda;
    }

    public static int getAgenda(String tanggal) {
        DBI dbagenda = new DBI("agenda");
        int kasagenda = 0;
        try {
            ResultSet kas = dbagenda.select("select * from agenda where iduser = " + user.getId()
                    + " AND DATE(tanggal) = STR_TO_DATE('" + tanggal + "','%d %M %Y') "
                    + " group by tanggal asc");

            while (kas.next()) {
                kasagenda = kas.getInt("nominalanggaran");
            }

        } catch (SQLException ex) {
            Validation.infoDialouge(null, "Terjadi kesalahan saat mengambil data agenda");
        }
        System.err.println("AGENDA : " + kasagenda);
        return kasagenda;
    }

    public static int getKas() {
        DBI dbpemasukan = new DBI("pemasukan");
        int kasresult = 0;
        try {
            ResultSet kas = dbpemasukan.select("SELECT (ifnull(pemasukan,0) - (ifnull(pengeluaran,0) + ifnull(agenda,0))) kas from "
                    + "(SELECT SUM(nominal) pemasukan, iduser from pemasukan where iduser = " + user.getId()
                    + " AND MONTH(tanggalpemasukan) = MONTH(NOW()) "
                    + " AND YEAR(tanggalpemasukan) = YEAR(NOW()) "
                    + " group by MONTH(tanggalpemasukan), YEAR(tanggalpemasukan) asc)"
                    + " pemasukan left join "
                    + "(SELECT SUM(nominal) pengeluaran, iduser from pengeluaran where iduser = " + user.getId()
                    + " AND MONTH(tanggalpengeluaran) = MONTH(NOW()) "
                    + " AND YEAR(tanggalpengeluaran) = YEAR(NOW()) "
                    + " group by MONTH(tanggalpengeluaran), YEAR(tanggalpengeluaran) asc) "
                    + " pengeluaran using(iduser) left join "
                    + "(select IFNULL(nominalanggaran, 0) agenda, iduser from agenda where iduser = " + user.getId()
                    + " AND MONTH(tanggal) = MONTH(NOW()) "
                    + " AND YEAR(tanggal) = YEAR(NOW()) "
                    + " group by MONTH(tanggal), YEAR(tanggal) asc) "
                    + " agenda using(iduser)");

            while (kas.next()) {
                kasresult = kas.getInt("kas");
            }

        } catch (SQLException ex) {
            Validation.infoDialouge(null, "Terjadi kesalahan saat mengambil data kas");
        }

        System.err.println("KAS : " + kasresult);
        return kasresult;
    }
}

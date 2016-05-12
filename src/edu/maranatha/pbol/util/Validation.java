/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.maranatha.pbol.util;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author didit
 */
public class Validation {

    private static String reason;

    public Validation() {
        Validation.reason = "";
    }

    public static String getReason() {
        return Validation.reason;
    }

    public static boolean Validate(Object... values) {

        boolean result = true;
        Validation.reason = "";

        for (Object value : values) {
            if (value == null) {
                Validation.reason += "Variable " + value.getClass().getSimpleName() + " not defined.\n";
                result = false;
            } else if (value instanceof String) {
                if (value.equals("")) {
                    Validation.reason += "Variable " + value.getClass().getSimpleName() + " is empty.\n";
                    result = false;
                }
            } else if (value instanceof Integer) {
                if ((int) value < 0) {
                    Validation.reason += "Variable " + value.getClass().getSimpleName() + " must equal to zero or positive.\n";
                    result = false;
                }
            } else if (value instanceof Double) {
                if ((double) value < 0) {
                    Validation.reason += "Variable " + value.getClass().getSimpleName() + " must equal to zero or positive.\n";
                    result = false;
                }
            }
        }

        if (!result) {
            errorDialouge(null);
        }
        return result;
    }

    private static void errorDialouge(Component parrent) {
        JOptionPane.showMessageDialog(parrent, Validation.reason,
                "Validation Error", JOptionPane.ERROR_MESSAGE);
    }

    public static int infoDialouge(Component parrent, String message) {
        return JOptionPane.showConfirmDialog(parrent, message,
                "Konfirmasi", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
    }

}

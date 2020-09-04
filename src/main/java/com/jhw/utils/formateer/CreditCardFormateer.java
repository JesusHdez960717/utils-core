/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.formateer;

import java.text.ParseException;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class CreditCardFormateer extends AbstractFormatter {

    private final String middle = "-";

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text == null) {
            return "";
        }
        return text.replace(middle, "");
    }

    @Override
    public String valueToString(Object text) throws ParseException {
        if (text == null) {
            return "";
        }
        return text.toString().replace(middle, "").replaceAll("....", "$0" + middle).trim();
    }

}

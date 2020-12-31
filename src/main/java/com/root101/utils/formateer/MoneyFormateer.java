/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.utils.formateer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.StringTokenizer;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class MoneyFormateer extends AbstractFormatter {

    public static MoneyFormateer from() {
        return new MoneyFormateer();
    }
    
    public static final String MIDDLE = " ";

    @Override
    public Object stringToValue(String text) throws ParseException {
        if (text == null) {
            return "";
        }
        return text.replace(MIDDLE, "").replace(",", ".");
    }

    @Override
    public String valueToString(Object object) throws ParseException {
        if (object == null) {
            return "";
        }
        String strInit = object.toString();
        if (strInit.trim().isEmpty()) {
            return "";
        }
        String initialString = strInit.replace(",", ".").replace(MIDDLE, "");

        StringTokenizer str = new StringTokenizer(initialString, ".");
        String integerPart = str.nextToken();

        String reverse = new StringBuilder(integerPart).reverse().toString();
        String split = reverse.replaceAll("...", "$0" + MIDDLE).trim();
        String integerFix = new StringBuilder(split).reverse().toString();

        String answ = integerFix;
        if (str.hasMoreTokens()) {
            answ += "." + str.nextToken().replaceAll("...", "$0" + MIDDLE).trim();;
        } else if (initialString.contains(".")) {
            answ += ".";
        }
        return answ;
    }

}

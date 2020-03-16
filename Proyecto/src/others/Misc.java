/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.util.Formatter;

/**
 *
 * @author Yo
 */
public class Misc {

    public static float round2f(double numero) {
        return Float.parseFloat(new Formatter().format("%.2f", numero).toString().replace(',', '.'));
    }

    public static float evaluate(float a, String op, float b) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return b == 0 ? 0 : a / b;
        }
        return 0;
    }
}

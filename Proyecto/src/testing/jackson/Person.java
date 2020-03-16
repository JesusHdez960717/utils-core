/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing.jackson;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Yo
 */
public class Person implements Serializable {

    String nombe = "Pepe";
    String CI = "96071710064";
    Color c = Color.BLUE;

    public Person() {
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    public String getNombe() {
        return nombe;
    }

    public void setNombe(String nombe) {
        this.nombe = nombe;
    }

    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    @Override
    public String toString() {
        return "NOMBRE = " + nombe + ", CI = " + CI + ", color = " + c;
    }

}

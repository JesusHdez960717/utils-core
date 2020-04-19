/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package others;

import java.io.Serializable;

/**
 *
 * @author Yo
 * @param <T>
 */
public class Pair<T> implements Serializable {

    private T A;
    private T B;

    public Pair() {
    }

    public Pair(T A, T B) {
        this.A = A;
        this.B = B;
    }

    public T getA() {
        return A;
    }

    public void setA(T A) {
        this.A = A;
    }

    public T getB() {
        return B;
    }

    public void setB(T B) {
        this.B = B;
    }

}

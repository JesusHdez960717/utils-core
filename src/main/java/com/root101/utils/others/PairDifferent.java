package com.root101.utils.others;

import java.io.Serializable;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 * @param <T>
 * @param <K>
 */
public class PairDifferent<T, K> implements Serializable {

    private T A;
    private K B;

    public PairDifferent() {
    }

    public PairDifferent(T A, K B) {
        this.A = A;
        this.B = B;
    }

    public T getA() {
        return A;
    }

    public void setA(T A) {
        this.A = A;
    }

    public K getB() {
        return B;
    }

    public void setB(K B) {
        this.B = B;
    }
}

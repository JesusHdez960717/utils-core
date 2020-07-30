package com.jhw.utils.others;

import java.io.Serializable;
//import lombok.Data;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 * @param <T>
 * @param <K>
 */
//@Data
public class Trio<T, K, L> implements Serializable {

    private T A;
    private K B;
    private L C;

    public Trio() {
    }

    public Trio(T A, K B, L C) {
        this.A = A;
        this.B = B;
        this.C = C;
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

    public L getC() {
        return C;
    }

    public void setC(L C) {
        this.C = C;
    }
}

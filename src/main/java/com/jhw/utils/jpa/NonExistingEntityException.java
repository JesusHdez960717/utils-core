/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.jpa;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class NonExistingEntityException extends Exception {

    public NonExistingEntityException() {
    }

    public NonExistingEntityException(String string) {
        super(string);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.clean;

import com.clean.core.utils.validation.ValidationResult;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class ValidationUtils {

    public static ValidationResult validateByAnnotations(Object obj) {
        ValidationResult val = new ValidationResult();
        val.checkFromAnnotations(obj);
        return val.throwException();
    }
}

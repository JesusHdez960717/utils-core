/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.clean;

import com.clean.core.domain.EntityDomainObject;
import com.clean.core.exceptions.ValidationException;
import com.clean.core.utils.validation.ValidationResult;
import com.jhw.utils.interfaces.Filtrable;
import com.jhw.utils.others.FiltrableRefraction;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class EntityDomainObjectValidated extends EntityDomainObject implements Filtrable {

    @Override
    public ValidationResult validate() throws ValidationException {
        return ValidationUtils.validateByAnnotations(this);
    }

    @Override
    public boolean test(String string) {
        return FiltrableRefraction.test(this, string);
    }

}

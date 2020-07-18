/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.utils.clean;

import com.clean.core.domain.EntityDomainObject;
import com.clean.core.exceptions.ValidationException;
import com.clean.core.utils.validation.ValidationResult;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class EntityDomainObjectValidated extends EntityDomainObject {

    @Override
    public ValidationResult validate() throws ValidationException {
        return ValidationUtils.validateByAnnotations(this);
    }

}

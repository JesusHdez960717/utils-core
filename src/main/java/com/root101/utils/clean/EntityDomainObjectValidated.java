/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.root101.utils.clean;

import com.root101.clean.core.domain.EntityDomainObject;
import com.root101.clean.core.exceptions.ValidationException;
import com.root101.clean.core.utils.validation.ValidationResult;
import com.root101.utils.interfaces.Filtrable;
import com.root101.utils.refraction.FiltrableRefraction;

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

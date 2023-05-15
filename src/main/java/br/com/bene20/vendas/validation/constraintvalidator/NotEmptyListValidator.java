package br.com.bene20.vendas.validation.constraintvalidator;

import br.com.bene20.vendas.validation.NotEmptyList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{

    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext cvc) {
        return (list != null) && (!list.isEmpty());
    }
    
}

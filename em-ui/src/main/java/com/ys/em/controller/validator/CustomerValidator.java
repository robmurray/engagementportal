package com.ys.em.controller.validator;

import com.ys.em.model.Customer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by rob on 4/20/15.
 */
public class CustomerValidator implements Validator {

   public boolean supports(Class clazz) {
        return Customer.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        Customer p = (Customer) obj;
        if (StringUtils.isEmpty(p.getName())) {
            e.rejectValue("name", "please enter a value");
        }
    }
}
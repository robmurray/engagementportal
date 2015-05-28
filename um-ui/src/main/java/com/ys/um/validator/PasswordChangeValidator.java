package com.ys.um.validator;

import com.ys.um.model.PasswordChange;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by rob on 4/20/15.
 */
@Component
public class PasswordChangeValidator implements Validator {

   public boolean supports(Class clazz) {
        return PasswordChange.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "newPassword", "newPassword.empty","Please enter password");
        ValidationUtils.rejectIfEmpty(e, "newPasswordConfirm", "newPasswordConfirm.empty","please re-entered the password");
        PasswordChange pc = (PasswordChange) obj;
        if (pc.getNewPassword()!=null && pc.getNewPasswordConfirm()!=null && !pc.getNewPassword().equals(pc.getNewPasswordConfirm())) {
            e.rejectValue("newPasswordConfirm", "newPassword.mismatch","passwords do not match");
        }
    }
}
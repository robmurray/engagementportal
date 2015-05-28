package com.ys.um.model;

import com.ys.ui.model.AbstractModelBase;

/**
 * Created by rob on 4/25/15.
 */
public class PasswordChange extends AbstractModelBase {

    private long userId;
    private String newPassword;
    private String newPasswordConfirm;

    public PasswordChange() {
    }

    public PasswordChange(long userId) {
        this.userId = userId;
    }

    public PasswordChange(long userId, String newPassword, String newPasswordConfirm) {
        this.userId = userId;
        this.newPassword = newPassword;
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}

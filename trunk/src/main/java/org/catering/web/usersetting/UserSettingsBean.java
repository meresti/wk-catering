/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catering.web.usersetting;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.catering.ejb.UserBean;
import org.catering.model.User;
import org.catering.qualifier.LoggedIn;
import org.catering.web.login.PasswordBean;
import org.catering.web.util.JsfUtil;

@Named("userSettings")
@RequestScoped
public class UserSettingsBean {

    private String oldPassword;

    private String newPassword;

    @EJB
    private UserBean userBean;

    @Inject
    private PasswordBean passwordBean;

    @Inject
    @LoggedIn
    private User authenticatedUser;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String changePassword() {
        final String result;

        if (passwordBean.checkPassword(oldPassword, authenticatedUser.getPassword())) {
            final String encrypted = passwordBean.encryptPassword(newPassword);
            this.oldPassword = null;
            this.newPassword = null;
            authenticatedUser.setPassword(encrypted);
            userBean.edit(authenticatedUser);
            JsfUtil.addSuccessMessage("auth.password.changed");
            result = "success";
        } else {
            JsfUtil.addErrorMessage("auth.password.change.failed");
            result = "error";
        }

        return result;
    }
}

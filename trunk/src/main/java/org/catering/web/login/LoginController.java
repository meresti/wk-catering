/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catering.web.login;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.catering.ejb.UserBean;
import org.catering.model.Group;
import org.catering.model.User;
import org.catering.qualifier.LoggedIn;
import org.catering.web.util.JsfUtil;

/**
 * LoginController is an authorization controller responsible for user
 * login/logout actions
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private User user;

    private String username;

    private String password;

    @EJB
    private UserBean userBean;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public @Produces
    @LoggedIn
    User getAuthenticatedUser() {
        return user;
    }

    public boolean isLogged() {
        return user != null;
    }

    public boolean isAdmin(User user) {
        for (Group g : user.getGroups()) {
            if (g.getName().equals("ADMINS")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Login method based on <code>HttpServletRequest</code> and security realm
     */
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String result;

        try {
            request.login(this.getUsername(), this.getPassword());

            this.password = null;
            this.user = userBean.getUserByName(getUsername());
            this.getAuthenticatedUser();

            result = "index";
            JsfUtil.addSuccessMessage("auth.login.succeded");
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage("auth.login.failed");
            result = "login";
        }

        return result;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.user = null;

            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
            request.logout();
            JsfUtil.addSuccessMessage("auth.logout.succeded");

        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JsfUtil.addErrorMessage("auth.logout.failed");
        }

        return "/index.xhtml?faces-redirect=true";
    }
}

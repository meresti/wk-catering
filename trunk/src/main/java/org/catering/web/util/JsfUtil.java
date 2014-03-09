/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catering.web.util;

import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {

    private static final String BUNDLE = "messages.Messages";

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static void addErrorMessage(Exception ex, String messageTag) {
        final String message = getTranslatedMessage(messageTag);
        addMessage(message, ex.getLocalizedMessage(), FacesMessage.SEVERITY_ERROR, null);
    }

    public static void addErrorMessage(String messageTag) {
        final String message = getTranslatedMessage(messageTag);
        addMessage(message, message, FacesMessage.SEVERITY_ERROR, null);
    }

    public static void addSuccessMessage(String messageTag) {
        final String message = getTranslatedMessage(messageTag);
        addMessage(message, message, FacesMessage.SEVERITY_INFO, "successInfo");
    }

    private static void addMessage(final String summary, final String details, final Severity severity, final String clientId) {
        FacesMessage facesMsg = new FacesMessage(severity, summary, details);
        FacesContext.getCurrentInstance().addMessage(clientId, facesMsg);
    }

    private static String getTranslatedMessage(String messageTag) {
        return ResourceBundle.getBundle(BUNDLE).getString(messageTag);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }
}

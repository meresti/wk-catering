package org.catering.web.controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import org.catering.ejb.AbstractFacade;
import org.catering.web.controller.util.JsfUtil;
import org.primefaces.model.LazyDataModel;

public abstract class AbstractController<T> implements Serializable {

    private LazyDataModel<T> lazyModel;

    private T current;

    private Boolean currentIsNew;

    protected void init() {
        prepareCreate();
    }

    public LazyDataModel<T> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<T> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
        currentIsNew = false;
    }

    public Boolean getCurrentIsNew() {
        return currentIsNew;
    }

    public void setCurrentIsNew(Boolean currentIsNew) {
        this.currentIsNew = currentIsNew;
    }

    protected abstract AbstractFacade<T> getFacade();

    protected abstract T createNew();

    protected abstract T createCopy();

    protected abstract String getSucessfullyDeletedMessageTag();

    protected abstract String getSucessfullyUpdatedMessageTag();

    protected abstract String getSucessfullyCreatedMessageTag();

    public void prepareCreate() {
        setCurrent(createNew());
        setCurrentIsNew(true);
    }

    public void prepareCopy() {
        setCurrent(createCopy());
        setCurrentIsNew(true);
    }

    public void save() {
        if (getCurrentIsNew()) {
            create();
        } else {
            update();
        }
    }

    protected void create() {
        try {
            getFacade().create(getCurrent());
            setCurrentIsNew(false);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString(getSucessfullyCreatedMessageTag()));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("Bundle").getString("PersistenceErrorOccured"));
        }
    }

    protected void update() {
        try {
            getFacade().edit(getCurrent());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString(getSucessfullyUpdatedMessageTag()));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("Bundle").getString("PersistenceErrorOccured"));
        }
    }

    public void deleteSelected() {
        try {
            getFacade().remove(getCurrent());
            prepareCreate();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("Bundle").getString(getSucessfullyDeletedMessageTag()));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("Bundle").getString("PersistenceErrorOccured"));
        }
    }
}

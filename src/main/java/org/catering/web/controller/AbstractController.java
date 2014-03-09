package org.catering.web.controller;

import java.io.Serializable;
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

    protected abstract String getDeleteSuccededMessageTag();

    protected abstract String getDeleteFailedMessageTag();

    protected abstract String getUpdateSuccededMessageTag();

    protected abstract String getUpdateFailedMessageTag();

    protected abstract String getCreateSuccededMessageTag();

    protected abstract String getCreateFailedMessageTag();

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
            JsfUtil.addSuccessMessage(getCreateSuccededMessageTag());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, getCreateFailedMessageTag());
        }
    }

    protected void update() {
        try {
            getFacade().edit(getCurrent());
            JsfUtil.addSuccessMessage(getUpdateSuccededMessageTag());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, getUpdateFailedMessageTag());
        }
    }

    public void deleteSelected() {
        try {
            getFacade().remove(getCurrent());
            prepareCreate();
            JsfUtil.addSuccessMessage(getDeleteSuccededMessageTag());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, getDeleteFailedMessageTag());
        }
    }
}

package org.catering.web.controller;


import org.catering.ejb.AbstractFacade;
import org.catering.ejb.MoneyBean;
import org.catering.model.Food;
import org.catering.model.Money;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

public class MoneyController extends AbstractController<Money>
{
    @EJB
    private MoneyBean ejbFacade;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        setLazyModel(new MoneyLazyDataModel(getFacade()));
    }

    @Override
    protected AbstractFacade<Money> getFacade() {
        return ejbFacade;
    }

    @Override
    protected Money createNew() {
        return new Money();
    }


    @Override
    public Money getCurrent() {
        return super.getCurrent();
    }

    @Override
    public void setCurrent(Money current) {
        super.setCurrent(current);
    }

    @Override
    protected Money createCopy() {
        final Money current = getCurrent();
        return new Money(current.getTotal(), current.getUsed(), current.getMissing());
    }

    @Override
    protected String getDeleteSuccededMessageTag() {
        return null;
    }

    @Override
    protected String getDeleteFailedMessageTag() {
        return null;
    }

    @Override
    protected String getUpdateSuccededMessageTag() {
        return null;
    }

    @Override
    protected String getUpdateFailedMessageTag() {
        return null;
    }

    @Override
    protected String getCreateSuccededMessageTag() {
        return null;
    }

    @Override
    protected String getCreateFailedMessageTag() {
        return null;
    }
}

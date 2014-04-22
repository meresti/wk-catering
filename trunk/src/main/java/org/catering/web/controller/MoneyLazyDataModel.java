package org.catering.web.controller;

import org.catering.ejb.AbstractFacade;
import org.catering.model.Money;


public class MoneyLazyDataModel extends AbstractLazyDataModel<Money>
{
    MoneyLazyDataModel(AbstractFacade<Money> facade) {
        super(facade);
    }

    @Override
    public Object getRowKey(Money object) {
        return object.getId();
    }
}

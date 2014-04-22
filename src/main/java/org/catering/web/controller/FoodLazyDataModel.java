package org.catering.web.controller;

import org.catering.ejb.FoodBean;
import org.catering.model.Food;

public class FoodLazyDataModel extends AbstractLazyDataModel<Food>
{

    FoodLazyDataModel(FoodBean facade) {
        super(facade);
    }

    @Override
    public Object getRowKey(Food object) {
        return object.getId();
    }
}

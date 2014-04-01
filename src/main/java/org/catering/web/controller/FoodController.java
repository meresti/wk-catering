package org.catering.web.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.catering.ejb.FoodBean;
import org.catering.model.Food;

@Named("foodController")
@ViewScoped
@Stateful
public class FoodController extends AbstractController<Food> {

    @EJB
    private FoodBean ejbFacade;

    @PostConstruct
    @Override
    public void init() {
        super.init();
        setLazyModel(new FoodLazyDataModel(getFacade()));
    }

    @Override
    protected FoodBean getFacade() {
        return ejbFacade;
    }

    @Override
    public Food getCurrent() {
        return super.getCurrent();
    }

    @Override
    public void setCurrent(Food current) {
        super.setCurrent(current);
    }

    public String getDateFormat() {
        return "yyyy-MM-dd";
    }

    @Override
    protected Food createNew() {
        return new Food();
    }

    @Override
    protected Food createCopy() {
        final Food current = getCurrent();
        return new Food(current.getName(), current.getPrice(), current.getDeliveryDate());
    }

    @Override
    protected String getDeleteSuccededMessageTag() {
        return "food.deleted";
    }

    @Override
    protected String getDeleteFailedMessageTag() {
        return "food.delete.failed";
    }

    @Override
    protected String getUpdateSuccededMessageTag() {
        return "food.updated";
    }

    @Override
    protected String getUpdateFailedMessageTag() {
        return "food.update.failed";
    }

    @Override
    protected String getCreateSuccededMessageTag() {
        return "food.created";
    }

    @Override
    protected String getCreateFailedMessageTag() {
        return "food.create.failed";
    }
}

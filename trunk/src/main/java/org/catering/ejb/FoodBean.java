/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catering.ejb;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.catering.model.Food;

@Stateless
@DeclareRoles({"ADMIN", "USER", "GUEST"})
@RolesAllowed({"ADMIN", "USER", "GUEST"})
public class FoodBean extends AbstractFacade<Food> {

    @PersistenceContext(unitName = "catering-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FoodBean() {
        super(Food.class);
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void remove(Food entity) {
        super.remove(entity);
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void edit(Food entity) {
        super.edit(entity);
    }

    @Override
    @RolesAllowed({"ADMIN"})
    public void create(Food entity) {
        super.create(entity);
    }
}

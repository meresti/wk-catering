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
import org.catering.ejb.role.Roles;
import org.catering.model.Food;

@Stateless
@DeclareRoles({Roles.ADMIN, Roles.USER, Roles.GUEST})
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
    @RolesAllowed(Roles.ADMIN)
    public void remove(Food entity) {
        super.remove(entity);
    }

    @Override
    @RolesAllowed(Roles.ADMIN)
    public void edit(Food entity) {
        super.edit(entity);
    }

    @Override
    @RolesAllowed(Roles.ADMIN)
    public void create(Food entity) {
        super.create(entity);
    }
}

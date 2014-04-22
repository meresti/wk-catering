package org.catering.ejb;


import org.catering.ejb.role.Roles;
import org.catering.model.Money;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@DeclareRoles({Roles.ADMIN, Roles.USER, Roles.GUEST})
public class MoneyBean extends AbstractFacade<Money> {

    @PersistenceContext(unitName = "catering-ejbPU")
    private EntityManager em;

    public MoneyBean() {
        super(Money.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @RolesAllowed(Roles.ADMIN)
    public void remove(Money entity) {
        super.remove(entity);
    }

    @Override
    @RolesAllowed(Roles.ADMIN)
    public void edit(Money entity) {
        super.edit(entity);
    }

    @Override
    @RolesAllowed(Roles.ADMIN)
    public void create(Money entity) {
        super.create(entity);
    }

}

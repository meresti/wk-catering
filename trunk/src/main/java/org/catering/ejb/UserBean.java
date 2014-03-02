/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.catering.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.catering.model.User;

@Stateless
public class UserBean extends AbstractFacade<User> {

    @PersistenceContext(unitName = "catering-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserBean() {
        super(User.class);
    }

    public User getUserByName(String name) {
        Query createNamedQuery = getEntityManager().createNamedQuery("User.findByName");

        createNamedQuery.setParameter("name", name);

        return (User) createNamedQuery.getSingleResult();
    }
}

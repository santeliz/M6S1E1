/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JAVA
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "M6S1E1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario buscarUsuario(String txtUsuario, String txtPassword) {
        Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :txtUsuario AND u.clave = :txtPassword");
        q.setParameter("txtUsuario", txtUsuario);
        q.setParameter("txtPassword", txtPassword);
        if (!q.getResultList().isEmpty()) {
            return (Usuario) q.getSingleResult();
        } else {
            return null;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
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
    
    public Long contar(String filtro){
        Long resultado = (long) 0;
        if (filtro == null) filtro = "";
        
        Query q = em.createQuery("SELECT COUNT(U) FROM Usuario U WHERE U.usuario LIKE :filtro");
        q.setParameter("filtro", "%" +  filtro + "%");
        if (!q.getResultList().isEmpty()) {
            return (Long) q.getSingleResult();
        }
        return resultado;
    }
    
    public List<Usuario> getFiltro(int first, int pageSize, String filtro) {
        List<Usuario> resultado = new ArrayList<>();
        if (filtro == null) filtro = "";
        Query q = em.createQuery("SELECT U FROM Usuario U WHERE U.usuario LIKE :filtro")
                .setFirstResult(first).setMaxResults(pageSize);
        q.setParameter("filtro", "%" + filtro + "%");
        if (!q.getResultList().isEmpty()) return q.getResultList();
        return resultado;
    }
    
}

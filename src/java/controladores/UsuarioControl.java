
package controladores;

import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelos.UsuarioFacade;


@ManagedBean
@SessionScoped
public class UsuarioControl implements Serializable {

    @EJB
    UsuarioFacade usuarioFacade;
    List<Usuario> lstUsuarios;
    
    @PostConstruct
    public void init() {
        listarUsuarios();
    }
    
    public List<Usuario> listarUsuarios() {
        lstUsuarios = usuarioFacade.findAll();
        System.out.println("La lista de usuarios devuelta es: " + lstUsuarios);
        return lstUsuarios;
    }

    public List<Usuario> getLstUsuarios() {
        return lstUsuarios;
    }
    
    
}

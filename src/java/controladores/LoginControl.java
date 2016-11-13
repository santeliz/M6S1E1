/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Usuario;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelos.UsuarioFacade;


@ManagedBean
@SessionScoped
public class LoginControl implements Serializable {

    private String txtUsuario;
    private String txtPassword;
    private Usuario usuarioValidado;

    @EJB
    UsuarioFacade usuarioFacade;

    @PostConstruct
    public void init() {

    }

    public String getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(String txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public String validarIngreso() {
        usuarioValidado = usuarioFacade.buscarUsuario(txtUsuario, txtPassword);
        if (usuarioValidado != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioValidado);
            //return "/catalogos/usuarios/usuariosList.xhtml?faces-redirect=true";
            return "/catalogos/usuarios/usuariosLazy.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Información", 
                    "Usuario o Contraseña incorrecta"));
            return null;
        }
    }

    public String cerrarSesion() {
        usuarioValidado = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/index.xhtml?faces-redirect=true";
    }
    
    public Usuario getUsuarioValidado() {
        return usuarioValidado; 
    }

    public void setUsuarioValidado(Usuario usuarioValidado) {
        this.usuarioValidado = usuarioValidado;
    }
    
    
}

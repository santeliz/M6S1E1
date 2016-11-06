/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidades.Empleado;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelos.EmpleadoFacade;

@ManagedBean
@RequestScoped
public class empleadosControl implements Serializable {

    @EJB
    EmpleadoFacade empleadoFacade;
    List<Empleado> lstEmpleado;
    
    @PostConstruct
    public void init() {
        lstEmpleado = listarEmpleados();
    }
    
    public List<Empleado> listarEmpleados() {
        return empleadoFacade.findAll();
    }

    public List<Empleado> getLstEmpleado() {
        return lstEmpleado;
    }
    
    
}

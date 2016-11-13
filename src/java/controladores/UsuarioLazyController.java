
package controladores;

import entidades.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelos.UsuarioFacade;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean
@SessionScoped
public class UsuarioLazyController implements Serializable {
    
    @EJB
    UsuarioFacade usuarioFacade;
    
    private LazyDataModel<Usuario> lazyUsuarios;
    private List<Usuario> lstUsuariosFiltrada;
    
    @PostConstruct
    public void init() {
        cargarUsuarios();
    }
    
    public void cargarUsuarios(){
        
        lazyUsuarios = new LazyDataModel<Usuario>() {
            @Override
            public List<Usuario> load(int first, int pageSize, String sortField, 
                    SortOrder sortorder, Map<String, Object> filters ) {
                
                String filtro ="";
                if (!filters.isEmpty()) {
                    filtro = (String) filters.get("globalFilter");
                }
                lazyUsuarios.setRowCount((usuarioFacade.contar(filtro).intValue()));
                return usuarioFacade.getFiltro(first, pageSize, filtro);
            }
        };
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public List<Usuario> getLstUsuariosFiltrada() {
        return lstUsuariosFiltrada;
    }

    public void setLstUsuariosFiltrada(List<Usuario> lstUsuariosFiltrada) {
        this.lstUsuariosFiltrada = lstUsuariosFiltrada;
    }
    

    
}

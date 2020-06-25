/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.Estado;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author carol
 */
@Named
@SessionScoped
public class EstadoControle implements Serializable {

    @Inject
    private EstadoFacade estadoFacade;
    private Estado estado;

    public void novo(){
        estado = new Estado();
    }
    
    public void excluir(Estado e){
        estadoFacade.excluir(e);
    }
    
    public void editar(Estado e){
        this.estado = e;
    }
    
    public void salvar() {
        estadoFacade.salvar(estado);
    }

    public List<Estado> listaTodos() {
        return estadoFacade.listaTodos();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}

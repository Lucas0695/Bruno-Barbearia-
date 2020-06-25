/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.GrupoServico;
import facade.GrupoServicoFacade;
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
public class GrupoServicoControle implements Serializable {

    @Inject
    private GrupoServicoFacade grupoServicoFacade;
    private GrupoServico grupoServico;

    public void novo(){
        grupoServico = new GrupoServico();
    }
    
    public void excluir(GrupoServico e){
        grupoServicoFacade.excluir(e);
    }
    
    public void editar(GrupoServico e){
        this.grupoServico = e;
    }
    
    public void salvar() {
        grupoServicoFacade.salvar(grupoServico);
    }

    public List<GrupoServico> listaTodos() {
        return grupoServicoFacade.listaTodos();
    }

    public GrupoServico getGrupoServico() {
        return grupoServico;
    }

    public void setGrupoServico(GrupoServico grupoServico) {
        this.grupoServico = grupoServico;
    }

}

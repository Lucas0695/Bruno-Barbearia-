/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.GrupoProduto;
import facade.GrupoProdutoFacade;
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
public class GrupoProdutoControle implements Serializable {

    @Inject
    private GrupoProdutoFacade grupoProdutoFacade;
    private GrupoProduto grupoProduto;

    public void novo(){
        grupoProduto = new GrupoProduto();
    }
    
    public void excluir(GrupoProduto e){
        grupoProdutoFacade.excluir(e);
    }
    
    public void editar(GrupoProduto e){
        this.grupoProduto = e;
    }
    
    public void salvar() {
        grupoProdutoFacade.salvar(grupoProduto);
    }

    public List<GrupoProduto> listaTodos() {
        return grupoProdutoFacade.listaTodos();
    }

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

}

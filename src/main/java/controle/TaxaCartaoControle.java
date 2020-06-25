/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.TaxaCartao;
import facade.TaxaCartaoFacade;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Lucas
 */
@Named
@SessionScoped
public class TaxaCartaoControle implements Serializable {

    @Inject
    private TaxaCartaoFacade taxaCartaoFacade;
    private TaxaCartao taxacartao;

    public void novo(){
        taxacartao = new TaxaCartao();
    }
    
    public void excluir(TaxaCartao e){
        taxaCartaoFacade.excluir(e);
    }
    
    public void editar(TaxaCartao e){
        this.taxacartao = e;
    }
    
    public void salvar() {
        taxaCartaoFacade.salvar(taxacartao);
    }

    public List<TaxaCartao> listaTodos() {
        return taxaCartaoFacade.listaTodos();
    }

    public TaxaCartao getTaxaCartao() {
        return taxacartao;
    }

    public void setTaxaCartao(TaxaCartao taxaCartao) {
        this.taxacartao = taxaCartao;
    }

}

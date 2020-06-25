/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.GrupoProduto;
import entidade.Produto;
import facade.GrupoProdutoFacade;
import facade.ProdutoFacade;
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
public class ProdutoControle implements Serializable {

    @Inject
    private ProdutoFacade produtoFacade;
    @Inject
    private GrupoProdutoFacade grupoProdutoFacade;
    private Produto produto;
    private MoneyConverter moneyConverter;
     private ConverterGenerico grupoProdutoConverter;


    public ConverterGenerico getGrupoProdutoConverter() {
        if(grupoProdutoConverter == null){
            grupoProdutoConverter = new ConverterGenerico(grupoProdutoFacade);
        }
        return grupoProdutoConverter;
    }

    public void setGrupoProdutoConverter(ConverterGenerico grupoProdutoConverter) {
        this.grupoProdutoConverter = grupoProdutoConverter;
    }
    
       public List<GrupoProduto> listaGrupoProduto(){
        return grupoProdutoFacade.listaTodos();
    }
    

    public MoneyConverter getMoneyConverter() {
        if(moneyConverter == null){
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public void novo() {
        produto = new Produto();
    }

    public void excluir(Produto e) {
        produtoFacade.excluir(e);
    }

    public void editar(Produto e) {
        this.produto = e;
    }

    public void salvar() {
        produtoFacade.salvar(produto);
    }

    public List<Produto> listaTodos() {
        return produtoFacade.listaTodos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}

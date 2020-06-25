/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.BaixaContasPagar;
import entidade.Fornecedor;
import entidade.Colaborador;
import entidade.ContasPagar;
import entidade.ItensCompra;
import entidade.Compra;
import entidade.Produto;
import facade.FornecedorFacade;
import facade.ColaboradorFacade;
import facade.CompraFacade;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

/**
 *
 * @author carol
 */
@Named
@ViewAccessScoped
public class CompraControle implements Serializable {

    @Inject
    private CompraFacade compraFacade;
    private Compra compra;
    private ItensCompra itensCompra;
    @Inject
    private FornecedorFacade fornecedorFacade;
    @Inject
    private ColaboradorFacade colaboradorFacade;
    @Inject
    private ProdutoFacade produtoFacade;
    private ConverterGenerico converterFornecedor;
    private ConverterGenerico converterProduto;
    private MoneyConverter moneyConverter;
    private Integer numParcela;
    private Date dtVencimento;
    private ContasPagar contasPagar;
    private Fornecedor fornecedor;

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    
    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public Integer getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    public Date getDtVencimento() {
        return dtVencimento;
    }

    public void setDtVencimento(Date dtVencimento) {
        this.dtVencimento = dtVencimento;
    }

    public ConverterGenerico getConverterProduto() {
        if (converterProduto == null) {
            converterProduto = new ConverterGenerico(produtoFacade);
        }
        return converterProduto;
    }

    public void setConverterProduto(ConverterGenerico converterProduto) {
        this.converterProduto = converterProduto;
    }

    public void addItemProduto() {
        Double estoque = itensCompra.getProduto().getEstoque();
        ItensCompra itemTemp = null;
        for (ItensCompra it : compra.getItensCompras()) {
            if (it.getProduto().equals(itensCompra.getProduto())) {
                estoque = estoque + it.getQuantidade();
                itemTemp = it;
            }
        }       
            if (itemTemp != null) {
                itemTemp.setQuantidade(itemTemp.getQuantidade() + itensCompra.getQuantidade());
            } else {
                itensCompra.setCompra(compra);
                compra.getItensCompras().add(itensCompra);
            }
            itensCompra = new ItensCompra();
        }


    public void removerItemCompra(ItensCompra it) {
        compra.getItensCompras().remove(it);
    }

    public MoneyConverter getMoneyConverter() {
        if (moneyConverter == null) {
            moneyConverter = new MoneyConverter();
        }
        return moneyConverter;
    }

    public void setMoneyConverter(MoneyConverter moneyConverter) {
        this.moneyConverter = moneyConverter;
    }

    public void setaValorProduto() {
        itensCompra.setValor(itensCompra.getProduto().getValor());
    }

    public ItensCompra getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItensCompra itensCompra) {
        this.itensCompra = itensCompra;
    }
    public List<Produto> listaFiltrandoProduto(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }

    
    public List<Fornecedor> listaFiltrandoFornecedor(String parte) {
        return fornecedorFacade.listaFiltrando(parte, "nome");
    }
    
    public List<Colaborador> listaFiltrandoColaborador(String parte) {
        return colaboradorFacade.listaFiltrando(parte, "nome");
    }

     public ConverterGenerico getConverterFornecedor() {
        if (converterFornecedor == null) {
            converterFornecedor = new ConverterGenerico(fornecedorFacade);
        }
        return converterFornecedor;
    }
    public void setConverterFornecedor(ConverterGenerico converterFornecedor) {
        this.converterFornecedor = converterFornecedor;
    }

    
    public void novo() {
        compra = new Compra();
        itensCompra = new ItensCompra();
    }

    public void excluir(Compra e) {
        compraFacade.excluir(e);
    }

    public void editar(Compra e) {
        this.compra = e;
    }

    public void salvar() {
        compraFacade.salvar(compra);
    }

    public List<Compra> listaTodos() {
        return compraFacade.listaTodos();
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    public void geraParcela() {
        compra.setContasPagars(new ArrayList<ContasPagar>());
        Double valor = compra.getValorTotal() / numParcela;
        Date dataVen = compra.getDataCompra();
        for (Integer i = 1; i <= numParcela; i++) {

            if (compra.getFormapag().equals("Cartão") || compra.getFormapag().equals("Cheque a Vista") || compra.getFormapag().equals("Dinheiro Avista")) {
                ContasPagar cr = new ContasPagar();
                cr.setDataLancamento(compra.getDataCompra());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setFornecedor(compra.getFornecedor());
                cr.setDataVencimento(dataVen);
                cr.setCompra(compra);
                cr.setFormapag(compra.getFormapag());

                BaixaContasPagar b = new BaixaContasPagar();
                b.setContasPagar(cr);
                b.setDataBaixa(compra.getDataCompra());
                b.setValor(valor);
                cr.setBaixaContasPagars(new ArrayList<BaixaContasPagar>());
                cr.getBaixaContasPagars().add(b);
                if (compra.getFornecedor() != null) {
                    cr.setFornecedor(compra.getFornecedor());
                }
                compra.getContasPagars().add(cr);
                //Soma 1 mês no compracimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();

            } else {
                ContasPagar cr = new ContasPagar();
                cr.setDataLancamento(compra.getDataCompra());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setFornecedor(compra.getFornecedor());
                cr.setDataVencimento(dataVen);
                cr.setCompra(compra);
                cr.setFormapag(compra.getFormapag());

                if (compra.getFornecedor() != null) {
                    cr.setFornecedor(compra.getFornecedor());
                }
                compra.getContasPagars().add(cr);
                //Soma 1 mês no compracimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();
            }
        }
    }
}    
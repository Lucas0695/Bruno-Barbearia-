/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.BaixaContasReceber;
import entidade.Cliente;
import entidade.ContasReceber;
import entidade.ItensVenda;
import entidade.Venda;
import entidade.Produto;
import facade.ClienteFacade;
import facade.VendaFacade;
import facade.ProdutoFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

/**
 *
 * @author carol
 */
@Named
@ViewAccessScoped
public class VendaControle implements Serializable {

    @Inject
    private VendaFacade vendaFacade;
    private Venda venda;
    private ItensVenda itensVenda;
    @Inject
    private ClienteFacade clienteFacade;
    @Inject
    private ProdutoFacade produtoFacade;
    private ConverterGenerico converterCliente;
    private ConverterGenerico converterProduto;
    private MoneyConverter moneyConverter;
    private Integer numParcela;
    private Date dtVencimento;
    private ContasReceber contasReceber;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
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
        Double estoque = itensVenda.getProduto().getEstoque();
        ItensVenda itemTemp = null;
        for (ItensVenda it : venda.getItensVendas()) {
            if (it.getProduto().equals(itensVenda.getProduto())) {
                estoque = estoque - it.getQuantidade();
                itemTemp = it;
            }
        }   
        
        if (estoque < itensVenda.getQuantidade()) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Estoque insuficiente!",
                            "Restam apenas "
                            + estoque
                            + " unidades."));
        } else {
            if (itemTemp != null) {
                itemTemp.setQuantidade(itemTemp.getQuantidade() - itensVenda.getQuantidade());
            } else {
                itensVenda.setVenda(venda);
                venda.getItensVendas().add(itensVenda);
            }
            itensVenda = new ItensVenda();
        }
    }
    
    public void removerItemProduto(ItensVenda it) {
        venda.getItensVendas().remove(it);
    }

    public void removerItemVenda(ItensVenda it) {
        venda.getItensVendas().remove(it);
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
        itensVenda.setValor(itensVenda.getProduto().getValor());
    }

    public ItensVenda getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(ItensVenda itensVenda) {
        this.itensVenda = itensVenda;
    }
    public List<Produto> listaFiltrandoProduto(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }

    
    public List<Cliente> listaFiltrandoCliente(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome");
    }

     public ConverterGenerico getConverterCliente() {
        if (converterCliente == null) {
            converterCliente = new ConverterGenerico(clienteFacade);
        }
        return converterCliente;
    }
    public void setConverterCliente(ConverterGenerico converterCliente) {
        this.converterCliente = converterCliente;
    }

    
    public void novo() {
        venda = new Venda();
        itensVenda = new ItensVenda();
    }

    public void excluir(Venda e) {
        vendaFacade.excluir(e);
    }

    public void editar(Venda e) {
        this.venda = e;
    }

    public void salvar() {
        vendaFacade.salvar(venda);
    }

    public List<Venda> listaTodos() {
        return vendaFacade.listaTodos();
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public void geraParcela() {
        venda.setContasRecebers(new ArrayList<ContasReceber>());
        Double valor = venda.getValorTotal() / numParcela;
        Date dataVen = venda.getDataVenda();
        for (Integer i = 1; i <= numParcela; i++) {

            if (venda.getFormapag().equals("Cartão") || venda.getFormapag().equals("Cheque a Vista") || venda.getFormapag().equals("Dinheiro Avista")) {
                ContasReceber cr = new ContasReceber();
                cr.setDataLancamento(venda.getDataVenda());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setCliente(venda.getCliente());
                cr.setDataVencimento(dataVen);
                cr.setVenda(venda);
                cr.setFormapag(venda.getFormapag());

                BaixaContasReceber b = new BaixaContasReceber();
                b.setContasReceber(cr);
                b.setDataBaixa(venda.getDataVenda());
                b.setValor(valor);
                cr.setBaixaContasRecebers(new ArrayList<BaixaContasReceber>());
                cr.getBaixaContasRecebers().add(b);
                if (venda.getCliente() != null) {
                    cr.setCliente(venda.getCliente());
                }
                venda.getContasRecebers().add(cr);
                //Soma 1 mês no vendacimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();

            } else {
                ContasReceber cr = new ContasReceber();
                cr.setDataLancamento(venda.getDataVenda());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setCliente(venda.getCliente());
                cr.setDataVencimento(dataVen);
                cr.setVenda(venda);
                cr.setFormapag(venda.getFormapag());

                if (venda.getCliente() != null) {
                    cr.setCliente(venda.getCliente());
                }
                venda.getContasRecebers().add(cr);
                //Soma 1 mês no vendacimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();
            }
        }
    }
}

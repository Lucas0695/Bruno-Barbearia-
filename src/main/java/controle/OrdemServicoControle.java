/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.AgendamentoHorario;
import entidade.BaixaContasReceber;
import entidade.Cliente;
import entidade.Colaborador;
import entidade.ContasReceber;
import entidade.ItensVenda;
import entidade.ItensServico;
import entidade.OrdemServico;
import entidade.Produto;
import entidade.Servico;
import entidade.TaxaCartao;
import facade.ClienteFacade;
import facade.ColaboradorFacade;
import facade.OrdemServicoFacade;
import facade.ProdutoFacade;
import facade.ServicoFacade;
import facade.TaxaCartaoFacade;
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
import util.ScheduleEventAgenda;

/**
 *
 * @author carol
 */
@Named
@ViewAccessScoped
public class OrdemServicoControle implements Serializable {

    @Inject
    private OrdemServicoFacade ordemServicoFacade;
    private OrdemServico ordemServico;
    private ItensVenda itensVenda = new ItensVenda();
    private ItensServico itensServico = new ItensServico();
    @Inject
    private ClienteFacade clienteFacade;
    @Inject
    private ColaboradorFacade colaboradorFacade;
    @Inject
    private ServicoFacade servicoFacade;
    @Inject
    private ProdutoFacade produtoFacade;
    @Inject
    private TaxaCartaoFacade taxaCartaoFacade;
    private ConverterGenerico converterColaborador;
    private ConverterGenerico converterCliente;
    private ConverterGenerico converterTaxaCartao;
    private ConverterGenerico converterServico;
    private MoneyConverter moneyConverter;
    private ConverterGenerico converterProduto;
    private Integer numParcela;
    private Date dtVencimento;
    private ContasReceber contasReceber;

    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public Boolean isCartao() {
        if (ordemServico.getFormapag().equals("Cartão")) {
            return true;
        }
        return false;
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
        for (ItensVenda it : ordemServico.getItensVendas()) {
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
                itemTemp.setQuantidade(itemTemp.getQuantidade() + itensVenda.getQuantidade());
            } else {
                itensVenda.setOrdemServico(ordemServico);
                ordemServico.getItensVendas().add(itensVenda);
            }
            itensVenda = new ItensVenda();
        }

    }

    public void removerItemProduto(ItensVenda it) {
        ordemServico.getItensVendas().remove(it);
    }

    public void removerItemServico(ItensServico it) {
        ordemServico.getItensServicos().remove(it);
    }

    public void addItemServico() {
        itensServico.setOrdemServico(ordemServico);
        ordemServico.getItensServicos().add(itensServico);
        itensServico = new ItensServico();
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

    public void setaValorServico() {
        itensServico.setValor(itensServico.getServico().getValor());
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

    public ItensServico getItensServico() {
        return itensServico;
    }

    public void setItensServico(ItensServico itensServico) {
        this.itensServico = itensServico;
    }

    public List<Servico> listaFiltrandoServico(String parte) {
        return servicoFacade.listaFiltrando(parte, "nome");
    }

    public List<Produto> listaFiltrandoProduto(String parte) {
        return produtoFacade.listaFiltrando(parte, "nome");
    }

    public List<Cliente> listaFiltrandoCliente(String parte) {
        return clienteFacade.listaFiltrando(parte, "nome");
    }

    public List<Colaborador> listaFiltrandoColaborador(String parte) {
        return colaboradorFacade.listaFiltrando(parte, "nome");
    }

    public List<TaxaCartao> listaFiltrandoTaxaCartao(String parte) {
        return taxaCartaoFacade.listaFiltrando(parte, "nome");
    }

    public ConverterGenerico getConverterServico() {
        if (converterServico == null) {
            converterServico = new ConverterGenerico(servicoFacade);
        }
        return converterServico;
    }

    public void setConverterServico(ConverterGenerico converterServico) {
        this.converterServico = converterServico;
    }

    public ConverterGenerico getConverterCliente() {
        if (converterCliente == null) {
            converterCliente = new ConverterGenerico(clienteFacade);
        }
        return converterCliente;
    }

    public ConverterGenerico getConverterTaxaCartao() {
        if (converterTaxaCartao == null) {
            converterTaxaCartao = new ConverterGenerico(taxaCartaoFacade);
        }
        return converterTaxaCartao;
    }

    public ConverterGenerico getConverterColaborador() {
        if (converterColaborador == null) {
            converterColaborador = new ConverterGenerico(colaboradorFacade);
        }
        return converterColaborador;
    }

    public void setColaboradorFacade(ColaboradorFacade colaboradorFacade) {
        this.colaboradorFacade = colaboradorFacade;
    }

    public void novo() {
        ordemServico = new OrdemServico();
        itensServico = new ItensServico();
        itensVenda = new ItensVenda();
    }
    
    

    public void excluir(OrdemServico e) {
        ordemServicoFacade.excluir(e);
    }

    public void editar(OrdemServico e) {
        this.ordemServico = e;
    }

    public void salvar() {
        System.out.println("---> " + ordemServico.getColaborador().getNome());
        ordemServicoFacade.salvar(ordemServico);
    }

    public List<OrdemServico> listaTodos() {
        return ordemServicoFacade.listaTodos();
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public void geraParcela() {
        ordemServico.setContasRecebers(new ArrayList<ContasReceber>());
        Double valor = ordemServico.getValorTotal() / numParcela;
        Date dataVen = ordemServico.getDataServico();
        for (Integer i = 1; i <= numParcela; i++) {

            if (ordemServico.getFormapag().equals("Cartão") || ordemServico.getFormapag().equals("Cheque a Vista") || ordemServico.getFormapag().equals("Dinheiro Avista")) {
                ContasReceber cr = new ContasReceber();
                cr.setDataLancamento(ordemServico.getDataServico());
//            cr.setDescricao(ordemServico.getCliente().getNome());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setCliente(ordemServico.getCliente());
                cr.setColaborador(ordemServico.getColaborador());
                cr.setDataVencimento(dataVen);
                cr.setOrdemServico(ordemServico);
                cr.setFormapag(ordemServico.getFormapag());

                BaixaContasReceber b = new BaixaContasReceber();
                b.setContasReceber(cr);
                b.setDataBaixa(ordemServico.getDataServico());
                b.setValor(valor);
                cr.setBaixaContasRecebers(new ArrayList<BaixaContasReceber>());
                cr.getBaixaContasRecebers().add(b);
                if (ordemServico.getCliente() != null) {
                    cr.setCliente(ordemServico.getCliente());
                }
                ordemServico.getContasRecebers().add(cr);
                //Soma 1 mês no ordemServicocimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();

            } else {
                ContasReceber cr = new ContasReceber();
                cr.setDataLancamento(ordemServico.getDataServico());
//            cr.setDescricao(ordemServico.getCliente().getNome());
                cr.setParcela(i.toString() + "/" + numParcela.toString());
                cr.setValor(valor);
                cr.setCliente(ordemServico.getCliente());
                cr.setColaborador(ordemServico.getColaborador());
                cr.setDataVencimento(dataVen);
                cr.setOrdemServico(ordemServico);
                cr.setFormapag(ordemServico.getFormapag());

                if (ordemServico.getCliente() != null) {
                    cr.setCliente(ordemServico.getCliente());
                }
                ordemServico.getContasRecebers().add(cr);
                //Soma 1 mês no ordemServicocimento
                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVen);
                cal.add(Calendar.MONTH, 1);
                dataVen = cal.getTime();
            }

        }

    }

}

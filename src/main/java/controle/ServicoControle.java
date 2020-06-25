/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import converter.MoneyConverter;
import entidade.GrupoServico;
import entidade.Servico;
import facade.GrupoServicoFacade;
import facade.ServicoFacade;
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
public class ServicoControle implements Serializable {

    @Inject
    private ServicoFacade servicoFacade;
    @Inject 
    private GrupoServicoFacade grupoServicoFacade;
    private Servico servico;
    private MoneyConverter moneyConverter;
    private ConverterGenerico grupoServicoConverter;


    public ConverterGenerico getGrupoServicoConverter() {
        if(grupoServicoConverter == null){
            grupoServicoConverter = new ConverterGenerico(grupoServicoFacade);
        }
        return grupoServicoConverter;
    }

    public void setGrupoServicoConverter(ConverterGenerico grupoServicoConverter) {
        this.grupoServicoConverter = grupoServicoConverter;
    }
    
       public List<GrupoServico> listaGrupoServico(){
        return grupoServicoFacade.listaTodos();
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
        servico = new Servico();
    }

    public void excluir(Servico e) {
        servicoFacade.excluir(e);
    }

    public void editar(Servico e) {
        this.servico = e;
    }

    public void salvar() {
        servicoFacade.salvar(servico);
    }

    public List<Servico> listaTodos() {
        return servicoFacade.listaTodos();
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

}

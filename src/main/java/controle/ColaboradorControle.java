/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Colaborador;
import facade.CidadeFacade;
import facade.ColaboradorFacade;
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
public class ColaboradorControle implements Serializable {

    @Inject
    private ColaboradorFacade colaboradorFacade;
    @Inject
    private CidadeFacade cidadeFacade;
    private Colaborador colaborador;
    private ConverterGenerico converterCidade;
  
    
    public void salvar(){
        colaboradorFacade.salvar(colaborador);
    }
    
    public List<Cidade> listaFiltrando(String parte){
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public ConverterGenerico getConverterCidade() {
        if(converterCidade == null){
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    }
    
    
    public void novo(){
        colaborador = new Colaborador();
        colaborador.setCadastro("Colaborador");
    }
    
    public void excluir(Colaborador e){
        colaboradorFacade.excluir(e);
    }
    
    public void editar(Colaborador e){
        this.colaborador = e;
    }

    public List<Colaborador> listaTodos() {
        return colaboradorFacade.listaTodos();
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

}

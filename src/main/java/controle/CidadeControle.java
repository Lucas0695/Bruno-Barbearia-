/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Estado;
import facade.CidadeFacade;
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
public class CidadeControle implements Serializable {

    @Inject
    private CidadeFacade cidadeFacade;
    @Inject
    private EstadoFacade estadoFacade;
    private Cidade cidade;
    private ConverterGenerico estadoConverter;

    public ConverterGenerico getEstadoConverter() {
        if(estadoConverter == null){
            estadoConverter = new ConverterGenerico(estadoFacade);
        }
        return estadoConverter;
    }

    public void setEstadoConverter(ConverterGenerico estadoConverter) {
        this.estadoConverter = estadoConverter;
    }
    
    

    public List<Estado> listaEstados(){
        return estadoFacade.listaTodos();
    }
    
    public void novo(){
        cidade = new Cidade();
    }
    
    public void excluir(Cidade e){
        cidadeFacade.excluir(e);
    }
    
    public void editar(Cidade e){
        this.cidade = e;
    }
    
    public void salvar() {
        cidadeFacade.salvar(cidade);
    }

    public List<Cidade> listaTodos() {
        return cidadeFacade.listaTodos();
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}

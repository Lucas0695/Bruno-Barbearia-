/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Cliente;
import facade.CidadeFacade;
import facade.ClienteFacade;
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
public class ClienteControle implements Serializable {

    @Inject
    private ClienteFacade clienteFacade;
    @Inject
    private CidadeFacade cidadeFacade;
    private Cliente cliente;
    private ConverterGenerico converterCidade;
  
    
    public void salvar(){
        clienteFacade.salvar(cliente);
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
        cliente = new Cliente();
        cliente.setCadastro("Cliente");
    }
    
    public void excluir(Cliente e){
        clienteFacade.excluir(e);
    }
    
    public void editar(Cliente e){
        this.cliente = e;
    }

    public List<Cliente> listaTodos() {
        return clienteFacade.listaTodos();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}

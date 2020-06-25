/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidade.Pessoa;
import entidade.Cliente;
import entidade.Colaborador;
import entidade.Fornecedor;
import facade.PessoaFacade;
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
public class PessoaControle implements Serializable {

    @Inject
    private PessoaFacade pessoaFacade;
    private Pessoa pessoa;
    @Inject
    private ClienteControle clienteControle;
    @Inject
    private FornecedorControle fornecedorControle;
    @Inject
    private ColaboradorControle colaboradorControle;

    public ClienteControle getClienteControle() {
        return clienteControle;
    }

    public void setClienteControle(ClienteControle clienteControle) {
        this.clienteControle = clienteControle;
    }

    public FornecedorControle getFornecedorControle() {
        return fornecedorControle;
    }

    public void setFornecedorControle(FornecedorControle fornecedorControle) {
        this.fornecedorControle = fornecedorControle;
    }
    

    public void excluir(Pessoa e){
        if(e instanceof Cliente){
            clienteControle.excluir((Cliente)e);
        }else{
            fornecedorControle.excluir((Fornecedor)e);
        }
    }
    
    public String editar(Pessoa p){
        if(p instanceof Cliente){
            clienteControle.editar((Cliente)p);
            return "editacliente";
        }else if(p instanceof Colaborador){
            colaboradorControle.editar((Colaborador)p);
            return "editacolaborador";
        }else{
            fornecedorControle.editar((Fornecedor)p);
            return "editafornecedor";
        }
    }   

    public List<Pessoa> listaTodos() {
        return pessoaFacade.listaTodos();
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}

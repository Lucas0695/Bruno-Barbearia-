/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidade.Cidade;
import entidade.Fornecedor;
import facade.CidadeFacade;
import facade.FornecedorFacade;
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
public class FornecedorControle implements Serializable {

    @Inject
    private FornecedorFacade fornecedorFacade;
    private Fornecedor fornecedor;
    private String tipoPessoa = "PessoaFisica";
    @Inject
    private CidadeFacade cidadeFacade;
    private ConverterGenerico converterCidade;
    
    public Boolean isPF() {
        if (tipoPessoa.equals("PessoaFisica")) {
            return true;
        }
        return false;
    }

    public Boolean isPJ() {
        if (tipoPessoa.equals("PessoaJuridica")) {
            return true;
        }
        return false;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    
    

    public void salvar() {
        fornecedorFacade.salvar(fornecedor);
    }

    public List<Cidade> listaFiltrando(String parte) {
        return cidadeFacade.listaFiltrando(parte, "nome");
    }

    public ConverterGenerico getConverterCidade() {
        if (converterCidade == null) {
            converterCidade = new ConverterGenerico(cidadeFacade);
        }
        return converterCidade;
    }

    public void setConverterCidade(ConverterGenerico converterCidade) {
        this.converterCidade = converterCidade;
    }

    public void novo() {
        fornecedor = new Fornecedor();
        fornecedor.setCadastro("Fornecedor");
    }

    public void excluir(Fornecedor e) {
        fornecedorFacade.excluir(e);
    }

    public void editar(Fornecedor e) {
        this.fornecedor = e;
    }

    public List<Fornecedor> listaTodos() {
        return fornecedorFacade.listaTodos();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}

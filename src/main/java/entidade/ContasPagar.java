/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jaimedias
 */
@Entity
public class ContasPagar implements Serializable {

      private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    private Double Valor;
    private String parcela;
    private String descricao;
    private String formapag;
    @ManyToOne
    private Compra compra;
    @ManyToOne
    private Fornecedor fornecedor;
    @OneToMany(cascade = CascadeType.ALL, 
            orphanRemoval = true, 
            fetch = FetchType.EAGER, 
            mappedBy = "contasPagar")
    private List<BaixaContasPagar> baixaContasPagars;
    
    public Double getTotalBaixado(){
        Double total = 0d;
        for(BaixaContasPagar b : baixaContasPagars){
            total = total + b.getValor();
        }
        return total;
    }
    
    public String getSituacao(){
        if(Objects.equals(getValor(), getTotalBaixado())){
            return "Baixado";
        }else{
            return "Aberto";
        }
    }

    public String getFormapag() {
        return formapag;
    }

    public void setFormapag(String formapag) {
        this.formapag = formapag;
    }

    
    public List<BaixaContasPagar> getBaixaContasPagars() {
        return baixaContasPagars;
    }

    public void setBaixaContasPagars(List<BaixaContasPagar> baixaContasPagars) {
        this.baixaContasPagars = baixaContasPagars;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    
 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContasPagar)) {
            return false;
        }
        ContasPagar other = (ContasPagar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ContasPagar[ id=" + id + " ]";
    }
    
}

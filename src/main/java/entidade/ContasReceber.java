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
public class ContasReceber implements Serializable, EntidadePai  {

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
    private Colaborador colaborador;
    @ManyToOne
    private OrdemServico ordemServico;
    @ManyToOne
    private Venda venda;
    @ManyToOne
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, 
            orphanRemoval = true, 
            fetch = FetchType.EAGER, 
            mappedBy = "contasReceber")
    private List<BaixaContasReceber> baixaContasRecebers;
    
    
    public Double getTotalBaixado(){
        Double total = 0d;
        for(BaixaContasReceber b : baixaContasRecebers){
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

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
    
    

    public String getFormapag() {
        return formapag;
    }

    public void setFormapag(String formapag) {
        this.formapag = formapag;
    }

    
    public List<BaixaContasReceber> getBaixaContasRecebers() {
        return baixaContasRecebers;
    }

    public void setBaixaContasRecebers(List<BaixaContasReceber> baixaContasRecebers) {
        this.baixaContasRecebers = baixaContasRecebers;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
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

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
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
        if (!(object instanceof ContasReceber)) {
            return false;
        }
        ContasReceber other = (ContasReceber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.ContasReceber[ id=" + id + " ]";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * @author sistemas
 */
@Entity
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenda;
    @ManyToOne
    private Cliente cliente;
    private String formapag;
    private Double ValorTotal;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "venda")
    private List<ItensVenda> itensVenda;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "venda")
    private List<ContasReceber> contasRecebers;

    public Venda() {
        this.dataVenda = new Date();
        this.itensVenda = new ArrayList<ItensVenda>();
        this.contasRecebers = new ArrayList<ContasReceber>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormapag() {
        return formapag;
    }

    public void setFormapag(String formapag) {
        this.formapag = formapag;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    
    public List<ContasReceber> getContasRecebers() {
        return contasRecebers;
    }

    public void setContasRecebers(List<ContasReceber> contasRecebers) {
        this.contasRecebers = contasRecebers;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorTotal() {
        ValorTotal = 0d;
        for (ItensVenda is : itensVenda) {
            ValorTotal += is.getSubtotal();
        }
        return ValorTotal;
    }

    public void setValorTotal(Double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public List<ItensVenda> getItensVendas() {
        return itensVenda;
    }

    public void setItensVendas(List<ItensVenda> itensVendas) {
        this.itensVenda = itensVendas;
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
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Venda[ id=" + id + " ]";
    }

}

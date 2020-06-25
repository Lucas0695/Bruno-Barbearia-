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
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;
    @ManyToOne
    private Fornecedor fornecedor;
    private String formapag;
    private Double ValorTotal;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "compra")
    private List<ItensCompra> itensCompra;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "compra")
    private List<ContasPagar> contasPagars;

    public Compra() {
        this.dataCompra = new Date();
        this.itensCompra = new ArrayList<ItensCompra>();
        this.contasPagars = new ArrayList<ContasPagar>();
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    
    
    public List<ContasPagar> getContasPagars() {
        return contasPagars;
    }

    public void setContasPagars(List<ContasPagar> contasPagars) {
        this.contasPagars = contasPagars;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        ValorTotal = 0d;
        for (ItensCompra is : itensCompra) {
            ValorTotal += is.getSubtotal();
        }
        return ValorTotal;
    }

    public void setValorTotal(Double ValorTotal) {
        this.ValorTotal = ValorTotal;
    }

    public List<ItensCompra> getItensCompras() {
        return itensCompra;
    }

    public void setItensCompras(List<ItensCompra> itensCompras) {
        this.itensCompra = itensCompras;
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
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidade.Compra[ id=" + id + " ]";
    }

}

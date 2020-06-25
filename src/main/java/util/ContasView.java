/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author jaimedias
 */

public class ContasView implements Serializable {
    
    private Integer mes;
    private Double valor;

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ContasView(Integer mes, Double valor) {
        this.mes = mes;
        this.valor = valor;
    }

    public ContasView() {
    }
    
}

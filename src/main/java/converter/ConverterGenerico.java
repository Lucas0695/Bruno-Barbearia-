/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import entidade.EntidadePai;
import facade.AbstractFacade;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author jaimedias
 */
public class ConverterGenerico implements Converter, Serializable{
    
    private AbstractFacade abstractFacade;

    public ConverterGenerico(AbstractFacade abstractFacade) {
        this.abstractFacade = abstractFacade;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return abstractFacade.buscar(Long.parseLong(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((EntidadePai)o).getId().toString();
    }
    
}

package facade;

import entidade.TaxaCartao;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class TaxaCartaoFacade extends AbstractFacade<TaxaCartao>{

    @Inject
    private EntityManager em;

    public TaxaCartaoFacade() {
        super(TaxaCartao.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

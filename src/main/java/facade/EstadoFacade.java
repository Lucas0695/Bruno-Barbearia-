package facade;

import entidade.Estado;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class EstadoFacade extends AbstractFacade<Estado>{

    @Inject
    private EntityManager em;

    public EstadoFacade() {
        super(Estado.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

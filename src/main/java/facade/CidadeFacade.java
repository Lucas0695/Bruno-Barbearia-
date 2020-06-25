package facade;

import entidade.Cidade;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class CidadeFacade extends AbstractFacade<Cidade>{

    @Inject
    private EntityManager em;

    public CidadeFacade() {
        super(Cidade.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

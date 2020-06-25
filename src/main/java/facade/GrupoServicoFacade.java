package facade;

import entidade.GrupoServico;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class GrupoServicoFacade extends AbstractFacade<GrupoServico>{

    @Inject
    private EntityManager em;

    public GrupoServicoFacade() {
        super(GrupoServico.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

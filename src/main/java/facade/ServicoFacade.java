package facade;

import entidade.Estado;
import entidade.Servico;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ServicoFacade extends AbstractFacade<Servico>{

    @Inject
    private EntityManager em;

    public ServicoFacade() {
        super(Servico.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

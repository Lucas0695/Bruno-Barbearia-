package facade;

import entidade.Pessoa;
import entidade.Cliente;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ClienteFacade extends AbstractFacade<Cliente>{

    @Inject
    private EntityManager em;

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

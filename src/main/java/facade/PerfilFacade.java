package facade;

import entidade.Perfil;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class PerfilFacade extends AbstractFacade<Perfil>{

    @Inject
    private EntityManager em;

    public PerfilFacade() {
        super(Perfil.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

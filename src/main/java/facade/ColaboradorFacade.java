package facade;

import entidade.Colaborador;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ColaboradorFacade extends AbstractFacade<Colaborador>{

    @Inject
    private EntityManager em;

    public ColaboradorFacade() {
        super(Colaborador.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

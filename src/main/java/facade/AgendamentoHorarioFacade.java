package facade;

import entidade.AgendamentoHorario;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class AgendamentoHorarioFacade extends AbstractFacade<AgendamentoHorario>{

    @Inject
    private EntityManager em;

    public AgendamentoHorarioFacade() {
        super(AgendamentoHorario.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

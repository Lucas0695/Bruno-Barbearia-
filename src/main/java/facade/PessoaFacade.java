package facade;

import entidade.Pessoa;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class PessoaFacade extends AbstractFacade<Pessoa>{

    @Inject
    private EntityManager em;

    public PessoaFacade() {
        super(Pessoa.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

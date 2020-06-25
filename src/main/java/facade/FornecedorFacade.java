package facade;

import entidade.Pessoa;
import entidade.Fornecedor;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class FornecedorFacade extends AbstractFacade<Fornecedor>{

    @Inject
    private EntityManager em;

    public FornecedorFacade() {
        super(Fornecedor.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

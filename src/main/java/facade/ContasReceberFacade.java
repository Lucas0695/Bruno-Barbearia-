package facade;

import entidade.ContasReceber;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import util.ContasView;
import util.Transacional;

@Transacional
public class ContasReceberFacade extends AbstractFacade<ContasReceber>{

    @Inject
    private EntityManager em;

    public ContasReceberFacade() {
        super(ContasReceber.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
//     public List<ContasView> listaCRAgrupado(){
//        Query q=getEntityManager().createNativeQuery("select extract(month from datavencimento) mes, sum(valor) valor from contasreceber "
//                + "group by extract(month from datavencimento) order by extract(month from datavencimento)", ContasView.class);
//        return q.getResultList();
//    }
//    
}

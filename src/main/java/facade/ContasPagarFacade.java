package facade;

import entidade.ContasPagar;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class ContasPagarFacade extends AbstractFacade<ContasPagar>{

    @Inject
    private EntityManager em;

    public ContasPagarFacade() {
        super(ContasPagar.class);
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

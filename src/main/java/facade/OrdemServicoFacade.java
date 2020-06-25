package facade;

import entidade.ItensVenda;
import entidade.OrdemServico;
import entidade.Produto;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class OrdemServicoFacade extends AbstractFacade<OrdemServico>{

    @Inject
    private EntityManager em;

    public OrdemServicoFacade() {
        super(OrdemServico.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvar(OrdemServico os) {        
        super.salvar(os); 
        baixaEstoque(os);
    }

    private void baixaEstoque(OrdemServico os) {
        for(ItensVenda it : os.getItensVendas()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
    }
    
       @Override
    public void excluir(OrdemServico os) {
        for(ItensVenda it : os.getItensVendas()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
        super.excluir(os);
    }
    
    
    
    
}

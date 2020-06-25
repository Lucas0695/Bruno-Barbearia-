package facade;

import entidade.Venda;
import entidade.ItensVenda;
import entidade.Produto;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class VendaFacade extends AbstractFacade<Venda>{

    @Inject
    private EntityManager em;

    public VendaFacade() {
        super(Venda.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    @Override
    public void salvar(Venda co) {        
        super.salvar(co); 
        somaEstoque(co);
    }

    private void somaEstoque(Venda co) {
        for(ItensVenda it : co.getItensVendas()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
    }
    
   
       @Override
    public void excluir(Venda ve) {
        for(ItensVenda it : ve.getItensVendas()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
        super.excluir(ve);
    }
    
    }
    
    
    
    


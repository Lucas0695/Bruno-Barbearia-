package facade;

import entidade.Compra;
import entidade.ItensCompra;
import entidade.ItensCompra;
import entidade.Compra;
import entidade.Produto;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import util.Transacional;

@Transacional
public class CompraFacade extends AbstractFacade<Compra>{

    @Inject
    private EntityManager em;

    public CompraFacade() {
        super(Compra.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    @Override
    public void salvar(Compra co) {        
        super.salvar(co); 
        somaEstoque(co);
    }

    private void somaEstoque(Compra co) {
        for(ItensCompra it : co.getItensCompras()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() + it.getQuantidade());
            em.merge(p);
        }
    }
    
    
       @Override
    public void excluir(Compra os) {
        for(ItensCompra it : os.getItensCompras()){
            Produto p = it.getProduto();
            p.setEstoque(p.getEstoque() - it.getQuantidade());
            em.merge(p);
        }
        super.excluir(os);
    }
    
    
    }
    
    
    
    


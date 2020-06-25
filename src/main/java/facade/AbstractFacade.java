package facade;

import entidade.Compra;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class AbstractFacade<T>  implements Serializable{
    
    private Class<T> entityClass;
    
    protected abstract EntityManager getEntityManager();
    
    public AbstractFacade(Class<T> entityClass){
        this.entityClass = entityClass;
    }
    
    public T buscar(Long id){
        return getEntityManager().find(entityClass, id);
    }
    
    public void salvar(T objeto){
        getEntityManager().merge(objeto);
    }
    
    public void excluir(T objeto){
        getEntityManager().remove(getEntityManager().merge(objeto));
    }
    
    public List<T> listaTodos(){
        Query q = getEntityManager().createQuery("from "+entityClass.getSimpleName());
        return q.getResultList();
    }
    
    public List<T> listaFiltrando(String filtro, String... atributos) {
        String hql = "from " + entityClass.getSimpleName() + " obj where ";
        for (String atributo : atributos) {
            hql += "lower(obj." + atributo + ") like :filtro OR ";
        }
        hql = hql.substring(0, hql.length() - 3);
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
        return q.getResultList();
    }

    void remove(Compra entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

package controle;

import converter.ConverterGenerico;
import entidade.Perfil;
import entidade.Usuario;
import facade.PerfilFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UsuarioControle implements Serializable {

    @Inject
    private UsuarioFacade usuarioFacade;
    @Inject
    private PerfilFacade perfilFacade;
    private Usuario usuario;    
    private ConverterGenerico converterPerfil;

    public List<Perfil> listaPerfis(){
        return perfilFacade.listaTodos();
    }
    
    public ConverterGenerico getConverterPerfil() {
        if(converterPerfil == null){
            converterPerfil = new ConverterGenerico(perfilFacade);
        }
        return converterPerfil;
    }

    public void setConverterPerfil(ConverterGenerico converterPerfil) {
        this.converterPerfil = converterPerfil;
    }

    public void novo() {
        usuario = new Usuario();
    }

    public void salvar() {
        if (!usuario.getSenha().equals(usuario.getConfirmasenha())) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "As senhas são diferente!",
                            null));
        } else {
            usuarioFacade.salvar(usuario);
        }
    }

    public void excluir(Usuario e) {
        usuarioFacade.excluir(e);
    }

    public void editar(Usuario e) {
        usuario = e;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        return usuarioFacade.listaTodos();
    }

}

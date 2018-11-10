package controller;

import DAO.UsuariosDAO;
import Util.Exibir;
import Util.Gerar;
import Util.JavaMailApp;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @Autor Winder Rezende
 * @Data 28/10/2018, 01:58:19
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private String usuario;
    private String senha;
    private String nomeUsr;
    private String novaSenha;
    private String novaSenhaConf;
    private String email;
    private boolean sessao = false;
    public int cont=0;
    
    public String efetuarLogin() {
        UsuariosDAO login = new UsuariosDAO();

        if (login.verificaUsuarioSenha(usuario, senha)) {
            sessao = true;
            nomeUsr = login.obterLogin(usuario);
            senha = null;
            return "index";
        } else if(cont != 3) {
            senha = null;
            cont++;
            return "login";
        } else {
            senha = null;
            cont = 0;
            return "recuperar";
        }
    }
    
    public String soliictarSenha() {
        UsuariosDAO login = new UsuariosDAO();

        if (login.verificaUsuarioEmail(usuario, email)) {
            String senhaGerada = Gerar.Senha();
            JavaMailApp enviar = new JavaMailApp();
            enviar.enviarEmail(email, senhaGerada);
            login.alterarSenha(usuario, senhaGerada);
            Exibir.Mensagem("Solicitação enviada! Verifique sua caixa de e-mail!");
            usuario = null;
            email = null;
            return "recuperar";
        } else {
            senha = null;
            return "recuperar";
        }
    }

    public String logOff() {
        sessao = false;
        senha = null;
        return "login";
    }

    public String paginaAlterar() {
        UsuariosDAO login = new UsuariosDAO();
        novaSenha = null;
        novaSenhaConf = null;
        if (login.verificaUsuarioSenha(usuario, senha)) {
            sessao = true;
            return "alterar";
        } else {
            sessao = false;
            return "login";
        }
    }

    public String alterarSenha() {
        UsuariosDAO login = new UsuariosDAO();
        if (login.verificaUsuarioSenha(usuario, senha)) {
            if (novaSenha.equals(novaSenhaConf) && !novaSenha.equals("")) {
                login.alterarSenha(usuario, novaSenha);
                senha = null;
                sessao = false;
                Exibir.Mensagem("A senha foi alterada!");
                return "login";
            } else {
                Exibir.Mensagem("A nova senha e a confirmação devem ser iguais!");
                return "alterar";
            }
        } else {
            Exibir.Mensagem("Usuário ou senha inválidos!");
            return "alterar";
        }
    }
    
    public String recuperarSenha() {
        sessao = false;
        senha = null;
        return "recuperar";
    }

    //Getters e Seters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUsr() {
        return nomeUsr;
    }

    public void setNomeUsr(String nomeUsr) {
        this.nomeUsr = nomeUsr;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getNovaSenhaConf() {
        return novaSenhaConf;
    }

    public void setNovaSenhaConf(String novaSenhaConf) {
        this.novaSenhaConf = novaSenhaConf;
    }

    public boolean isSessao() {
        return sessao;
    }

    public void setSessao(boolean sessao) {
        this.sessao = sessao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

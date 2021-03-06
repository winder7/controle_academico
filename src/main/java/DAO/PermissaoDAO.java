package DAO;

import Util.Exibir;
import entities.Permissao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Autor Winder Rezende
 * @Data 15/11/2018
 */
public class PermissaoDAO {

    public void inserirPermissao(String nomePermissao) {

        String SQL = "INSERT INTO Permissao(nome, admin, diret, coord, func, aluno) VALUES (?, false, false, false, false, false)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, nomePermissao);
            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Permissão inserida com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir Permissão: " + ex);
        }
    }

    public Map<String, Boolean> obterPermissoesSessao(String tpUsr) {

        switch (tpUsr) {
            case "Administrador":
                tpUsr = "admin";
                break;
            case "Diretor":
                tpUsr = "diret";
                break;
            case "Coordenador":
                tpUsr = "coord";
                break;
            case "Funcionário":
                tpUsr = "func";
                break;
            case "Aluno":
                tpUsr = "aluno";
                break;
            default:
                break;
        }
        
        System.out.println("Tipo de Usuário: " + tpUsr);

        Map<String, Boolean> permissao = new LinkedHashMap<>();

        String SQL = "SELECT nome, " + tpUsr + " FROM permissao";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    permissao.put(rs.getString("nome"), rs.getBoolean(tpUsr));
                    //System.out.println(rs.getString("nome"));
                }
                pstm.close();
            }
            System.out.println("Permissões da sessão obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter as permissões da sessao!: \n" + ex);
        }
        return permissao;
    }

    public ArrayList<Permissao> obterPermissoes() {

        ArrayList<Permissao> permissoes = new ArrayList<>();

        String SQL = "SELECT * FROM permissao ORDER BY nome";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                boolean vazio = true;
                while (rs.next()) {
                    Permissao perm = new Permissao(
                            rs.getString("nome"),
                            rs.getBoolean("admin"),
                            rs.getBoolean("diret"),
                            rs.getBoolean("coord"),
                            rs.getBoolean("func"),
                            rs.getBoolean("aluno")
                    );
                    permissoes.add(perm);
                    vazio = false;
                }
                if (vazio) {
                    Exibir.Mensagem("Não há permissões");
                    inserirPermissoesPadrao();
                }
                pstm.close();
            }
            System.out.println("Permissões obtidas com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Permissões!: \n" + ex);
        }
        return permissoes;
    }

    public void inserirPermissoesPadrao() {
        String SQL = "INSERT INTO Permissao(nome, admin, diret, coord, func, aluno) VALUES \n"
                + "	('1 - TODAS', false, false, false, false, false),\n"
                + "	('2 - (MENU) CADASTRAR', false, false, false, false, false),\n"
                + "	('2.1 - Cadastrar Usuários', false, false, false, false, false),\n"
                + "	('2.2 - Cadastrar Funcionários', false, false, false, false, false),\n"
                + "	('2.3 - Cadastrar Cursos', false, false, false, false, false),\n"
                + "	('2.4 - Cadastrar Disciplinas', false, false, false, false, false),\n"
                + "	('2.5 - Cadastrar Alunos', false, false, false, false, false),\n"
                + "	('3 - (MENU) GERENCIAR ALUNOS', false, false, false, false, false),\n"
                + "	('3.1 - (Sub Menu) Matricular', false, false, false, false, false),\n"
                + "	('3.1.1 - Matricular Aluno no Curso', false, false, false, false, false),\n"
                + "	('3.1.2 - Matricular Aluno nas Disciplinas', false, false, false, false, false),\n"
                + "	('3.2 - Aprovação', false, false, false, false, false),\n"
                + "	('3.3 - Histórico Acadêmico', false, false, false, false, false),\n"
                + "	('4 - (MENU) RELATÓRIOS', false, false, false, false, false),\n"
                + "	('4.1 - (Sub Menu) Relação de Alunos', false, false, false, false, false),\n"
                + "	('4.1.1 - Matriculados', false, false, false, false, false),\n"
                + "	('4.1.2 - Poderão Colar Grau', false, false, false, false, false),\n"
                + "	('4.1.3 - Podem Colar Grau', false, false, false, false, false),\n"
                + "	('4.2 - (Sub Menu) Gerenciais', false, false, false, false, false),\n"
                + "	('4.2.1 - Relação de Usuários do Sistema', false, false, false, false, false),\n"
                + "	('4.2.2 - Relação de Funcionários', false, false, false, false, false),\n"
                + "	('5 - (MENU) PORTAL DO ALUNO', false, false, false, false, false)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            Exibir.Mensagem("Permissões padrão inseridas com sucesso! (Recarregue a pagina!)");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir permissões padrão: " + ex);
        }
    }

    public void alterarPermissao(String nomeAcesso, String omeAcessAnterior) {

        String SQL = "UPDATE permissao SET nome = ? WHERE nome = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, nomeAcesso);
            pstm.setString(2, omeAcessAnterior);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Permissão Alterada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Permissão!:\n" + ex);
        }
    }

    public void alterarPermissaoUsr(String usr, boolean situacao, String nomeAcesso) {
        String SQL = "";
        if (nomeAcesso.equals("1 - TODAS")) {
            SQL = "UPDATE permissao SET " + usr + " = ?";
        }
        else{
            SQL = "UPDATE permissao SET " + usr + " = ? WHERE nome = ?";
        }

        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setBoolean(1, situacao);
            
            if (!nomeAcesso.equals("1 - TODAS")) {
                pstm.setString(2, nomeAcesso);
            }
            
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Permissão Alterada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar Permissão!:\n" + ex);
        }
    }

    public void apagarPermissao(String nomeAcesso) {

        String SQL = "DELETE FROM permissao WHERE nome = (?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, nomeAcesso);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Permissão Apagada! ");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Apagar Permissão!:\n" + ex);
        }
    }
}

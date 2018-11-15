package DAO;

import Util.Exibir;
import entities.MatriculaDisciplina;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * @Autor Alexandre Almeida
 * @Data 31/10/2018
 */
public class MatriculaDisciplinaDAO {

    public void inserirMatriculaDisciplina(MatriculaDisciplina md) {
        String SQL = "INSERT INTO matriculadisciplina(conceito, semestre, ano, situacao, fk_disciplina_codigo, fk_aluno_cpf) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, md.getConceito());
            pstm.setString(2, md.getSemestre());
            pstm.setInt(3, md.getAno());
            pstm.setString(4, md.getSituacao());
            pstm.setString(5, md.getFk_Disciplina_codigo());
            pstm.setString(6, md.getFk_Aluno_cpf());

            pstm.execute();

            BD.getConexao().close();
            pstm.close();
            System.out.println("Inserido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao inserir MatriculaDisciplina: " + ex);
        }
    }

    public ArrayList<MatriculaDisciplina> obterMatriculaDisciplina() {

        ArrayList<MatriculaDisciplina> matdis = new ArrayList<>();

        String SQL = "SELECT * FROM matriculadisciplina";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    MatriculaDisciplina mat = new MatriculaDisciplina(
                            rs.getString("conceito"),
                            rs.getString("semestre"),
                            rs.getInt("ano"),
                            rs.getString("situacao"),
                            rs.getString("fk_disciplina_codigo"),
                            rs.getString("fk_aluno_cpf")
                    );
                    matdis.add(mat);
                }
                pstm.close();
            }
            System.out.println("Disciplinas obtidos com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao obter Disciplinas!: \n" + ex);
        }
        return matdis;
    }

    public void editarMatriculaDisciplina(MatriculaDisciplina md) {
        String SQL = "UPDATE matriculadisciplina SET conceito = ?, semestre = ?, ano = ?, situacao = ? WHERE fk_disciplina_codigo = ? AND fk_aluno_cpf = ? ";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, md.getConceito());
            pstm.setString(2, md.getSemestre());
            pstm.setInt(3, md.getAno());
            pstm.setString(4, md.getSituacao());
            pstm.setString(5, md.getFk_Disciplina_codigo());
            pstm.setString(6, md.getFk_Aluno_cpf());

            System.out.println(SQL);
            pstm.executeUpdate();

            pstm.close();
            BD.getConexao().close();
            System.out.println("Alteração efetuada!");
        } catch (Exception ex) {
            Exibir.Mensagem("Erro ao Alterar matriculaDisciplina!:\n" + ex);
        }
    }

    public void removerMatriculaDisciplina(MatriculaDisciplina md) {
        String SQL = "DELETE FROM matriculadisciplina WHERE fk_disciplina_codigo = ? AND fk_aluno_cpf = ?";
        try (PreparedStatement pstm = BD.getConexao().prepareStatement(SQL)) {
            pstm.setString(1, md.getFk_Disciplina_codigo());
            pstm.setString(2, md.getFk_Aluno_cpf());

            pstm.execute();

            BD.getConexao().close();
            System.out.println("Removido com sucesso!");
        } catch (Exception ex) {
            Exibir.Mensagem("\nErro ao remover MatriculaDisciplina: " + ex);
        }
    }
}

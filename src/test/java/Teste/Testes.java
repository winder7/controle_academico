package Teste;


import DAO.DisciplinaDAO;
import DAO.FuncionarioDAO;
import DAO.UsuariosDAO;
import entities.Disciplina;
import entities.Funcionario;
import entities.Usuarios;

/**
 *
 * @author Alexandre Almeida
 */
public class Testes {
    public static void main(String[] args) {
//        UsuariosDAO us = new UsuariosDAO();
//        Usuarios user = new Usuarios();        
//        user.setLogin("Admin");
//        user.setSenha("1");
//        user.setTipo("admin");        
//        us.inserirUsuario(user);
        
 
        FuncionarioDAO f = new FuncionarioDAO();
        Funcionario func = new Funcionario();
        func.setNome("Administrador");
        func.setEmail("windergt@gmail.com");
        func.setTelefone("62982121212");
        func.setFk_Usuarios_id_user(1);
        f.inserirFuncionario(func);

       /*
        CursoDAO c = new CursoDAO();
        Curso curso = new Curso();
        curso.setCod(1);
        curso.setNome_curso("Dev");
        curso.setFk_Funcionario_id(2);
        c.inserirCurso(curso);
       */
       
        /*
        Date d = new Date(27031991);
        AlunoDAO a = new AlunoDAO();
        Aluno al = new Aluno();
        al.setCpf("03011498105");
        al.setNome("Alexandre");
        al.setData_nascimento(d);
        al.setSexo("M");
        al.setFoto("caminho da foto ou em base64");
        al.setEndereco("Rua aculá");
        al.setTelefone("623333333");
        a.inserirAluno(al);
        */
        
        /*
        DisciplinaDAO dis = new DisciplinaDAO();
        Disciplina d = new Disciplina();
        d.setCodigo("12345");
        d.setNome("GTI");
        d.setSituacao("Aberto");
        d.setFk_Curso_cod(1);
        dis.inserirDisciplina(d);*/
        
        
    }
}

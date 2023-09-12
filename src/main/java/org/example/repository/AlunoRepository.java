package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.connection.ConnectionDB.connect;

public class AlunoRepository {
    private Connection conn = connect();
    private PreparedStatement pstm;

    public AlunoRepository() {
    }

    public AlunoRepository(Connection conn) {
        this.conn = conn;
    }

    public boolean consultarAlunosMatriculadosEmUmCursoRspecifico(String nomecurso) {
        try {
            String SQL = "SELECT Alunos.nomealuno FROM Alunos " +
                    "INNER JOIN Matriculas ON Alunos.alunoId=Matriculas.id_aluno " +
                    "INNER JOIN Curso ON Curso.cursoId=Matriculas.id_curso " +
                    "WHERE Curso.nomecurso = ? ";
            pstm = conn.prepareStatement(SQL);
            pstm.setString(1, nomecurso);
            ResultSet rset = pstm.executeQuery();
            System.out.println("NOME DOS ALUNOS MATRICULADOS NO CURSO " + nomecurso.toUpperCase() + ": ");
            while (rset.next()) {
                System.out.println("\t" + rset.getString("nomealuno"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean consultarAlunosQueNaoEstaoMatriculadosEmNenhumCurso(){
        try{
            String SQL = "SELECT Alunos.nomealuno FROM Matriculas " +
                    "RIGHT JOIN Alunos ON Alunos.alunoid = Matriculas.id_aluno " +
                    "WHERE Matriculas.id_curso is NULL; ";
            pstm = conn.prepareStatement(SQL);
            ResultSet rset = pstm.executeQuery();

            System.out.println("NOME DOS ALUNOS QUE NÃO ESTÃO MATRICULADOS EM NENHUM CURSO ");
            while (rset.next()) {
                System.out.println("\t" + rset.getString("nomealuno"));
            }
            return true;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean consultarAlunosMatriculadosEmMaisDeUmCurso(){
        try{
            String SQL = "SELECT Alunos.nomealuno FROM Alunos " +
                    "INNER JOIN Matriculas M1 ON Alunos.alunoid = M1.id_aluno " +
                    "INNER JOIN Matriculas M2 ON Alunos.alunoid = M2.id_aluno " +
                    "WHERE M1.id_curso <> M2.id_curso; ";
            pstm = conn.prepareStatement(SQL);
            ResultSet rset = pstm.executeQuery();

            System.out.println("NOME DOS ALUNOS MATRICULADOS EM MAIS DE UM CURSO ");
            while (rset.next()) {
                System.out.println("\t" + rset.getString("nomealuno"));
            }
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}

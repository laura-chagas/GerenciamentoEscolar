package org.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.example.connection.ConnectionDB.connect;

public class CursoRepository {
    private Connection conn = connect();
    private PreparedStatement pstm;

    public CursoRepository() {
    }

    public CursoRepository(Connection conn) {
        this.conn = conn;
    }

    public boolean consultarCursosMinistradosPorUmProfessor(String nomeprofessor) {
        try {
            String SQL = "SELECT Curso.nomecurso FROM Curso " +
                    "LEFT JOIN Professor ON  Professor.professorid = Curso.cursoId " +
                    "WHERE Professor.nomeprofessor = ? ";
            pstm = conn.prepareStatement(SQL);
            pstm.setString(1, nomeprofessor);
            ResultSet rset = pstm.executeQuery();
            System.out.println("NOME DOS CURSOS MINISTRADOS POR " + nomeprofessor.toUpperCase() + ": ");
            while (rset.next()) {
                System.out.println("\t" + rset.getString("nomecurso"));
            }
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean consultarCursosSemAlunosMatriculados() {
        try {
            String SQL = "SELECT Curso.nomecurso FROM Curso " +
                    "FULL JOIN Matriculas ON Curso.cursoid = Matriculas.matriculaid " +
                    "WHERE Matriculas.id_aluno is NULL";
            pstm = conn.prepareStatement(SQL);
            ResultSet rset = pstm.executeQuery();

            System.out.println("NOME DOS CURSOS QUE NÃO POSSUEM ALUNOS MATRICULADOS");
            while (rset.next()) {
                System.out.println("\t" + rset.getString("nomecurso"));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }
}

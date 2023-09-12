package org.example.controller;

import org.example.model.ProfessorModel;
import org.example.repository.CursoRepository;

public class CursoController {
    ProfessorModel professorModel = new ProfessorModel();
    CursoRepository cursoRepository = new CursoRepository();

    public boolean consultarCursosMinistradosPorUmProfessor(String nomeprofessor){
        professorModel.setNomeprofessor(nomeprofessor);
        return  cursoRepository.consultarCursosMinistradosPorUmProfessor(professorModel.getNomeprofessor());
    }

    public boolean consultarCursosSemAlunosMatriculados(){
        return cursoRepository.consultarCursosSemAlunosMatriculados();
    }
}

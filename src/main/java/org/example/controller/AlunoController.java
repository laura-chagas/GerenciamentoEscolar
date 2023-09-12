package org.example.controller;

import org.example.model.CursoModel;
import org.example.repository.AlunoRepository;

public class AlunoController {
    AlunoRepository alunoRepository = new AlunoRepository();
    CursoModel cursoModel = new CursoModel();

    public boolean consultarAlunosMatriculadosEmUmCursoRspecifico(String nomecurso){
        cursoModel.setNomecurso(nomecurso);
        return alunoRepository.consultarAlunosMatriculadosEmUmCursoRspecifico(cursoModel.getNomecurso());
    }

    public boolean consultarAlunosQueNaoEstaoMatriculadosEmNenhumCurso(){
        return alunoRepository.consultarAlunosQueNaoEstaoMatriculadosEmNenhumCurso();
    }

    public boolean consultarAlunosMatriculadosEmMaisDeUmCurso(){
        return alunoRepository.consultarAlunosMatriculadosEmMaisDeUmCurso();
    }
}

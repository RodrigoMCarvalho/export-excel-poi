package com.rodrigo.exportexcel.repositoy;

import com.rodrigo.exportexcel.domain.Aluno;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlunoRepository {

    public List<Aluno> getAlunos () {
        return List.of(
                Aluno.builder().nome("Rodrigo").idade(14).serie("8 serie").build(),
                Aluno.builder().nome("Gustavo").idade(8).serie("3 serie").build(),
                Aluno.builder().nome("Raquel").idade(12).serie("6 serie").build());
    }
}

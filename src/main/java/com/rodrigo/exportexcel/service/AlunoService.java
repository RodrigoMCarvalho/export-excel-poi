package com.rodrigo.exportexcel.service;

import com.rodrigo.exportexcel.domain.Aluno;
import com.rodrigo.exportexcel.repositoy.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;
    private final ExportExcelService excelService;

    public ByteArrayInputStream exportarAlunosExcel() {
        return excelService.exportToExcel();
    }

    public List<Aluno> getAlunos () {
        return repository.getAlunos();
    }
}

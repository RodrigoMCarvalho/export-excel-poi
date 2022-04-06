package com.rodrigo.exportexcel.controller;

import com.rodrigo.exportexcel.domain.Aluno;
import com.rodrigo.exportexcel.service.AlunoService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("alunos")
@AllArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @GetMapping
    public ResponseEntity<List<Aluno>> getAlunos() {
        List<Aluno> alunos = service.getAlunos();

        return ResponseEntity.ok(alunos);
    }

    @GetMapping("export-excel")
    public ResponseEntity<InputStreamResource> exportExcel() {
        ByteArrayInputStream stream = service.exportarAlunosExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchment; filename=alunos.xls");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
    }
}

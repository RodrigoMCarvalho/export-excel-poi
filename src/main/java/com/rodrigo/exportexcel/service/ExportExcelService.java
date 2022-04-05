package com.rodrigo.exportexcel.service;

import com.rodrigo.exportexcel.domain.Aluno;
import com.rodrigo.exportexcel.repositoy.AlunoRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ExportExcelService {

    private final AlunoRepository repository;

    public ByteArrayInputStream exportToExcel() {
        String[] columns = {"Nome", "Idade", "Série"};

        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Sheet sheet = workbook.createSheet("Alunos");
        Row row = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }

        List<Aluno> alunos = repository.getAlunos();
        int initRow = 1;

        for (Aluno aluno : alunos) {
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(aluno.getNome());
            row.createCell(1).setCellValue(aluno.getIdade());
            row.createCell(2).setCellValue(aluno.getSerie());
            initRow++;
        }

        try {
            workbook.write(stream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(stream.toByteArray());
    }
}

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
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ExportExcelService {

    private final AlunoRepository repository;

    public ByteArrayInputStream exportToExcel() {

        //Usando reflection para obter os nomes dos atributos da classe
        String[] columns = Arrays.stream(Aluno.class.getDeclaredFields())
                .map(Field::getName)
                .map(l -> l.replaceFirst(l.substring(0,1), l.substring(0,1).toUpperCase()))
                .toArray(String[]::new);

        Workbook workbook = new HSSFWorkbook();

        //Setando o nome da aba da planilha
        Sheet sheet = workbook.createSheet("Alunos");
        Row row = sheet.createRow(0);

        //Setando o header das colunas
        for (int i = 0; i < columns.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }

        List<Aluno> alunos = repository.getAlunos();
        int initRow = 1;

        //Setando os valores da lista nas células da planilha
        for (Aluno aluno : alunos) {
            row = sheet.createRow(initRow);
            row.createCell(0).setCellValue(aluno.getNome());
            row.createCell(1).setCellValue(aluno.getIdade());
            row.createCell(2).setCellValue(aluno.getSerie());
            initRow++;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            workbook.write(stream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(stream.toByteArray());
    }
}

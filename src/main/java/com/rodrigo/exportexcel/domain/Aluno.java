package com.rodrigo.exportexcel.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Aluno {

    private String nome;
    private int idade;
    private String serie;
}

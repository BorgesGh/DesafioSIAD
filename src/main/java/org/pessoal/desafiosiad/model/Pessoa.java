package org.pessoal.desafiosiad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPessoa;

    private String nome;

    private Date dataNascimento;

    public Pessoa(String nome, Date dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa() {}
}

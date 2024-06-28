package org.pessoal.desafiosiad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEndereco;

    private String cep;

    private String cidade;

    private String bairro;

    private String rua;

    private String numero;

}

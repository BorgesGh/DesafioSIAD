package org.pessoal.desafiosiad.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.pessoal.desafiosiad.model.Venda;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    private String nome;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    private PessoaJuridica procedencia;

}

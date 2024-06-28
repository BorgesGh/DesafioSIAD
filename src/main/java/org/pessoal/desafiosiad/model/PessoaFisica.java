package org.pessoal.desafiosiad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "idPessoa")
public class PessoaFisica extends Pessoa {

    @CPF
    String cpf;

    @OneToMany
    private List<Endereco> enderecos;

    //Não deveria ser uma relação  1 . 1 ?


    public PessoaFisica(String nome, Date dataNascimento, String cpf, List<Endereco> enderecos) {
        super(nome, dataNascimento);
        this.cpf = cpf;
        this.enderecos = enderecos;
    }
    public PessoaFisica(){}

}

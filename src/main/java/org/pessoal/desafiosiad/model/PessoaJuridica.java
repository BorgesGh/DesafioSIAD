package org.pessoal.desafiosiad.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "idPessoa")
public class PessoaJuridica extends Pessoa {

    //@CNPJ
    private String cnpj;

    @OneToMany(mappedBy = "procedencia")
    private List<Produto> produtos;

    public PessoaJuridica(String nome, Date dataNascimento, String cnpj) {
        super(nome, dataNascimento);
        this.cnpj = cnpj;
    }
    public PessoaJuridica(int id, String nome, Date dataNascimento, String cnpj) {
        this(nome, dataNascimento, cnpj);
        this.setIdPessoa(id);

    }

    public PessoaJuridica() {}


}

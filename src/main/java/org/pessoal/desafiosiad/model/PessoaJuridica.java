package org.pessoal.desafiosiad.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "idPessoa")
public class PessoaJuridica extends Pessoa {

    //@CNPJ
    private String cnpj;

    @JsonManagedReference
    @OneToMany(mappedBy = "procedencia")
    private List<Produto> produtos;


}

package org.pessoal.desafiosiad.repository;

import org.pessoal.desafiosiad.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica,Integer> {

    //Adicionar operações do banco...

}

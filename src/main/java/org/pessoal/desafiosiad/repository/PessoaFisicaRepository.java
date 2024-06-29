package org.pessoal.desafiosiad.repository;

import org.pessoal.desafiosiad.model.PessoaFisica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends CrudRepository<PessoaFisica, Integer> {}

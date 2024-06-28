package org.pessoal.desafiosiad.repository;

import org.pessoal.desafiosiad.model.Venda;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends CrudRepository<Venda,Integer> {
}

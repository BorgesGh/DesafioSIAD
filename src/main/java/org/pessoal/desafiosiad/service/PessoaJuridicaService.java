package org.pessoal.desafiosiad.service;

import org.pessoal.desafiosiad.model.PessoaJuridica;
import org.pessoal.desafiosiad.repository.PessoaJuridicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository pJRepository;

    public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pJRepository = pessoaJuridicaRepository;
    }

    public void save(PessoaJuridica pJ) {
        //Adicionar regras de negocios...
        //Ex.: Idade > 18
        pJRepository.save(pJ);
    }

    public List<PessoaJuridica> findAll() {
        return pJRepository.findAll();
    }

}

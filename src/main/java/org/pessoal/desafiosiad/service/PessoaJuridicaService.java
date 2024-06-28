package org.pessoal.desafiosiad.service;

import org.pessoal.desafiosiad.model.PessoaJuridica;
import org.pessoal.desafiosiad.repository.PessoaJuridicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return (List<PessoaJuridica>) pJRepository.findAll();
    }

    public Optional<PessoaJuridica> findById(int id) {
        return pJRepository.findById(id);
    }

    public void delete(int id) throws Exception {
        Optional<PessoaJuridica> pJ = pJRepository.findById(id);
        if (pJ.isPresent()) {
            pJRepository.delete(pJ.get());
        }
        else {
            throw new Exception("Pessoa não encontrada!");
        }
    }

    public PessoaJuridica update(int id, PessoaJuridica pJAtualizado) {
        Optional<PessoaJuridica> oPj = pJRepository.findById(id);
        if(oPj.isPresent()) {
            PessoaJuridica pJ = oPj.get();
            pJ.setNome(pJAtualizado.getNome());
            pJ.setCnpj(pJAtualizado.getCnpj());
            pJ.setDataNascimento(pJAtualizado.getDataNascimento());

            pJRepository.save(pJ);

            return pJ;
        }
        return null;

    }



}

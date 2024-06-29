package org.pessoal.desafiosiad.service;

import org.pessoal.desafiosiad.exceptions.CNPJInvalidoException;
import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.PessoaJuridica;
import org.pessoal.desafiosiad.model.Produto;
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

    public void save(PessoaJuridica pJ)  {
        //Adicionar regras de negocios...
        //Ex.: Idade > 18
        pJRepository.save(pJ);
    }

    public List<PessoaJuridica> findAll() {
        return (List<PessoaJuridica>) pJRepository.findAll();
    }

    public void delete(int id) throws NotFindException {
        pJRepository.delete(this.findById(id));
    }

    public PessoaJuridica update(int id, PessoaJuridica pJAtualizado) throws NotFindException {

        PessoaJuridica pJ = this.findById(id);
        pJ.setNome(pJAtualizado.getNome());
        pJ.setCnpj(pJAtualizado.getCnpj());
        pJ.setDataNascimento(pJAtualizado.getDataNascimento());

        pJRepository.save(pJ);
        return pJ;
    }

    public PessoaJuridica findById(int id) throws NotFindException {
        Optional<PessoaJuridica> pj = pJRepository.findById(id);
        if (pj.isPresent()) {
            return pj.get();
        }
        else{
            throw new NotFindException("Pessoa Juridica não encontrada!");
        }
    }
}

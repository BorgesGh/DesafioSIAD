package org.pessoal.desafiosiad.service;

import org.pessoal.desafiosiad.exceptions.CPFInvalidoException;
import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.PessoaFisica;
import org.pessoal.desafiosiad.repository.PessoaFisicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService {

    private PessoaFisicaRepository pfRepository;
    private EnderecoService enderecoService;
    public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository, EnderecoService enderecoService) {
        this.pfRepository = pessoaFisicaRepository;
        this.enderecoService = enderecoService;
    }

    public void save(PessoaFisica pf) {
        //Adicionar regras de negocios...
        //Ex.: Idade > 18 e adicionar Exception disso
        enderecoService.saveAll(pf.getEnderecos());
        pfRepository.save(pf);
    }

    public List<PessoaFisica> findAll() {
        return (List<PessoaFisica>) pfRepository.findAll();
    }

    public void delete(int id) throws NotFindException {
        pfRepository.delete(this.findById(id));
    }

    public PessoaFisica update(int id, PessoaFisica pfAtualizado) throws NotFindException, CPFInvalidoException {
        PessoaFisica pf = this.findById(id);

        pf.setNome(pfAtualizado.getNome());
        pf.setCpf(pfAtualizado.getCpf());
        pf.setDataNascimento(pfAtualizado.getDataNascimento());
        pf.setEnderecos(enderecoService.updateOrAddAll(pfAtualizado.getEnderecos()));

        pfRepository.save(pf);
        return pf;
    }

    public PessoaFisica findById(int id) throws NotFindException {

        Optional<PessoaFisica> pf = pfRepository.findById(id);
        if (pf.isPresent()) {
            return pf.get();
        }
        throw new NotFindException("Pessoa Fisica não encontrada!!");
    }





}
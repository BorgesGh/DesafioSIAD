package org.pessoal.desafiosiad.service;

import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.Endereco;
import org.pessoal.desafiosiad.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    EnderecoRepository enderecoRepository;
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public void saveAll(List<Endereco> enderecos) {
        enderecoRepository.saveAll(enderecos);
    }

    public List<Endereco> findAll() {
        return (List<Endereco>) enderecoRepository.findAll();
    }

    public Endereco update(int id, Endereco endereco) throws NotFindException {

        Endereco e =this.findById(id);
        e.setCidade(endereco.getCidade());
        e.setBairro(endereco.getBairro());
        e.setRua(endereco.getRua());
        e.setNumero(endereco.getNumero());
        e.setCep(endereco.getCep());

        return e;
    }

    public void delete(int id) throws NotFindException {
        enderecoRepository.delete(this.findById(id));
    }

    public Endereco findById(int id) throws NotFindException {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()) {
            return endereco.get();
        }
        throw new NotFindException("Endereço não encontrado!");
    }


    public List<Endereco> updateOrAddAll(List<Endereco> enderecos) {

        List<Endereco> newEnderecos = new ArrayList<>();

        for (Endereco enderecoAtualizado : enderecos) {
            try{
                Endereco novo = this.findById(enderecoAtualizado.getIdEndereco());
                newEnderecos.add(this.update(novo.getIdEndereco(), enderecoAtualizado));

            }catch (NotFindException e) {
                newEnderecos.add(this.save(enderecoAtualizado));
            }

        }
        return newEnderecos;
    }





}

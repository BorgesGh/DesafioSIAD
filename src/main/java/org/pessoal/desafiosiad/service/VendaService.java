package org.pessoal.desafiosiad.service;

import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.Venda;
import org.pessoal.desafiosiad.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private VendaRepository vendaRepository;
    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    public List<Venda> listar() {
        return (List<Venda>) vendaRepository.findAll();
    }

    public void salvar(Venda venda) {
        venda.calcularTotal();
        vendaRepository.save(venda);
    }

    public void delete(int id) throws NotFindException {
        vendaRepository.delete(this.findById(id));
    }

    public Venda update(int id, Venda venda) throws NotFindException {

        Venda v = this.findById(id);

        v.setProduto(venda.getProduto());
        v.setQuantidade(venda.getQuantidade());
        v.setPessoaFisica(venda.getPessoaFisica());
        v.calcularTotal();

        return vendaRepository.save(v);

    }

    public Venda findById(int id)throws NotFindException {
        Optional<Venda> venda = vendaRepository.findById(id);
        if(venda.isPresent()) {
            return venda.get();
        }
        throw new NotFindException("Venda não encontrada");
    }


}

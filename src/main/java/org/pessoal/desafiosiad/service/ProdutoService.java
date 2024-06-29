package org.pessoal.desafiosiad.service;

import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.Produto;
import org.pessoal.desafiosiad.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() {
        return (List<Produto>) produtoRepository.findAll();
    }

    public void save(Produto produto) {
        produtoRepository.save(produto);
    }

    public void delete(int id) throws NotFindException {
        produtoRepository.delete(this.findById(id));
    }

    public Produto update(int id, Produto produtoAtualizado) throws NotFindException {
        Produto p = this.findById(id);

        p.setNome(produtoAtualizado.getNome());
        p.setValor(produtoAtualizado.getValor());
        p.setProcedencia(produtoAtualizado.getProcedencia());
        return produtoRepository.save(p);

    }

    public Produto findById(int id) throws NotFindException {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return produto.get();
        }
        else{
            throw new NotFindException("Produto não encontrado!");
        }
    }
}

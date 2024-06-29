package org.pessoal.desafiosiad.controller;


import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.Produto;
import org.pessoal.desafiosiad.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/list")
    public List<Produto> list() {
        return produtoService.findAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody Produto produto) {
        produtoService.save(produto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Produto> update(@PathVariable int id, @RequestBody Produto produto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.update(id,produto));

        } catch (NotFindException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) throws NotFindException {
        try {
            produtoService.delete(id);

        } catch (NotFindException e) {
            throw new NotFindException("Produto não encontrado");
        }
    }
}

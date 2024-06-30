package org.pessoal.desafiosiad.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.Produto;
import org.pessoal.desafiosiad.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@Tag(name = "Controller de Produtos", description = "Controller que contém todos os end-points de acesso para Produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(description = "Lista todos os produtos do BD.")
    @GetMapping("/list")
    public List<Produto> list() {
        return produtoService.findAll();
    }

    @Operation(description = "Salva um produto no BD.")
    @PostMapping("/save")
    public void save(@RequestBody Produto produto) {
        produtoService.save(produto);
    }

    @Operation(description = "Atualiza os dados de um produto.")
    @Parameter(name = "produto", description = "Envie o corpo, em JSON dos novos dados a serem escritos no produto de ID desejado")
    @PutMapping("/update/{id}")
    public ResponseEntity<Produto> update(@PathVariable int id, @RequestBody Produto produto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.update(id,produto));

        } catch (NotFindException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) ;
        }
    }

    @Operation(description = "Deleta um produto caso exista.")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) throws NotFindException {
        try {
            produtoService.delete(id);

        } catch (NotFindException e) {
            throw new NotFindException("Produto não encontrado");
        }
    }
}

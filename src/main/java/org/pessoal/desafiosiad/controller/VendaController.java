package org.pessoal.desafiosiad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.Venda;
import org.pessoal.desafiosiad.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venda")
@Tag(name = "Controller de Venda", description = "Controller que contém todos os end-points de acesso para Venda")
public class VendaController {

    private VendaService vendaService;
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @Operation(description = "Lista todas as vendas realizadas.")
    @GetMapping("/list")
    public ResponseEntity<List<Venda>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.listar());
    }

    @Operation(description = "Salva uma venda no BD.")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Venda venda) {
        vendaService.salvar(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body("Salvo com sucesso!");
    }

    @Operation(description = "Atualiza uma venda no BD. O calculo de total é realizado.")
    @PutMapping("/update/{id}")
    public ResponseEntity<Venda> update(@PathVariable int id,@RequestBody Venda venda) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(vendaService.update(id,venda));

        } catch (NotFindException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(description = "Deleta uma venda. Caso exista")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@PathVariable int id) {
        try {
            vendaService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deletado com sucesso!");
        } catch (NotFindException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada!");
        }
    }
}

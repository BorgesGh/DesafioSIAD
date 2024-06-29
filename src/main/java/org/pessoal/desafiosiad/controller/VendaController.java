package org.pessoal.desafiosiad.controller;

import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.Venda;
import org.pessoal.desafiosiad.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venda")
public class VendaController {

    private VendaService vendaService;
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Venda>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(vendaService.listar());
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Venda venda) {
        vendaService.salvar(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body("Salvo com sucesso!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Venda> update(@PathVariable int id,@RequestBody Venda venda) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(vendaService.update(id,venda));

        } catch (NotFindException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

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

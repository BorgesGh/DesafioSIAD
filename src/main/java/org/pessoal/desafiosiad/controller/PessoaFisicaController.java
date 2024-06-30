package org.pessoal.desafiosiad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import org.pessoal.desafiosiad.exceptions.CPFInvalidoException;
import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.PessoaFisica;
import org.pessoal.desafiosiad.service.PessoaFisicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pf")
@Tag(name = "Controller de Pessoa Fisica", description = "Controller que contém todos os end-points de acesso para PF")
public class PessoaFisicaController {

    private final PessoaFisicaService pfService;

    public PessoaFisicaController(PessoaFisicaService pfService) {
        this.pfService = pfService;
    }

    @Operation(description = "Salva uma pessoa fisica no BD. Acessando os dados do ResponseBody")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PessoaFisica pf){
        try {
            pfService.save(pf);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cpf Inválido!!");
        }
    }

    @Operation(description = "Lista todas as pessoa Fisicas do BD.")
    @GetMapping("/list")
    public ResponseEntity<List<PessoaFisica>> list() {
        List<PessoaFisica> pfs = pfService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pfs);
    }

    @Operation(description = "Atualizad uma pessoa Fisica")
    @Parameter(description = "Para adicionar um novo endereço basta mandar o JSON de pessoa física mais um novo endereço ou um endereço atualizado")
    @PutMapping("/update/{id}")
    public ResponseEntity<PessoaFisica> update(@PathVariable int id, @RequestBody PessoaFisica pf) throws CPFInvalidoException, NotFindException {

        try{
            PessoaFisica pfAtualizada = pfService.update(id,pf);
            return ResponseEntity.status(HttpStatus.OK).body(pfAtualizada);

        }catch (ConstraintViolationException e) {
            throw new CPFInvalidoException();
        }
    }

    @Operation(description = "Deleta uma pessoa fisica, caso existir")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id)  {

        try {
            pfService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
        } catch (NotFindException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }
}

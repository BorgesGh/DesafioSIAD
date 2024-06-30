package org.pessoal.desafiosiad.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolationException;
import org.pessoal.desafiosiad.exceptions.CNPJInvalidoException;
import org.pessoal.desafiosiad.exceptions.NotFindException;
import org.pessoal.desafiosiad.model.PessoaJuridica;
import org.pessoal.desafiosiad.service.PessoaJuridicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/pj")
@Tag(name = "Controller de Pessoa Jurídica", description = "Controller que contém todos os end-points de acesso para Pessoa Jurídica")
public class PessoaJuridicaController {

    private PessoaJuridicaService pjService;

    public PessoaJuridicaController(PessoaJuridicaService pjService) {
        this.pjService = pjService;
    }

    @Operation(description = "Salva uma pessoa Jurídica no Banco de Dados")
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PessoaJuridica pj) throws CNPJInvalidoException {
        try {
            pjService.save(pj);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("CNPJ Inválido");
        }
    }

    @Operation(description = "Lista todas as Pessoas juridicas do BD")
    @GetMapping("/list")
    public ResponseEntity<List<PessoaJuridica>> list() {
        List<PessoaJuridica> pjs = pjService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pjs);
    }

    @Operation(description = "Atualiza um pessoa jurídica")
    @Parameter(description = "Passe o id que deseja alterar e os novos dados")
    @PutMapping("/update/{id}")
    public ResponseEntity<PessoaJuridica> update(@PathVariable int id, @RequestBody PessoaJuridica pj) throws NotFindException, CNPJInvalidoException {

        try{
            PessoaJuridica pjAtualizada = pjService.update(id,pj);
            return ResponseEntity.status(HttpStatus.OK).body(pjAtualizada);

        }catch (ConstraintViolationException e) {
            throw new CNPJInvalidoException();

        }
    }

    @Operation(description = "Deleta uma pessoa juridica. Caso exista")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PessoaJuridica> delete(@PathVariable int id) throws NotFindException {

        try{
            pjService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (Exception e){
            throw new NotFindException("Pessoa Juridica não encontrada!");
        }
    }
}

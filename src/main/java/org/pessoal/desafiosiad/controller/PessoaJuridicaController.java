package org.pessoal.desafiosiad.controller;

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
public class PessoaJuridicaController {

    private PessoaJuridicaService pjService;

    public PessoaJuridicaController(PessoaJuridicaService pjService) {
        this.pjService = pjService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PessoaJuridica pj) throws CNPJInvalidoException {
        try {
            pjService.save(pj);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("CNPJ Inválido");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<PessoaJuridica>> list() {
        List<PessoaJuridica> pjs = pjService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pjs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PessoaJuridica> update(@PathVariable int id, @RequestBody PessoaJuridica pj) throws NotFindException, CNPJInvalidoException {

        try{
            PessoaJuridica pjAtualizada = pjService.update(id,pj);
            return ResponseEntity.status(HttpStatus.OK).body(pjAtualizada);

        }catch (ConstraintViolationException e) {
            throw new CNPJInvalidoException();

        }
    }

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

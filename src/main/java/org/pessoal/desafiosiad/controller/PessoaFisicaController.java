package org.pessoal.desafiosiad.controller;

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
public class PessoaFisicaController {

    private final PessoaFisicaService pfService;

    public PessoaFisicaController(PessoaFisicaService pfService) {
        this.pfService = pfService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PessoaFisica pf){
        try {
            pfService.save(pf);
            return ResponseEntity.status(HttpStatus.CREATED).build();

        }catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cpf Inválido!!");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<PessoaFisica>> list() {
        List<PessoaFisica> pfs = pfService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pfs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PessoaFisica> update(@PathVariable int id, @RequestBody PessoaFisica pf) throws CPFInvalidoException, NotFindException {

        try{
            PessoaFisica pfAtualizada = pfService.update(id,pf);
            return ResponseEntity.status(HttpStatus.OK).body(pfAtualizada);

        }catch (ConstraintViolationException e) {
            throw new CPFInvalidoException();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) throws NotFindException {
        pfService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");

    }
}

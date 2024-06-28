package org.pessoal.desafiosiad.controller;

import jakarta.validation.ConstraintViolationException;
import org.pessoal.desafiosiad.model.Pessoa;
import org.pessoal.desafiosiad.model.PessoaJuridica;
import org.pessoal.desafiosiad.service.PessoaJuridicaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/PessoaJuridica")
public class PessoaJuridicaController {

    private final PessoaJuridicaService pessoaJuridicaService;
    private PessoaJuridicaService pjService;

    public PessoaJuridicaController(PessoaJuridicaService pjService, PessoaJuridicaService pessoaJuridicaService) {
        this.pjService = pjService;
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @PostMapping("/save")
    public ResponseEntity<PessoaJuridica> savePessoaJuridica(@RequestBody PessoaJuridica pj) {
        try {
            pjService.save(pj);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<PessoaJuridica>> listPessoaJuridica() {
        List<PessoaJuridica> pjs = pjService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(pjs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PessoaJuridica> updatePJ(@PathVariable int id, @RequestBody PessoaJuridica pj) {

        try{
            PessoaJuridica pjAtualizada = pjService.update(id,pj);

            return ResponseEntity.status(HttpStatus.OK).body(pjAtualizada);
        }catch (ConstraintViolationException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PessoaJuridica> deletePJ(@PathVariable int id) {

        try{
            pjService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}

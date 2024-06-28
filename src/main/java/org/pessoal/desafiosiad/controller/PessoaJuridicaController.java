package org.pessoal.desafiosiad.controller;

import jakarta.validation.ConstraintViolationException;
import org.pessoal.desafiosiad.model.PessoaJuridica;
import org.pessoal.desafiosiad.service.PessoaJuridicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/PessoaJuridica")
public class PessoaJuridicaController {

    private PessoaJuridicaService pjService;

    public PessoaJuridicaController(PessoaJuridicaService pjService) {
        this.pjService = pjService;
    }

    @PostMapping("/save")
    public ResponseEntity savePessoaJuridica(@RequestBody PessoaJuridica pj) {
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

}

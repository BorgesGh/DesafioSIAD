package org.pessoal.desafiosiad.controller;

import org.pessoal.desafiosiad.model.Endereco;
import org.pessoal.desafiosiad.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/end")
public class EnderecoController {

    private final EnderecoService service;
    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Endereco>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }





}

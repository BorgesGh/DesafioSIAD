package org.pessoal.desafiosiad.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.pessoal.desafiosiad.model.Endereco;
import org.pessoal.desafiosiad.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
@OpenAPIDefinition(
        info = @Info(
                title = "Desafio Siad",
                version = "v1.0.0",
                description = "CRUD realizado para reconhecer a capacitação de inscritos",
                license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html"),
                contact = @Contact(name = "Desenvolvedor: Ghabriel Borges Campi", email = "ghabrielbc@gmail.com")
        )
)



@RestController
@RequestMapping("/api/end")
@Tag(name = "Controller de Endereço", description = "Controller que contém todos os end-points de acesso para Endereço")
public class EnderecoController {

    private final EnderecoService service;
    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @Operation(description = "Listagem de todos os endereços")
    @GetMapping("/list")
    public ResponseEntity<List<Endereco>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }


}

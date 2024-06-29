package org.pessoal.desafiosiad;

import org.pessoal.desafiosiad.model.*;
import org.pessoal.desafiosiad.service.PessoaFisicaService;
import org.pessoal.desafiosiad.service.PessoaJuridicaService;
import org.pessoal.desafiosiad.service.ProdutoService;
import org.pessoal.desafiosiad.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class DesafioSiadApplication {

    @Autowired
    private PessoaJuridicaService pjService;
    @Autowired
    private PessoaFisicaService pfService;
    @Autowired
    private ProdutoService proService;
    @Autowired
    private VendaService vService;

    public static void main(String[] args) {
        SpringApplication.run(DesafioSiadApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {

            //Inserção de dados para teste...

            //Pessoa Juridica
            PessoaJuridica pj1 = new PessoaJuridica();
            pj1.setNome("Carlos");
            pj1.setCnpj("00.000.000/0001-00");
            pj1.setDataNascimento(Date.valueOf("2000-01-01"));

            PessoaJuridica pj2 = new PessoaJuridica();
            pj2.setNome("Mauricio");
            pj2.setCnpj("00.550.220/0001-00");
            pj2.setDataNascimento(Date.valueOf("2000-01-20"));

            pjService.save(pj1);
            pjService.save(pj2);

            //Inserir Pessoa fisica e endereço
            PessoaFisica pf1 = new PessoaFisica();
            pf1.setNome("Pedro");
            pf1.setCpf("141.214.997-30");
            pf1.setDataNascimento(Date.valueOf("2000-01-01"));

            Endereco end1 = new Endereco();
            end1.setRua("Rua 1");
            end1.setBairro("Bairro 1");
            end1.setCidade("Estado 1");
            end1.setCep("1111");
            end1.setNumero("12");

            Endereco end2 = new Endereco();
            end2.setRua("Rua 2");
            end2.setBairro("Bairro 2");
            end2.setCidade("Estado 2");
            end2.setCep("22222");
            end2.setNumero("13");

            pf1.setEnderecos(Arrays.asList(end1,end2));

            pfService.save(pf1);

            //Inserir Produto e vendedor
            Produto p1 = new Produto();
            p1.setValor(22.70);
            p1.setNome("Laranja");
            p1.setProcedencia(pj1);

            proService.save(p1);

            //Inserir Venda
            Venda v1 = new Venda();
            v1.setProduto(p1);
            v1.setQuantidade(5);
            v1.setPessoaFisica(pf1);
            v1.calcularTotal();

            vService.salvar(v1);

        };
    }

}

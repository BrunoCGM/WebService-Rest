package com.webrest.webservice_rest.controller;

import com.webrest.webservice_rest.model.Empresa;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EmpresaController {

    private List<Empresa> empresas;

    public EmpresaController() {
        empresas = new ArrayList<>();
        empresas.add(new Empresa(1, "Empresa Alfa LTDA", "12.345.678/0001-90", "contato@empresa-alfa.com"));
        empresas.add(new Empresa(2, "Beta Comércio ME", "98.765.432/0001-10", "beta@comercio.com"));
        empresas.add(new Empresa(3, "Gamma Serviços S.A.", "11.222.333/0001-44", "servicos@gamma.com"));
        empresas.add(new Empresa(4, "Delta Engenharia", "22.333.444/0001-55", "contato@deltaeng.com"));
        empresas.add(new Empresa(5, "Epsilon Digital", "33.444.555/0001-66", "email@epsilondigital.com"));
    }

    @GetMapping("/empresas")
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    @GetMapping("/empresas/{id}")
    public Empresa getEmpresa(@PathVariable long id) {
        for (Empresa e : empresas) {
            if (id == e.getId()) {
                return e;
            }
        }
        return null;
    }

    @PostMapping("/empresas")
    public Empresa create(@RequestBody Empresa e) {
        long maior = 0;
        for (Empresa empresa : empresas) {
            if (empresa.getId() > maior) {
                maior = empresa.getId();
            }
        }
        e.setId(maior + 1);
        empresas.add(e);
        return e;
    }

    @PutMapping("/empresas/{id}")
    public Empresa update(@PathVariable long id, @RequestBody Empresa e) {
        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getId() == id) {
                e.setId(id); // Garante que o ID não mude na atualização
                empresas.set(i, e);
                return e;
            }
        }
        return null;
    }

    @DeleteMapping("/empresas/{id}")
    public void delete(@PathVariable long id) {
        for (int i = 0; i < empresas.size(); i++) {
            if (empresas.get(i).getId() == id) {
                empresas.remove(i);
                break;
            }
        }
    }
}
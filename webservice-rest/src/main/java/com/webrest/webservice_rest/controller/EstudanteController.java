package com.webrest.webservice_rest.controller;

import com.webrest.webservice_rest.model.Estudante;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EstudanteController {

    private List<Estudante> estudantes;

    public EstudanteController() {
        estudantes = new ArrayList<>();
        estudantes.add(new Estudante(1, "Ana Paula Souza", "ana.souza@email.com", LocalDate.parse("2002-03-15"), 2020));
        estudantes.add(new Estudante(2, "Carlos Henrique Lima", "carlos.lima@email.com", LocalDate.parse("2001-10-22"), 2019));
        estudantes.add(new Estudante(3, "Fernanda Oliveira", "fernanda.oliveira@email.com", LocalDate.parse("2003-07-05"), 2021));
        estudantes.add(new Estudante(4, "Lucas Pereira", "lucas.pereira@email.com", LocalDate.parse("2002-04-11"), 2020));
        estudantes.add(new Estudante(5, "Gabriela Martins", "gabriela.martins@email.com", LocalDate.parse("2001-12-25"), 2019));
        estudantes.add(new Estudante(6, "Rafael Costa", "rafael.costa@email.com", LocalDate.parse("2000-09-13"), 2018));
        estudantes.add(new Estudante(7, "Juliana Silva", "juliana.silva@email.com", LocalDate.parse("2002-06-18"), 2020));
        estudantes.add(new Estudante(8, "Marcos Vinícius", "marcos.vinicius@email.com", LocalDate.parse("2003-01-30"), 2021));
        estudantes.add(new Estudante(9, "Camila Azevedo", "camila.azevedo@email.com", LocalDate.parse("2001-11-08"), 2019));
        estudantes.add(new Estudante(10, "Felipe Cardoso", "felipe.cardoso@email.com", LocalDate.parse("2000-08-27"), 2018));
    }

    @GetMapping("/estudantes")
    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    @GetMapping("/estudantes/{id}")
    public Estudante getEstudante(@PathVariable long id) {
        for (Estudante e : estudantes) {
            if (id == e.getId()) {
                return e;
            }
        }
        return null;
    }

    @PostMapping("/estudantes")
    public Estudante create(@RequestBody Estudante e) {
        long maior = 0;
        for (Estudante estudante : estudantes) {
            if (estudante.getId() > maior) {
                maior = estudante.getId();
            }
        }
        e.setId(maior + 1);
        estudantes.add(e);
        return e;
    }

    @PutMapping("/estudantes/{id}")
    public Estudante update(@PathVariable long id, @RequestBody Estudante e) {
        for (int i = 0; i < estudantes.size(); i++) {
            if (estudantes.get(i).getId() == id) {
                e.setId(id);
                estudantes.set(i, e);
                return e;
            }
        }
        return null;
    }

    @DeleteMapping("/estudantes/{id}")
    public void delete(@PathVariable long id) {
        for (int i = 0; i < estudantes.size(); i++) {
            if (estudantes.get(i).getId() == id) {
                estudantes.remove(i);
                break;
            }
        }
    }
}
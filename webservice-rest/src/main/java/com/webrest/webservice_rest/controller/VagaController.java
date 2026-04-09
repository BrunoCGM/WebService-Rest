package com.webrest.webservice_rest.controller;

import com.webrest.webservice_rest.model.Vaga;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VagaController {

    private List<Vaga> vagas;

    public VagaController() {
        vagas = new ArrayList<>();
        vagas.add(new Vaga(1, "Desenvolvedor Java", "Atuação em projetos backend com Java e Spring. Experiência desejada em APIs REST.", LocalDate.parse("2025-10-01"), true, 1));
        vagas.add(new Vaga(2, "Analista de Suporte Técnico", "Suporte a clientes, resolução de chamados e participação em treinamentos internos.", LocalDate.parse("2025-09-27"), true, 2));
        vagas.add(new Vaga(3, "Engenheiro de Software", "Desenvolvimento de soluções para sistemas corporativos, integração e automação.", LocalDate.parse("2025-10-03"), false, 3));
        vagas.add(new Vaga(4, "Analista de Dados", "Manipulação e análise de grandes volumes de dados. Conhecimentos de SQL e Python.", LocalDate.parse("2025-09-18"), true, 4));
        vagas.add(new Vaga(5, "Designer Digital", "Criação de materiais gráficos, UX/UI e participação em campanhas de marketing.", LocalDate.parse("2025-09-30"), false, 5));
        vagas.add(new Vaga(6, "Consultor de Projetos", "Elaboração e acompanhamento de projetos empresariais e treinamentos.", LocalDate.parse("2025-10-06"), true, 1));
        vagas.add(new Vaga(7, "Programador Full Stack", "Desenvolvimento de aplicações web frontend e backend com foco em automação.", LocalDate.parse("2025-10-04"), true, 2));
    }

    @GetMapping("/vagas")
    public List<Vaga> getVagas() {
        return vagas;
    }

    @GetMapping("/vagas/{id}")
    public Vaga getVaga(@PathVariable long id) {
        for (Vaga v : vagas) {
            if (id == v.getId()) {
                return v;
            }
        }
        return null;
    }

    @PostMapping("/vagas")
    public Vaga create(@RequestBody Vaga v) {
        long maior = 0;
        for (Vaga vaga : vagas) {
            if (vaga.getId() > maior) {
                maior = vaga.getId();
            }
        }
        v.setId(maior + 1);
        vagas.add(v);
        return v;
    }

    @PutMapping("/vagas/{id}")
    public Vaga update(@PathVariable long id, @RequestBody Vaga v) {
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).getId() == id) {
                v.setId(id);
                vagas.set(i, v);
                return v;
            }
        }
        return null;
    }

    @DeleteMapping("/vagas/{id}")
    public void delete(@PathVariable long id) {
        for (int i = 0; i < vagas.size(); i++) {
            if (vagas.get(i).getId() == id) {
                vagas.remove(i);
                break;
            }
        }
    }
}
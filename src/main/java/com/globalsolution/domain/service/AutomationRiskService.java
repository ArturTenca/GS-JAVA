package com.globalsolution.domain.service;

import com.globalsolution.domain.exception.ProfissaoNaoEncontradaException;
import com.globalsolution.domain.model.Profissao;
import com.globalsolution.domain.model.Tarefa;
import com.globalsolution.domain.model.TrilhaDeAprendizagem;
import com.globalsolution.domain.repository.ProfissaoRepository;
import com.globalsolution.domain.repository.TarefaRepository;
import com.globalsolution.domain.repository.TrilhaDeAprendizagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutomationRiskService {

    @Autowired
    private ProfissaoRepository profissaoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> analisarRiscoAutomacao(String nomeProfissao) {
        Profissao profissao = profissaoRepository.findByNomeIgnoreCase(nomeProfissao)
                .orElseThrow(() -> new ProfissaoNaoEncontradaException(nomeProfissao));

        List<Tarefa> tarefas = tarefaRepository.findByProfissaoIdWithRelations(profissao.getId());

        if (tarefas.isEmpty()) {
            throw new RuntimeException("Nenhuma tarefa encontrada para esta profissão");
        }

        // Calcular score médio de automação
        double scoreMedio = tarefas.stream()
                .mapToInt(Tarefa::getRiscoAutomacao)
                .average()
                .orElse(0.0);

        // Classificar o risco
        String nivelRisco;
        String corRisco;
        if (scoreMedio >= 70) {
            nivelRisco = "ALTO RISCO";
            corRisco = "#dc143c";
        } else if (scoreMedio >= 40) {
            nivelRisco = "RISCO MODERADO";
            corRisco = "#ffa500";
        } else {
            nivelRisco = "BAIXO RISCO";
            corRisco = "#00ff00";
        }

        // Agrupar tarefas por nível de risco
        Map<String, List<Tarefa>> tarefasPorRisco = tarefas.stream()
                .collect(Collectors.groupingBy(t -> {
                    int risco = t.getRiscoAutomacao();
                    if (risco >= 70) return "ALTO";
                    if (risco >= 40) return "MODERADO";
                    return "BAIXO";
                }));

        // Recomendar trilhas baseado nas tarefas de alto risco
        List<TrilhaDeAprendizagem> trilhasRecomendadas = tarefas.stream()
                .filter(t -> t.getRiscoAutomacao() >= 70 && t.getTrilhaSugerida() != null)
                .map(Tarefa::getTrilhaSugerida)
                .distinct()
                .collect(Collectors.toList());

        // Se não houver trilhas específicas, recomendar trilhas gerais
        if (trilhasRecomendadas.isEmpty()) {
            // Buscar trilhas relacionadas a competências do futuro
            trilhasRecomendadas = buscarTrilhasGenericas();
        }

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("profissao", profissao);
        resultado.put("tarefas", tarefas);
        resultado.put("scoreMedio", Math.round(scoreMedio * 10.0) / 10.0);
        resultado.put("nivelRisco", nivelRisco);
        resultado.put("corRisco", corRisco);
        resultado.put("totalTarefas", tarefas.size());
        resultado.put("tarefasPorRisco", tarefasPorRisco);
        resultado.put("trilhasRecomendadas", trilhasRecomendadas);
        resultado.put("recomendacao", gerarRecomendacao(scoreMedio, nivelRisco));

        return resultado;
    }

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaRepository;

    private List<TrilhaDeAprendizagem> buscarTrilhasGenericas() {
        // Retornar trilhas relacionadas a requalificação
        return trilhaRepository.findAll();
    }

    private String gerarRecomendacao(double score, String nivelRisco) {
        if (score >= 70) {
            return "⚠️ ATENÇÃO: Sua profissão apresenta ALTO RISCO de automação. " +
                   "Recomendamos fortemente o início imediato de um processo de requalificação profissional. " +
                   "Considere as trilhas sugeridas abaixo para desenvolver novas competências.";
        } else if (score >= 40) {
            return "⚡ ATENÇÃO: Sua profissão apresenta RISCO MODERADO de automação. " +
                   "Algumas tarefas podem ser automatizadas nos próximos anos. " +
                   "Recomendamos começar a desenvolver competências complementares.";
        } else {
            return "✅ Sua profissão apresenta BAIXO RISCO de automação. " +
                   "No entanto, o aprendizado contínuo é sempre recomendado para manter-se atualizado no mercado.";
        }
    }

    public List<Profissao> listarTodas() {
        return profissaoRepository.findAll();
    }

    public List<String> buscarNomesProfissoes() {
        return profissaoRepository.findAll().stream()
                .map(Profissao::getNome)
                .sorted()
                .collect(Collectors.toList());
    }
}


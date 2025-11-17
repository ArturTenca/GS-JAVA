package com.globalsolution.domain.service;

import com.globalsolution.domain.model.CaminhoCarreira;
import com.globalsolution.domain.model.Habilidade;
import com.globalsolution.domain.model.ProfissaoFutura;
import com.globalsolution.domain.repository.CaminhoCarreiraRepository;
import com.globalsolution.domain.repository.ProfissaoFuturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CareerNavigatorService {

    @Autowired
    private CaminhoCarreiraRepository caminhoRepository;

    @Autowired
    private ProfissaoFuturaRepository profissaoFuturaRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> navegarCarreira(String profissaoAtual) {
        List<CaminhoCarreira> caminhos = caminhoRepository.findByProfissaoAtualWithRelations(profissaoAtual);

        if (caminhos.isEmpty()) {
            throw new RuntimeException("Nenhum caminho encontrado para a profissão: " + profissaoAtual);
        }

        // Agrupar caminhos por profissão futura
        Map<ProfissaoFutura, List<CaminhoCarreira>> caminhosPorProfissao = caminhos.stream()
                .collect(Collectors.groupingBy(CaminhoCarreira::getProfissaoFutura));

        // Separar habilidades por tipo
        Map<String, List<String>> habilidadesExigidas = new HashMap<>();
        Map<String, List<String>> habilidadesTransicao = new HashMap<>();
        Map<String, List<Object>> trilhasRecomendadas = new HashMap<>();

        caminhosPorProfissao.forEach((profissaoFutura, caminhosList) -> {
            Set<String> exigidas = new HashSet<>();
            Set<String> transicao = new HashSet<>();
            List<Object> trilhas = new ArrayList<>();

            caminhosList.forEach(caminho -> {
                if (caminho.getHabilidades() != null) {
                    caminho.getHabilidades().forEach(habilidade -> {
                        if ("EXIGIDA".equals(habilidade.getTipo())) {
                            exigidas.add(habilidade.getNome());
                        } else if ("TRANSICAO".equals(habilidade.getTipo())) {
                            transicao.add(habilidade.getNome());
                        }
                    });
                }
                if (caminho.getTrilha() != null) {
                    Map<String, Object> trilhaInfo = new HashMap<>();
                    trilhaInfo.put("id", caminho.getTrilha().getId());
                    trilhaInfo.put("nome", caminho.getTrilha().getNome());
                    trilhaInfo.put("descricao", caminho.getTrilha().getDescricao());
                    trilhaInfo.put("cargaHoraria", caminho.getTrilha().getCargaHoraria());
                    trilhaInfo.put("nivel", caminho.getTrilha().getNivel());
                    trilhas.add(trilhaInfo);
                }
            });

            String nomeProfissao = profissaoFutura.getNome();
            habilidadesExigidas.put(nomeProfissao, new ArrayList<>(exigidas));
            habilidadesTransicao.put(nomeProfissao, new ArrayList<>(transicao));
            trilhasRecomendadas.put(nomeProfissao, trilhas);
        });

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("profissaoAtual", profissaoAtual);
        resultado.put("profissoesFuturas", caminhosPorProfissao.keySet().stream()
                .map(pf -> {
                    Map<String, Object> info = new HashMap<>();
                    info.put("id", pf.getId());
                    info.put("nome", pf.getNome());
                    info.put("descricao", pf.getDescricao());
                    info.put("categoria", pf.getCategoria());
                    return info;
                })
                .collect(Collectors.toList()));
        resultado.put("habilidadesExigidas", habilidadesExigidas);
        resultado.put("habilidadesTransicao", habilidadesTransicao);
        resultado.put("trilhasRecomendadas", trilhasRecomendadas);
        resultado.put("totalCaminhos", caminhos.size());

        return resultado;
    }

    public List<ProfissaoFutura> listarTodasProfissoesFuturas() {
        return profissaoFuturaRepository.findAll();
    }

    public List<String> listarProfissoesAtuais() {
        return caminhoRepository.findDistinctProfissoesAtuais();
    }
}


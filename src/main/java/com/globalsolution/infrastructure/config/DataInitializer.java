package com.globalsolution.infrastructure.config;

import com.globalsolution.domain.model.*;
import com.globalsolution.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private TrilhaDeAprendizagemRepository trilhaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private com.globalsolution.domain.repository.ProfissaoRepository profissaoRepository;

    @Autowired
    private com.globalsolution.domain.repository.TarefaRepository tarefaRepository;

    @Autowired
    private com.globalsolution.domain.repository.ProfissaoFuturaRepository profissaoFuturaRepository;

    @Autowired
    private com.globalsolution.domain.repository.HabilidadeRepository habilidadeRepository;

    @Autowired
    private com.globalsolution.domain.repository.CaminhoCarreiraRepository caminhoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificar se já existem dados (para evitar duplicação)
        if (competenciaRepository.count() > 0) {
            System.out.println("✅ Dados já existem no banco. Pulando inicialização automática.");
            return;
        }

        // Criar Competências do Futuro
        Competencia ia = criarCompetencia("Inteligência Artificial", "Tecnológica",
                "Desenvolvimento e aplicação de sistemas de IA e Machine Learning");
        Competencia dados = criarCompetencia("Análise de Dados", "Tecnológica",
                "Coleta, processamento e análise de dados para tomada de decisão");
        Competencia empatia = criarCompetencia("Empatia e Inteligência Emocional", "Humana",
                "Capacidade de compreender e se conectar com outras pessoas");
        Competencia colaboracao = criarCompetencia("Colaboração e Trabalho em Equipe", "Humana",
                "Habilidades para trabalhar efetivamente em grupos diversos");
        Competencia cloud = criarCompetencia("Cloud Computing", "Tecnológica",
                "Conhecimento em serviços e infraestrutura em nuvem");
        Competencia comunicacao = criarCompetencia("Comunicação Efetiva", "Humana",
                "Habilidades de comunicação verbal e escrita");

        // Criar Trilhas de Aprendizagem
        TrilhaDeAprendizagem trilhaIA = criarTrilha(
                "Trilha de Inteligência Artificial para Iniciantes",
                "Aprenda os fundamentos de IA, Machine Learning e suas aplicações práticas no mercado de trabalho.",
                "INICIANTE",
                80,
                "Inteligência Artificial"
        );
        trilhaIA.setCompetencias(Arrays.asList(ia, dados, cloud));

        TrilhaDeAprendizagem trilhaDados = criarTrilha(
                "Análise de Dados e Business Intelligence",
                "Domine ferramentas de análise de dados, visualização e storytelling com dados.",
                "INTERMEDIARIO",
                120,
                "Data Science"
        );
        trilhaDados.setCompetencias(Arrays.asList(dados, cloud));

        TrilhaDeAprendizagem trilhaSoftSkills = criarTrilha(
                "Competências Humanas para o Futuro do Trabalho",
                "Desenvolva habilidades interpessoais essenciais para o mercado de trabalho 2030+.",
                "INICIANTE",
                60,
                "Soft Skills"
        );
        trilhaSoftSkills.setCompetencias(Arrays.asList(empatia, colaboracao, comunicacao));

        TrilhaDeAprendizagem trilhaAvancadaIA = criarTrilha(
                "IA Avançada e Deep Learning",
                "Aprofunde seus conhecimentos em redes neurais, NLP e visão computacional.",
                "AVANCADO",
                200,
                "Inteligência Artificial"
        );
        trilhaAvancadaIA.setCompetencias(Arrays.asList(ia, dados, cloud));

        TrilhaDeAprendizagem trilhaHibrida = criarTrilha(
                "Tecnologia e Humanidade: O Equilíbrio Perfeito",
                "Combine competências técnicas e humanas para se destacar no mercado de trabalho.",
                "INTERMEDIARIO",
                150,
                "Desenvolvimento Integral"
        );
        trilhaHibrida.setCompetencias(Arrays.asList(ia, empatia, colaboracao, comunicacao));

        // Criar Usuários de Exemplo
        Usuario usuario1 = criarUsuario(
                "Ana Silva",
                "ana.silva@email.com",
                "Tecnologia da Informação",
                "Júnior"
        );

        Usuario usuario2 = criarUsuario(
                "Carlos Mendes",
                "carlos.mendes@email.com",
                "Marketing Digital",
                "Pleno"
        );

        Usuario usuario3 = criarUsuario(
                "Mariana Costa",
                "mariana.costa@email.com",
                "Recursos Humanos",
                "Sênior"
        );

        Usuario usuario4 = criarUsuario(
                "João Santos",
                "joao.santos@email.com",
                "Engenharia",
                "Júnior"
        );

        Usuario usuario5 = criarUsuario(
                "Patricia Lima",
                "patricia.lima@email.com",
                "Vendas",
                "Pleno"
        );

        // Criar Matrículas
        criarMatricula(usuario1, trilhaIA, "ATIVA");
        criarMatricula(usuario1, trilhaSoftSkills, "ATIVA");
        criarMatricula(usuario2, trilhaDados, "ATIVA");
        criarMatricula(usuario3, trilhaSoftSkills, "CONCLUIDA");
        criarMatricula(usuario3, trilhaHibrida, "ATIVA");
        criarMatricula(usuario4, trilhaIA, "ATIVA");
        criarMatricula(usuario5, trilhaDados, "ATIVA");

        // Criar Profissões e Tarefas para Análise de Risco de Automação
        criarDadosAutomacao(trilhaIA, trilhaDados, trilhaSoftSkills, trilhaHibrida, trilhaAvancadaIA);

        // Criar Navegador de Carreiras Emergentes
        criarDadosCarreirasFuturas(trilhaIA, trilhaDados, trilhaSoftSkills, trilhaHibrida, trilhaAvancadaIA);

        System.out.println("✅ Dados iniciais carregados com sucesso!");
    }

    private void criarDadosAutomacao(TrilhaDeAprendizagem trilhaIA, TrilhaDeAprendizagem trilhaDados,
                                     TrilhaDeAprendizagem trilhaSoftSkills, TrilhaDeAprendizagem trilhaHibrida,
                                     TrilhaDeAprendizagem trilhaAvancadaIA) {
        // Profissão: Operador de Telemarketing
        Profissao telemarketing = criarProfissao("Operador de Telemarketing",
                "Atendimento telefônico para vendas, suporte e cobrança");

        criarTarefa("Atender chamadas de clientes", "Realizar atendimento telefônico", 85, telemarketing, trilhaSoftSkills);
        criarTarefa("Registrar informações no sistema", "Inserir dados em sistemas CRM", 90, telemarketing, trilhaDados);
        criarTarefa("Seguir scripts de atendimento", "Aplicar roteiros pré-definidos", 95, telemarketing, trilhaSoftSkills);
        criarTarefa("Resolver problemas simples", "Solução de problemas básicos", 40, telemarketing, trilhaHibrida);

        // Profissão: Caixa de Supermercado
        Profissao caixa = criarProfissao("Caixa de Supermercado",
                "Operação de caixa registradora e atendimento ao cliente");

        criarTarefa("Passar produtos no caixa", "Escanear códigos de barras", 95, caixa, trilhaDados);
        criarTarefa("Receber pagamentos", "Processar transações financeiras", 80, caixa, trilhaDados);
        criarTarefa("Organizar produtos", "Arrumar mercadorias nas prateleiras", 60, caixa, null);
        criarTarefa("Atender dúvidas de clientes", "Prestar informações e suporte", 25, caixa, trilhaSoftSkills);

        // Profissão: Motorista de Aplicativo
        Profissao motorista = criarProfissao("Motorista de Aplicativo",
                "Transporte de passageiros através de plataformas digitais");

        criarTarefa("Navegar usando GPS", "Seguir rotas automatizadas", 70, motorista, trilhaDados);
        criarTarefa("Aceitar/rejeitar corridas", "Gerenciar solicitações via app", 85, motorista, trilhaDados);
        criarTarefa("Manter veículo limpo", "Cuidados com manutenção básica", 10, motorista, null);
        criarTarefa("Interagir com passageiros", "Comunicação e atendimento", 15, motorista, trilhaSoftSkills);

        // Profissão: Analista de Dados
        Profissao analistaDados = criarProfissao("Analista de Dados",
                "Análise e interpretação de dados para tomada de decisão");

        criarTarefa("Coletar dados de fontes diversas", "Agregação de informações", 30, analistaDados, trilhaDados);
        criarTarefa("Processar grandes volumes de dados", "ETL e transformação", 50, analistaDados, trilhaDados);
        criarTarefa("Criar relatórios automatizados", "Geração de dashboards", 60, analistaDados, trilhaDados);
        criarTarefa("Interpretar resultados e insights", "Análise crítica e estratégica", 20, analistaDados, trilhaHibrida);
        criarTarefa("Apresentar conclusões", "Comunicação de resultados", 15, analistaDados, trilhaSoftSkills);

        // Profissão: Desenvolvedor de Software
        Profissao desenvolvedor = criarProfissao("Desenvolvedor de Software",
                "Desenvolvimento de aplicações e sistemas");

        criarTarefa("Escrever código repetitivo", "Implementação de padrões", 40, desenvolvedor, trilhaIA);
        criarTarefa("Fazer testes automatizados", "Criação de testes unitários", 35, desenvolvedor, trilhaIA);
        criarTarefa("Debugar problemas complexos", "Solução de bugs e erros", 15, desenvolvedor, trilhaAvancadaIA);
        criarTarefa("Arquitetar soluções", "Design de sistemas e arquitetura", 10, desenvolvedor, trilhaAvancadaIA);
        criarTarefa("Colaborar em equipe", "Trabalho colaborativo e code review", 5, desenvolvedor, trilhaSoftSkills);

        // Profissão: Contador
        Profissao contador = criarProfissao("Contador",
                "Gestão contábil e financeira de empresas");

        criarTarefa("Lançar movimentações contábeis", "Registro de transações", 75, contador, trilhaDados);
        criarTarefa("Calcular impostos", "Cálculos fiscais e tributários", 65, contador, trilhaDados);
        criarTarefa("Gerar relatórios financeiros", "Elaboração de demonstrações", 55, contador, trilhaDados);
        criarTarefa("Consultoria fiscal", "Orientação estratégica tributária", 20, contador, trilhaHibrida);
        criarTarefa("Auditoria e análise", "Revisão e análise crítica", 15, contador, trilhaHibrida);
    }

    private void criarDadosCarreirasFuturas(TrilhaDeAprendizagem trilhaIA, TrilhaDeAprendizagem trilhaDados,
                                           TrilhaDeAprendizagem trilhaSoftSkills, TrilhaDeAprendizagem trilhaHibrida,
                                           TrilhaDeAprendizagem trilhaAvancadaIA) {
        // Criar Profissões do Futuro
        ProfissaoFutura aiTrainer = criarProfissaoFutura("AI Trainer",
                "Profissional responsável por treinar e otimizar sistemas de inteligência artificial, garantindo que aprendam corretamente e de forma ética.",
                "Tecnologia");

        ProfissaoFutura dataEthicist = criarProfissaoFutura("Data Ethicist",
                "Especialista em ética de dados, garantindo que o uso de informações seja responsável, transparente e respeite a privacidade.",
                "Tecnologia");

        ProfissaoFutura vrArchitect = criarProfissaoFutura("VR Architect",
                "Arquiteto de experiências em realidade virtual e aumentada, criando ambientes imersivos para trabalho, educação e entretenimento.",
                "Tecnologia");

        ProfissaoFutura humanAiCollaboration = criarProfissaoFutura("Especialista em Colaboração Humano-IA",
                "Facilita a integração entre equipes humanas e sistemas de IA, otimizando processos e melhorando resultados.",
                "Tecnologia");

        ProfissaoFutura sustainabilityManager = criarProfissaoFutura("Gestor de Sustentabilidade Digital",
                "Gerencia práticas sustentáveis em ambientes digitais, reduzindo impacto ambiental de tecnologias.",
                "Sustentabilidade");

        // Criar Habilidades
        Habilidade python = criarHabilidade("Python", "Programação em Python para IA e Machine Learning", "EXIGIDA");
        Habilidade machineLearning = criarHabilidade("Machine Learning", "Fundamentos e aplicações de ML", "EXIGIDA");
        Habilidade etica = criarHabilidade("Ética em IA", "Princípios éticos no desenvolvimento de sistemas de IA", "EXIGIDA");
        Habilidade privacidade = criarHabilidade("Privacidade de Dados", "LGPD, GDPR e proteção de dados", "EXIGIDA");
        Habilidade unity = criarHabilidade("Unity/Unreal Engine", "Desenvolvimento de ambientes VR/AR", "EXIGIDA");
        Habilidade design3d = criarHabilidade("Design 3D", "Modelagem e design de espaços virtuais", "EXIGIDA");
        Habilidade comunicacao = criarHabilidade("Comunicação Efetiva", "Habilidades de comunicação interpessoal", "TRANSICAO");
        Habilidade pensamentoCritico = criarHabilidade("Pensamento Crítico", "Análise e resolução de problemas complexos", "TRANSICAO");
        Habilidade colaboracao = criarHabilidade("Colaboração", "Trabalho em equipe multidisciplinar", "TRANSICAO");
        Habilidade adaptabilidade = criarHabilidade("Adaptabilidade", "Capacidade de se adaptar a mudanças rápidas", "TRANSICAO");

        // Criar Caminhos de Carreira
        // De Desenvolvedor de Software para AI Trainer
        criarCaminhoCarreira("Desenvolvedor de Software", aiTrainer, 
                Arrays.asList(python, machineLearning, pensamentoCritico), trilhaAvancadaIA);

        // De Analista de Dados para Data Ethicist
        criarCaminhoCarreira("Analista de Dados", dataEthicist,
                Arrays.asList(etica, privacidade, pensamentoCritico, comunicacao), trilhaHibrida);

        // De Desenvolvedor para VR Architect
        criarCaminhoCarreira("Desenvolvedor de Software", vrArchitect,
                Arrays.asList(unity, design3d, pensamentoCritico), trilhaIA);

        // De Operador de Telemarketing para Especialista em Colaboração Humano-IA
        criarCaminhoCarreira("Operador de Telemarketing", humanAiCollaboration,
                Arrays.asList(comunicacao, colaboracao, adaptabilidade, pensamentoCritico), trilhaSoftSkills);

        // De Contador para Gestor de Sustentabilidade Digital
        criarCaminhoCarreira("Contador", sustainabilityManager,
                Arrays.asList(pensamentoCritico, comunicacao, adaptabilidade), trilhaHibrida);

        // De Analista de Dados para AI Trainer
        criarCaminhoCarreira("Analista de Dados", aiTrainer,
                Arrays.asList(python, machineLearning, pensamentoCritico), trilhaAvancadaIA);
    }

    private ProfissaoFutura criarProfissaoFutura(String nome, String descricao, String categoria) {
        ProfissaoFutura profissao = new ProfissaoFutura();
        profissao.setNome(nome);
        profissao.setDescricao(descricao);
        profissao.setCategoria(categoria);
        return profissaoFuturaRepository.save(profissao);
    }

    private Habilidade criarHabilidade(String nome, String descricao, String tipo) {
        Habilidade habilidade = new Habilidade();
        habilidade.setNome(nome);
        habilidade.setDescricao(descricao);
        habilidade.setTipo(tipo);
        return habilidadeRepository.save(habilidade);
    }

    private CaminhoCarreira criarCaminhoCarreira(String profissaoAtual, ProfissaoFutura profissaoFutura,
                                                 List<Habilidade> habilidades, TrilhaDeAprendizagem trilha) {
        CaminhoCarreira caminho = new CaminhoCarreira();
        caminho.setProfissaoAtual(profissaoAtual);
        caminho.setProfissaoFutura(profissaoFutura);
        caminho.setHabilidades(habilidades);
        caminho.setTrilha(trilha);
        return caminhoRepository.save(caminho);
    }

    private Profissao criarProfissao(String nome, String descricao) {
        Profissao profissao = new Profissao();
        profissao.setNome(nome);
        profissao.setDescricao(descricao);
        return profissaoRepository.save(profissao);
    }

    private Tarefa criarTarefa(String nome, String descricao, Integer risco, Profissao profissao, TrilhaDeAprendizagem trilha) {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome(nome);
        tarefa.setDescricao(descricao);
        tarefa.setRiscoAutomacao(risco);
        tarefa.setProfissao(profissao);
        tarefa.setTrilhaSugerida(trilha);
        return tarefaRepository.save(tarefa);
    }

    private Competencia criarCompetencia(String nome, String categoria, String descricao) {
        Competencia competencia = new Competencia();
        competencia.setNome(nome);
        competencia.setCategoria(categoria);
        competencia.setDescricao(descricao);
        return competenciaRepository.save(competencia);
    }

    private TrilhaDeAprendizagem criarTrilha(String nome, String descricao, String nivel,
                                             Integer cargaHoraria, String focoPrincipal) {
        TrilhaDeAprendizagem trilha = new TrilhaDeAprendizagem();
        trilha.setNome(nome);
        trilha.setDescricao(descricao);
        trilha.setNivel(nivel);
        trilha.setCargaHoraria(cargaHoraria);
        trilha.setFocoPrincipal(focoPrincipal);
        return trilhaRepository.save(trilha);
    }

    private Usuario criarUsuario(String nome, String email, String areaAtuacao, String nivelCarreira) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setAreaAtuacao(areaAtuacao);
        usuario.setNivelCarreira(nivelCarreira);
        usuario.setDataCadastro(LocalDate.now());
        return usuarioRepository.save(usuario);
    }

    private Matricula criarMatricula(Usuario usuario, TrilhaDeAprendizagem trilha, String status) {
        Matricula matricula = new Matricula();
        matricula.setUsuario(usuario);
        matricula.setTrilha(trilha);
        matricula.setStatus(status);
        matricula.setDataInscricao(LocalDate.now());
        return matriculaRepository.save(matricula);
    }
}


-- ============================================
-- Global Solution 2025 - Dados Iniciais (Oracle)
-- ============================================

-- ============================================
-- COMPETÊNCIAS DO FUTURO
-- ============================================
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Inteligência Artificial', 'Tecnológica', 'Desenvolvimento e aplicação de sistemas de IA e Machine Learning');
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Análise de Dados', 'Tecnológica', 'Coleta, processamento e análise de dados para tomada de decisão');
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Empatia e Inteligência Emocional', 'Humana', 'Capacidade de compreender e se conectar com outras pessoas');
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Colaboração e Trabalho em Equipe', 'Humana', 'Habilidades para trabalhar efetivamente em grupos diversos');
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Cloud Computing', 'Tecnológica', 'Conhecimento em serviços e infraestrutura em nuvem');
INSERT INTO competencias (nome, categoria, descricao) VALUES ('Comunicação Efetiva', 'Humana', 'Habilidades de comunicação verbal e escrita');

-- ============================================
-- TRILHAS DE APRENDIZAGEM
-- ============================================
INSERT INTO trilhas (nome, descricao, nivel, carga_horaria, foco_principal) 
VALUES ('Trilha de Inteligência Artificial para Iniciantes', 
        'Aprenda os fundamentos de IA, Machine Learning e suas aplicações práticas no mercado de trabalho.', 
        'INICIANTE', 80, 'Inteligência Artificial');

INSERT INTO trilhas (nome, descricao, nivel, carga_horaria, foco_principal) 
VALUES ('Análise de Dados e Business Intelligence', 
        'Domine ferramentas de análise de dados, visualização e storytelling com dados.', 
        'INTERMEDIARIO', 120, 'Data Science');

INSERT INTO trilhas (nome, descricao, nivel, carga_horaria, foco_principal) 
VALUES ('Competências Humanas para o Futuro do Trabalho', 
        'Desenvolva habilidades interpessoais essenciais para o mercado de trabalho 2030+.', 
        'INICIANTE', 60, 'Soft Skills');

INSERT INTO trilhas (nome, descricao, nivel, carga_horaria, foco_principal) 
VALUES ('IA Avançada e Deep Learning', 
        'Aprofunde seus conhecimentos em redes neurais, NLP e visão computacional.', 
        'AVANCADO', 200, 'Inteligência Artificial');

INSERT INTO trilhas (nome, descricao, nivel, carga_horaria, foco_principal) 
VALUES ('Tecnologia e Humanidade: O Equilíbrio Perfeito', 
        'Combine competências técnicas e humanas para se destacar no mercado de trabalho.', 
        'INTERMEDIARIO', 150, 'Desenvolvimento Integral');

-- ============================================
-- RELACIONAR TRILHAS E COMPETÊNCIAS
-- ============================================
-- Trilha 1 (IA Iniciantes) com Competências 1, 2, 5
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (1, 1);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (1, 2);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (1, 5);

-- Trilha 2 (Dados) com Competências 2, 5
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (2, 2);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (2, 5);

-- Trilha 3 (Soft Skills) com Competências 3, 4, 6
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (3, 3);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (3, 4);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (3, 6);

-- Trilha 4 (IA Avançada) com Competências 1, 2, 5
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (4, 1);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (4, 2);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (4, 5);

-- Trilha 5 (Híbrida) com Competências 1, 3, 4, 6
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (5, 1);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (5, 3);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (5, 4);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (5, 6);

-- ============================================
-- USUÁRIOS DE EXEMPLO
-- ============================================
INSERT INTO usuarios (nome, email, area_atuacao, nivel_carreira, data_cadastro) 
VALUES ('Ana Silva', 'ana.silva@email.com', 'Tecnologia da Informação', 'Júnior', SYSDATE);

INSERT INTO usuarios (nome, email, area_atuacao, nivel_carreira, data_cadastro) 
VALUES ('Carlos Mendes', 'carlos.mendes@email.com', 'Marketing Digital', 'Pleno', SYSDATE);

INSERT INTO usuarios (nome, email, area_atuacao, nivel_carreira, data_cadastro) 
VALUES ('Mariana Costa', 'mariana.costa@email.com', 'Recursos Humanos', 'Sênior', SYSDATE);

INSERT INTO usuarios (nome, email, area_atuacao, nivel_carreira, data_cadastro) 
VALUES ('João Santos', 'joao.santos@email.com', 'Engenharia', 'Júnior', SYSDATE);

INSERT INTO usuarios (nome, email, area_atuacao, nivel_carreira, data_cadastro) 
VALUES ('Patricia Lima', 'patricia.lima@email.com', 'Vendas', 'Pleno', SYSDATE);

-- ============================================
-- MATRÍCULAS
-- ============================================
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (1, 1, SYSDATE, 'ATIVA');
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (1, 3, SYSDATE, 'ATIVA');
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (2, 2, SYSDATE, 'ATIVA');
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (3, 3, SYSDATE, 'CONCLUIDA');
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (3, 5, SYSDATE, 'ATIVA');
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (4, 1, SYSDATE, 'ATIVA');
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status) VALUES (5, 2, SYSDATE, 'ATIVA');

-- ============================================
-- PROFISSÕES (Análise de Risco)
-- ============================================
INSERT INTO profissoes (nome, descricao) VALUES ('Operador de Telemarketing', 'Atendimento telefônico para vendas, suporte e cobrança');
INSERT INTO profissoes (nome, descricao) VALUES ('Caixa de Supermercado', 'Operação de caixa registradora e atendimento ao cliente');
INSERT INTO profissoes (nome, descricao) VALUES ('Motorista de Aplicativo', 'Transporte de passageiros através de plataformas digitais');
INSERT INTO profissoes (nome, descricao) VALUES ('Analista de Dados', 'Análise e interpretação de dados para tomada de decisão');
INSERT INTO profissoes (nome, descricao) VALUES ('Desenvolvedor de Software', 'Desenvolvimento de aplicações e sistemas');
INSERT INTO profissoes (nome, descricao) VALUES ('Contador', 'Gestão contábil e financeira de empresas');

-- ============================================
-- TAREFAS (com Risco de Automação)
-- ============================================
-- Operador de Telemarketing
INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Atender chamadas de clientes', 'Realizar atendimento telefônico', 85, 1, 3);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Registrar informações no sistema', 'Inserir dados em sistemas CRM', 90, 1, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Seguir scripts de atendimento', 'Aplicar roteiros pré-definidos', 95, 1, 3);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Resolver problemas simples', 'Solução de problemas básicos', 40, 1, 5);

-- Caixa de Supermercado
INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Passar produtos no caixa', 'Escanear códigos de barras', 95, 2, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Receber pagamentos', 'Processar transações financeiras', 80, 2, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Organizar produtos', 'Arrumar mercadorias nas prateleiras', 60, 2, NULL);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Atender dúvidas de clientes', 'Prestar informações e suporte', 25, 2, 3);

-- Motorista de Aplicativo
INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Navegar usando GPS', 'Seguir rotas automatizadas', 70, 3, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Aceitar/rejeitar corridas', 'Gerenciar solicitações via app', 85, 3, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Manter veículo limpo', 'Cuidados com manutenção básica', 10, 3, NULL);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Interagir com passageiros', 'Comunicação e atendimento', 15, 3, 3);

-- Analista de Dados
INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Coletar dados de fontes diversas', 'Agregação de informações', 30, 4, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Processar grandes volumes de dados', 'ETL e transformação', 50, 4, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Criar relatórios automatizados', 'Geração de dashboards', 60, 4, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Interpretar resultados e insights', 'Análise crítica e estratégica', 20, 4, 5);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Apresentar conclusões', 'Comunicação de resultados', 15, 4, 3);

-- Desenvolvedor de Software
INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Escrever código repetitivo', 'Implementação de padrões', 40, 5, 1);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Fazer testes automatizados', 'Criação de testes unitários', 35, 5, 1);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Debugar problemas complexos', 'Solução de bugs e erros', 15, 5, 4);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Arquitetar soluções', 'Design de sistemas e arquitetura', 10, 5, 4);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Colaborar em equipe', 'Trabalho colaborativo e code review', 5, 5, 3);

-- Contador
INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Lançar movimentações contábeis', 'Registro de transações', 75, 6, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Calcular impostos', 'Cálculos fiscais e tributários', 65, 6, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Gerar relatórios financeiros', 'Elaboração de demonstrações', 55, 6, 2);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Consultoria fiscal', 'Orientação estratégica tributária', 20, 6, 5);

INSERT INTO tarefas (nome, descricao, risco_automacao, profissao_id, trilha_sugerida_id) 
VALUES ('Auditoria e análise', 'Revisão e análise crítica', 15, 6, 5);

-- ============================================
-- PROFISSÕES FUTURAS
-- ============================================
INSERT INTO profissoes_futuras (nome, descricao, categoria) 
VALUES ('AI Trainer', 
        'Profissional responsável por treinar e otimizar sistemas de inteligência artificial, garantindo que aprendam corretamente e de forma ética.', 
        'Tecnologia');

INSERT INTO profissoes_futuras (nome, descricao, categoria) 
VALUES ('Data Ethicist', 
        'Especialista em ética de dados, garantindo que o uso de informações seja responsável, transparente e respeite a privacidade.', 
        'Tecnologia');

INSERT INTO profissoes_futuras (nome, descricao, categoria) 
VALUES ('VR Architect', 
        'Arquiteto de experiências em realidade virtual e aumentada, criando ambientes imersivos para trabalho, educação e entretenimento.', 
        'Tecnologia');

INSERT INTO profissoes_futuras (nome, descricao, categoria) 
VALUES ('Especialista em Colaboração Humano-IA', 
        'Facilita a integração entre equipes humanas e sistemas de IA, otimizando processos e melhorando resultados.', 
        'Tecnologia');

INSERT INTO profissoes_futuras (nome, descricao, categoria) 
VALUES ('Gestor de Sustentabilidade Digital', 
        'Gerencia práticas sustentáveis em ambientes digitais, reduzindo impacto ambiental de tecnologias.', 
        'Sustentabilidade');

-- ============================================
-- HABILIDADES
-- ============================================
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Python', 'Programação em Python para IA e Machine Learning', 'EXIGIDA');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Machine Learning', 'Fundamentos e aplicações de ML', 'EXIGIDA');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Ética em IA', 'Princípios éticos no desenvolvimento de sistemas de IA', 'EXIGIDA');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Privacidade de Dados', 'LGPD, GDPR e proteção de dados', 'EXIGIDA');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Unity/Unreal Engine', 'Desenvolvimento de ambientes VR/AR', 'EXIGIDA');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Design 3D', 'Modelagem e design de espaços virtuais', 'EXIGIDA');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Comunicação Efetiva', 'Habilidades de comunicação interpessoal', 'TRANSICAO');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Pensamento Crítico', 'Análise e resolução de problemas complexos', 'TRANSICAO');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Colaboração', 'Trabalho em equipe multidisciplinar', 'TRANSICAO');
INSERT INTO habilidades (nome, descricao, tipo) VALUES ('Adaptabilidade', 'Capacidade de se adaptar a mudanças rápidas', 'TRANSICAO');

-- ============================================
-- CAMINHOS DE CARREIRA
-- ============================================
-- De Desenvolvedor de Software para AI Trainer
INSERT INTO caminhos_carreira (profissao_atual, profissao_futura_id, trilha_id) VALUES ('Desenvolvedor de Software', 1, 4);

-- De Analista de Dados para Data Ethicist
INSERT INTO caminhos_carreira (profissao_atual, profissao_futura_id, trilha_id) VALUES ('Analista de Dados', 2, 5);

-- De Desenvolvedor para VR Architect
INSERT INTO caminhos_carreira (profissao_atual, profissao_futura_id, trilha_id) VALUES ('Desenvolvedor de Software', 3, 1);

-- De Operador de Telemarketing para Especialista em Colaboração Humano-IA
INSERT INTO caminhos_carreira (profissao_atual, profissao_futura_id, trilha_id) VALUES ('Operador de Telemarketing', 4, 3);

-- De Contador para Gestor de Sustentabilidade Digital
INSERT INTO caminhos_carreira (profissao_atual, profissao_futura_id, trilha_id) VALUES ('Contador', 5, 5);

-- De Analista de Dados para AI Trainer
INSERT INTO caminhos_carreira (profissao_atual, profissao_futura_id, trilha_id) VALUES ('Analista de Dados', 1, 4);

-- ============================================
-- RELACIONAR CAMINHOS E HABILIDADES
-- ============================================
-- Caminho 1: Desenvolvedor -> AI Trainer (habilidades 1, 2, 8)
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (1, 1);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (1, 2);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (1, 8);

-- Caminho 2: Analista -> Data Ethicist (habilidades 3, 4, 8, 7)
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (2, 3);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (2, 4);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (2, 8);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (2, 7);

-- Caminho 3: Desenvolvedor -> VR Architect (habilidades 5, 6, 8)
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (3, 5);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (3, 6);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (3, 8);

-- Caminho 4: Telemarketing -> Colaboração Humano-IA (habilidades 7, 9, 10, 8)
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (4, 7);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (4, 9);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (4, 10);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (4, 8);

-- Caminho 5: Contador -> Sustentabilidade Digital (habilidades 8, 7, 10)
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (5, 8);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (5, 7);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (5, 10);

-- Caminho 6: Analista -> AI Trainer (habilidades 1, 2, 8)
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (6, 1);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (6, 2);
INSERT INTO caminho_habilidade (caminho_id, habilidade_id) VALUES (6, 8);

COMMIT;


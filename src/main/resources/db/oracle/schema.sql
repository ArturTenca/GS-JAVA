-- ============================================
-- Global Solution 2025 - Scripts Oracle SQL
-- Plataforma de Upskilling/Reskilling
-- ============================================

-- Criar usuário e conceder permissões (executar como SYS)
-- CREATE USER GLOBALSOLUTION IDENTIFIED BY GLOBALSOLUTION123;
-- GRANT CONNECT, RESOURCE, DBA TO GLOBALSOLUTION;
-- ALTER USER GLOBALSOLUTION QUOTA UNLIMITED ON USERS;
-- COMMIT;

-- Conectar como GLOBALSOLUTION antes de executar os scripts abaixo

-- ============================================
-- TABELA: USUARIOS
-- ============================================
CREATE TABLE usuarios (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    email VARCHAR2(150) NOT NULL UNIQUE,
    area_atuacao VARCHAR2(100),
    nivel_carreira VARCHAR2(50),
    data_cadastro DATE NOT NULL
);

COMMENT ON TABLE usuarios IS 'Tabela de usuários/profissionais da plataforma';
COMMENT ON COLUMN usuarios.id IS 'Identificador único do usuário';
COMMENT ON COLUMN usuarios.nome IS 'Nome completo do usuário';
COMMENT ON COLUMN usuarios.email IS 'Email único do usuário';
COMMENT ON COLUMN usuarios.area_atuacao IS 'Área de atuação profissional';
COMMENT ON COLUMN usuarios.nivel_carreira IS 'Nível de carreira (Júnior, Pleno, Sênior)';
COMMENT ON COLUMN usuarios.data_cadastro IS 'Data de cadastro na plataforma';

-- ============================================
-- TABELA: COMPETENCIAS
-- ============================================
CREATE TABLE competencias (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    categoria VARCHAR2(100),
    descricao CLOB
);

COMMENT ON TABLE competencias IS 'Competências do futuro (IA, dados, soft skills, etc.)';
COMMENT ON COLUMN competencias.id IS 'Identificador único da competência';
COMMENT ON COLUMN competencias.nome IS 'Nome da competência';
COMMENT ON COLUMN competencias.categoria IS 'Categoria da competência (Tecnológica, Humana)';
COMMENT ON COLUMN competencias.descricao IS 'Descrição detalhada da competência';

-- ============================================
-- TABELA: TRILHAS
-- ============================================
CREATE TABLE trilhas (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(150) NOT NULL,
    descricao CLOB,
    nivel VARCHAR2(50) NOT NULL,
    carga_horaria NUMBER(10) NOT NULL CHECK (carga_horaria >= 1),
    foco_principal VARCHAR2(100)
);

COMMENT ON TABLE trilhas IS 'Trilhas de aprendizagem disponíveis';
COMMENT ON COLUMN trilhas.id IS 'Identificador único da trilha';
COMMENT ON COLUMN trilhas.nome IS 'Nome da trilha de aprendizagem';
COMMENT ON COLUMN trilhas.descricao IS 'Descrição detalhada da trilha';
COMMENT ON COLUMN trilhas.nivel IS 'Nível: INICIANTE, INTERMEDIARIO, AVANCADO';
COMMENT ON COLUMN trilhas.carga_horaria IS 'Carga horária em horas';
COMMENT ON COLUMN trilhas.foco_principal IS 'Foco principal da trilha';

-- ============================================
-- TABELA: TRILHA_COMPETENCIA (N:N)
-- ============================================
CREATE TABLE trilha_competencia (
    trilha_id NUMBER(19) NOT NULL,
    competencia_id NUMBER(19) NOT NULL,
    PRIMARY KEY (trilha_id, competencia_id),
    CONSTRAINT fk_trilha_comp_trilha FOREIGN KEY (trilha_id) REFERENCES trilhas(id) ON DELETE CASCADE,
    CONSTRAINT fk_trilha_comp_comp FOREIGN KEY (competencia_id) REFERENCES competencias(id) ON DELETE CASCADE
);

COMMENT ON TABLE trilha_competencia IS 'Relação N:N entre trilhas e competências';

-- ============================================
-- TABELA: MATRICULAS
-- ============================================
CREATE TABLE matriculas (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    usuario_id NUMBER(19) NOT NULL,
    trilha_id NUMBER(19) NOT NULL,
    data_inscricao DATE NOT NULL,
    status VARCHAR2(50) NOT NULL,
    CONSTRAINT fk_matricula_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    CONSTRAINT fk_matricula_trilha FOREIGN KEY (trilha_id) REFERENCES trilhas(id) ON DELETE CASCADE
);

COMMENT ON TABLE matriculas IS 'Matrículas de usuários em trilhas';
COMMENT ON COLUMN matriculas.id IS 'Identificador único da matrícula';
COMMENT ON COLUMN matriculas.usuario_id IS 'ID do usuário matriculado';
COMMENT ON COLUMN matriculas.trilha_id IS 'ID da trilha';
COMMENT ON COLUMN matriculas.data_inscricao IS 'Data da inscrição';
COMMENT ON COLUMN matriculas.status IS 'Status: ATIVA, CONCLUIDA, CANCELADA';

-- ============================================
-- TABELA: PROFISSOES (para Análise de Risco)
-- ============================================
CREATE TABLE profissoes (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL UNIQUE,
    descricao CLOB
);

COMMENT ON TABLE profissoes IS 'Profissões para análise de risco de automação';

-- ============================================
-- TABELA: TAREFAS
-- ============================================
CREATE TABLE tarefas (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(200) NOT NULL,
    descricao CLOB,
    risco_automacao NUMBER(3) NOT NULL CHECK (risco_automacao >= 0 AND risco_automacao <= 100),
    profissao_id NUMBER(19) NOT NULL,
    trilha_sugerida_id NUMBER(19),
    CONSTRAINT fk_tarefa_profissao FOREIGN KEY (profissao_id) REFERENCES profissoes(id) ON DELETE CASCADE,
    CONSTRAINT fk_tarefa_trilha FOREIGN KEY (trilha_sugerida_id) REFERENCES trilhas(id) ON DELETE SET NULL
);

COMMENT ON TABLE tarefas IS 'Tarefas de profissões com risco de automação (0-100)';
COMMENT ON COLUMN tarefas.risco_automacao IS 'Risco de automação de 0 a 100';

-- ============================================
-- TABELA: PROFISSOES_FUTURAS
-- ============================================
CREATE TABLE profissoes_futuras (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL UNIQUE,
    descricao CLOB,
    categoria VARCHAR2(200)
);

COMMENT ON TABLE profissoes_futuras IS 'Profissões emergentes de 2030+';

-- ============================================
-- TABELA: HABILIDADES
-- ============================================
CREATE TABLE habilidades (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    descricao CLOB,
    tipo VARCHAR2(50) NOT NULL
);

COMMENT ON TABLE habilidades IS 'Habilidades exigidas e de transição para carreiras futuras';
COMMENT ON COLUMN habilidades.tipo IS 'Tipo: EXIGIDA ou TRANSICAO';

-- ============================================
-- TABELA: CAMINHOS_CARREIRA
-- ============================================
CREATE TABLE caminhos_carreira (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    profissao_atual VARCHAR2(100) NOT NULL,
    profissao_futura_id NUMBER(19) NOT NULL,
    trilha_id NUMBER(19),
    CONSTRAINT fk_caminho_prof_futura FOREIGN KEY (profissao_futura_id) REFERENCES profissoes_futuras(id) ON DELETE CASCADE,
    CONSTRAINT fk_caminho_trilha FOREIGN KEY (trilha_id) REFERENCES trilhas(id) ON DELETE SET NULL
);

COMMENT ON TABLE caminhos_carreira IS 'Caminhos de transição de carreira: profissão atual -> futura';

-- ============================================
-- TABELA: CAMINHO_HABILIDADE (N:N)
-- ============================================
CREATE TABLE caminho_habilidade (
    caminho_id NUMBER(19) NOT NULL,
    habilidade_id NUMBER(19) NOT NULL,
    PRIMARY KEY (caminho_id, habilidade_id),
    CONSTRAINT fk_caminho_hab_caminho FOREIGN KEY (caminho_id) REFERENCES caminhos_carreira(id) ON DELETE CASCADE,
    CONSTRAINT fk_caminho_hab_hab FOREIGN KEY (habilidade_id) REFERENCES habilidades(id) ON DELETE CASCADE
);

COMMENT ON TABLE caminho_habilidade IS 'Relação N:N entre caminhos de carreira e habilidades';

-- ============================================
-- ÍNDICES PARA PERFORMANCE
-- ============================================
-- NOTA: idx_usuarios_email não é necessário pois a coluna email já tem índice único (UNIQUE constraint)
-- CREATE INDEX idx_usuarios_email ON usuarios(email); -- REMOVIDO: já existe índice único

-- Índices para melhorar performance de consultas
CREATE INDEX idx_matriculas_usuario ON matriculas(usuario_id);
CREATE INDEX idx_matriculas_trilha ON matriculas(trilha_id);
CREATE INDEX idx_tarefas_profissao ON tarefas(profissao_id);
CREATE INDEX idx_caminhos_prof_atual ON caminhos_carreira(profissao_atual);
CREATE INDEX idx_caminhos_prof_futura ON caminhos_carreira(profissao_futura_id);

-- ============================================
-- SEQUENCES (se não usar IDENTITY)
-- ============================================
-- Caso precise usar sequences ao invés de IDENTITY:
-- CREATE SEQUENCE seq_usuarios START WITH 1 INCREMENT BY 1;
-- CREATE SEQUENCE seq_trilhas START WITH 1 INCREMENT BY 1;
-- CREATE SEQUENCE seq_matriculas START WITH 1 INCREMENT BY 1;
-- CREATE SEQUENCE seq_profissoes START WITH 1 INCREMENT BY 1;
-- CREATE SEQUENCE seq_tarefas START WITH 1 INCREMENT BY 1;
-- CREATE SEQUENCE seq_profissoes_futuras START WITH 1 INCREMENT BY 1;
-- CREATE SEQUENCE seq_habilidades START WITH 1 INCREMENT BY 1;
-- CREATE SEQUENCE seq_caminhos_carreira START WITH 1 INCREMENT BY 1;

COMMIT;


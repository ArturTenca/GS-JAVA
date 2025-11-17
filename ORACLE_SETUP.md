# 🗄️ Configuração Oracle SQL - Global Solution 2025

## 📋 Pré-requisitos

- Oracle Database instalado (Oracle XE, 19c, 21c ou superior)
- SQL*Plus ou SQL Developer instalado
- Acesso como usuário SYS ou SYSTEM

## 🔧 Passo 1: Criar Usuário e Schema

Conecte-se como SYS ou SYSTEM e execute:

```sql
-- Criar usuário
CREATE USER GLOBALSOLUTION IDENTIFIED BY GLOBALSOLUTION123;

-- Conceder permissões
GRANT CONNECT, RESOURCE, DBA TO GLOBALSOLUTION;

-- Permitir espaço ilimitado
ALTER USER GLOBALSOLUTION QUOTA UNLIMITED ON USERS;

-- Permitir criar tabelas
GRANT CREATE TABLE TO GLOBALSOLUTION;
GRANT CREATE SEQUENCE TO GLOBALSOLUTION;
GRANT CREATE VIEW TO GLOBALSOLUTION;

COMMIT;
```

## 📝 Passo 2: Executar Scripts SQL

### Opção A: Via SQL*Plus

```bash
# Conectar ao Oracle
sqlplus GLOBALSOLUTION/GLOBALSOLUTION123@localhost:1521/XE

# Executar schema
@src/main/resources/db/oracle/schema.sql

# Executar dados iniciais
@src/main/resources/db/oracle/data.sql
```

### Opção B: Via SQL Developer

1. Conecte-se ao banco como `GLOBALSOLUTION`
2. Abra o arquivo `schema.sql`
3. Execute o script completo (F5)
4. Abra o arquivo `data.sql`
5. Execute o script completo (F5)

### Opção C: Executar Manualmente

Copie e cole o conteúdo dos arquivos:
- `src/main/resources/db/oracle/schema.sql`
- `src/main/resources/db/oracle/data.sql`

## ⚙️ Passo 3: Configurar Application Properties

### Opção 1: Usar Profile Oracle

Crie o arquivo `application-oracle.properties` (já criado) e execute com:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=oracle
```

### Opção 2: Modificar application.properties

Edite `src/main/resources/application.properties`:

```properties
# Configuração do Banco de Dados Oracle
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=GLOBALSOLUTION
spring.datasource.password=GLOBALSOLUTION123
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Configuração JPA/Hibernate para Oracle
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 🔍 Passo 4: Verificar Conexão

### Testar Conexão Manualmente

```sql
-- Conectar
sqlplus GLOBALSOLUTION/GLOBALSOLUTION123@localhost:1521/XE

-- Verificar tabelas
SELECT table_name FROM user_tables;

-- Verificar dados
SELECT COUNT(*) FROM usuarios;
SELECT COUNT(*) FROM trilhas;
SELECT COUNT(*) FROM profissoes;
```

## 📊 Estrutura do Banco

### Tabelas Principais

1. **usuarios** - Usuários da plataforma
2. **trilhas** - Trilhas de aprendizagem
3. **competencias** - Competências do futuro
4. **trilha_competencia** - Relação N:N
5. **matriculas** - Matrículas de usuários
6. **profissoes** - Profissões para análise de risco
7. **tarefas** - Tarefas com risco de automação
8. **profissoes_futuras** - Profissões emergentes 2030+
9. **habilidades** - Habilidades exigidas/transição
10. **caminhos_carreira** - Caminhos de transição
11. **caminho_habilidade** - Relação N:N

## 🔐 Configurações Importantes

### String de Conexão

Formato: `jdbc:oracle:thin:@HOST:PORT:SID`

Exemplos:
- Oracle XE: `jdbc:oracle:thin:@localhost:1521:XE`
- Oracle 19c: `jdbc:oracle:thin:@localhost:1521:ORCL`
- Oracle Cloud: `jdbc:oracle:thin:@hostname:1521:PDB1`

### Dialeto Hibernate

Use: `org.hibernate.dialect.OracleDialect`

### DDL Auto

- `update` - Atualiza schema existente (recomendado)
- `create` - Cria schema (apaga dados existentes)
- `create-drop` - Cria e apaga ao encerrar
- `validate` - Apenas valida (não modifica)

## 🚀 Executar Aplicação

```bash
# Com profile Oracle
mvn spring-boot:run -Dspring-boot.run.profiles=oracle

# Ou modifique application.properties diretamente
mvn spring-boot:run
```

## ✅ Verificação

Após iniciar a aplicação, verifique:

1. **Logs**: Deve mostrar conexão com Oracle
2. **H2 Console**: Não estará disponível (apenas com H2)
3. **API**: Deve funcionar normalmente
4. **Dados**: Verifique se os dados iniciais foram carregados

## 🔧 Troubleshooting

### Erro: "ORA-01017: invalid username/password"
- Verifique usuário e senha
- Confirme que o usuário foi criado corretamente

### Erro: "ORA-12505: TNS:listener does not currently know of SID"
- Verifique o SID na string de conexão
- Confirme que o Oracle está rodando: `lsnrctl status`

### Erro: "ORA-00942: table or view does not exist"
- Execute o script `schema.sql` primeiro
- Verifique se está conectado como usuário correto

### Erro: "ORA-00001: unique constraint violated"
- Dados já existem no banco
- Use `ddl-auto=update` ou limpe as tabelas

## 📝 Notas Importantes

1. **IDENTITY Columns**: Oracle 12c+ suporta IDENTITY (auto-increment)
2. **CLOB**: Use CLOB para campos TEXT grandes
3. **DATE**: Oracle usa DATE (inclui hora), não apenas data
4. **Sequences**: Se usar Oracle antigo, pode precisar criar sequences manualmente

## 🔄 Migração de H2 para Oracle

1. Exporte dados do H2 (se necessário)
2. Execute `schema.sql` no Oracle
3. Execute `data.sql` no Oracle
4. Altere `application.properties`
5. Reinicie a aplicação


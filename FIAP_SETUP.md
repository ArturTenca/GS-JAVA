# 🗄️ Configuração Oracle SQL - FIAP
## Conexão: FIAP | Usuário: RM555171

## 📋 Passos para Configurar

### 1️⃣ Conectar ao Oracle via SQL Developer ou SQL*Plus

- **Conexão**: FIAP
- **Usuário**: RM555171
- **Senha**: (sua senha)

### 2️⃣ Executar Scripts SQL

#### Opção A: Via SQL Developer
1. Conecte-se à conexão **FIAP** como usuário **RM555171**
2. Abra o arquivo: `src/main/resources/db/oracle/schema-FIAP.sql`
3. Execute o script completo (F5 ou botão Run)
4. Abra o arquivo: `src/main/resources/db/oracle/data-FIAP.sql`
5. Execute o script completo (F5 ou botão Run)

#### Opção B: Via SQL*Plus
```bash
sqlplus RM555171@FIAP
# Digite sua senha quando solicitado

# Executar schema
@src/main/resources/db/oracle/schema-FIAP.sql

# Executar dados
@src/main/resources/db/oracle/data-FIAP.sql
```

### 3️⃣ Configurar application.properties

Edite `src/main/resources/application.properties` e configure:

```properties
# Oracle Database (FIAP)
spring.datasource.url=jdbc:oracle:thin:@FIAP
# OU se precisar do formato completo:
# spring.datasource.url=jdbc:oracle:thin:@HOST:PORT:SID
spring.datasource.username=RM555171
spring.datasource.password=SUA_SENHA_AQUI
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Configuração JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**⚠️ IMPORTANTE**: Substitua `SUA_SENHA_AQUI` pela sua senha real do Oracle.

### 4️⃣ Verificar Conexão

Execute no SQL Developer/SQL*Plus:

```sql
-- Verificar tabelas criadas
SELECT table_name FROM user_tables ORDER BY table_name;

-- Verificar dados
SELECT COUNT(*) FROM usuarios;
SELECT COUNT(*) FROM trilhas;
SELECT COUNT(*) FROM profissoes;
```

### 5️⃣ Executar Aplicação

```bash
mvn clean install
mvn spring-boot:run
```

## 🔍 Troubleshooting

### Erro: "ORA-01017: invalid username/password"
- Verifique se a senha está correta no `application.properties`
- Confirme que está usando o usuário correto: **RM555171**

### Erro: "ORA-12505: TNS:listener does not currently know of SID"
- Se usar TNS (nome da conexão FIAP), use: `jdbc:oracle:thin:@FIAP`
- Se precisar de formato direto, descubra HOST, PORT e SID da conexão FIAP
- Formato: `jdbc:oracle:thin:@HOST:PORT:SID`

### Erro: "ORA-00942: table or view does not exist"
- Execute primeiro o `schema-FIAP.sql`
- Verifique se está conectado como usuário correto

### Erro: "ORA-00001: unique constraint violated"
- Dados já existem no banco
- Use `ddl-auto=update` ou limpe as tabelas antes

## 📊 Estrutura Criada

O script cria 11 tabelas:

1. `usuarios` - Usuários da plataforma
2. `trilhas` - Trilhas de aprendizagem
3. `competencias` - Competências do futuro
4. `trilha_competencia` - Relação N:N
5. `matriculas` - Matrículas
6. `profissoes` - Profissões para análise de risco
7. `tarefas` - Tarefas com risco de automação
8. `profissoes_futuras` - Profissões emergentes 2030+
9. `habilidades` - Habilidades exigidas/transição
10. `caminhos_carreira` - Caminhos de transição
11. `caminho_habilidade` - Relação N:N

## ✅ Verificação Final

Após executar tudo, verifique:

1. ✅ Tabelas criadas (11 tabelas)
2. ✅ Dados inseridos (usuários, trilhas, etc.)
3. ✅ Aplicação conecta ao banco
4. ✅ API funciona corretamente

## 📝 Notas

- Os scripts usam **IDENTITY columns** (Oracle 12c+)
- Se seu Oracle for mais antigo, pode precisar criar sequences manualmente
- A conexão FIAP pode usar TNS (nome da conexão) ou formato direto


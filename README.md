# 🚀 Global Solution 2025 – Plataforma de Upskilling/Reskilling

Plataforma em **Java + Spring Boot** para conectar profissionais a trilhas de aprendizado e preparar talentos para o futuro do trabalho 2030+.

---

## 👤 Integrantes 

- **Artur Tenca** - RM555171
- **Igor Brunelli** - RM555035
- **Victor Capp** - RM555753

---

## 🎯 Objetivos de Desenvolvimento Sustentável (ODS)

Este projeto está alinhado com os seguintes ODS da ONU:

### 🎓 ODS 4 - Educação de Qualidade
A plataforma oferece **trilhas de aprendizado estruturadas** que permitem que profissionais desenvolvam competências essenciais para o futuro do trabalho. Através de trilhas personalizadas por nível (Iniciante, Intermediário, Avançado), democratizamos o acesso à educação de qualidade e capacitação profissional.

### 💼 ODS 8 - Trabalho Decente e Crescimento Econômico
A plataforma promove a **requalificação profissional** (reskilling) e o desenvolvimento contínuo (upskilling), preparando trabalhadores para novas oportunidades de carreira. Isso contribui para reduzir o desemprego tecnológico e promover o crescimento econômico sustentável.

### 🏭 ODS 9 - Indústria, Inovação e Infraestrutura
Focamos no desenvolvimento de **competências tecnológicas** essenciais como IA Generativa, Data Literacy, Cloud Computing e Segurança Cibernética. Essas habilidades são fundamentais para impulsionar a inovação e construir infraestruturas digitais resilientes.

### ⚖️ ODS 10 - Redução das Desigualdades
A plataforma **reduz desigualdades de acesso** à educação e capacitação profissional, oferecendo trilhas de aprendizado acessíveis que permitem que profissionais de diferentes níveis e áreas desenvolvam competências do futuro, independentemente de sua localização ou recursos iniciais.

---

## 🔧 Tecnologias

- **Java 21** (OpenJDK 21)
- **Spring Boot 3.3.0**
- **Maven**
- **H2 Database** (desenvolvimento) / **Oracle Database** (produção)
- **Spring Data JPA**
- **Bean Validation**
- **OpenAPI/Swagger** (Documentação da API)
- **JUnit 5** + **Mockito** (Testes)

---

## ▶️ Como Rodar o Projeto

### 1️⃣ Pré-requisitos

- **Java OpenJDK 21**
- **Maven 3.6+**
- **IntelliJ IDEA** (recomendado) ou outra IDE

### 2️⃣ Configuração no IntelliJ IDEA

1. **File → Project Structure**
2. **Project SDK:** OpenJDK 21
3. **Project Language Level:** 21 – Pattern Matching, Records, etc.

### 3️⃣ Instalar Dependências

```bash
mvn clean install
```

### 4️⃣ Executar a Aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em: **http://localhost:8080**

---

## 📚 Documentação da API (Swagger)

Após iniciar a aplicação, acesse a documentação interativa da API:

**🔗 http://localhost:8080/swagger-ui.html**

O Swagger permite:
- Visualizar todos os endpoints disponíveis
- Testar requisições diretamente na interface
- Ver exemplos de requisições e respostas
- Entender os modelos de dados (DTOs)

---

## 🗄️ Banco de Dados

### H2 Database (Desenvolvimento)

O projeto está configurado para usar o **H2 Database** (banco em memória) por padrão:

```properties
spring.datasource.url=jdbc:h2:mem:upskillingdb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
```

### Console H2

Acesse o console H2 em: **http://localhost:8080/h2-console**

- **JDBC URL:** `jdbc:h2:mem:upskillingdb`
- **Username:** `sa`
- **Password:** (vazio)

### Dados Iniciais (Seeds)

O `DataInitializer` carrega automaticamente:

- **8 Competências** do futuro:
  - IA Generativa
  - Data Literacy
  - Empatia Digital
  - Segurança Cibernética
  - Colaboração Remota
  - Automação de Processos
  - Cultura de Inovação
  - Cloud Computing
  - Comunicação Efetiva

- **5 Trilhas de Aprendizagem** pré-cadastradas
- **5 Usuários** de exemplo
- **7 Matrículas** de exemplo

---

## 📡 Endpoints da API

### 🔹 Usuários (`/api/usuarios`)

#### Listar Usuários (com paginação)
```http
GET /api/usuarios?page=0&size=10
```

**Resposta:**
```json
{
  "content": [
    {
      "id": 1,
      "nome": "Ana Silva",
      "email": "ana.silva@email.com",
      "areaAtuacao": "Tecnologia da Informação",
      "nivelCarreira": "Júnior",
      "dataCadastro": "2025-01-15"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 5,
  "totalPages": 1
}
```

#### Buscar Usuário por ID
```http
GET /api/usuarios/1
```

#### Criar Usuário
```http
POST /api/usuarios
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "areaAtuacao": "Desenvolvimento",
  "nivelCarreira": "Pleno"
}
```

**Resposta (201 Created):**
```json
{
  "id": 6,
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "areaAtuacao": "Desenvolvimento",
  "nivelCarreira": "Pleno",
  "dataCadastro": "2025-01-17"
}
```

#### Atualizar Usuário
```http
PUT /api/usuarios/1
Content-Type: application/json

{
  "nome": "Ana Silva Atualizada",
  "email": "ana.silva@email.com",
  "areaAtuacao": "Tecnologia da Informação",
  "nivelCarreira": "Pleno"
}
```

#### Deletar Usuário
```http
DELETE /api/usuarios/1
```

---

### 🔹 Trilhas de Aprendizagem (`/api/trilhas`)

#### Listar Trilhas (com paginação)
```http
GET /api/trilhas?page=0&size=5
```

**Resposta:**
```json
{
  "content": [
    {
      "id": 1,
      "nome": "Trilha de IA Generativa para Iniciantes",
      "descricao": "Aprenda os fundamentos de IA generativa...",
      "nivel": "INICIANTE",
      "cargaHoraria": 80,
      "focoPrincipal": "Inteligência Artificial",
      "competencias": [
        {
          "id": 1,
          "nome": "IA Generativa",
          "categoria": "Tecnológica",
          "descricao": "Domínio de ferramentas de IA generativa..."
        }
      ]
    }
  ],
  "totalElements": 5
}
```

#### Buscar Trilha por ID
```http
GET /api/trilhas/1
```

#### Buscar Trilhas por Nível
```http
GET /api/trilhas/nivel/INICIANTE
```

#### Criar Trilha
```http
POST /api/trilhas
Content-Type: application/json

{
  "nome": "Trilha de Segurança Cibernética",
  "descricao": "Aprenda a proteger sistemas e dados",
  "nivel": "INTERMEDIARIO",
  "cargaHoraria": 100,
  "focoPrincipal": "Segurança",
  "competenciaIds": [4]
}
```

**Valores válidos para `nivel`:**
- `INICIANTE`
- `INTERMEDIARIO`
- `AVANCADO`

#### Atualizar Trilha
```http
PUT /api/trilhas/1
Content-Type: application/json

{
  "nome": "Trilha Atualizada",
  "descricao": "Nova descrição",
  "nivel": "AVANCADO",
  "cargaHoraria": 150,
  "focoPrincipal": "Novo Foco",
  "competenciaIds": [1, 2, 3]
}
```

#### Deletar Trilha
```http
DELETE /api/trilhas/1
```

---

### 🔹 Matrículas (`/api/matriculas`)

#### Listar Matrículas (com paginação)
```http
GET /api/matriculas?page=0&size=10
```

#### Buscar Matrícula por ID
```http
GET /api/matriculas/1
```

**Resposta:**
```json
{
  "id": 1,
  "usuarioId": 1,
  "usuarioNome": "Ana Silva",
  "trilhaId": 1,
  "trilhaNome": "Trilha de IA Generativa para Iniciantes",
  "dataInscricao": "2025-01-15",
  "status": "ATIVA"
}
```

#### Buscar Matrículas por Usuário
```http
GET /api/matriculas/usuario/1
```

#### Buscar Matrículas por Trilha
```http
GET /api/matriculas/trilha/1
```

#### Criar Matrícula
```http
POST /api/matriculas
Content-Type: application/json

{
  "usuarioId": 1,
  "trilhaId": 1
}
```

**Resposta (201 Created):**
```json
{
  "id": 8,
  "usuarioId": 1,
  "usuarioNome": "Ana Silva",
  "trilhaId": 1,
  "trilhaNome": "Trilha de IA Generativa para Iniciantes",
  "dataInscricao": "2025-01-17",
  "status": "ATIVA"
}
```

#### Atualizar Status da Matrícula
```http
PUT /api/matriculas/1/status
Content-Type: application/json

{
  "status": "CONCLUIDA"
}
```

**Valores válidos para `status`:**
- `ATIVA`
- `CONCLUIDA`
- `CANCELADA`

#### Deletar Matrícula
```http
DELETE /api/matriculas/1
```

---

## ⚠️ Tratamento de Erros

A API retorna erros em formato JSON estruturado:

### Exemplo: Usuário não encontrado (404)
```http
GET /api/usuarios/999
```

**Resposta:**
```json
{
  "status": 404,
  "error": "Usuário não encontrado",
  "message": "Usuário com ID 999 não foi encontrado",
  "timestamp": "2025-01-17T10:23:11",
  "path": "/api/usuarios/999"
}
```

### Exemplo: Email já cadastrado (409)
```http
POST /api/usuarios
Content-Type: application/json

{
  "nome": "Teste",
  "email": "ana.silva@email.com",
  "areaAtuacao": "TI",
  "nivelCarreira": "Júnior"
}
```

**Resposta:**
```json
{
  "status": 409,
  "error": "Email já cadastrado",
  "message": "Email ana.silva@email.com já está cadastrado",
  "timestamp": "2025-01-17T10:25:30",
  "path": "/api/usuarios"
}
```

### Exemplo: Validação de dados (400)
```http
POST /api/usuarios
Content-Type: application/json

{
  "nome": "AB",
  "email": "email-invalido"
}
```

**Resposta:**
```json
{
  "status": 400,
  "message": "Erro de validação",
  "errors": {
    "nome": "Nome deve ter entre 3 e 100 caracteres",
    "email": "Email deve ser válido"
  },
  "timestamp": "2025-01-17T10:27:15"
}
```

---

## 🏗️ Arquitetura

O projeto segue **arquitetura em camadas** com separação clara de responsabilidades:

```
Controller (API/DTOs) → Service (Regras de Negócio) → Repository (Acesso a Dados) → Database
```

### Estrutura de Pacotes

```
com.globalsolution
├── api
│   ├── controller      # Controllers REST
│   ├── dto            # Data Transfer Objects (DTOs)
│   └── exception      # Tratamento de exceções
├── domain
│   ├── model          # Entidades JPA
│   ├── repository     # Interfaces de repositório
│   ├── service        # Serviços de negócio
│   └── exception      # Exceções de domínio
├── infrastructure
│   └── config         # Configurações (DataInitializer)
└── UpskillingPlatformApplication.java
```

### Padrões Implementados

- ✅ **DTOs (Data Transfer Objects)**: Separação entre entidades de domínio e objetos de transferência
- ✅ **Paginação**: Endpoints de listagem suportam paginação com Spring Data
- ✅ **Validação**: Bean Validation para validação de dados
- ✅ **Tratamento de Erros**: GlobalExceptionHandler com respostas JSON estruturadas
- ✅ **Testes Unitários**: Testes com JUnit 5 e Mockito

---

## 🧪 Testes

Execute os testes unitários:

```bash
mvn test
```

Os testes cobrem:
- Criação de usuários
- Validação de regras de negócio
- Tratamento de exceções
- Validação de trilhas (carga horária, nível)

---

## 📝 Regras de Negócio Implementadas

- ✅ **Email único**: Não permite cadastro de emails duplicados
- ✅ **Carga horária positiva**: Trilhas devem ter carga horária maior que zero
- ✅ **Níveis válidos**: Apenas INICIANTE, INTERMEDIARIO ou AVANCADO
- ✅ **Matrícula única ativa**: Não permite matrícula duplicada com status ATIVA
- ✅ **Validação de existência**: Verifica se usuário/trilha existe antes de criar matrícula

---

## 🚀 Melhorias Implementadas

1. ✅ **Separação de DTOs**: Controllers usam DTOs, não entidades diretamente
2. ✅ **Paginação**: Endpoints de listagem suportam `?page=0&size=10`
3. ✅ **OpenAPI/Swagger**: Documentação interativa em `/swagger-ui.html`
4. ✅ **Tratamento de Erros**: Respostas JSON estruturadas com timestamp e path
5. ✅ **Validações de Negócio**: Carga horária, email único, matrícula única
6. ✅ **Testes Unitários**: Cobertura de testes para Services
7. ✅ **Seeds Melhorados**: Competências mais ricas e alinhadas ao futuro do trabalho
8. ✅ **Documentação Completa**: README com exemplos JSON e explicação de ODS

---

## 📞 Contato

Para dúvidas ou sugestões, entre em contato com a equipe de desenvolvimento.

---

**Desenvolvido com ❤️ para o Global Solution 2025**

# 🌍 Global Solution 2025 – Plataforma de Upskilling/Reskilling

## 📋 Descrição do Projeto

Plataforma de Upskilling/Reskilling desenvolvida em Java com Spring Boot para preparar profissionais para o futuro do trabalho (2030+). A solução permite que pessoas se cadastrem na plataforma, acessem trilhas de aprendizagem focadas em competências do futuro e se inscrevam nessas trilhas para se requalificar profissionalmente.

### 🎯 Objetivo

Desenvolver uma API RESTful que conecta profissionais com oportunidades de aprendizado contínuo, alinhada aos Objetivos de Desenvolvimento Sustentável (ODS) 4 (Educação de Qualidade), 8 (Trabalho Decente e Crescimento Econômico), 9 (Indústria, Inovação e Infraestrutura) e 10 (Redução das Desigualdades).

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Bean Validation**
- **Maven**

## 📦 Dependências Principais

- `spring-boot-starter-web` - Framework web REST
- `spring-boot-starter-data-jpa` - Persistência de dados
- `spring-boot-starter-validation` - Validação de dados
- `h2` - Banco de dados em memória
- `lombok` - Redução de boilerplate (opcional)

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6+ (ou use o Maven Wrapper incluído)

### Passos para Execução

1. **Clone o repositório** (ou navegue até a pasta do projeto):
   ```bash
   cd DDD-Java
   ```

2. **Compile o projeto**:
   ```bash
   mvn clean install
   ```

3. **Execute a aplicação**:
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a aplicação**:
   - API: `http://localhost:8080`
   - Console H2: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:upskillingdb`
     - Username: `sa`
     - Password: (deixe em branco)

## 🗄️ Configuração do Banco de Dados

O projeto está configurado para usar o **H2 Database** (banco em memória) por padrão. A configuração está no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:upskillingdb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
```

### Dados Iniciais (Seeds)

O projeto possui um `DataInitializer` que carrega automaticamente dados fictícios ao iniciar a aplicação:

- **6 Competências** do futuro (IA, Dados, Empatia, Colaboração, Cloud, Comunicação)
- **5 Trilhas de Aprendizagem** pré-cadastradas
- **5 Usuários** de exemplo
- **7 Matrículas** de exemplo

## 📚 Estrutura da API

### Endpoints de Usuários (`/api/usuarios`)

| Método | Rota | Descrição | Status Code |
|--------|------|-----------|-------------|
| GET | `/api/usuarios` | Lista todos os usuários | 200 |
| GET | `/api/usuarios/{id}` | Busca usuário por ID | 200 / 404 |
| POST | `/api/usuarios` | Cria novo usuário | 201 / 400 / 409 |
| PUT | `/api/usuarios/{id}` | Atualiza usuário | 200 / 404 / 400 |
| DELETE | `/api/usuarios/{id}` | Remove usuário | 204 / 404 |

### Endpoints de Trilhas (`/api/trilhas`)

| Método | Rota | Descrição | Status Code |
|--------|------|-----------|-------------|
| GET | `/api/trilhas` | Lista todas as trilhas | 200 |
| GET | `/api/trilhas/{id}` | Busca trilha por ID | 200 / 404 |
| GET | `/api/trilhas/nivel/{nivel}` | Busca trilhas por nível | 200 |
| POST | `/api/trilhas` | Cria nova trilha | 201 / 400 |
| PUT | `/api/trilhas/{id}` | Atualiza trilha | 200 / 404 / 400 |
| DELETE | `/api/trilhas/{id}` | Remove trilha | 204 / 404 |

### Endpoints de Matrículas (`/api/matriculas`)

| Método | Rota | Descrição | Status Code |
|--------|------|-----------|-------------|
| GET | `/api/matriculas` | Lista todas as matrículas | 200 |
| GET | `/api/matriculas/{id}` | Busca matrícula por ID | 200 / 404 |
| GET | `/api/matriculas/usuario/{usuarioId}` | Busca matrículas do usuário | 200 |
| GET | `/api/matriculas/trilha/{trilhaId}` | Busca matrículas da trilha | 200 |
| POST | `/api/matriculas` | Cria nova matrícula | 201 / 400 / 409 |
| PUT | `/api/matriculas/{id}/status` | Atualiza status da matrícula | 200 / 404 |
| DELETE | `/api/matriculas/{id}` | Remove matrícula | 204 / 404 |

## 📝 Exemplos de Requisições

### Criar Usuário

**POST** `http://localhost:8080/api/usuarios`

```json
{
  "nome": "Maria Oliveira",
  "email": "maria.oliveira@email.com",
  "areaAtuacao": "Desenvolvimento de Software",
  "nivelCarreira": "Pleno"
}
```

**Resposta (201 Created):**
```json
{
  "id": 6,
  "nome": "Maria Oliveira",
  "email": "maria.oliveira@email.com",
  "areaAtuacao": "Desenvolvimento de Software",
  "nivelCarreira": "Pleno",
  "dataCadastro": "2025-01-15"
}
```

### Criar Trilha de Aprendizagem

**POST** `http://localhost:8080/api/trilhas`

```json
{
  "nome": "Trilha de DevOps e Cloud",
  "descricao": "Aprenda a implementar práticas de DevOps e trabalhar com infraestrutura em nuvem.",
  "nivel": "INTERMEDIARIO",
  "cargaHoraria": 100,
  "focoPrincipal": "DevOps"
}
```

**Resposta (201 Created):**
```json
{
  "id": 6,
  "nome": "Trilha de DevOps e Cloud",
  "descricao": "Aprenda a implementar práticas de DevOps e trabalhar com infraestrutura em nuvem.",
  "nivel": "INTERMEDIARIO",
  "cargaHoraria": 100,
  "focoPrincipal": "DevOps",
  "competencias": [],
  "matriculas": []
}
```

### Criar Matrícula

**POST** `http://localhost:8080/api/matriculas`

```json
{
  "usuarioId": 1,
  "trilhaId": 1
}
```

**Resposta (201 Created):**
```json
{
  "id": 8,
  "usuario": {
    "id": 1,
    "nome": "Ana Silva",
    "email": "ana.silva@email.com"
  },
  "trilha": {
    "id": 1,
    "nome": "Trilha de Inteligência Artificial para Iniciantes"
  },
  "dataInscricao": "2025-01-15",
  "status": "ATIVA"
}
```

### Listar Todas as Trilhas

**GET** `http://localhost:8080/api/trilhas`

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "Trilha de Inteligência Artificial para Iniciantes",
    "descricao": "Aprenda os fundamentos de IA...",
    "nivel": "INICIANTE",
    "cargaHoraria": 80,
    "focoPrincipal": "Inteligência Artificial"
  },
  ...
]
```

### Buscar Trilhas por Nível

**GET** `http://localhost:8080/api/trilhas/nivel/INICIANTE`

## ✅ Validações Implementadas

### Usuário
- Nome: obrigatório, entre 3 e 100 caracteres
- Email: obrigatório, formato válido, único
- Área de atuação: máximo 100 caracteres
- Nível de carreira: máximo 50 caracteres

### Trilha de Aprendizagem
- Nome: obrigatório, entre 5 e 150 caracteres
- Descrição: máximo 1000 caracteres
- Nível: obrigatório, deve ser `INICIANTE`, `INTERMEDIARIO` ou `AVANCADO`
- Carga horária: obrigatória, maior que zero

### Matrícula
- Usuário: obrigatório
- Trilha: obrigatória
- Status: obrigatório (padrão: `ATIVA`)

## ⚠️ Tratamento de Exceções

O projeto utiliza `@RestControllerAdvice` para tratamento global de exceções:

- **404 Not Found**: Recurso não encontrado
- **400 Bad Request**: Erro de validação ou argumento inválido
- **409 Conflict**: Email já cadastrado ou matrícula duplicada
- **422 Unprocessable Entity**: Erro de validação de dados
- **500 Internal Server Error**: Erro interno do servidor

### Exemplo de Resposta de Erro

```json
{
  "status": 404,
  "message": "Usuário com ID 999 não encontrado",
  "timestamp": "2025-01-15T10:30:00"
}
```

## 🏗️ Arquitetura

O projeto segue arquitetura em camadas:

```
Controller (API) → Service (Regras de Negócio) → Repository (Acesso a Dados) → Database
```

### Estrutura de Pacotes

```
com.globalsolution
├── api
│   ├── controller      # Controllers REST
│   └── exception       # Tratamento de exceções
├── domain
│   ├── model           # Entidades JPA
│   ├── repository      # Interfaces de repositório
│   ├── service         # Serviços de negócio
│   └── exception       # Exceções de domínio
├── infrastructure
│   └── config          # Configurações (DataInitializer)
└── UpskillingPlatformApplication.java
```

## 🧪 Como Testar

### Usando Postman ou Insomnia

1. Importe as requisições abaixo ou crie manualmente
2. Configure a URL base: `http://localhost:8080`
3. Teste os endpoints conforme os exemplos acima

### Usando cURL

**Listar usuários:**
```bash
curl http://localhost:8080/api/usuarios
```

**Criar usuário:**
```bash
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Teste Usuario",
    "email": "teste@email.com",
    "areaAtuacao": "TI",
    "nivelCarreira": "Júnior"
  }'
```

**Buscar trilha por ID:**
```bash
curl http://localhost:8080/api/trilhas/1
```

## 📊 Modelo de Dados

### Entidades Principais

- **Usuario**: Profissionais/alunos da plataforma
- **TrilhaDeAprendizagem**: Conjunto de módulos/temas de aprendizado
- **Competencia**: Competências do futuro (IA, dados, soft skills, etc.)
- **Matricula**: Relacionamento entre usuários e trilhas

### Relacionamentos

- Usuario ↔ Matricula (1:N)
- TrilhaDeAprendizagem ↔ Matricula (1:N)
- TrilhaDeAprendizagem ↔ Competencia (N:N)

## 🌟 Diferenciais

- ✅ CRUDs completos para Usuário e Trilha de Aprendizagem
- ✅ Validações robustas com Bean Validation
- ✅ Tratamento global de exceções
- ✅ Dados iniciais (seeds) automáticos
- ✅ Documentação completa
- ✅ Código organizado seguindo boas práticas
- ✅ Conexão com ODS 4, 8, 9 e 10

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos no contexto da disciplina de Domain Driven Design.

## 👥 Autores

- [Seu Nome] - RM: [Seu RM]

---

**Desenvolvido com ❤️ para o Futuro do Trabalho 2030+**


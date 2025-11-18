# 🚀 Global Solution 2025 – Plataforma de Upskilling/Reskilling

Plataforma em **Java + Spring Boot** para conectar profissionais a trilhas de aprendizado e preparar talentos para o futuro do trabalho.  
Documentação resumida, clara e focada no essencial: **como rodar o projeto**.

---

# 👤 Integrantes 

Artur Tenca - RM555171

Igor Brunelli - RM555035

Victor Capp - RM555753

---


# 🔧 Tecnologias

- Java **21** (OpenJDK 21)
- Spring Boot
- Maven
- H2 Database
- Spring Data JPA
- Bean Validation

---

# ▶️ Como Rodar o Projeto (Passo a Passo)

## 1️⃣ Pré-requisitos obrigatórios

- **Java OpenJDK 21**
- **Maven instalado**  
  → Se o comando `mvn` não funcionar no terminal, instale e configure o Maven no PATH.

## 2️⃣ Configuração no IntelliJ IDEA

1. Vá em **File → Project Structure**
2. **Project SDK:** selecione **OpenJDK 21**
3. **Project Language Level:** escolha **21 – Pattern Matching, Records, etc.**
4. Verifique se o IntelliJ está utilizando o **Maven** corretamente.

---

# 3️⃣ Instalar as Dependências do Maven

No terminal do IntelliJ ou do sistema:

```bash
mvn clean install

```

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
curl http://localhost:8080/api/trilhas/1

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

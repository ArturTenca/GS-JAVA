# 🚀 Como Executar o Projeto

## Opção 1: Usando IDE (Recomendado)

### IntelliJ IDEA / Eclipse / VS Code
1. Abra a IDE
2. Importe o projeto como projeto Maven
3. Localize a classe `UpskillingPlatformApplication.java`
4. Clique com botão direito → Run / Executar

## Opção 2: Instalar Maven

### Windows
1. Baixe o Maven: https://maven.apache.org/download.cgi
2. Extraia em uma pasta (ex: `C:\Program Files\Apache\maven`)
3. Adicione ao PATH: `C:\Program Files\Apache\maven\bin`
4. Execute:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Verificar instalação
```bash
mvn --version
```

## Opção 3: Usar Maven Wrapper (Após instalar Maven uma vez)

Se você tiver Maven instalado, execute:
```bash
mvn wrapper:wrapper
```

Depois use:
```bash
./mvnw spring-boot:run    # Linux/Mac
mvnw.cmd spring-boot:run  # Windows
```

## Acessar a Aplicação

Após iniciar, acesse:
- **API Home**: http://localhost:8080/api
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:upskillingdb`
  - Username: `sa`
  - Password: (deixe em branco)

## Testar Endpoints

```bash
# Listar usuários
curl http://localhost:8080/api/usuarios

# Listar trilhas
curl http://localhost:8080/api/trilhas

# Ver informações da API
curl http://localhost:8080/api
```




# 🔧 Solução para Erro de Dependências

## Erro Encontrado
```
java: package org.springframework.beans.factory.annotation does not exist
```

## Causa
As dependências do Spring Boot não foram baixadas pelo Maven. Isso acontece quando:
- O projeto é compilado sem usar o Maven
- A IDE não baixou as dependências ainda
- O Maven não foi executado para baixar as dependências

## ✅ Soluções

### Solução 1: Usando IDE (IntelliJ IDEA / Eclipse)

1. **IntelliJ IDEA:**
   - Clique com botão direito no arquivo `pom.xml`
   - Selecione **"Maven" → "Reload Project"**
   - Ou vá em **File → Invalidate Caches / Restart**
   - Aguarde o Maven baixar todas as dependências (barra de progresso no canto inferior)

2. **Eclipse:**
   - Clique com botão direito no projeto
   - Selecione **"Maven" → "Update Project"**
   - Marque **"Force Update of Snapshots/Releases"**
   - Clique em **OK**

3. **VS Code:**
   - Abra o terminal integrado
   - Execute: `mvn clean install` (se tiver Maven instalado)
   - Ou instale a extensão "Extension Pack for Java"

### Solução 2: Usando Maven via Terminal

Se você tiver Maven instalado:

```bash
# Navegue até a pasta do projeto
cd C:\Users\artur\Desktop\DDD-Java

# Baixe todas as dependências
mvn dependency:resolve

# Ou compile o projeto (isso também baixa dependências)
mvn clean compile
```

### Solução 3: Verificar se o Maven está funcionando

```bash
# Verificar versão do Maven
mvn --version

# Se não funcionar, instale o Maven:
# https://maven.apache.org/download.cgi
```

### Solução 4: Limpar e Recompilar

```bash
# Limpar projeto
mvn clean

# Baixar dependências e compilar
mvn install

# Executar aplicação
mvn spring-boot:run
```

## 📝 Verificação

Após executar uma das soluções acima, verifique se a pasta `target` foi criada e se há uma pasta `.m2` no seu diretório home (onde o Maven armazena as dependências baixadas).

## ⚠️ Importante

- **NÃO compile manualmente com `javac`** - use sempre o Maven
- O Maven precisa estar instalado OU você precisa usar uma IDE que tenha Maven integrado
- As dependências são baixadas automaticamente na primeira vez que você compila com Maven

## 🎯 Próximos Passos

Depois que as dependências forem baixadas:
1. O erro deve desaparecer
2. Você poderá executar: `mvn spring-boot:run`
3. A aplicação estará disponível em: `http://localhost:8080/api`




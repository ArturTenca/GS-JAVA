# 🔧 Solução para Erro com Java 24

## Erro Encontrado
```
java.lang.ExceptionInInitializerError
com.sun.tools.javac.code.TypeTag :: UNKNOWN
```

## Causa
Incompatibilidade entre Java 24 e Spring Boot 3.2.0. O Java 24 é muito recente e pode ter problemas de compatibilidade.

## ✅ Soluções Aplicadas

1. **Atualizado o pom.xml para Java 21** (versão LTS mais estável)
2. **Atualizado Spring Boot para 3.3.0** (melhor suporte para Java mais recente)

## 📝 Próximos Passos no IntelliJ IDEA

### 1. Configurar o SDK do Projeto

1. Vá em **File → Project Structure** (ou `Ctrl+Alt+Shift+S`)
2. Na aba **Project**, verifique:
   - **Project SDK**: Deve estar configurado para Java 24 (ou a versão que você tem)
   - **Project language level**: Deve estar em **21** ou superior
3. Clique em **OK**

### 2. Configurar o Maven

1. Vá em **File → Settings** (ou `Ctrl+Alt+S`)
2. Navegue até **Build, Execution, Deployment → Build Tools → Maven**
3. Verifique se o **Maven home path** está correto
4. Clique em **OK**

### 3. Sincronizar o Projeto Novamente

1. Clique com botão direito no `pom.xml`
2. Selecione **Maven → Reload Project**
3. Aguarde o download das dependências

### 4. Limpar e Recompilar

1. Vá em **Build → Rebuild Project** (ou `Ctrl+Shift+F9`)
2. Aguarde a compilação terminar

### 5. Executar a Aplicação

1. Clique com botão direito em `UpskillingPlatformApplication.java`
2. Selecione **Run 'UpskillingPlatformApplication'**
3. Ou use o atalho: `Shift + F10`

## ⚠️ Se o Erro Persistir

### Opção A: Usar Java 21 (Recomendado)

Se você tiver Java 21 instalado:
1. **File → Project Structure → Project**
2. Altere o **Project SDK** para Java 21
3. Recompile o projeto

### Opção B: Configurar Compilador

1. **File → Settings → Build, Execution, Deployment → Compiler → Java Compiler**
2. Verifique se a versão do compilador está correta
3. Tente usar **21** ou **24** dependendo do que funcionar

### Opção C: Invalidar Cache

1. **File → Invalidate Caches...**
2. Marque todas as opções
3. Clique em **Invalidate and Restart**
4. Aguarde o IntelliJ reiniciar

## 🎯 Verificação

Após seguir os passos:
- O projeto deve compilar sem erros
- A aplicação deve iniciar normalmente
- Você verá logs do Spring Boot no console

## 📌 Nota

Java 24 é uma versão muito recente. Se continuar tendo problemas, considere:
- Usar Java 21 (LTS) que é mais estável
- Ou usar Java 17 (LTS) que é amplamente suportado




# 🔍 Como Descobrir a URL do Oracle FIAP

## Erro Atual
```
ORA-17868: Host desconhecido especificado.: FIAP
```

O driver JDBC não consegue resolver o nome TNS "FIAP". Precisamos descobrir a URL real.

## 📋 Métodos para Descobrir a URL

### Método 1: SQL Developer (Mais Fácil)

1. Abra o SQL Developer
2. Clique com botão direito na conexão **FIAP**
3. Selecione **Properties** (ou **Propriedades**)
4. Na aba **Connection** (ou **Conexão**), você verá:
   - **Hostname** (ou Host)
   - **Port**
   - **SID** ou **Service Name**

**Exemplo do que você pode ver:**
- Hostname: `oracle.fiap.com.br`
- Port: `1521`
- SID: `ORCL` ou Service Name: `ORCL`

**URL resultante:**
```
jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
```

### Método 2: Verificar tnsnames.ora

Se você tiver acesso ao arquivo `tnsnames.ora`, procure pela entrada `FIAP`:

**Localização comum:**
- Windows: `C:\oracle\product\...\network\admin\tnsnames.ora`
- Ou: `%ORACLE_HOME%\network\admin\tnsnames.ora`

**Exemplo de entrada:**
```
FIAP =
  (DESCRIPTION =
    (ADDRESS = (PROTOCOL = TCP)(HOST = oracle.fiap.com.br)(PORT = 1521))
    (CONNECT_DATA =
      (SERVER = DEDICATED)
      (SERVICE_NAME = ORCL)
    )
  )
```

**URL resultante:**
```
jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
```

### Método 3: Testar Conexão no SQL Developer

1. Conecte-se à conexão FIAP no SQL Developer
2. Execute:
```sql
SELECT 
    SYS_CONTEXT('USERENV', 'SERVER_HOST') as HOST,
    SYS_CONTEXT('USERENV', 'IP_ADDRESS') as IP,
    SYS_CONTEXT('USERENV', 'DB_NAME') as DB_NAME,
    SYS_CONTEXT('USERENV', 'SERVICE_NAME') as SERVICE_NAME
FROM DUAL;
```

Isso retornará informações que podem ajudar a montar a URL.

### Método 4: URLs Comuns da FIAP

Tente estas URLs comuns (substitua a senha):

```properties
# Opção 1
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL

# Opção 2
spring.datasource.url=jdbc:oracle:thin:@oracledb.fiap.com.br:1521:XE

# Opção 3
spring.datasource.url=jdbc:oracle:thin:@fiap-oracle.fiap.com.br:1521:ORCL
```

## ✅ Após Descobrir a URL

1. Edite `src/main/resources/application.properties`
2. Substitua `jdbc:oracle:thin:@HOST:PORT:SID` pela URL correta
3. Substitua `SUA_SENHA_AQUI` pela sua senha real
4. Salve o arquivo
5. Execute novamente: `mvn spring-boot:run`

## 🔧 Formato da URL

```
jdbc:oracle:thin:@HOST:PORT:SID
```

Onde:
- **HOST**: Endereço do servidor Oracle
- **PORT**: Porta (geralmente 1521)
- **SID**: Nome do banco de dados ou Service Name

**Exemplo:**
```
jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
```

## ⚠️ Importante

- Se usar **Service Name** ao invés de SID, use:
  ```
  jdbc:oracle:thin:@HOST:PORT/SERVICE_NAME
  ```
  Exemplo: `jdbc:oracle:thin:@oracle.fiap.com.br:1521/ORCL`

- Mantenha a senha segura e não commite no Git!


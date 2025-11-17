@echo off
echo ========================================
echo Global Solution - Plataforma Upskilling
echo ========================================
echo.

REM Verifica se Maven está instalado
where mvn >nul 2>&1
if %ERRORLEVEL% EQU 0 (
    echo [OK] Maven encontrado!
    echo.
    echo Compilando o projeto...
    call mvn clean compile
    if %ERRORLEVEL% EQU 0 (
        echo.
        echo Iniciando a aplicacao...
        call mvn spring-boot:run
    ) else (
        echo.
        echo [ERRO] Falha na compilacao. Verifique os erros acima.
        pause
    )
) else (
    echo [AVISO] Maven nao encontrado!
    echo.
    echo Opcoes:
    echo 1. Instale o Maven: https://maven.apache.org/download.cgi
    echo 2. Use uma IDE (IntelliJ IDEA, Eclipse, VS Code)
    echo 3. Veja o arquivo EXECUTAR.md para mais detalhes
    echo.
    pause
)




@echo off
title EtherMenu - Install
echo ============================================
echo   EtherMenu v1.0.6 - Installer
echo ============================================
echo.

if not exist "jre64\bin\java.exe" (
    echo ERROR: jre64\bin\java.exe not found.
    echo Make sure this file is in your Project Zomboid game folder.
    echo.
    pause
    exit /b 1
)

jre64\bin\java -jar EtherMenu-1.0.6-lite.jar --install
echo.
pause

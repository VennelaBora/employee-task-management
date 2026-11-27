@echo off
setlocal
set "MAVEN_VERSION=3.9.9"
set "MAVEN_HOME=%USERPROFILE%\.m2\wrapper\apache-maven-%MAVEN_VERSION%"
set "MAVEN_BIN=%MAVEN_HOME%\bin\mvn.cmd"

if not exist "%MAVEN_BIN%" (
    echo Maven %MAVEN_VERSION% not found. Downloading...
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$ErrorActionPreference='Stop'; $base=Join-Path $env:USERPROFILE '.m2\wrapper'; New-Item -ItemType Directory -Force -Path $base | Out-Null; $zip=Join-Path $env:TEMP 'apache-maven-3.9.9-bin.zip'; Invoke-WebRequest 'https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.9/apache-maven-3.9.9-bin.zip' -OutFile $zip; Expand-Archive $zip -DestinationPath $base -Force; Remove-Item $zip -Force"
)

if not exist "%MAVEN_BIN%" (
    echo Maven installation failed.
    exit /b 1
)

call "%MAVEN_BIN%" %*
exit /b %ERRORLEVEL%

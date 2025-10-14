# IBM TTS Demo – Backend (Spring Boot)

## Resumo breve

Projeto full stack que gera áudio a partir de um texto. Consome a API de Text To Speech do IBM Watson através de API REST, desenvolvido em Java Spring Boot.

O sistema possui um endpoint POST para a síntese do áudio e um endpoint GET para obter o histórico das requisições que ficaram armazenadas num banco de dados PostgreSQL, tanto sucessos quanto erros.

Frontend desenvolvido em React.js usa a biblioteca Axios para consumir a API local. O áudio é recebido como um arquivo binário em bytes, e convertido para um arquivo mp3 usando Blob, para ser possível reproduzir e baixar o áudio na interface.

Para a melhor visualização do histórico foi implementada paginação no endpoint. O método POST pode enviar apenas o texto, mantendo a voz padrão do sistema, ou definir uma voz alternativa no corpo da requisição.

O projeto também apresenta um tratamento de exceções, o que garante respostas consistentes em casos de erros.

---

## Instruções de como executar

### 1) Pré-requisitos

* **Java 21**
* **Maven 3.9+**
* **PostgreSQL** rodando localmente
* Conta na **IBM Cloud** com o serviço **Text to Speech** (plano *Lite* funciona)

### 2) Configure as variáveis de ambiente

Crie um arquivo **`.env`** na raiz do projeto (há um `.env.example` como referência):

```env
# IBM Watson TTS
IBM_API_KEY=YOUR_KEY_HERE
IBM_TTS_URL=https://api.us-south.text-to-speech.watson.cloud.ibm.com/instances/YOUR_INSTANCE_ID

# Postgres
DB_URL=jdbc:postgresql://localhost:5432/ttsdb
DB_USER=ttsuser
DB_PASS=ttspass
```

> O projeto carrega o `.env` automaticamente (via **spring-dotenv**).

### 3) Crie o banco de dados (se ainda não existir)

**Via psql (exemplo):**

```bash
psql -U postgres
CREATE DATABASE ttsdb;
CREATE USER ttsuser WITH ENCRYPTED PASSWORD 'ttspass';
GRANT ALL PRIVILEGES ON DATABASE ttsdb TO ttsuser;
```

> Ao subir a aplicação, o Hibernate cria/atualiza a tabela automaticamente.

### 4) Instale e rode

```bash
mvn clean package
mvn spring-boot:run
```

A API ficará disponível em: `http://localhost:8080`

### 5) Teste rápido

* **POST /api/v1/tts/synthesize** (gera MP3):

```bash
curl -X POST "http://localhost:8080/api/v1/tts/synthesize" \
  -H "Content-Type: application/json" \
  --data '{"text":"Olá, IBM Innovation Studio!","voice":"pt-BR_IsabelaV3Voice"}' \
  --output output.mp3
```

* **GET /api/v1/tts/history** (histórico paginado):

```
http://localhost:8080/api/v1/tts/history?sort=createdAt,desc&page=0&size=5
```

> Observação: o projeto inclui **tratamento global de erros** — respostas de validação e falhas internas vêm padronizadas em JSON.

---

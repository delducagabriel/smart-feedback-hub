# smart-feedback-hub *(para fins de estudo)*
Sistema de análise de sentimentos em tempo real, construído com uma arquitetura baseada em microsserviços. O projeto recebe avaliações de usuários, processa o texto usando Inteligência Artificial (NLP) e exibe os resultados dinamicamente. 

## arquitetura do sistema
O projeto foi dividido em três frentes principais para garantir separação de responsabilidades:

1. client-ui: uma Single Page Application (SPA) desenvolvida em **React (Vite)**, responsável por capturar a entrada do usuário e exibir o estado atualizado dos dados sem recarregar a página.
2. core-backend / api gateway: o orquestrador central desenvolvido em **Java (Spring Boot)**, ele gerencia a persistência de dados no banco em memória (**H2**), resolve o CORS e atua como ponte de comunicação via HTTP.
3. serviço de NLP: um microsserviço isolado construído em **Python (Flask)** utilizando a biblioteca `TextBlob`, responsável por receber um texto, analisar a polaridade e devolver o sentimento (Positivo, Negativo ou Neutro).

---
## como executar 
Para que o sistema funcione de ponta a ponta, os três serviços precisam estar rodando simultaneamente em terminais separados. Pré-requisitos:
* Python 3.x
* Java 17+ (e Maven)
* Node.js e npm

### passo 1: iniciar o serviço de IA
Abra um terminal, navegue até a pasta do serivço NLP, ative o ambiente virtual e rode o Flask. 

```
cd nlp-service
source venv/bin/activate
python3 app.py
```

### passo 2: inicial o orquestrador 
Abra um segundo terminal, navegue até a pasta do backend e rode o Spring Boot.

```
cd core-backend
./mvnw spring-boot:run
```

### passo 3: iniciar a interface react
bra um terceiro terminal, navegue até a pasta do frontend e inicie o servidor do Vite.

```
cd client-ui
npm run dev
```

Acesse `http://localhost:5173` no seu navegador para usar a aplicação. 

---

## tecnologias utilizadas
- **core backend:** java 17, spring boot, spring data JPA, H2 database
- **microsserviço de NLP:** python3, flask, textBlob
- **client ui (frontend):** react, vite axios, CSS puro
- **boas práticas:** padrão de arquitetura em microsserviços, conventional commits, nested records (java), API RESTful

![Logo](images/logo.png)

# 🩺 MedSave: Controle Inteligente, Saúde Eficiente.

> **Prevenção de perdas e otimização de compras em tempo real. A inteligência que a sua gestão de estoque hospitalar precisa.**

A **MedSave** é uma plataforma robusta de **Business Intelligence (BI)** que transforma dados de estoque em **alertes estratégicos**. Desenvolvida para prevenir perdas por vencimento e ruptura de estoque, a MedSave garante a disponibilidade de suprimentos essenciais e maximiza a eficiência operacional.

---

## ✨ Destaques da Plataforma

| Funcionalidade | Descrição |
| :--- | :--- |
| 🚨 **Alertas Inteligentes** | Notificações em tempo real sobre lotes próximos ao vencimento e estoques mínimos. |
| 💸 **Otimização de Compras** | Sugestões de pedidos baseadas em padrões de consumo histórico e níveis de segurança. |
| 🔍 **Rastreabilidade Total** | Controle completo sobre a entrada, localização e dispensa de cada lote de medicamento. |
| 📊 **Relatórios Gerenciais** | Dashboards em Oracle Apex para tomada de decisão estratégica e *compliance*. |

---

## 🏗️ Arquitetura e Tecnologia

A MedSave adota uma arquitetura moderna e escalável, utilizando o melhor de cada tecnologia:

* **Frontend & BI:** **Oracle Apex**
* **Backend & Microserviços:** **Java** e **C#**
* **Mobile:** **React Native**
* **Banco de Dados:** **Oracle DB**
* **Cloud:** **Azure**

---

## 🤝 Integrantes do Projeto

| Nome                                  | Função no Projeto          | LinkedIn | GitHub |
|---------------------------------------|----------------------------|----------|--------|
| Cleyton Enrike de Oliveira            | Desenvolvedor .NET & IOT   | [LinkedIn](https://www.linkedin.com/in/cleyton-enrike-de-oliveira99) | [@Cleytonrik99](https://github.com/Cleytonrik99) |
| Matheus Henrique Nascimento de Freitas| Desenvolvedor Mobile & DBA | [LinkedIn](https://www.linkedin.com/in/matheus-henrique-freitas)     | [@MatheusHenriqueNF](https://github.com/MatheusHenriqueNF) |
| Pedro Henrique Sena                   | Desenvolvedor Java & DevOps| [LinkedIn](https://www.linkedin.com/in/pedro-henrique-sena)          | [@devpedrosena1](https://github.com/devpedrosena1) |

---

## 🎬 Pitch

▶️ [**Assista ao vídeo da nossa solução**](https://youtu.be/jCSo9ISv7RY)

---
---

## 🎬 VIDEO SPRINT 4

▶️ [**Assista ao vídeo técnico explicativo**](https://www.youtube.com/watch?v=e75tdDZstnI)

---

### 🗃️ Diagrama de Entidade-Relacionamento (DER)

<div align="center">
  <img src="images/der.jpg" alt="Diagrama DER" style="max-width: 90%; border: 1px solid #ddd; border-radius: 4px;">
</div>

---
## ✨ Tecnologias

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?style=for-the-badge&logo=apachemaven&logoColor=white)

- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **H2 Database** (banco de dados local para testes)
- **Oracle DB** (banco de dados real/final)
- **Maven** (gerenciador de dependências)
- **Springdoc OpenAPI** (documentação Swagger UI)
- **Spring Security** (segurança)
- **Docker** (containers)
- **ActiveMQ** (microserviços)
---

---

# 🚀 Como Executar Localmente

## **Pré-requisitos**

Antes de rodar o projeto, certifique-se de ter os seguintes softwares instalados:

- **[Java 21+](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)**
- **[Maven 3.9+](https://maven.apache.org/download.cgi)**

---

## **Passos para Instalação e Execução**

### 1. **Clonar o repositório**

```bash
git clone https://github.com/oraclechallenge1/Oracle-Java-Advanced.git
```

### 2. **Acesse a pasta do projeto**

```bash
cd ProjectMedSave
```

### 3. **Compile o projeto**

```bash
mvn clean install
```
### 4. **Execute o projeto**

```bash
mvn spring-boot:run
```

O projeto iniciará em:  

👉 [http://localhost:8080](http://localhost:8080)

A documentação Swagger estará disponível em:  

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---
# 🚀 Como Executar o Docker

### 1. **Dê um pull na imagem docker**

```bash
docker pull devpedrosena1/project-med-save:4.0
```

### 2. **Rode o container**

```bash
docker run -p 8080:8080 devpedrosena1/project-med-save:4.0
```

O projeto iniciará em:  

👉 [http://localhost:8080](http://localhost:8080)

A documentação Swagger estará disponível em:  

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

---

###  NOSSO WEBAPP

[**NOSSO WEBAPP**](http://webapp-java-medsave-rm560485.azurewebsites.net/swagger-ui/index.html)
---


## 🌐 Mapeamento de Endpoints (API REST)

Os serviços de backend são acessados através da nossa API REST. Abaixo está o mapeamento dos principais *endpoints*.

"Link" é a âncora para as URIs de cada endpoint.

- Em nosso projeto, é obrigatório que o usuário esteja autenticado. Como funciona essa autenticação? É bem simples, o usuário deve se registrar em nosso app com informações básicas, e depois efetuar o login. Ao efeturar login, o usuário receberá um token único com expiração de 24h, e esse token será responsável por permitir que o usuário acesse todas as requisições do sistema. Caso o usuário já tenha um usuário cadastrado, basta efeturar login.

## User ("/api/v4/auth/register")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v4/auth/register`                   | Registra um usuário                              | [Link](http://localhost:8080/api/v4/auth/register)          |

```bash
{
  "name": "Orlando",
  "email": "prof@fiap.com.br",
  "password": "12345678",
  "phone": "1190909878",
  "roleId": 1,
  "profileId": 1
}
```

## User ("/api/v4/auth/login")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v4/auth/login`                   | Realiza login de um usuário                              | [Link](http://localhost:8080/api/v4/auth/login)          |

```bash
{
  "email": "prof@fiap.com.br",
  "password": "12345678"
}
```

## Medicines ("/api/v4/medicines")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v4/medicines`                        | Retorna todos os medicamentos.                                   | [Link](http://localhost:8080/api/v4/medicines)        |
| GET    | `/api/v4/medicines/{id}`                   | Retorna um medicamento específico por ID.                        | [Link](http://localhost:8080/api/v4/medicines/2)   |
| POST   | `/api/v4/medicines`                        | Cadastra um novo medicamento.                                    | [Link](http://localhost:8080/api/v4/medicines)        |
| DELETE | `/api/v4/medicines/{id}`                   | Remove um medicamento por ID.                                    | [Link](http://localhost:8080/api/v4/medicines/16)   |
| PUT    | `/api/v4/medicines/{id}`                   | Atualiza um medicamento específico por ID                        | [Link](http://localhost:8080/api/v4/medicines/2)   |

```bash
{
  "nameMedication": "test1",
  "statusMed": "ativo",
  "activeIngredientIds": [
    1, 4
  ],
  "pharmFormIds": [
    3
  ],
  "categoryMedicineId": 1,
  "unitMeasureId": 1
}
```

## UnitMeasure ("/api/v4/unit-measures")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v4/unit-measures`                    | Retorna todas as unidades de medida.                             | [Link](http://localhost:8080/api/v4/unit-measures)        |
| GET    | `/api/v4/unit-measure/{id}`                | Retorna uma unidade de medida específica por ID.                 | [Link](http://localhost:8080/api/v4/unit-measure/2)    |
| POST   | `/api/v4/unit-measures`                    | Cadastra uma nova unidade de medida.                             | [Link](http://localhost:8080/api/v4/unit-measures)        |
| DELETE | `/api/v4/unit-measures/{id}`               | Remove uma unidade de medida por ID.                             | [Link](http://localhost:8080/api/v4/unit-measures/16)   |
| PUT    | `/api/v4/unit-measure/{id}`                | Atualiza uma unidade de medida específica por ID                 | [Link](http://localhost:8080/api/v4/unit-measure/2)    |


```bash
{
  "unit": "test1"
}
```

## PharmaceuticalForm ("/api/v4/pharmaceutical-forms")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v4/pharmaceutical-forms`             | Retorna todas as formas farmacêuticas.                           | [Link](http://localhost:8080/api/v4/pharmaceutical-forms)        |
| GET    | `/api/v4/pharmaceutical-forms/{id}`        | Retorna uma forma farmacêutica específica por ID.                | [Link](http://localhost:8080/api/v4/pharmaceutical-forms/2)   |
| POST   | `/api/v4/pharmaceutical-forms`             | Cadastra uma nova forma farmacêutica.                            | [Link](http://localhost:8080/api/v4/pharmaceutical-forms)        |
| DELETE | `/api/v4/pharmaceutical-forms/{id}`        | Remove uma forma farmacêutica por ID.                            | [Link](http://localhost:8080/api/v4/pharmaceutical-forms/17)   |
| PUT    | `/api/v4/pharmaceutical-forms/{id}`        | Atualiza uma forma farmacêutica específica por ID                | [Link](http://localhost:8080/api/v4/pharmaceutical-forms/2)   |


```bash
{
  "name": "test1"
}
```

## ActiveIngredient ("/api/v4/active-ingredients")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v4/active-ingredients`               | Retorna todos os ingredientes ativos.                            | [Link](http://localhost:8080/api/v4/active-ingredients)        |
| GET    | `/api/v4/active-ingredients/{id}`          | Retorna um ingrediente ativo específico por ID.                  | [Link](http://localhost:8080/api/v4/active-ingredients/2)   |
| POST   | `/api/v4/active-ingredients`               | Cadastra um novo ingrediente ativo.                              | [Link](http://localhost:8080/api/v4/active-ingredients)        |
| DELETE | `/api/v4/active-ingredients/{id}`          | Remove um ingrediente ativo por ID.                              | [Link](http://localhost:8080/api/v4/active-ingredients/16)   |
| PUT    | `/api/v4/active-ingredients/{id}`          | Atualiza um ingrediente ativo específico por ID                  | [Link](http://localhost:8080/api/v4/active-ingredients/2)   |


```bash
{
  "nameActiveIngre": "test1"
}
```

## CategoryMedicine ("/api/v4/categories")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v4/categories`                       | Retorna todas as categorias.                                     | [Link](http://localhost:8080/api/v4/categories)        |
| GET    | `/api/v4/categories/{id}`                  | Retorna uma categoria específica por ID.                         | [Link](http://localhost:8080/api/v4/categories/2)   |
| POST   | `/api/v4/categories`                       | Cadastra uma nova categoria.                                     | [Link](http://localhost:8080/api/v4/categories)        |
| DELETE | `/api/v4/categories/{id}`                  | Remove uma categoria por ID.                                     | [Link](http://localhost:8080/api/v4/categories/16)   |
| PUT    | `/api/v4/categories/{id}`                  | Atualiza uma categoria específica por ID                         | [Link](http://localhost:8080/api/v4/categories/2)   |


```bash
{
  "categoryName": "test1"
}
```

## Batch ("/api/v4/batches/receipts")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v4/batches/receipts`                 | Cria um LOTE.                                                    | [Link](http://localhost:8080/api/v4/batches/receipts)        |

```bash
{
  "batchNumber": "LOTEtest",
  "manufacturingDate": "2025-10-09",
  "expirationDate": "2026-01-01",
  "quantity": 800,
  "medicineId": 2,
  "locationId": 2,
  "manufacturerId": 2
}

```

## Batch ("/api/v4/stock/transfer")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v4/stock/transfer`                   | Transfere de um estoque para outro.                              | [Link](http://localhost:8080/api/v4/stock/transfer)        |

```bash
{
  "medicineId": 2,
  "batchId": 2,
  "sourceLocationId": 2,
  "destinationLocationId": 13,
  "quantity": 5
}

```
![FIAP](https://img.shields.io/badge/FIAP-ED145B?style=for-the-badge)

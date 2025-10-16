![Diagrama DER MedSave](images/logo.png)

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
* **Cloud:** **Oracle Cloud Infrastructure**

---

## 🤝 Integrantes do Projeto

| Nome                                  | Função no Projeto          | LinkedIn | GitHub |
|---------------------------------------|----------------------------|----------|--------|
| Cleyton Enrike de Oliveira            | Desenvolvedor .NET & IOT   | [LinkedIn](https://www.linkedin.com/in/cleyton-enrike-de-oliveira99) | [@Cleytonrik99](https://github.com/Cleytonrik99) |
| Matheus Henrique Nascimento de Freitas| Desenvolvedor Mobile & DBA | [LinkedIn](https://www.linkedin.com/in/matheus-henrique-freitas)     | [@MatheusHenriqueNF](https://github.com/MatheusHenriqueNF) |
| Pedro Henrique Sena                   | Desenvolvedor Java & DevOps| [LinkedIn](https://www.linkedin.com/in/pedro-henrique-sena)          | [@devpedrosena1](https://github.com/devpedrosena1) |

---

## 📅 Cronograma

<div align="center">
  <img src="images/cronograma.png" alt="Cronograma do Projeto MedSave" style="max-width: 90%; border: 1px solid #ddd; border-radius: 4px;">
</div>

---

## 🎬 Pitch

▶️ [**Assista ao nosso vídeo demonstrativo no YouTube**](linkDoVideoYt)

---

## 📐 Modelagem de Dados

### 🎨 Diagrama de Classes (UML - Mermaid)

O diagrama abaixo representa as principais entidades e seus relacionamentos no ecossistema MedSave.

```mermaid

---
---
config:
  theme: redux-dark
  look: classic
---
classDiagram
%% =======================
%% GEO / ENDEREÇOS
%% =======================
class STATES {
  +NUMERIC STATE_ID <<PK>>
  VARCHAR(255) STATE_NAME
}
class CITY {
  +NUMERIC CITY_ID <<PK>>
  VARCHAR(255) NAME_CITY
  NUMERIC STATE_ID <<FK>>
}
class NEIGHBOURHOOD {
  +NUMERIC NEIGH_ID <<PK>>
  VARCHAR(255) NEIGH_NAME
  NUMERIC CITY_ID <<FK>>
}
class ADDRESS_STOCK {
  +NUMERIC ADDRESS_ID_STOCK <<PK>>
  VARCHAR(255) COMPLEMENT
  NUMERIC(7)  NUMBER_STOCK
  VARCHAR(255) ADDRESS_DESCRIPTION
  NUMERIC(8)  CEP
  NUMERIC NEIGH_ID <<FK>>
}
class ADDRESS_MANUFACTURER {
  +NUMERIC ADDRESS_ID_MANUFACTURER <<PK>>
  VARCHAR(255) COMPLEMENT
  NUMERIC(7)  NUMBER_MANUFACTURER
  VARCHAR(255) ADDRESS_DESCRIPTION
  NUMERIC(8)  CEP
  NUMERIC NEIGH_ID <<FK>>
}

STATES "1" --> "many" CITY : has
CITY "1" --> "many" NEIGHBOURHOOD : has
NEIGHBOURHOOD "1" --> "many" ADDRESS_STOCK : has
NEIGHBOURHOOD "1" --> "many" ADDRESS_MANUFACTURER : has

%% =======================
%% LOCAL / ESTOQUE / LOTE
%% =======================
class LOCATION {
  +NUMERIC LOCATION_ID <<PK>>
  VARCHAR(30) NAME_LOCATION
  VARCHAR(100) LOCATION_STOCK
  NUMERIC ADDRESS_ID_STOCK <<FK>>
}
class BATCH {
  +NUMERIC BATCH_ID <<PK>>
  VARCHAR(255) BATCH_NUMBER
  NUMERIC CURRENT_QUANTITY
  Date MANUFACTURING_DATE
  Date EXPIRATION_DATE
  NUMERIC MANUFAC_ID <<FK>>
}
class STOCK {
  +NUMERIC STOCK_ID <<PK>>
  NUMERIC(6) QUANTITY
  NUMERIC BATCH_ID <<FK>>
  NUMERIC MEDICINE_ID <<FK>>
  NUMERIC LOCATION_ID <<FK>>
}

ADDRESS_STOCK "1" --> "many" LOCATION : located at
LOCATION "1" --> "many" STOCK : stores
BATCH "1" --> "many" STOCK : provides
MEDICINES "1" --> "many" STOCK : stocked

%% =======================
%% MEDICAMENTO / CATEGORIA
%% =======================
class ACTIVE_INGREDIENT {
  +NUMERIC ACT_INGRE_ID <<PK>>
  VARCHAR(200) ACT_INGREDIENT
}
class PHARMACEUTICAL_FORM {
  +NUMERIC PHARM_FORM_ID <<PK>>
  VARCHAR(100) PHARMA_FORM
}
class UNIT_MEASURE {
  +NUMERIC UNIT_MEA_ID <<PK>>
  VARCHAR(20) UNIT_MEASURE_MEDICINE
}
class CATEGORY_MEDICINE {
  +NUMERIC CATEGORY_MED_ID <<PK>>
  VARCHAR(255) CATEGORY
}
class MEDICINES {
  +NUMERIC MEDICINE_ID <<PK>>
  VARCHAR(255) NAME_MEDICATION
  VARCHAR(20)  STATUS_MED
  NUMERIC CATEGORY_MED_ID <<FK>>
  NUMERIC UNIT_MEA_ID <<FK>>
  NUMERIC PHARM_FORM_ID <<FK>>
  NUMERIC ACT_INGRE_ID <<FK>>
}

ACTIVE_INGREDIENT "1" --> "many" MEDICINES : ingredient
PHARMACEUTICAL_FORM "1" --> "many" MEDICINES : form
UNIT_MEASURE "1" --> "many" MEDICINES : unit
CATEGORY_MEDICINE "1" --> "many" MEDICINES : category
MEDICINES "1" --> "many" STOCK : stocked

%% =======================
%% MOVIMENTAÇÃO / DISPENSAÇÃO
%% =======================
class MOVEMENT_TYPE {
  +NUMERIC MOVEMENT_TYPE_ID <<PK>>
  VARCHAR(30) TYPE_NAME
}
class MEDICINE_DISPENSE {
  +NUMERIC DISPENSATION_ID <<PK>>
  Date DATE_DISPENSATION
  NUMERIC(6) QUANTITY_DISPENSED
  VARCHAR(255) DESTINATION
  VARCHAR(255) OBSERVATION
  NUMERIC USER_ID <<FK>>
  NUMERIC MOVEMENT_TYPE_ID <<FK>>
  NUMERIC STOCK_ID <<FK>>
}

MOVEMENT_TYPE "1" --> "many" MEDICINE_DISPENSE : classifies
STOCK "1" --> "many" MEDICINE_DISPENSE : consumes

%% =======================
%% USUÁRIOS / PERFIS
%% =======================
class CONTACT_USER {
  +NUMERIC CONTACT_USER_ID <<PK>>
  VARCHAR(255) EMAIL_USER
  NUMERIC(11) PHONE_NUMBER_USER
}
class POSITION_USER {
  +NUMERIC POS_USER_ID <<PK>>
  VARCHAR(100) USER_POSITION
}
class PROFILE_USER {
  +NUMERIC PROF_USER_ID <<PK>>
  VARCHAR(50) USER_PROFILE
}
class USERS_SYS {
  +NUMERIC USER_ID <<PK>>
  VARCHAR(150) NAME_USER
  VARCHAR(50)  LOGIN <<U>>
  VARCHAR(255) PASSWORD_USER
  NUMERIC POS_USER_ID <<FK>>
  NUMERIC PROF_USER_ID <<FK>>
  NUMERIC CONTACT_USER_ID <<FK>>
}

POSITION_USER "1" --> "many" USERS_SYS : position
PROFILE_USER "1" --> "many" USERS_SYS : profile
CONTACT_USER "1" --> "1" USERS_SYS : contact
USERS_SYS "1" --> "many" MEDICINE_DISPENSE : performed by

```

---

### 🗃️ Diagrama de Entidade-Relacionamento (DER)

<div align="center">
  <img src="images/der.jpg" alt="Diagrama DER" style="max-width: 90%; border: 1px solid #ddd; border-radius: 4px;">
</div>

---
![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?style=for-the-badge&logo=apachemaven&logoColor=white)
![FIAP](https://img.shields.io/badge/FIAP-ED145B?style=for-the-badge)

- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **H2 Database** (banco de dados local para testes)
- **Oracle DB** (banco de dados real/final)
- **Maven** (gerenciador de dependências)
- **Springdoc OpenAPI** (documentação Swagger UI)
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
### 4. **CExecute o projeto**

```bash
mvn spring-boot:run
```

O projeto iniciará em:  

👉 [http://localhost:8080](http://localhost:8080)

A documentação Swagger estará disponível em:  

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🌐 Mapeamento de Endpoints (API REST)

Os microserviços de backend são acessados através da nossa API REST. Abaixo está o mapeamento dos principais *endpoints*.

## Medicines ("/api/v1/medicines")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v1/medicines`                        | Retorna todos os medicamentos.                                   | [Link](http://localhost:8080/api/v1/medicines)        |
| GET    | `/api/v1/medicines/{id}`                   | Retorna um medicamento específico por ID.                        | [Link](http://localhost:8080/api/v1/medicines/{id})   |
| POST   | `/api/v1/medicines`                        | Cadastra um novo medicamento.                                    | [Link](http://localhost:8080/api/v1/medicines)        |
| DELETE | `/api/v1/medicines/{id}`                   | Remove um medicamento por ID.                                    | [Link](http://localhost:8080/api/v1/medicines/{id})   |
| PUT    | `/api/v1/medicines/{id}`                   | Atualiza um medicamento específico por ID                        | [Link](http://localhost:8080/api/v1/medicines/{id})   |

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

## UnitMeasure ("/api/v1/unit-measures")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v1/unit-measures`                    | Retorna todas as unidades de medida.                             | [Link](http://localhost:8080/api/v1/unit-measures)        |
| GET    | `/api/v1/unit-measure/{id}`                | Retorna uma unidade de medida específica por ID.                 | [Link](http://localhost:8080/api/v1/unit-measure/{id})    |
| POST   | `/api/v1/unit-measures`                    | Cadastra uma nova unidade de medida.                             | [Link](http://localhost:8080/api/v1/unit-measures)        |
| DELETE | `/api/v1/unit-measures/{id}`               | Remove uma unidade de medida por ID.                             | [Link](http://localhost:8080/api/v1/unit-measures/{id})   |
| PUT    | `/api/v1/unit-measure/{id}`                | Atualiza uma unidade de medida específica por ID                 | [Link](http://localhost:8080/api/v1/unit-measure/{id})    |


```bash
{
  "unit": "test1"
}
```

## PharmaceuticalForm ("/api/v1/pharmaceutical-forms")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v1/pharmaceutical-forms`             | Retorna todas as formas farmacêuticas.                           | [Link](http://localhost:8080/api/v1/pharmaceutical-forms)        |
| GET    | `/api/v1/pharmaceutical-forms/{id}`        | Retorna uma forma farmacêutica específica por ID.                | [Link](http://localhost:8080/api/v1/pharmaceutical-forms/{id})   |
| POST   | `/api/v1/pharmaceutical-forms`             | Cadastra uma nova forma farmacêutica.                            | [Link](http://localhost:8080/api/v1/pharmaceutical-forms)        |
| DELETE | `/api/v1/pharmaceutical-forms/{id}`        | Remove uma forma farmacêutica por ID.                            | [Link](http://localhost:8080/api/v1/pharmaceutical-forms/{id})   |
| PUT    | `/api/v1/pharmaceutical-forms/{id}`        | Atualiza uma forma farmacêutica específica por ID                | [Link](http://localhost:8080/api/v1/pharmaceutical-forms/{id})   |


```bash
{
  "name": "test1"
}
```

## ActiveIngredient ("/api/v1/active-ingredients")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v1/active-ingredients`               | Retorna todos os ingredientes ativos.                            | [Link](http://localhost:8080/api/v1/active-ingredients)        |
| GET    | `/api/v1/active-ingredients/{id}`          | Retorna um ingrediente ativo específico por ID.                  | [Link](http://localhost:8080/api/v1/active-ingredients/{id})   |
| POST   | `/api/v1/active-ingredients`               | Cadastra um novo ingrediente ativo.                              | [Link](http://localhost:8080/api/v1/active-ingredients)        |
| DELETE | `/api/v1/active-ingredients/{id}`          | Remove um ingrediente ativo por ID.                              | [Link](http://localhost:8080/api/v1/active-ingredients/{id})   |
| PUT    | `/api/v1/active-ingredients/{id}`          | Atualiza um ingrediente ativo específico por ID                  | [Link](http://localhost:8080/api/v1/active-ingredients/{id})   |


```bash
{
  "nameActiveIngre": "test1"
}
```

## CategoryMedicine ("/api/v1/categories")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v1/categories`                       | Retorna todas as categorias.                                     | [Link](http://localhost:8080/api/v1/categories)        |
| GET    | `/api/v1/categories/{id}`                  | Retorna uma categoria específica por ID.                         | [Link](http://localhost:8080/api/v1/categories/{id})   |
| POST   | `/api/v1/categories`                       | Cadastra uma nova categoria.                                     | [Link](http://localhost:8080/api/v1/categories)        |
| DELETE | `/api/v1/categories/{id}`                  | Remove uma categoria por ID.                                     | [Link](http://localhost:8080/api/v1/categories/{id})   |
| PUT    | `/api/v1/categories/{id}`                  | Atualiza uma categoria específica por ID                         | [Link](http://localhost:8080/api/v1/categories/{id})   |


```bash
{
  "categoryName": "test1"
}
```

## Batch ("/api/v1/batches/receipts")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v1/batches/receipts`                 | Cria um LOTE.                                                    | [Link](http://localhost:8080/api/v1/batches/receipts)        |

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

## Batch ("/api/v1/stock/transfer")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v1/stock/transfer`                   | Transfere de um estoque para outro.                              | [Link](http://localhost:8080/api/v1/stock/transfer)        |

```bash
{
  "medicineId": 2,
  "batchId": 2,
  "sourceLocationId": 2,
  "destinationLocationId": 13,
  "quantity": 5
}

```


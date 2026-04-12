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
* **Cloud:** **Oracle Cloud Infrastructure**

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

## 🎬 VIDEO SPRINT 3

▶️ [**Assista ao vídeo da nossa API**](https://www.youtube.com/watch?v=e75tdDZstnI)

---

##  Modelagem de Dados

### 🎨 Diagrama de Classes (UML - Mermaid)

O diagrama abaixo representa as principais entidades e seus relacionamentos no ecossistema MedSave.

```mermaid

---
config:
  theme: mc
  look: neo
---
classDiagram
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
class LOCATION_STOCK {
  +NUMERIC LOCATION_ID_STOCK <<PK>>
  VARCHAR(30) NAME_LOCATION
  VARCHAR(100) LOCATION_STOCK_NAME
  NUMERIC ADDRESS_ID_STOCK <<FK>>
}
STATES "1" --> "many" CITY : has
CITY "1" --> "many" NEIGHBOURHOOD : has
NEIGHBOURHOOD "1" --> "many" ADDRESS_STOCK : has
NEIGHBOURHOOD "1" --> "many" ADDRESS_MANUFACTURER : has
ADDRESS_STOCK "1" --> "many" LOCATION_STOCK : located_at
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
  VARCHAR(255) CATEGORY_MED
}
class MEDICINES {
  +NUMERIC MEDICINE_ID <<PK>>
  VARCHAR(255) NAME_MEDICATION
  VARCHAR(20) STATUS_MED
  NUMERIC CATEGORY_MED_ID <<FK>>
  NUMERIC UNIT_MEA_ID <<FK>>
}
class MEDICINE_ACTIVE_INGR {
  +NUMERIC MED_ACTIVE_INGR_ID <<PK>>
  NUMERIC MEDICINE_ID <<FK>>
  NUMERIC ACT_INGRE_ID <<FK>>
}
class MEDICINE_PHARM_FORM {
  +NUMERIC MED_PHARM_FORM_ID <<PK>>
  NUMERIC MEDICINE_ID <<FK>>
  NUMERIC PHARM_FORM_ID <<FK>>
}
CATEGORY_MEDICINE "1" --> "many" MEDICINES : category
UNIT_MEASURE "1" --> "many" MEDICINES : unit
MEDICINES "1" --> "many" MEDICINE_ACTIVE_INGR : has
ACTIVE_INGREDIENT "1" --> "many" MEDICINE_ACTIVE_INGR : ingredient_of
MEDICINES "1" --> "many" MEDICINE_PHARM_FORM : has
PHARMACEUTICAL_FORM "1" --> "many" MEDICINE_PHARM_FORM : form_of
class MANUFACTURER {
  +NUMERIC MANUFAC_ID <<PK>>
  VARCHAR(255) NAME_MANU
  NUMERIC(14) CNPJ <<U>>
  NUMERIC ADDRESS_ID_MANUFACTURER <<FK>>
}
class CONTACT_MANUFACTURER {
  +NUMERIC CONTACT_MANU_ID <<PK>>
  VARCHAR(255) EMAIL_MANU
  NUMERIC(11) PHONE_NUMBER_MANU
  NUMERIC MANUFAC_ID <<FK>>
}
class BATCH_MEDICINE {
  +NUMERIC BATCH_ID <<PK>>
  VARCHAR(255) BATCH_NUMBER
  NUMERIC CURRENT_QUANTITY
  Date MANUFACTURING_DATE
  Date EXPIRATION_DATE
  NUMERIC MANUFAC_ID <<FK>>
  NUMERIC MEDICINE_ID <<FK>>
}
class STOCK {
  +NUMERIC STOCK_ID <<PK>>
  NUMERIC(6) QUANTITY
  NUMERIC BATCH_ID <<FK>>
  NUMERIC MEDICINE_ID <<FK>>
  NUMERIC LOCATION_ID_STOCK <<FK>>
}
ADDRESS_MANUFACTURER "1" --> "many" MANUFACTURER : located_at
MANUFACTURER "1" --> "many" CONTACT_MANUFACTURER : contact
MANUFACTURER "1" --> "many" BATCH_MEDICINE : produces
MEDICINES "1" --> "many" BATCH_MEDICINE : lot_for
BATCH_MEDICINE "1" --> "many" STOCK : stocked_lot
MEDICINES "1" --> "many" STOCK : stocked_item
LOCATION_STOCK "1" --> "many" STOCK : stored_at
class CONTACT_USER {
  +NUMERIC CONTACT_USER_ID <<PK>>
  VARCHAR(255) EMAIL_USER
  NUMERIC(11) PHONE_NUMBER_USER
}
class ROLE_USER {
  +NUMERIC ROLE_USER_ID <<PK>>
  VARCHAR(100) USER_ROLE
}
class PROFILE_USER {
  +NUMERIC PROF_USER_ID <<PK>>
  VARCHAR(50) USER_PROFILE
}
class USERS_SYS {
  +NUMERIC USER_ID <<PK>>
  VARCHAR(150) NAME_USER
  VARCHAR(50) LOGIN <<U>>
  VARCHAR(255) PASSWORD_USER
  NUMERIC ROLE_USER_ID <<FK>>
  NUMERIC PROF_USER_ID <<FK>>
  NUMERIC CONTACT_USER_ID <<FK>>
}
ROLE_USER "1" --> "many" USERS_SYS : role
PROFILE_USER "1" --> "many" USERS_SYS : profile
CONTACT_USER "1" --> "many" USERS_SYS : contact
class MEDICINE_DISPENSE {
  +NUMERIC DISPENSATION_ID <<PK>>
  Date DATE_DISPENSATION
  NUMERIC(6) QUANTITY_DISPENSED
  VARCHAR(255) DESTINATION
  VARCHAR(255) OBSERVATION
  NUMERIC USER_ID <<FK>>
}
class MOVEMENT_TYPE {
  +NUMERIC MOVEMENT_TYPE_ID <<PK>>
  VARCHAR(30) TYPE_NAME
}
class STOCK_MOVEMENT {
  +NUMERIC STOCK_MOVEMENT_ID <<PK>>
  NUMERIC(6) QUANTITY_DISPENSED
  Date DATE_MOVEMENT
  NUMERIC MOVEMENT_TYPE_ID <<FK>>
  NUMERIC STOCK_ID <<FK>>
  NUMERIC DISPENSATION_ID <<FK>>
  NUMERIC USER_ID <<FK>>
}
USERS_SYS "1" --> "many" MEDICINE_DISPENSE : dispensed_by
USERS_SYS "1" --> "many" STOCK_MOVEMENT : moved_by
STOCK "1" --> "many" STOCK_MOVEMENT : affects
MOVEMENT_TYPE "1" --> "many" STOCK_MOVEMENT : type
MEDICINE_DISPENSE "1" --> "many" STOCK_MOVEMENT : related_to


```


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
docker pull devpedrosena1/project-med-save:3.0
```

### 2. **Rode o container**

```bash
docker run -p 8080:8080 devpedrosena1/project-med-save:3.0
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

Os microserviços de backend são acessados através da nossa API REST. Abaixo está o mapeamento dos principais *endpoints*.

Caso queria uma outra opção de acesso as APIs, clique no link abaixo.

[Requisições via Postman](https://www.postman.com/pedrosena-442731/workspace/pedro-sena-s-workspace/collection/44355033-bd106e60-d82f-4d66-8015-839e5baae611?action=share&source=copy-link&creator=44355033)

"Link" é a âncora para as URIs de cada endpoint.

- Em nosso projeto, é obrigatório que o usuário esteja autenticado. Como funciona essa autenticação? É bem simples, o usuário deve se registrar em nosso app com informações básicas, e depois efetuar o login. Ao efeturar login, o usuário receberá um token único com expiração de 24h, e esse token será responsável por permitir que o usuário acesse todas as requisições do sistema. Caso o usuário já tenha um usuário cadastrado, basta efeturar login.

## User ("/api/v3/auth/register")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v3/auth/register`                   | Registra um usuário                              | [Link](http://localhost:8080/api/v3/auth/register)          |

```bash
{
  "name": Orlando,
  "email": prof@fiap.com.br,
  "password": "12345678",
  "phone": 1190909878,
  "roleId": 1,
  "profileId: 1"
}
```

## User ("/api/v3/auth/login")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v3/auth/login`                   | Realiza login de um usuário                              | [Link](http://localhost:8080/api/v3/auth/login)          |

```bash
{
  "email": "prof@fiap.com.br",
  "password": "12345678"
}
```

## Medicines ("/api/v3/medicines")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v3/medicines`                        | Retorna todos os medicamentos.                                   | [Link](http://localhost:8080/api/v3/medicines)        |
| GET    | `/api/v3/medicines/{id}`                   | Retorna um medicamento específico por ID.                        | [Link](http://localhost:8080/api/v3/medicines/2)   |
| POST   | `/api/v3/medicines`                        | Cadastra um novo medicamento.                                    | [Link](http://localhost:8080/api/v3/medicines)        |
| DELETE | `/api/v3/medicines/{id}`                   | Remove um medicamento por ID.                                    | [Link](http://localhost:8080/api/v3/medicines/16)   |
| PUT    | `/api/v3/medicines/{id}`                   | Atualiza um medicamento específico por ID                        | [Link](http://localhost:8080/api/v3/medicines/2)   |

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

## UnitMeasure ("/api/v3/unit-measures")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v3/unit-measures`                    | Retorna todas as unidades de medida.                             | [Link](http://localhost:8080/api/v3/unit-measures)        |
| GET    | `/api/v3/unit-measure/{id}`                | Retorna uma unidade de medida específica por ID.                 | [Link](http://localhost:8080/api/v3/unit-measure/2)    |
| POST   | `/api/v3/unit-measures`                    | Cadastra uma nova unidade de medida.                             | [Link](http://localhost:8080/api/v3/unit-measures)        |
| DELETE | `/api/v3/unit-measures/{id}`               | Remove uma unidade de medida por ID.                             | [Link](http://localhost:8080/api/v3/unit-measures/16)   |
| PUT    | `/api/v3/unit-measure/{id}`                | Atualiza uma unidade de medida específica por ID                 | [Link](http://localhost:8080/api/v3/unit-measure/2)    |


```bash
{
  "unit": "test1"
}
```

## PharmaceuticalForm ("/api/v3/pharmaceutical-forms")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v3/pharmaceutical-forms`             | Retorna todas as formas farmacêuticas.                           | [Link](http://localhost:8080/api/v3/pharmaceutical-forms)        |
| GET    | `/api/v3/pharmaceutical-forms/{id}`        | Retorna uma forma farmacêutica específica por ID.                | [Link](http://localhost:8080/api/v3/pharmaceutical-forms/2)   |
| POST   | `/api/v3/pharmaceutical-forms`             | Cadastra uma nova forma farmacêutica.                            | [Link](http://localhost:8080/api/v3/pharmaceutical-forms)        |
| DELETE | `/api/v3/pharmaceutical-forms/{id}`        | Remove uma forma farmacêutica por ID.                            | [Link](http://localhost:8080/api/v3/pharmaceutical-forms/17)   |
| PUT    | `/api/v3/pharmaceutical-forms/{id}`        | Atualiza uma forma farmacêutica específica por ID                | [Link](http://localhost:8080/api/v3/pharmaceutical-forms/2)   |


```bash
{
  "name": "test1"
}
```

## ActiveIngredient ("/api/v3/active-ingredients")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v3/active-ingredients`               | Retorna todos os ingredientes ativos.                            | [Link](http://localhost:8080/api/v3/active-ingredients)        |
| GET    | `/api/v3/active-ingredients/{id}`          | Retorna um ingrediente ativo específico por ID.                  | [Link](http://localhost:8080/api/v3/active-ingredients/2)   |
| POST   | `/api/v3/active-ingredients`               | Cadastra um novo ingrediente ativo.                              | [Link](http://localhost:8080/api/v3/active-ingredients)        |
| DELETE | `/api/v3/active-ingredients/{id}`          | Remove um ingrediente ativo por ID.                              | [Link](http://localhost:8080/api/v3/active-ingredients/16)   |
| PUT    | `/api/v3/active-ingredients/{id}`          | Atualiza um ingrediente ativo específico por ID                  | [Link](http://localhost:8080/api/v3/active-ingredients/2)   |


```bash
{
  "nameActiveIngre": "test1"
}
```

## CategoryMedicine ("/api/v3/categories")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| GET    | `/api/v3/categories`                       | Retorna todas as categorias.                                     | [Link](http://localhost:8080/api/v3/categories)        |
| GET    | `/api/v3/categories/{id}`                  | Retorna uma categoria específica por ID.                         | [Link](http://localhost:8080/api/v3/categories/2)   |
| POST   | `/api/v3/categories`                       | Cadastra uma nova categoria.                                     | [Link](http://localhost:8080/api/v3/categories)        |
| DELETE | `/api/v3/categories/{id}`                  | Remove uma categoria por ID.                                     | [Link](http://localhost:8080/api/v3/categories/16)   |
| PUT    | `/api/v3/categories/{id}`                  | Atualiza uma categoria específica por ID                         | [Link](http://localhost:8080/api/v3/categories/2)   |


```bash
{
  "categoryName": "test1"
}
```

## Batch ("/api/v3/batches/receipts")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v3/batches/receipts`                 | Cria um LOTE.                                                    | [Link](http://localhost:8080/api/v3/batches/receipts)        |

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

## Batch ("/api/v3/stock/transfer")

| Método | Endpoint                                   | Funcionalidade                                                   | URI                             |
|--------|--------------------------------------------|------------------------------------------------------------------|---------------------------------|
| POST   | `/api/v3/stock/transfer`                   | Transfere de um estoque para outro.                              | [Link](http://localhost:8080/api/v3/stock/transfer)        |

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

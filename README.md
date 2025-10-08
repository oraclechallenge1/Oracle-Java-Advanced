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
  NUMERIC(7) NUMBER_STOCK
  VARCHAR(255) ADDRESS_DESCRIPTION
  NUMERIC(8) CEP
  NUMERIC NEIGH_ID <<FK>>
}
class ADDRESSS_MANUFACTURER {
  +NUMERIC ADDRESS_ID_MANUFACTURER <<PK>>
  VARCHAR(255) COMPLEMENT
  NUMERIC(7) NUMBER_MANUFACTURER
  VARCHAR(255) ADDRESS_DESCRIPTION
  NUMERIC(8) CEP
  NUMERIC NEIGH_ID <<FK>>
}

STATES "1" --> "many" CITY : has
CITY "1" --> "many" NEIGHBOURHOOD : has
NEIGHBOURHOOD "1" --> "many" ADDRESS_STOCK : has
NEIGHBOURHOUD "1" --> "many" ADDRESSS_MANUFACTURER : has

%% =======================
%% LOCAL / ESTOQUE / LOTE
%% =======================
class LOCATION_STOCK {
  +NUMERIC LOCATION_ID <<PK>>
  VARCHAR(30) NAME_LOCATION
  VARCHAR(100) LOCATION_STOCK
  NUMERIC ADDRESS_ID_STOCK <<FK>>
}
class BATCH_MEDICINE {
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

ADDRESS_STOCK "1" --> "many" LOCATION_STOCK : located at
LOCATION_STOCK "1" --> "many" STOCK : stores
BATCH_MEDICINE "1" --> "many" STOCK : from batch

%% =======================
%% CADASTROS DE MEDICAMENTO
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
%% FABRICANTE
%% =======================
class CONTACT_MANUFACTURER {
  +NUMERIC CONTACT_MANU_ID <<PK>>
  VARCHAR(255) EMAIL_MANU
  NUMERIC(11) PHONE_NUMBER_MANU
}
class MANUFACTURER {
  +NUMERIC MANUFAC_ID <<PK>>
  VARCHAR(255) NAME_MANU
  NUMERIC(14) CNPJ
  NUMERIC CONTACT_MANU_ID <<FK>>
  NUMERIC ADDRESS_ID_MANUFACTURER <<FK>>
}

ADDRESSS_MANUFACTURER "1" --> "many" MANUFACTURER : located at
CONTACT_MANUFACTURER "1" --> "1" MANUFACTURER : contact of
MANUFACTURER "1" --> "many" BATCH_MEDICINE : produces

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
  <img src="images/Logical.jpg" alt="Diagrama DER" style="max-width: 90%; border: 1px solid #ddd; border-radius: 4px;">
</div>

---

## 🌐 Mapeamento de Endpoints (API REST)

Os microserviços de backend são acessados através da nossa API REST. Abaixo está o mapeamento dos principais *endpoints*.


## Medicines ("/api/medicines")

| Método | Endpoint                                   | Funcionalidade                                                   |
|--------|--------------------------------------------|------------------------------------------------------------------|
| GET    | `/api/medicines`                           | Retorna todos os medicamentos.                                   |
| GET    | `/api/medicines/{id}`                      | Retorna um medicamento específico por ID.                        |
| POST   | `/api/medicines`                           | Cadastra um novo medicamento.                                    |
| DELETE | `/api/medicines/{id}`                      | Remove um medicamento por ID.                                    |
| DELETE | `/api/medicines/removeObject`              | Remove todos os medicamentos.                                    |
| PUT    | `/api/medicines/{id}`                      | Atualiza um medicamento específico por ID                        |

## Medicines ("/api/medicines")

| Método | Endpoint                                   | Funcionalidade                                                   |
|--------|--------------------------------------------|------------------------------------------------------------------|
| GET    | `/api/medicines`                           | Retorna todos os medicamentos.                                   |
| GET    | `/api/medicines/{id}`                      | Retorna um medicamento específico por ID.                        |
| POST   | `/api/medicines`                           | Cadastra um novo medicamento.                                    |
| DELETE | `/api/medicines`                           | Remove um medicamento por ID.                                    |
| DELETE | `/api/medicines/removeObject`              | Remove todos os medicamentos.                                    |
| PUT    | `/api/medicines/{id}`                      | Atualiza um medicamento específico por ID                        |



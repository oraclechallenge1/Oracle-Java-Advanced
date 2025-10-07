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

---

## 🤝 Integrantes do Projeto

### `descricaoIntegrantes`

*Insira aqui a lista de membros da equipe, com nomes e funções, em formato de lista (ex: - Nome Sobrenome: Função)*

---

## 📅 Cronograma

### `fotoCronograma`

*Insira aqui o link ou a tag HTML para a imagem do cronograma do projeto.*

---

## 🎬 Demonstração da Plataforma

Veja como o MedSave funciona na prática, transformando o caos do estoque em clareza estratégica.

▶️ [**Assista ao nosso vídeo demonstrativo no YouTube**](linkDoVideoYt)

---

## 📐 Modelagem de Dados

### 🎨 Diagrama de Classes (UML - Mermaid)

O diagrama abaixo representa as principais entidades e seus relacionamentos no ecossistema MedSave.

```mermaid

---
config:
  theme: neo-dark
  layout: elk
  look: neo
---
classDiagram
%% =======================
%%   CORE – LOCALIDADE
%% =======================
class STATE {
  +NUMBER STATE_ID <<PK>>
  VARCHAR(265) STATE_NAME
}

class CITY {
  +NUMBER CITY_ID <<PK>>
  VARCHAR(255) NAME_CITY
  NUMBER STATE_ID <<FK>>
}          

class NEIGHBOURHOOD {
  +NUMBER NEIGH_ID <<PK>>
  VARCHAR(255) NEIGH_NAME
  NUMBER CITY_ID <<FK>>
}

class ADDRESS {
  +NUMBER ADDRESS_ID <<PK>>
  VARCHAR(265) COMPLEMENT
  VARCHAR(8)  CEP
  VARCHAR(265) DESCRIPTION
  VARCHAR(6)  NUMBER
  NUMBER NEIGH_ID <<FK>>
}

STATE "1" --> "many" CITY : has
CITY "1" --> "many" NEIGHBOURHOOD : has
NEIGHBOURHOOD "1" --> "many" ADDRESS : has

%% =======================
%%   CADASTROS – MEDICINA
%% =======================
class ACTIVE_INGREDIENT {
  +NUMBER ACT_INGRE_ID <<PK>>
  VARCHAR(200) ACT_INGREDIENT
}

class PHARMACEUTICAL_FORM {
  +NUMBER PHARM_FORM_ID <<PK>>
  VARCHAR(100) PHARMA_FORM
}

class UNIT_MEASURE {
  +NUMBER UNIT_MEA_ID <<PK>>
  VARCHAR(20) UNIT_MEASURE_MEDICINE
}

class CATEGORY_MEDICINE {
  +NUMBER CATEGORY_MED_ID <<PK>>
  VARCHAR(255) CATEGORY
}

class CONTACT_MANUFACTURER {
  +NUMBER CONTACT_ID <<PK>>
  VARCHAR EMAIL_MANU
  NUMBER  PHONE_NUMBER_MANU
}

class MANUFACTURER {
  +NUMBER MANUFAC_ID <<PK>>
  VARCHAR(255) NAME_MANU
  NUMBER(14) CNPJ
  NUMBER ADDRESS_ID <<FK>>
  NUMBER CONTACT_ID <<FK>>
}

class MEDICINES {
  +NUMBER MEDICINE_ID <<PK>>
  VARCHAR(255) NAME_MEDICATION
  VARCHAR(200) STATUS_MED
  NUMBER CATEGORY_MED_ID <<FK>>
  NUMBER UNIT_MEA_ID <<FK>>
  NUMBER PHARM_FORM_ID <<FK>>
  NUMBER ACT_INGRE_ID <<FK>>
  NUMBER MANUFAC_ID <<FK>>
}

ADDRESS "1" --> "many" MANUFACTURER : located at
CONTACT_MANUFACTURER "1" --> "1" MANUFACTURER : contact of
ACTIVE_INGREDIENT "1" --> "many" MEDICINES : composes
PHARMACEUTICAL_FORM "1" --> "many" MEDICINES : has form
UNIT_MEASURE "1" --> "many" MEDICINES : measured in
CATEGORY_MEDICINE "1" --> "many" MEDICINES : belongs to
MANUFACTURER "1" --> "many" MEDICINES : produces

%% =======================
%%   ESTOQUE / LOTE / LOCAL
%% =======================
class LOCATION {
  +NUMBER LOCATION_ID <<PK>>
  VARCHAR(30)  NAME_LOCATION
  VARCHAR(100) LOCATION
}

class BATCH {
  +NUMBER BATCH_ID <<PK>>
  VARCHAR(255) BATCH_NUMBER
  NUMBER CURRENT_QUANTITY
  DATE   MANUFACTURING_DATE
  DATE   EXPIRATION_DATE
}

class STOCK {
  +NUMBER STOCK_ID <<PK>>
  NUMBER QUANTITY
  NUMBER BATCH_ID <<FK>>
  NUMBER MEDICINE_ID <<FK>>
  NUMBER LOCATION_ID <<FK>>
}

LOCATION "1" --> "many" STOCK : stores
BATCH "1" --> "many" STOCK : provides
MEDICINES "1" --> "many" STOCK : stocked

%% =======================
%%   MOVIMENTAÇÃO / DISPENSAÇÃO
%% =======================
class MOVEMENT_TYPE {
  +NUMBER MOVEMENT_TYPE_ID <<PK>>
  VARCHAR(30) TYPE
}

class MEDICINE_DISPENSE {
  +NUMBER DISPENSATION_ID <<PK>>
  DATE  DATE_DISPENSATION
  NUMBER QUANTITY_DISPENSED
  VARCHAR(265) DESTINATION
  VARCHAR(255) OBSERVATION
  NUMBER MOVEMENT_TYPE_ID <<FK>>
  NUMBER USER_ID <<FK>>
  NUMBER MEDICINE_ID <<FK>>
  NUMBER BATCH_ID <<FK>>
}

MOVEMENT_TYPE "1" --> "many" MEDICINE_DISPENSE : classifies
USERS_SYS "1" --> "many" MEDICINE_DISPENSE : performed by
MEDICINES "1" --> "many" MEDICINE_DISPENSE : references
BATCH "1" --> "many" MEDICINE_DISPENSE : from batch

%% =======================
%%   USUÁRIOS / PERMISSÕES
%% =======================
class CONTACT_USER {
  +NUMBER CONTACT_ID <<PK>>
  VARCHAR EMAIL_USER
  NUMBER  PHONE_NUMBER_USER
}

class POSITION_USER {
  +NUMBER POS_USER_ID <<PK>>
  VARCHAR(100) USER_POSITION
}

class PROFILE_USER {
  +NUMBER PROF_USER_ID <<PK>>
  VARCHAR(50) USER_PROFILE
}

class USERS_SYS {
  +NUMBER USER_ID <<PK>>
  VARCHAR(150) NAME_USER
  VARCHAR(50)  LOGIN
  VARCHAR(255) PASSWORD_USER
  NUMBER POS_USER_ID <<FK>>
  NUMBER PROF_USER_ID <<FK>>
  NUMBER CONTACT_ID <<FK>>
}

POSITION_USER "1" --> "many" USERS_SYS : role
PROFILE_USER "1" --> "many" USERS_SYS : profile
CONTACT_USER "1" --> "1" USERS_SYS : contact
```

---

### 🗃️ Diagrama de Entidade-Relacionamento (DER)

<div align="center">
  <img src="images/der.jpg" alt="Cronograma do Projeto MedSave" style="max-width: 90%; border: 1px solid #ddd; border-radius: 4px;">
</div>

---

## 🌐 Mapeamento de Endpoints (API REST)

Os microserviços de backend são acessados através da nossa API REST. Abaixo está o mapeamento dos principais *endpoints*.


## Medicines

#### Retorna todos os itens

```http
  GET /api/items
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `api_key` | `string` | **Obrigatório**. A chave da sua API |

#### Retorna um item

```http
  GET /api/items/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Obrigatório**. O ID do item que você quer |

#### add(num1, num2)

Recebe dois números e retorna a sua soma.



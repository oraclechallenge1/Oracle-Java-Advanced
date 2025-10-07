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
  look: classic
---
classDiagram
class STATE {
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
  NUMERIC(7)  NUMBER
  VARCHAR(255) DESCRIPTION
  NUMERIC(8)  CEP
  NUMERIC NEIGH_ID <<FK>>
}
class ADDRESS_MANUFACTURER {
  +NUMERIC ADDRESS_ID_MANUFACTURER <<PK>>
  VARCHAR(255) COMPLEMENT
  NUMERIC(7)  NUMBER
  VARCHAR(255) DESCRIPTION
  NUMERIC(8)  CEP
  NUMERIC NEIGH_ID <<FK>>
}
STATE "1" --> "many" CITY : has
CITY "1" --> "many" NEIGHBOURHOOD : has
NEIGHBOURHOOD "1" --> "many" ADDRESS_STOCK : has
NEIGHBOURHOOD "1" --> "many" ADDRESS_MANUFACTURER : has
class LOCATION {
  +NUMERIC LOCATION_ID <<PK>>
  VARCHAR(30)  NAME_LOCATION
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
BATCH "1" --> "many" STOCK : provides
LOCATION "1" --> "many" STOCK : stores
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
class CONTACT_MANUFACTURER {
  +NUMERIC CONTACT_MANU_ID <<PK>>
  VARCHAR(255) EMAIL_MANU
  NUMERIC(11)  PHONE_NUMBER_MANU
}
class MANUFACTURER {
  +NUMERIC MANUFAC_ID <<PK>>
  VARCHAR(255) NAME_MANU
  NUMERIC(14)  CNPJ
  NUMERIC CONTACT_MANU_ID <<FK>>
  NUMERIC ADDRESS_ID_MANUFACTURER <<FK>>
}
ADDRESS_MANUFACTURER "1" --> "many" MANUFACTURER : located at
CONTACT_MANUFACTURER "1" --> "1" MANUFACTURER : contact of
MANUFACTURER "1" --> "many" BATCH : produces
class MOVEMENT_TYPE {
  +NUMERIC MOVEMENT_TYPE_ID <<PK>>
  VARCHAR(30) TYPE
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
class CONTACT_USER {
  +NUMERIC CONTACT_USER_ID <<PK>>
  VARCHAR(255) EMAIL_USER
  NUMERIC(11)  PHONE_NUMBER_USER
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
  <img src="images/Logical.png" alt="Cronograma do Projeto MedSave" style="max-width: 90%; border: 1px solid #ddd; border-radius: 4px;">
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



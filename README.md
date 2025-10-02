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
direction LR
    class Medicine {
	    -Long medicineId
	    -String comercialName
	    -String activeIngredient
	    -String pharmaceuticalForm
	    -String unitMeasure
	    -String manufacturer
	    -String anvisaRecord
	    -String categoryMedicine
	    -Integer minimalStock
	    -String status
    }
    class StockBatch {
	    -Long batchId
	    -String numberBatch
	    -LocalDate dateEntry
	    -LocalDate dateValidity
	    -Long currentQuantity
	    -BigDecimal unitaryCost
	    -String locationMedicine
	    -String supplier
	    -String status
	    -Long medicineId
    }
    class Dispensation {
	    -Long dispensationId
	    -LocalDate dateDispensation
	    -Integer withdrawalAmount
	    -String sectorDestiny
	    -String movementType
	    -String observation
	    -Long userId
	    -Long batchId
    }
    class UserSys {
	    -Long userId
	    -String nameUser
	    -String positionUser
	    -String login
	    -String passwordUser
	    -String profileUser
	    -String email
	    -String phoneNumber
    }
	<<Class>> Medicine
	<<Class>> StockBatch
	<<Class>> Dispensation
	<<Class>> UserSys
    Medicine "1" --|> "n" StockBatch
    StockBatch "1" --|> "n" Dispensation
    UserSys "1" --|> "n" Dispensation
	style Medicine :,stroke-width:1px,stroke-dasharray:none,stroke:#374D7C,fill:#E2EBFF,color:#374D7C
	style StockBatch :,stroke-width:1px,stroke-dasharray:none,stroke:#374D7C,fill:#E2EBFF,color:#374D7C
	style Dispensation :,stroke-width:1px,stroke-dasharray:none,stroke:#374D7C,fill:#E2EBFF,color:#374D7C
	style UserSys :,stroke-width:1px,stroke-dasharray:none,stroke:#374D7C,fill:#E2EBFF,color:#374D7C
	class Medicine:::Sky
	class StockBatch:::Sky
	class Dispensation:::Sky
	class UserSys:::Sky
```

---

### 🗃️ Diagrama de Entidade-Relacionamento (DER)

<div align="center">
  <img src="images/der.jpg" alt="Cronograma do Projeto MedSave" style="max-width: 90%; border: 1px solid #ddd; border-radius: 4px;">
</div>

---

## 🌐 Mapeamento de Endpoints (API REST)

Os microserviços de backend são acessados através da nossa API REST. Abaixo está o mapeamento dos principais *endpoints*.


## Documentação da API

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



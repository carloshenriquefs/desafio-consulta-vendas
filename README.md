# Desafio: Consulta vendas

Trata-se de um sistema de vendas (Sale) e vendedores (Seller). Cada venda está para um vendedor, e um vendedor pode ter várias vendas.

##

![desafioConsulta drawio](https://github.com/carloshenriquefs/desafio-consulta-vendas/assets/54969405/eaf154aa-a1cd-45f1-87f2-bb688100a428)

##

### 1 - Sumário de vendas por vendedor

* ```GET``` - /sales/summary?minDate=2022-01-01&maxDate=2022-06-30

```
[
  {
    "sellerName": "Anakin",
    "total": 110571.0
  },
  {
    "sellerName": "Logan",
    "total": 83587.0
  },
  {
    "sellerName": "Loki Odinson",
    "total": 150597.0
  },
  {
    "sellerName": "Padme",
    "total": 135902.0
  },
  {
    "sellerName": "Thor Odinson",
    "total": 144896.0
  }
]
```

##

### 2 - Sumário de vendas por vendedor

* ```GET``` /sales/summary

##

### 3 - Relatório de vendas

* ```GET``` /sales/report

##

### 4 - Relatório de vendas

* ```GET``` /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson

```
{
  "content": [
    {
      "id": 9,
      "date": "2022-05-22",
      "amount": 19476.0,
      "sellerName": "Loki Odinson"
    },
    {
      "id": 10,
      "date": "2022-05-18",
      "amount": 20530.0,
      "sellerName": "Thor Odinson"
    },
    {
      "id": 12,
      "date": "2022-05-06",
      "amount": 21753.0,
      "sellerName": "Loki Odinson"
    }
],
...
```

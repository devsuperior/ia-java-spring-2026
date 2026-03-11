---
name: issue-to-brief
description: Analisa uma issue do GitHub e transforma em um briefing tecnico acionavel para este projeto Spring Boot. Use antes de implementar, quando o pedido envolver issue, ticket, backlog, bug, feature, requisito ou criterios de aceite, para mapear impacto no codigo, preservar contrato HTTP e preparar handoff para refactor e testes.
argument-hint: [issue] [contexto adicional] [restricoes ou contrato a preservar]
---

# Issue To Brief

Esta skill conduz a etapa de entendimento e preparacao antes da implementacao.

## Quando usar esta skill

Use esta skill quando precisar:

- ler uma issue do GitHub e converter em briefing tecnico objetivo
- separar escopo confirmado de suposicoes antes de codar
- mapear impacto provavel no backend Spring Boot deste repositorio
- preparar handoff claro para refactor e para testes

Nao use esta skill para resumir o trabalho ja concluido. Nesse caso, use a skill `pr-summary`.

## Objetivo

Reduzir ambiguidade da issue e deixar a implementacao pronta para seguir com contexto suficiente para os proximos agents.

## Procedimento

1. Ler a issue e, se existirem, comentarios, checklist, labels e criterios de aceite via GitHub MCP.
2. Resumir o objetivo funcional em linguagem direta.
3. Separar fatos confirmados de inferencias.
4. Mapear impacto provavel no codigo deste repositorio.
5. Destacar invariantes observaveis que nao devem mudar.
6. Preparar um handoff explicito para o custom agent de refatoracao.
7. Preparar um handoff explicito para o custom agent de testes.

## Saida obrigatoria

Responder com estas secoes, nesta ordem:

```text
Resumo da issue
- Objetivo:
- Contexto:
- Criterios de aceite:

Escopo confirmado
- O que precisa mudar:
- O que nao foi pedido:

Impacto provavel no codigo
- Controller:
- Service:
- Repository:
- Entity:
- DTO:
- Util/Exception:
- Testes existentes relacionados:

Invariantes e restricoes
- Contrato HTTP a preservar:
- Headers relevantes:
- Estrutura de sucesso e erro:
- Restricoes tecnicas:

Pontos em aberto
- Duvidas:
- Suposicoes adotadas:

Handoff para refactor
- Arquivos ou classes candidatas:
- Resultado esperado:
- Cuidados obrigatorios:

Handoff para test
- Classes a validar:
- Cenarios minimos:
- Evidencias esperadas:
```

## Como analisar neste repositorio

Ao analisar impacto provavel, considerar primeiro:

- `src/main/java/br/com/devsuperior/dev_xp_ai/controller`
- `src/main/java/br/com/devsuperior/dev_xp_ai/service`
- `src/main/java/br/com/devsuperior/dev_xp_ai/repository`
- `src/main/java/br/com/devsuperior/dev_xp_ai/entity`
- `src/main/java/br/com/devsuperior/dev_xp_ai/dto`
- `src/main/java/br/com/devsuperior/dev_xp_ai/exception`
- `src/main/java/br/com/devsuperior/dev_xp_ai/util`
- `src/test/java/br/com/devsuperior/dev_xp_ai`

## Como preencher a saida

- Em `Resumo da issue`, priorize objetivo funcional, contexto operacional e criterios de aceite explicitos.
- Em `Escopo confirmado`, diferencie pedido explicito de extrapolacao tecnica.
- Em `Impacto provavel no codigo`, cite apenas camadas plausiveis; se uma camada nao parecer afetada, diga isso.
- Em `Invariantes e restricoes`, trate contrato HTTP observavel como item critico: path, verbo, status, headers e JSON.
- Em `Pontos em aberto`, registre lacunas reais antes de sugerir implementacao.
- Nos handoffs, escreva instrucoes que possam ser usadas diretamente pelos agents `refactor` e `test`.

## Regras

1. Nao comecar implementando.
2. Nao inventar requisito ausente na issue sem marcar como suposicao.
3. Se a issue tocar endpoint existente, explicitar que path, verbo, status code, headers e JSON observavel devem ser preservados salvo pedido explicito em contrario.
4. Se a issue estiver incompleta, registrar as lacunas antes do handoff.
5. Sempre produzir handoff utilizavel pelos custom agents `refactor` e `test`.

## Como preparar o handoff para os agents

Para o `refactor`:

- dizer qual comportamento deve ser criado ou alterado
- listar classes e pacotes provaveis
- destacar contrato HTTP e restricoes de persistencia
- apontar riscos de regressao

Para o `test`:

- listar classes alteradas ou provaveis
- informar cenarios de sucesso e erro
- cobrar validacao de status, body, headers e cobertura

## Exemplo de uso

Entrada:

```text
/issue-to-brief issue #456 sobre ajuste de validacao no endpoint de clientes sem mudar o contrato HTTP
```

Saida esperada:

- briefing com escopo confirmado e lacunas visiveis
- impacto provavel no codigo deste projeto
- handoff claro para refactor e para test

## Resultado esperado

Um bom resultado reduz ambiguidade da issue e deixa a implementacao pronta para seguir, com contexto suficiente para o agent de refatoracao atuar primeiro e o agent de testes atuar em seguida.

---
name: pr-summary
description: Gera um resumo tecnico final de implementacao para demo, comentario de pull request ou fechamento de tarefa. Use quando a mudanca ja tiver sido implementada e validada e voce precisar consolidar evidencias, testes, cobertura, riscos e relacao com a issue sem inventar fatos.
argument-hint: [issue ou contexto] [arquivos alterados] [testes/comandos executados]
---

# PR Summary

Esta skill conduz o fechamento tecnico depois da implementacao e da validacao.

## Quando usar esta skill

Use esta skill quando precisar:

- transformar uma implementacao concluida em um resumo final apresentavel
- preparar texto para comentario de PR, demo ou encerramento de tarefa
- consolidar mudancas, testes executados, cobertura, riscos e pendencias com base em evidencia

Nao use esta skill para entender uma issue antes de implementar. Nesse caso, use a skill `issue-to-brief`.

## Objetivo

Produzir um resumo final consistente, auditavel e facil de apresentar, preservando a separacao entre fato observado e inferencia.

## Procedimento

1. Reunir o contexto da issue original.
2. Reunir o que foi alterado no codigo de producao.
3. Reunir o que foi alterado nos testes.
4. Consolidar comandos executados e seus resultados relevantes.
5. Consolidar cobertura, quando disponivel.
6. Registrar riscos, limitacoes e pendencias.
7. Fechar com uma relacao explicita entre issue, implementacao e validacao.

## Saida obrigatoria

Responder com estas secoes, nesta ordem:

```text
Resumo final
- Issue:
- Objetivo entregue:

Implementacao
- O que mudou:
- Principais classes afetadas:
- Decisoes tecnicas relevantes:

Testes e validacao
- Testes criados ou ajustados:
- Comandos executados:
- Resultado dos testes:
- Cobertura JaCoCo:

Contrato e riscos
- Contrato HTTP preservado ou alterado:
- Riscos e limitacoes:
- Pendencias:

Pronto para PR
- Resumo curto para PR:
- Relacao com a issue:
```

## Como preencher a saida

- Use apenas fatos que estejam visiveis no contexto, no diff, nos arquivos alterados ou nos resultados de comando.
- Se algum ponto relevante nao estiver disponivel, diga explicitamente que a evidencia nao foi encontrada.
- Ao mencionar cobertura, informe numeros somente quando houver relatorio ou saida concreta.
- Ao mencionar contrato HTTP, deixe claro se o contrato foi preservado ou alterado e cite o que mudou.
- Ao mencionar riscos, prefira riscos observaveis, regressivos ou de rollout, e nao frases genericas.

## Regras

1. Nao afirmar execucao, cobertura ou sucesso de testes sem evidencia no contexto.
2. Separar claramente fato observado de inferencia.
3. Se nao houver cobertura disponivel, dizer explicitamente.
4. Se a issue tiver criterios de aceite, dizer como cada um foi atendido ou o que ficou pendente.
5. Se houve mudanca de contrato HTTP, explicitar de forma objetiva e concreta.

## Exemplo de uso

Entrada:

```text
/pr-summary issue #123, alteracoes no endpoint de pedidos, testes de integracao executados
```

Saida esperada:

- resumo final com secoes fixas
- relacao objetiva entre issue, implementacao e validacao
- riscos e pendencias explicitados sem exagero

## Fechamento curto para demonstracao

Quando fizer sentido, termine com um bloco curto de resumo executivo contendo:

- objetivo da issue
- o que foi implementado
- como foi validado
- se esta pronto para revisao/PR

## Resultado esperado

Um bom resultado deixa a demo com um encerramento claro: contexto da issue, implementacao feita, testes executados, cobertura reportada e riscos remanescentes.

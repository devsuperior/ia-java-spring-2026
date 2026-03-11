---
name: pr-summary
description: Gera um resumo tecnico final de implementacao pronto para demonstracao, comentario de pull request ou fechamento de tarefa. Use quando o trabalho ja tiver sido implementado e testado, especialmente apos usar custom agents de refatoracao e testes. Ajuda a consolidar mudancas, comandos executados, cobertura, riscos, relacao com a issue e proximos passos sem omitir evidencias.
---

# Skill pr-summary

Conduza a etapa final de fechamento tecnico depois da implementacao e da validacao.

## Objetivo

Produzir um resumo final consistente, auditavel e facil de apresentar.

## Fluxo

1. Reunir o contexto da issue original.
2. Reunir o que foi alterado no codigo de producao.
3. Reunir o que foi alterado nos testes.
4. Consolidar comandos executados e seus resultados relevantes.
5. Consolidar cobertura, quando disponivel.
6. Registrar riscos, limitacoes e pendencias.
7. Fechar com uma relacao explicita entre issue, implementacao e validacao.

## Estrutura de saida obrigatoria

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

## Regras

1. Nao afirmar execucao, cobertura ou sucesso de testes sem evidencia no contexto.
2. Separar claramente fato observado de inferencia.
3. Se nao houver cobertura disponivel, dizer explicitamente.
4. Se a issue tiver criterios de aceite, dizer como cada um foi atendido ou o que ficou pendente.
5. Se houve mudanca de contrato HTTP, explicitar de forma objetiva e concreta.

## Fechamento curto para demonstracao

Quando fizer sentido, terminar com um bloco curto de resumo executivo contendo:

- objetivo da issue
- o que foi implementado
- como foi validado
- se esta pronto para revisao/PR

## Resultado esperado

Um bom resultado deixa a demo com um encerramento claro: contexto da issue, implementacao feita, testes executados, cobertura reportada e riscos remanescentes.

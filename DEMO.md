# Demo: GitHub Copilot + MCP + Skills + Custom Agents

Este material foi escrito para quem está assistindo à demonstração.

## O que está acontecendo nesta demo

Nesta apresentação, uma issue do GitHub é usada como ponto de partida para uma implementação real dentro deste projeto. Em vez de depender de um único comando genérico, o fluxo foi dividido em etapas com responsabilidades claras.

O objetivo é mostrar como diferentes mecanismos do GitHub Copilot podem trabalhar juntos:

- `GitHub MCP` traz o contexto externo da issue
- a skill `issue-to-brief` transforma esse contexto em um briefing técnico
- o custom agent `refactor` implementa a mudança no código de produção
- o custom agent `test` valida o resultado com testes automatizados
- a skill `pr-summary` organiza o fechamento técnico da execução

## Conceitos principais

### MCP

O MCP funciona como uma ponte entre o agente e sistemas externos. Nesta demo, ele é usado para acessar as informações da issue no GitHub sem depender de cópia manual para o prompt.

### Skill

Uma skill não é o componente que executa a mudança no código. Ela serve para orientar o raciocínio do agente em uma etapa específica do fluxo.

Nesta demo, duas skills são usadas:

- `issue-to-brief`: organiza a issue em um briefing técnico acionável
- `pr-summary`: consolida o resultado final em um resumo claro e rastreável

### Custom agent

Um custom agent é um especialista com instruções próprias para um tipo de tarefa.

Nesta demo, há dois:

- `refactor`: cuida da implementação no código de produção
- `test`: cuida da validação por meio de testes automatizados

## Fluxo da demonstração

### 1. A issue é lida e entendida

A primeira etapa não é alterar código. Primeiro, o sistema lê a issue do GitHub e organiza o problema em termos técnicos.

### 2. O problema vira um briefing técnico

Depois de ler a issue, a skill `issue-to-brief` transforma o conteúdo em algo mais útil para desenvolvimento.

Esse briefing normalmente destaca:

- objetivo da mudança
- critérios de aceite
- impacto provável no código
- riscos de regressão
- partes do contrato HTTP que devem ser preservadas

### 3. A implementação vai para um especialista em código

Com o briefing pronto, o trabalho segue para o custom agent `refactor`.

Esse agent foi configurado para atuar especificamente em refatoração e implementação de código de produção, com foco arquitetural e com menor risco de regressão.

### 4. A validação vai para um especialista em testes

Depois da implementação, a tarefa segue para o custom agent `test`.

Isso mostra que testes não foram tratados como uma consequência opcional da implementação. Eles entram como uma etapa própria, com foco próprio e com critérios objetivos de validação.

### 5. O resultado final é consolidado

Por fim, a skill `pr-summary` organiza um fechamento técnico da execução.

Esse fechamento conecta:

- a issue original
- o que foi alterado no código
- o que foi validado em testes
- riscos, limitações e pendências

## Prompts usados na apresentação

Os prompts abaixo são os que serão usados durante a demonstração.

### Prompt 1: Ler a issue e gerar o briefing

```text
Leia a issue #2 do repositorio devsuperior/ia-java-spring-2026 via GitHub MCP.

Use a skill `issue-to-brief` #file:.github/skills/issue-to-brief/SKILL.md para transformar a issue em um briefing técnico acionável para este projeto
```

### Prompt 2: Implementar com o agent de refatoração

```text
Agora implemente a issue com o custom agent `refactor`, seguindo o briefing gerado.

Preserve o contrato HTTP existente, faça mudanças incrementais e ao final entregue a lista de classes que precisam ser analisadas pelo agent de testes.
```

### Prompt 3: Validar com o agent de testes

(altere o agente antes de executar o prompt)
```text
Agora use o custom agent `test` para criar ou ajustar os testes necessários com base nas classes listadas pela etapa anterior.

Execute os testes relevantes, informe os comandos executados, o resultado e a cobertura JaCoCo quando disponível.
```

### Prompt 4: Gerar o fechamento final

Antes faça:
- altere a branch para `feature/tabela`
- git add e git commit das mudanças
- git push

```text
Use a skill `pr-summary` #file:.github/skills/pr-summary/SKILL.md  para gerar o fechamento técnico desta execução.
O commit e push já foi feito, mapeie a branch atual e seguida para próxima tarefa.
Faça o PR dessa branch para a main, usando via GitHub MCP, usando o resumo curto gerado e referenciando a issue #2.
```

## O que observar durante a apresentação

Durante a demo, vale prestar atenção em cinco sinais:

1. O sistema primeiro entende a issue antes de alterar qualquer arquivo.
2. O contexto da tarefa vem do GitHub, não de uma descrição manual resumida.
3. A responsabilidade é distribuída entre skill e custom agents.
4. O teste aparece como etapa de validação, não como detalhe opcional.
5. O fechamento final conecta problema, implementação e evidências.

## Estrutura usada no repositório

Os arquivos mais relevantes para esta demonstração são:

- `.github/agents/refactor.agent.md`
- `.github/agents/test.agent.md`
- `.github/skills/issue-to-brief/SKILL.md`
- `.github/skills/pr-summary/SKILL.md`
- `ISSUE-SEPARAR-USUARIO-EXPERIENCIA.md`

## Resultado esperado

Ao final da apresentação, a expectativa é que fique claro que:

- o MCP traz contexto externo de forma integrada
- skills ajudam a organizar e orientar o fluxo
- custom agents permitem especialização por responsabilidade
- implementação e validação podem ser encadeadas de forma mais disciplinada

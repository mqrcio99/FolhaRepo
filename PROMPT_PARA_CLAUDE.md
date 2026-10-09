# Prompt para o Claude

Analise este repositório considerando o código-fonte como fonte principal da verdade. O projeto se chama **FolhaRepo** e é um sistema de gestão de folha de pagamento escrito em Java. Quero que você primeiro entenda o estado atual do projeto antes de propor ou implementar mudanças.

## Contexto do app

O módulo principal é `GestaoFolhaPGT`, no pacote `gestaofolhapgt`. A aplicação atual é executada pelo terminal e apresenta um menu para:

- cadastrar funcionários assalariados, informando nome, CPF, salário-base e bônus;
- cadastrar funcionários horistas, informando nome, CPF, valor por hora e horas trabalhadas;
- listar funcionários cadastrados, com os dados e o pagamento calculado;
- exibir o custo total da folha.

## Organização e regras atuais

- `GestaoFolhaPGT.java`: ponto de entrada (`main`) e menu interativo com `Scanner`.
- `GerenciadorRH.java`: mantém uma lista de `Funcionario`, cadastra e lista funcionários, busca por CPF e soma os pagamentos.
- `Funcionario.java`: classe abstrata com nome, CPF e salário-base; declara `calcularPagamento()`.
- `FuncionarioAssalariado.java`: especialização cujo pagamento é salário-base mais bônus.
- `FuncionarioHorista.java`: especialização cujo pagamento é horas trabalhadas multiplicadas pelo valor por hora.

O cálculo da folha usa polimorfismo chamando `calcularPagamento()` em cada funcionário. A implementação observada não demonstra persistência em banco ou arquivo, interface gráfica, cálculo de INSS/IR, emissão de contracheques nem relatórios além da listagem e do total; trate esses itens do README como funcionalidades anunciadas, não como funcionalidades confirmadas no código.

## Atenção ao estado do repositório

Foram encontrados marcadores de conflito de merge (`<<<<<<<`, `=======`, `>>>>>>>`) em `GestaoFolhaPGT.java`, `FuncionarioAssalariado.java` e `FuncionarioHorista.java`. Isso indica que os arquivos podem não compilar e que há versões conflitantes de implementação. Antes de sugerir mudanças maiores, inspecione esses arquivos e identifique a intenção compatível com o restante do projeto. Não remova conteúdo conflitante às cegas nem presuma que o README está atualizado.

## Como trabalhar neste projeto

1. Confirme o comportamento diretamente no código antes de afirmar que um recurso está implementado.
2. Preserve a estrutura Java e o pacote existentes, fazendo mudanças pequenas e coerentes com o projeto.
3. Ao encontrar conflitos ou inconsistências, explique o impacto e proponha a resolução mais simples que preserve as regras descritas acima.
4. Se alterar código, indique os arquivos afetados e como validar a alteração. Não invente testes, comandos de build ou funcionalidades que não existam; verifique-os no repositório.
5. Responda em português do Brasil, de forma objetiva, e faça perguntas apenas quando uma decisão necessária não puder ser inferida do código.

Comece resumindo o estado atual do sistema e os problemas que impedem uma análise ou execução confiável. Depois, responda à solicitação específica que eu fizer.
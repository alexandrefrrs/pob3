# Exercícios 17 — JDBC

Os cinco exercícios usam JDBC e um banco H2 em memória. A classe auxiliar
`ConexaoBanco` cria a conexão e prepara as tabelas com dados de exemplo.
Cada programa começa com um banco novo; os dados são descartados ao fechar a conexão.

## Executar

Com Java 17 ou superior e Maven, execute na raiz do projeto:

```sh
mvn -f exercicios/exercicios17/pom.xml compile exec:java "-Dexec.mainClass=exercicios.exercicios17.InsercaoCliente"
```

Troque o nome da classe para executar os demais exercícios:

1. `InsercaoCliente`: inserção parametrizada e recuperação do ID.
2. `ConsultaProdutos`: consulta por faixa de preço e mapeamento de objetos.
3. `ManutencaoFuncionarios`: atualização e exclusão com contagem das linhas afetadas.
4. `InsercaoLoteLogs`: gravação de 300 mensagens em lote.
5. `TransferenciaBancaria`: transferência, falha após débito e saldo insuficiente.

O Maven baixa o driver H2 na primeira execução. Não é necessário instalar um servidor
de banco de dados. Os métodos recebem uma conexão e deixam seu fechamento para quem
os chamou, exceto se o rollback falhar e a conexão ficar insegura para reutilização.

As transferências aceitam valores com até duas casas decimais e exigem uma conexão
com autocommit ativo, para não confirmar uma transação iniciada por outro código.
Após sucesso ou rollback, o autocommit é restaurado.

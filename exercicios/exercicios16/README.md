Exercícios 16 — Testes unitários

Cada exercício contém uma implementação e uma classe de teste correspondente:

1. `ValidadorSenha` — testes parametrizados de senhas.
2. `ContaBancaria` — preparação da conta e validação de exceções.
3. `UsuarioService` — atualização de e-mail com repositório simulado.
4. `NotificacaoService` — captura dos argumentos de uma notificação.
5. `ProcessadorPagamentoService` — pagamento aprovado e recusado.

Executar

Com Java 17 ou superior e Maven instalados, execute na raiz do projeto:

```sh
mvn -f exercicios/exercicios16/pom.xml test
```

O Maven baixa JUnit 5 e Mockito na primeira execução. Os resultados ficam em
`target/surefire-reports` dentro desta pasta. Os testes simulam os serviços externos;
nenhum e-mail ou pagamento real é realizado.

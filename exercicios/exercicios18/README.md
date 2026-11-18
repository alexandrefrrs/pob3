# Exercícios 18 — DAO

Os modelos e contratos auxiliares ficam junto ao exercício correspondente, como nas
pastas anteriores. `GenericDAO`, `ConnectionFactory` e `PersistenciaException` são
compartilhados pelos exemplos desta pasta.

## Executar

Com Java 17 ou superior e Maven, execute na raiz do projeto:

```sh
mvn -f exercicios/exercicios18/pom.xml compile exec:java "-Dexec.mainClass=exercicios.exercicios18.AlunoDAOImpl"
```

Troque a classe principal para executar cada exercício:

1. `AlunoDAOImpl`: inserção com ID gerado e listagem de alunos.
2. `ProdutoDAOImpl`: CRUD completo usando `GenericDAO<Produto, Long>`.
3. `LivroDAOImpl`: segunda página de livros, com dois registros por página.
4. `ClienteService`: cadastro e rejeição de CPF duplicado.
5. `VendaService`: venda confirmada e falha em item com rollback de toda a venda.

O H2 mantém os dados em memória durante a execução do processo, inclusive entre
conexões. Ao encerrar o programa, os dados são descartados. Não é necessário
instalar um servidor de banco de dados.

A paginação começa em 1. O cadastro exige e-mail preenchido e CPF com 11 dígitos,
sem pontuação; não verifica os dígitos verificadores do CPF. A restrição UNIQUE no
banco também impede cadastros duplicados concorrentes.

Os DAOs fecham seus comandos e resultados, mas não a conexão recebida. Na venda,
o serviço abre e fecha uma única conexão e controla o commit ou rollback dos dois DAOs.

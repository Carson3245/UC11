<<<<<<< HEAD
# UC11

# Casa de Leilões

Sistema acadêmico para gerenciar uma casa de leilões: cadastro de itens, clientes, lances e encerramento de leilões.

## Tecnologias utilizadas
- Java
- MySQL
=======
# Casa de Leilões — Atividade 2

Projeto Java Swing com SQLite implementando:
- Tela principal: `cadastroVIEW`.
- Botão **Salvar** grava no banco `db/leiloes.db`.
- `JOptionPane` informa sucesso ou erro.
- Tela **Listagem** exibe todos os itens cadastrados.

## Requisitos

- JDK 17 ou superior.
- Driver JDBC do SQLite (adicione o JAR em `lib/`, ex: `sqlite-jdbc-3.45.3.0.jar`).

## Como compilar e executar (linha de comando)

```bash
cd LeiloesTDSat_Atividade2

# Compilar
javac -cp "lib/*" -d out $(find src -name "*.java")

# Executar
java -cp "out:lib/*:." Main
```

No Windows, troque `:` por `;` no classpath:

```bat
javac -cp "lib/*" -d out src\**\*.java
java -cp "out;lib/*;." Main
```

## Estrutura

```
LeiloesTDSat_Atividade2/
  src/
    model/Produto.java
    dao/ConnectionFactory.java
    dao/ProdutoDAO.java
    view/cadastroVIEW.java
    view/listagemVIEW.java
    Main.java
  db/leiloes.db
  lib/              # coloque aqui o sqlite-jdbc-x.y.z.jar
```

## Observações

- O arquivo `db/leiloes.db` já contém 1 registro de exemplo.
- `ConnectionFactory` cria a tabela automaticamente, se não existir.
>>>>>>> 03664e8 (feat: salvar produto no banco e mensagens de sucesso/erro)

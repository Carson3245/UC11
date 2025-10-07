# Plano de Commits sugeridos

1. `feat: definir cadastroVIEW como tela principal`
   - `Main.java` abre `cadastroVIEW`.

2. `feat: salvar produto no banco e mensagens de sucesso/erro`
   - `ProdutoDAO.inserir(...)`
   - Ação do botão **Salvar** em `cadastroVIEW`.

3. `feat: listagem de produtos com JTable`
   - `view/listagemVIEW` com botão **Atualizar** e carga inicial.

4. `chore: adicionar banco SQLite e README`
   - `db/leiloes.db`, `README.md`.

5. (se necessário) `fix: correção de bug em <descrição>`
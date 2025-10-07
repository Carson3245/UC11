package model;

public class Produto {
    private Integer id;
    private String nome;
    private String descricao;
    private String categoria;
    private Double valor;

    public Produto() {}

    public Produto(Integer id, String nome, String descricao, String categoria, Double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.valor = valor;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}
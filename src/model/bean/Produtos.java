package model.bean;

public class Produtos {
    /* ATRIBUTOS DO BANCO DE DADOS NO MYSQL */
    private int GTIN; 
    private String nome;
    private String descricao;
    private int qtd;
    private double preco;

    public int getGTIN() {
        return GTIN;
    }

    public void setGTIN(int GTIN) {
        this.GTIN = GTIN;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }  
}

package br.exemplo.model;

public class Produto {
  
  private int id;

  private String nome;

  public void setId(int id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getId() {
    return id;
  }
  
  public String getNome() {
    return nome;
  }
  
  @Override
  public String toString() {
    return nome;
  }
}

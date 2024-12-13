package automacao.utils;

public class Usuario {
  private String nome;
  private String email;
  private String senha;
  private String conta;

  public Usuario(String nome, String email, String senha, String conta) {
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.conta = conta;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getSenha() {
    return senha;
  }

  public String getConta() {
    return conta;
  }

  public void setConta(String conta) {
    this.conta = conta;
  }
}

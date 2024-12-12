package automacao.utils;

import com.github.javafaker.Faker;
import automacao.pages.RegistrarPage;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

public class MassaDeDados {

  /**
   * Gera uma lista de usuários com dados aleatórios
   * 
   * @param numeroUsuarios quantidade de usuários a serem gerados
   * @return Lista de usuários
   */
  public static List<Usuario> gerarUsuarios(int numeroUsuarios) {
    if (numeroUsuarios <= 0) {
      throw new IllegalArgumentException("Número de usuários deve ser maior que zero");
    }

    Faker faker = new Faker();
    List<Usuario> usuarios = new ArrayList<>();

    for (int i = 0; i < numeroUsuarios; i++) {
      String nome = faker.name().fullName();
      String email = faker.internet().emailAddress();
      String senha = faker.internet().password(8, 16);

      usuarios.add(new Usuario(nome, email, senha));
    }

    return usuarios;
  }

  /**
   * Realiza o cadastro de um usuário no sistema
   * 
   * @param usuario Usuario a ser cadastrado
   * @param driver  WebDriver da sessão atual
   * @throws IllegalArgumentException se usuario ou driver forem nulos
   */
  public static void cadastrar(Usuario usuario, WebDriver driver) {
    if (usuario == null || driver == null) {
      throw new IllegalArgumentException("Usuário e driver não podem ser nulos");
    }

    try {
      RegistrarPage registrarPage = new RegistrarPage(driver);
      registrarPage.clickRegistrar();
      registrarPage.inserirEmail(usuario.getEmail());
      registrarPage.inserirSenha(usuario.getSenha());
      registrarPage.inserirNome(usuario.getNome());
      registrarPage.inserirConfirmarSenha(usuario.getSenha());
      registrarPage.clickCadastrar();
      registrarPage.clicarFechar();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao cadastrar usuário: " + e.getMessage(), e);
    }
  }
}

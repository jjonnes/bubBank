package automacao.utils;

import com.github.javafaker.Faker;
import automacao.pages.CadastroPage;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

public class MassaDeDados {

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
      String conta = String.valueOf(i);

      usuarios.add(new Usuario(nome, email, senha, conta));
    }

    return usuarios;
  }

  public static void cadastrar(Usuario usuario, WebDriver driver, int contaComSaldo) {
    if (usuario == null || driver == null) {
      throw new IllegalArgumentException("Usuário e driver não podem ser nulos");
    }

    try {
      CadastroPage registrarPage = new CadastroPage(driver);
      registrarPage.clickRegistrar();
      registrarPage.inserirEmail(usuario.getEmail());
      registrarPage.inserirSenha(usuario.getSenha());
      registrarPage.inserirNome(usuario.getNome());
      registrarPage.inserirConfirmarSenha(usuario.getSenha());
      if (contaComSaldo == 1) {
        registrarPage.clickContaComSaldo();
      }
      registrarPage.clickCadastrar();

      String numeroConta = registrarPage.obterNumeroDaConta();
      usuario.setConta(numeroConta);

      registrarPage.clicarFechar();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao cadastrar usuário: " + e.getMessage(), e);
    }
  }
}
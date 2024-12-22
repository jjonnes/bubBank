package automacao.steps;

import automacao.pages.HomePage;
import automacao.pages.LoginPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.TimeoutException;
import java.util.List;
import org.junit.Assert;

public class LoginSteps {

  private LoginPage loginPage;
  private HomePage homePage;
  private List<Usuario> massaDeDados;

  @Dado("que eu acesse a plataforma")
  public void queEuAcesseAPlataforma() {
    try {
      loginPage = Hooks.getLoginPage();
      homePage = Hooks.getHomePage();
      massaDeDados = Hooks.getMassaDeDados();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível acessar a plataforma: " + e.getMessage());
    }
  }

  @Quando("clicar em acessar")
  public void clicarEmAcessar() {
    try {
      loginPage.clickAcessar();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Botão acessar não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("as mensagens de email e senha devem ser apresentadas {string} e {string}")
  public void verificarMensagensEmailESenha(String msg1, String msg2) {
    try {
      String spanEmailActual = loginPage.obterTextoEmailFieldSpan();
      String spanSenhaActual = loginPage.obterTextoSenhaFieldSpan();

      Assert.assertEquals(msg1, spanEmailActual);
      Assert.assertEquals(msg2, spanSenhaActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Mensagens de erro não encontradas no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir um email incompleto no login")
  public void inserirEmailIncompleto() {
    try {
      String emailIncompleto = "exemplo@";
      loginPage.inserirEmail(emailIncompleto);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de email não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir email no login {string}")
  public void inserirEmailLogin(String msg1) {
    try {
      loginPage.inserirEmail(msg1);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de email não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir email cadastrado no login")
  public void inserirEmailCadastrado() {
    try {
      Usuario usuario = massaDeDados.get(0);
      loginPage.inserirEmail(usuario.getEmail());
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de email não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("a mensagem de email invalido deve ser apresentada {string}")
  public void verificarMensagemEmailInvalido(String msg1) {
    try {
      String spanEmailActual = loginPage.obterTextoEmailFieldSpan();
      Assert.assertEquals(msg1, spanEmailActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Mensagem de email inválido não encontrada no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("a mensagem de login vazio deve ser apresentada {string}")
  public void verificarMensagemLoginVazio(String msg1) {
    try {
      String spanEmailActual = loginPage.obterTextoEmailFieldSpan();
      Assert.assertEquals(msg1, spanEmailActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Mensagem de login vazio não encontrada no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("a mensagem de senha vazia deve ser apresentada {string}")
  public void verificarMensagemSenhaVazia(String msg1) {
    try {
      String spanSenhaActual = loginPage.obterTextoSenhaFieldSpan();
      Assert.assertEquals(msg1, spanSenhaActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Mensagem de senha vazia não encontrada no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir a senha no login {string}")
  public void inserirSenhaLogin(String senha) {
    try {
      loginPage.inserirSenha(senha);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de senha não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir a senha cadastrada no login")
  public void inserirSenhaCadastrada() {
    try {
      Usuario usuario = massaDeDados.get(0);
      loginPage.inserirSenha(usuario.getSenha());
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de senha não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("a mensagem de alerta login não cadastrado deve ser apresentada {string}")
  public void verificarMensagemAlertaLoginNaoCadastrado(String msg1) {
    try {
      boolean modalExpected = loginPage.obterTextoDeAlertaUsuarioOuSenhaInvalidos().contains(msg1);
      Assert.assertTrue(modalExpected);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Mensagem de alerta não encontrada no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("deve acessar a pagina home {string}")
  public void verificarAcessoPaginaHome(String endPoint) {
    try {
      Assert.assertTrue(homePage.getCurrentUrl(endPoint));
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível acessar a página home no tempo esperado: " + e.getMessage());
    }
  }
}

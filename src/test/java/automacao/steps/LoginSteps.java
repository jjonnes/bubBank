package automacao.steps;

import automacao.pages.HomePage;
import automacao.pages.LoginPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import java.util.List;

import org.junit.Assert;

public class LoginSteps {

  private LoginPage loginPage;
  private HomePage homePage;
  private List<Usuario> massaDeDados;

  @Dado("que eu acesse a plataforma")
  public void queEuAcesseAPlataforma() {
    loginPage = Hooks.getLoginPage();
    homePage = Hooks.getHomePage();
    massaDeDados = Hooks.getMassaDeDados();
  }

  @Quando("clicar em acessar")
  public void clicarEmAcessar() {
    loginPage.clickAcessar();
  }

  @Entao("as mensagens de email e senha devem ser apresentadas {string} e {string}")
  public void asMensagensDeEmaiESenhaDevemSerApresentadas(String msg1, String msg2) {
    String spanEmailActual = loginPage.obterTextoEmailFieldSpan();
    String spanSenhaActual = loginPage.obterTextoSenhaFieldSpan();

    Assert.assertEquals(msg1, spanEmailActual);
    Assert.assertEquals(msg2, spanSenhaActual);
  }

  @Quando("inserir um email incompleto no login")
  public void inserirUmEmailIncompleto() {
    String emailIncompleto = "exemplo@";

    loginPage.inserirEmail(emailIncompleto);
  }

  @Quando("inserir email no login {string}")
  public void inserirEmailNoLogin(String msg1) {
    loginPage.inserirEmail(msg1);
  }

  @Quando("inserir email cadastrado no login")
  public void inserirEmailCadastradoNoLogin() {
    Usuario usuario = massaDeDados.get(0);

    loginPage.inserirEmail(usuario.getEmail());
  }

  @Entao("a mensagem de email invalido deve ser apresentada {string}")
  public void aMensagemDeLoginDeveSerApresentada(String msg1) {
    String spanEmailActual = loginPage.obterTextoEmailFieldSpan();

    Assert.assertEquals(msg1, spanEmailActual);
  }

  @Entao("a mensagem de login vazio deve ser apresentada {string}")
  public void aMensagemDeLoginVazioDeveSerApresentada(String msg1) {
    String spanEmailActual = loginPage.obterTextoEmailFieldSpan();

    Assert.assertEquals(msg1, spanEmailActual);
  }

  @Entao("a mensagem de senha vazia deve ser apresentada {string}")
  public void aMensagemDeSenhaVaziaDeveSerApresentada(String msg1) {
    String spanSenhaActual = loginPage.obterTextoSenhaFieldSpan();

    Assert.assertEquals(msg1, spanSenhaActual);
  }

  @Quando("inserir a senha no login {string}")
  public void inserirASenhaNoLogin(String senha) {
    loginPage.inserirSenha(senha);
  }

  @Quando("inserir a senha cadastrada no login")
  public void inserirASenhaCadastradaNoLogin() {
    Usuario usuario = massaDeDados.get(0);

    loginPage.inserirSenha(usuario.getSenha());
  }

  @Entao("a mensagem de alerta login não cadastrado deve ser apresentada {string}")
  public void aMensagemDeAlertaLoginDeveSerApresentada(String msg1) {
    boolean modalExpected = loginPage.obterTextoDeAlertaUsuarioOuSenhaInvalidos().contains(msg1);

    Assert.assertTrue(modalExpected);
  }

  @Entao("deve acessar a pagina home {string}")
  public void deveAcessarAPaginaHome(String endPoint) {
    Assert.assertTrue(homePage.getCurrentUrl(endPoint));
  }
}

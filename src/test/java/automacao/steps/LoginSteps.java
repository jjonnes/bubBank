package automacao.steps;

import automacao.pages.LoginPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import java.util.List;

import org.junit.Assert;

public class LoginSteps {

  private LoginPage loginPage;
  private List<Usuario> massaDeDados = Hooks.getMassaDeDados();

  @Dado("que eu acesse a plataforma")
  public void queEuAcesseAPlataforma() {
    loginPage = Hooks.getLoginPage();

  }

  @Quando("clicar em acessar")
  public void clicarEmAcessar() {
    loginPage.clickAcessar();
  }

  @Entao("as mensagens de email e senha devem ser apresentadas {string} e {string}")
  public void asMensagensDeEmaiESenhaDevemSerApresentadas(String msg1, String msg2) {
    Assert.assertEquals(msg1, loginPage.obterTextoEmailFieldSpan());
    Assert.assertEquals(msg2, loginPage.obterTextoSenhaFieldSpan());
  }

  @Quando("inserir um email incompleto no login")
  public void inserirUmEmailIncompleto() {
    loginPage.inserirEmail("email_incompleto");
  }

  @Quando("inserir email no login {string}")
  public void inserirEmailNoLogin(String msg1) {
    loginPage.inserirEmail(msg1);
  }

  @Quando("inserir email cadastrado no login")
  public void inserirEmailCadastradoNoLogin() {
    loginPage.inserirEmail(massaDeDados.get(0).getEmail());
  }

  @Entao("a mensagem de email invalido deve ser apresentada {string}")
  public void aMensagemDeLoginDeveSerApresentada(String msg1) {
    Assert.assertEquals(msg1, loginPage.obterTextoEmailFieldSpan());
  }

  @Entao("a mensagem de login vazio deve ser apresentada {string}")
  public void aMensagemDeLoginVazioDeveSerApresentada(String msg1) {
    Assert.assertEquals(msg1, loginPage.obterTextoEmailFieldSpan());
  }

  @Entao("a mensagem de senha vazia deve ser apresentada {string}")
  public void aMensagemDeSenhaVaziaDeveSerApresentada(String msg1) {
    Assert.assertEquals(msg1, loginPage.obterTextoSenhaFieldSpan());
  }

  @Quando("inserir a senha no login {string}")
  public void inserirASenhaNoLogin(String senha) {
    loginPage.inserirSenha(senha);
  }

  @Quando("inserir a senha cadastrada no login")
  public void inserirASenhaCadastradaNoLogin() {
    loginPage.inserirSenha(massaDeDados.get(0).getSenha());
  }

  @Entao("a mensagem de alerta login não cadastrado deve ser apresentada {string}")
  public void aMensagemDeAlertaLoginDeveSerApresentada(String msg1) {
    Assert.assertTrue(loginPage.obterTextoDeAlertaUsuarioOuSenhaInvalidos().contains(msg1));
  }

  @Entao("deve acessar a pagina home")
  public void deveAcessarAPaginaHome() {
    Assert.assertTrue(loginPage.getCurrentUrl("/home").contains("/home"));
  }
}

package automacao.steps;

import automacao.pages.RegistrarPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import org.junit.Assert;

public class CadastroSteps {

  private RegistrarPage registrarPage;

  @Dado("que eu acesse a tela de cadastro")
  public void queEuAcesseATelaDeCadastro() {
    registrarPage = Hooks.getRegistrarPage();
    registrarPage.clickRegistrar();
  }

  @Quando("inserir email no cadastro {string}")
  public void inserirEmailNoCadastro(String email) {
    registrarPage.inserirEmail(email);
  }

  @Quando("inserir nome no cadastro {string}")
  public void inserirNomeNoCadastro(String nome) {
    registrarPage.inserirNome(nome);
  }

  @Quando("inserir senha no cadastro {string}")
  public void inserirSenhaNoCadastro(String senha) {
    registrarPage.inserirSenha(senha);
  }

  @Quando("inserir confirmação da senha {string}")
  public void inserirConfirmacaoDaSenha(String senha) {
    registrarPage.inserirConfirmarSenha(senha);
  }

  @Quando("clicar em cadastrar")
  public void clicarEmCadastrar() {
    registrarPage.clickCadastrar();
  }

  @Quando("marcar a opção conta com saldo")
  public void marcarOpcaoContaComSaldo() {
    registrarPage.clickContaComSaldo();
  }

  @Entao("as mensagens de email senha e confirmar senha devem ser apresentadas {string}, {string}, {string}")
  public void asMensagensDeEmailSenhaEConfirmarSenhaDevemSerApresentadas(String msg1, String msg2, String msg3) {
    Assert.assertEquals(msg1, registrarPage.obterTextoEmailFieldSpan());
    Assert.assertEquals(msg2, registrarPage.obterTextoSenhaFieldSpan());
    Assert.assertEquals(msg3, registrarPage.obterTextoConfirmarSenhaFieldSpan());
  }

  @Quando("inserir um e-mail incompleto no cadastro")
  public void inserirUmEmailIncompletoNoCadastro() {
    registrarPage.inserirEmail("email_incompleto");
  }

  @Quando("clique em criar conta com saldo")
  public void cliqueEmCriarContaComSaldo() throws InterruptedException {
    registrarPage.clickContaComSaldo();
  }

  @Entao("a mensagem em cadastro de email invalido deve ser apresentada {string}")
  public void aMensagemEmCadastroDeEmailInvalidoDeveSerApresentada(String msg1) {
    Assert.assertEquals(msg1, registrarPage.obterTextoEmailFieldSpan());
  }

  @Entao("a mensagem deve ser apresentada em confirmar senha {string}")
  public void aMensagemDeveSerApresentadaEmConfirmarSenha(String msg1) {
    Assert.assertEquals(msg1, registrarPage.obterTextoConfirmarSenhaFieldSpan());
  }

  @Entao("a mensagem de alerta de cadastro deve ser apresentada {string}")
  public void aMensagemDeAlertaDeCadastroDeveSerApresentada(String msg1) {
    Assert.assertNotNull(registrarPage.obterTextoAlerta());
  }
}

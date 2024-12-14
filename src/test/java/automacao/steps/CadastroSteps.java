package automacao.steps;

import automacao.pages.CadastroPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import org.junit.Assert;

public class CadastroSteps {

  private CadastroPage cadastroPage;

  @Dado("que eu acesse a tela de cadastro")
  public void queEuAcesseATelaDeCadastro() {
    cadastroPage = Hooks.getCadastroPage();
    cadastroPage.clickRegistrar();
  }

  @Quando("inserir email no cadastro {string}")
  public void inserirEmailNoCadastro(String email) {
    cadastroPage.inserirEmail(email);
  }

  @Quando("inserir nome no cadastro {string}")
  public void inserirNomeNoCadastro(String nome) {
    cadastroPage.inserirNome(nome);
  }

  @Quando("inserir senha no cadastro {string}")
  public void inserirSenhaNoCadastro(String senha) {
    cadastroPage.inserirSenha(senha);
  }

  @Quando("inserir confirmação da senha {string}")
  public void inserirConfirmacaoDaSenha(String senha) {
    cadastroPage.inserirConfirmarSenha(senha);
  }

  @Quando("clicar em cadastrar")
  public void clicarEmCadastrar() {
    cadastroPage.clickCadastrar();
  }

  @Quando("marcar a opção conta com saldo")
  public void marcarOpcaoContaComSaldo() {
    cadastroPage.clickContaComSaldo();
  }

  @Entao("as mensagens de email senha e confirmar senha devem ser apresentadas {string}, {string}, {string}, {string}")
  public void asMensagensDeEmailSenhaEConfirmarSenhaDevemSerApresentadas(String msg1, String msg2, String msg3,
      String msg4) {
    String spanNomeActual = cadastroPage.obterTextoNomeFieldSpan();
    String spanEmailActual = cadastroPage.obterTextoEmailFieldSpan();
    String spanSenhaActual = cadastroPage.obterTextoSenhaFieldSpan();
    String spanConfirmarSenhaActual = cadastroPage.obterTextoConfirmarSenhaFieldSpan();

    Assert.assertEquals(msg1, spanNomeActual);
    Assert.assertEquals(msg1, spanEmailActual);
    Assert.assertEquals(msg2, spanSenhaActual);
    Assert.assertEquals(msg3, spanConfirmarSenhaActual);
  }

  @Quando("inserir um e-mail incompleto no cadastro")
  public void inserirUmEmailIncompletoNoCadastro() {
    String emailIncompleto = "exemplo@";

    cadastroPage.inserirEmail(emailIncompleto);
  }

  @Quando("clique em criar conta com saldo")
  public void cliqueEmCriarContaComSaldo() {
    cadastroPage.clickContaComSaldo();
  }

  @Entao("a mensagem em cadastro de email invalido deve ser apresentada {string}")
  public void aMensagemEmCadastroDeEmailInvalidoDeveSerApresentada(String msg1) {
    String spanEmailActual = cadastroPage.obterTextoEmailFieldSpan();

    Assert.assertEquals(msg1, spanEmailActual);
  }

  @Entao("a mensagem deve ser apresentada em confirmar senha {string}")
  public void aMensagemDeveSerApresentadaEmConfirmarSenha(String msg1) {
    String spanConfirmarSenhaActual = cadastroPage.obterTextoConfirmarSenhaFieldSpan();

    Assert.assertEquals(msg1, spanConfirmarSenhaActual);
  }

  @Entao("a mensagem de alerta de cadastro deve ser apresentada {string}")
  public void aMensagemDeAlertaDeCadastroDeveSerApresentada(String msg1) {
    String alertaActual = cadastroPage.obterTextoAlerta();

    Assert.assertEquals(msg1, alertaActual);
  }
}

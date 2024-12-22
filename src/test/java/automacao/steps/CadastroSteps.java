package automacao.steps;

import automacao.pages.CadastroPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.TimeoutException;
import org.junit.Assert;

public class CadastroSteps {

  private CadastroPage cadastroPage;

  @Dado("que eu acesse a tela de cadastro")
  public void queEuAcesseATelaDeCadastro() {
    try {
      cadastroPage = Hooks.getCadastroPage();
      cadastroPage.clickRegistrar();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível acessar a tela de cadastro: " + e.getMessage());
    }
  }

  @Quando("inserir email no cadastro {string}")
  public void inserirEmailCadastro(String email) {
    try {
      cadastroPage.inserirEmail(email);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de email não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir nome no cadastro {string}")
  public void inserirNomeCadastro(String nome) {
    try {
      cadastroPage.inserirNome(nome);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de nome não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir senha no cadastro {string}")
  public void inserirSenhaCadastro(String senha) {
    try {
      cadastroPage.inserirSenha(senha);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de senha não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir confirmação da senha {string}")
  public void inserirConfirmacaoSenha(String senha) {
    try {
      cadastroPage.inserirConfirmarSenha(senha);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de confirmação de senha não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("clicar em cadastrar")
  public void clicarEmCadastrar() {
    try {
      cadastroPage.clickCadastrar();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Botão cadastrar não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("marcar a opção conta com saldo")
  public void marcarOpcaoContaComSaldo() {
    try {
      cadastroPage.clickContaComSaldo();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Opção conta com saldo não encontrada no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("as mensagens de email senha e confirmar senha devem ser apresentadas {string}, {string}, {string}, {string}")
  public void verificarMensagensEmailSenhaConfirmarSenha(String msg1, String msg2, String msg3, String msg4) {
    try {
      String spanNomeActual = cadastroPage.obterTextoNomeFieldSpan();
      String spanEmailActual = cadastroPage.obterTextoEmailFieldSpan();
      String spanSenhaActual = cadastroPage.obterTextoSenhaFieldSpan();
      String spanConfirmarSenhaActual = cadastroPage.obterTextoConfirmarSenhaFieldSpan();

      Assert.assertEquals(msg1, spanNomeActual);
      Assert.assertEquals(msg1, spanEmailActual);
      Assert.assertEquals(msg2, spanSenhaActual);
      Assert.assertEquals(msg3, spanConfirmarSenhaActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Uma ou mais mensagens de erro não encontradas no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("inserir um e-mail incompleto no cadastro")
  public void inserirEmailIncompleto() {
    try {
      String emailIncompleto = "exemplo@";
      cadastroPage.inserirEmail(emailIncompleto);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Campo de email não encontrado no tempo esperado: " + e.getMessage());
    }
  }

  @Quando("clique em criar conta com saldo")
  public void clicarCriarContaComSaldo() {
    try {
      cadastroPage.clickContaComSaldo();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Opção conta com saldo não encontrada no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("a mensagem em cadastro de email invalido deve ser apresentada {string}")
  public void verificarMensagemEmailInvalido(String msg1) {
    try {
      String spanEmailActual = cadastroPage.obterTextoEmailFieldSpan();
      Assert.assertEquals(msg1, spanEmailActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Mensagem de email inválido não encontrada no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("a mensagem deve ser apresentada em confirmar senha {string}")
  public void verificarMensagemConfirmarSenha(String msg1) {
    try {
      String spanConfirmarSenhaActual = cadastroPage.obterTextoConfirmarSenhaFieldSpan();
      Assert.assertEquals(msg1, spanConfirmarSenhaActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Mensagem de confirmação de senha não encontrada no tempo esperado: " + e.getMessage());
    }
  }

  @Entao("a mensagem de alerta de cadastro deve ser apresentada {string}")
  public void verificarMensagemAlertaCadastro(String msg1) {
    try {
      String alertaActual = cadastroPage.obterTextoAlerta();
      Assert.assertTrue(alertaActual.trim().contains(msg1.trim()));
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Mensagem de alerta não encontrada no tempo esperado: " + e.getMessage());
    }
  }
}

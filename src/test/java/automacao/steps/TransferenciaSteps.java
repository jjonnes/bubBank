package automacao.steps;

import automacao.pages.ExtratoPage;
import automacao.pages.HomePage;
import automacao.pages.LoginPage;
import automacao.pages.TransferenciaPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.TimeoutException;
import java.util.List;
import org.junit.Assert;

public class TransferenciaSteps {

  private TransferenciaPage transferenciaPage;
  private LoginPage loginPage;
  private HomePage homePage;
  private ExtratoPage extratoPage;
  private List<Usuario> massaDeDados;

  @Dado("que eu estou na página de transferência")
  public void queEuEstouNaPaginaDeTransferencia() {
    try {
      loginPage = Hooks.getLoginPage();
      homePage = Hooks.getHomePage();
      extratoPage = Hooks.getExtratoPage();
      transferenciaPage = Hooks.getTransferenciaPage();
      massaDeDados = Hooks.getMassaDeDados();

      Usuario usuario = massaDeDados.get(0);
      String userEmail = usuario.getEmail();
      String userPass = usuario.getSenha();

      loginPage.inserirEmail(userEmail);
      loginPage.inserirSenha(userPass);
      loginPage.clickAcessar();
      homePage.clickTransferencia();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível acessar a página de transferência: " + e.getMessage());
    }
  }

  @Quando("inserir o número da conta inexistente {string} e o dígito {string}")
  public void inserirNumeroContaInexistenteEDigito(String conta, String digito) {
    try {
      transferenciaPage.inserirNumeroConta(conta);
      transferenciaPage.inserirDigito(digito);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível inserir número da conta e dígito: " + e.getMessage());
    }
  }

  @Quando("inserir o campo descrição com {string}")
  public void inserirCampoDescricao(String descricao) {
    try {
      transferenciaPage.inserirDescricao(descricao);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível inserir a descrição: " + e.getMessage());
    }
  }

  @Quando("inserir o valor da transferência com {string}")
  public void inserirValorTransferencia(String valor) {
    try {
      transferenciaPage.inserirValorTransferencia(valor);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível inserir o valor da transferência: " + e.getMessage());
    }
  }

  @Quando("clicar no botão Tranferir agora")
  public void clicarBotaoTransferirAgora() {
    try {
      transferenciaPage.clicarTransferir();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível clicar no botão transferir: " + e.getMessage());
    }
  }

  @Entao("eu devo ver a mensagem {string}")
  public void verMensagemErro(String string) {
    try {
      String modalActual = transferenciaPage.obterMensagemDeAlerta();
      Assert.assertEquals(string, modalActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar a mensagem: " + e.getMessage());
    }
  }

  @Quando("inserir o número da conta")
  public void inserirNumeroConta() {
    try {
      Usuario usuario = massaDeDados.get(1);
      String conta = usuario.getConta();
      transferenciaPage.inserirNumeroConta(conta);
      transferenciaPage.inserirDigito(conta);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível inserir o número da conta: " + e.getMessage());
    }
  }

  @Entao("eu devo ser redirecionado para a página de extrato {string}")
  public void verificarRedirecionamentoExtrato(String endPoint) {
    try {
      transferenciaPage.fecharModal();
      Assert.assertTrue(homePage.getCurrentUrl(endPoint));
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar redirecionamento para extrato: " + e.getMessage());
    }
  }

  @Entao("eu devo ter dedução no saldo de {string}")
  public void verificarDeducaoSaldo(String valorDeducao) {
    try {
      transferenciaPage.fecharModal();
      transferenciaPage.clicarVoltar();

      String saldoActual = homePage.obterTextBalance();
      double saldoEsperadoDouble = 1000.00 - Integer.valueOf(valorDeducao);
      String saldoExpected = String.format("%.2f", saldoEsperadoDouble)
          .replace(".", ",");

      Assert.assertEquals(saldoExpected, saldoActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar a dedução no saldo: " + e.getMessage());
    }
  }

  @Entao("a conta destino deve receber {string} com a descrição {string}")
  public void verificarRecebimentoContaDestino(String valor, String descriptionExpected) {
    try {
      Usuario usuario = massaDeDados.get(1);
      transferenciaPage.fecharModal();
      transferenciaPage.clickExit();
      loginPage.inserirEmail(usuario.getEmail());
      loginPage.inserirSenha(usuario.getSenha());
      loginPage.clickAcessar();
      homePage.clickExtrato();

      String saldoActual = extratoPage.obterValorTransferencia();
      String descricaoActual = extratoPage.obterDescricaoTransferencia();
      double valorDouble = Double.parseDouble(valor);
      String valorExpected = String.format("%.2f", valorDouble)
          .replace(".", ",");

      Assert.assertEquals(valorExpected, saldoActual);
      Assert.assertEquals(descriptionExpected, descricaoActual);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível verificar o recebimento na conta destino: " + e.getMessage());
    }
  }

  @Quando("inserir o valor da transferência {string}")
  public void inserirValorDaTransferencia(String valor) {
    try {
      transferenciaPage.inserirValorTransferencia(valor);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível inserir o valor da transferência: " + e.getMessage());
    }
  }

  @Entao("validar que o campo número da conta aceita apenas o padrão {string}")
  public void validarPadraoNumeroConta(String regex) {
    try {
      boolean resultado = transferenciaPage.caracteresAceitosNumeroConta(regex);
      Assert.assertTrue(resultado);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível validar o padrão do número da conta: " + e.getMessage());
    }
  }

  @Entao("validar que o campo dígito aceita apenas o padrão {string}")
  public void validarPadraoDigito(String regex) {
    try {
      boolean resultado = transferenciaPage.caracteresAceitosDigitoConta(regex);
      Assert.assertTrue(resultado);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível validar o padrão do dígito da conta: " + e.getMessage());
    }
  }
}

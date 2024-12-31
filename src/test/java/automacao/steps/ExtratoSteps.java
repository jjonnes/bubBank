package automacao.steps;

import automacao.pages.ExtratoPage;
import automacao.pages.HomePage;
import automacao.pages.LoginPage;
import automacao.pages.TransferenciaPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.TimeoutException;
import java.util.List;
import org.junit.Assert;

public class ExtratoSteps {

  private ExtratoPage extratoPage;
  private LoginPage loginPage;
  private HomePage homePage;
  private TransferenciaPage transferenciaPage;
  private List<Usuario> massaDeDados;

  @Dado("que eu estou na página de extrato")
  public void queEuEstouNaPaginaDeExtrato() {
    try {
      loginPage = Hooks.getLoginPage();
      homePage = Hooks.getHomePage();
      transferenciaPage = Hooks.getTransferenciaPage();
      extratoPage = Hooks.getExtratoPage();
      massaDeDados = Hooks.getMassaDeDados();

      Usuario usuario = massaDeDados.get(0);
      loginPage.inserirEmail(usuario.getEmail());
      loginPage.inserirSenha(usuario.getSenha());
      loginPage.clickAcessar();
      homePage.clickExtrato();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível acessar a página de extrato: " + e.getMessage());
    }
  }

  @Entao("devo visualizar o saldo disponível atual")
  public void visualizarSaldoDisponivel() {
    try {
      String saldo = extratoPage.obterSaldoDisponivel();
      Assert.assertNotNull(saldo);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível visualizar o saldo disponível: " + e.getMessage());
    }
  }

  @Entao("devo visualizar a transação com a descricao {string}")
  public void visualizarTransacaoComDescricao(String tipo) {
    try {
      String texto = extratoPage.obterTipoAbertura();
      Assert.assertNotNull(texto);
      Assert.assertEquals(tipo, texto);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível visualizar a transação: " + e.getMessage());
    }
  }

  @Quando("eu realizar uma transferência de {string} com a descrição {string}")
  public void realizarTransferenciaComDescricao(String valor, String descricao) {
    try {
      Usuario usuario = massaDeDados.get(1);
      extratoPage.clicarVoltar();
      homePage.clickTransferencia();
      transferenciaPage.inserirNumeroConta(usuario.getConta());
      transferenciaPage.inserirDigito(usuario.getConta());
      transferenciaPage.inserirValorTransferencia(valor);
      transferenciaPage.inserirDescricao(descricao);
      transferenciaPage.clicarTransferir();
      transferenciaPage.fecharModal();
      transferenciaPage.clicarVoltar();
      homePage.clickExtrato();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível realizar a transferência: " + e.getMessage());
    }
  }

  @Entao("devo visualizar a transação em vermelho com a descricao {string} e o valor negativo")
  public void visualizarTransacaoVermelhaComDescricao(String descricao) {
    try {
      String corValor = extratoPage.obterCorValorTransacao();
      Assert.assertTrue(extratoPage.isValorTransacaoNegativo());
      Assert.assertEquals("rgba(255, 0, 0, 1)", corValor);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível visualizar a transação em vermelho: " + e.getMessage());
    }
  }

  @Quando("eu receber uma transferência de {string} reais com a descrição {string}")
  public void receberTransferenciaComDescricao(String valor, String descricao) {
    try {
      Usuario usuario = massaDeDados.get(1);
      extratoPage.clicarVoltar();
      homePage.clickTransferencia();
      transferenciaPage.inserirNumeroConta(usuario.getConta());
      transferenciaPage.inserirDigito(usuario.getConta());
      transferenciaPage.inserirValorTransferencia(valor);
      transferenciaPage.inserirDescricao(descricao);
      transferenciaPage.clicarTransferir();
      transferenciaPage.fecharModal();
      homePage.clickExit();

      loginPage.inserirEmail(usuario.getEmail());
      loginPage.inserirSenha(usuario.getSenha());
      loginPage.clickAcessar();
      homePage.clickExtrato();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível receber a transferência: " + e.getMessage());
    }
  }

  @Entao("devo visualizar a transação em verde")
  public void visualizarTransacaoVerde() {
    try {
      String corValor = extratoPage.obterCorValorTransacao();
      Assert.assertTrue(extratoPage.isValorTransacaoPositivo());
      Assert.assertEquals("rgba(0, 128, 0, 1)", corValor);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível visualizar a transação em verde: " + e.getMessage());
    }
  }

  @Quando("eu realizar uma transferência de {string} reais sem descrição")
  public void realizarTransferenciaSemDescricao(String valor) {
    try {
      Usuario usuario = massaDeDados.get(1);
      extratoPage.clicarVoltar();
      homePage.clickTransferencia();
      transferenciaPage.inserirNumeroConta(usuario.getConta());
      transferenciaPage.inserirDigito(usuario.getConta());
      transferenciaPage.inserirValorTransferencia(valor);
      transferenciaPage.clicarTransferir();
      transferenciaPage.fecharModal();
      transferenciaPage.clicarVoltar();
      homePage.clickExtrato();
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível realizar a transferência sem descrição: " + e.getMessage());
    }
  }

  @Entao("devo visualizar a transação em vermelho com a descricao -")
  public void visualizarTransacaoVermelhaComDescricaoTraco() {
    try {
      String descricaoTransacao = extratoPage.obterDescricaoTransferencia();
      Assert.assertEquals("-", descricaoTransacao);
    } catch (TimeoutException e) {
      Assert.fail("Timeout: Não foi possível visualizar a descrição da transação: " + e.getMessage());
    }
  }
}

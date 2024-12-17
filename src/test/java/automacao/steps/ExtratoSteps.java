package automacao.steps;

import automacao.pages.ExtratoPage;
import automacao.pages.HomePage;
import automacao.pages.LoginPage;
import automacao.pages.TransferenciaPage;
import automacao.utils.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.List;

import org.junit.Assert;

public class ExtratoSteps {

  private ExtratoPage extratoPage;
  private LoginPage loginPage;
  private HomePage homePage;
  private TransferenciaPage transferenciaPage;
  private List<Usuario> massaDeDados;

  @Dado("que eu estou na página de extrato")
  public void que_eu_estou_na_página_de_extrato() {
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
  }

  @Entao("devo visualizar o saldo disponível atual")
  public void devoVisualizarOSaldoDisponívelAtual() {
    String saldo = extratoPage.obterSaldoDisponivel();
    Assert.assertNotNull("O saldo disponível deve ser exibido", saldo);
  }

  @Então("devo visualizar a transação com a descricao \"Abertura de conta\"nnnnnnnnnnnnn")
  public void devoVisualizarATransaçãoComADescricaoAberturaDeContaNnnnnnnnnnnnn() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Quando("eu realizar uma transferência de {string} com a descrição {string}")
  public void euRealizarUmaTransferênciaDeReaisComADescrição(String valor, String descricao) {
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
  }

  @Entao("devo visualizar a transação em vermelho com a descricao {string} e o valor negativo")
  public void devoVisualizarATransaçãoEmVermelhoComADescricaoEOValor(String descricao) {
    String corValor = extratoPage.obterCorValorTransacao();
    Assert.assertTrue("A transação deve ser negativa e estar em vermelho",
        extratoPage.isValorTransacaoNegativo());
    Assert.assertEquals("A cor do valor da transação deve ser vermelho",
        "rgba(255, 0, 0, 1)", corValor);
  }

  @Quando("eu receber uma transferência de {string} reais com a descrição {string}")
  public void euReceberUmaTransferênciaDeReaisComADescrição(String valor, String descricao) {
    extratoPage.clicarVoltar();
    homePage.clickTransferencia();
    transferenciaPage.inserirValorTransferencia(valor);
    transferenciaPage.inserirDescricao(descricao);
    transferenciaPage.clicarTransferir();
    transferenciaPage.fecharModal();
    homePage.clickExit();

    Usuario usuario = massaDeDados.get(1);
    loginPage.inserirEmail(usuario.getEmail());
    loginPage.inserirSenha(usuario.getSenha());
    loginPage.clickAcessar();
    homePage.clickExtrato();
  }

  @Então("devo visualizar a transação em verde")
  public void devoVisualizarATransaçãoEmVerde() {
    String corValor = extratoPage.obterCorValorTransacao();
    Assert.assertTrue("A transação deve ser positiva e estar em verde",
        extratoPage.isValorTransacaoPositivo());
    Assert.assertEquals("A cor do valor da transação deve ser verde",
        "rgba(0, 128, 0, 1)", corValor);
  }

  @Quando("eu realizar uma transferência de {string} reais sem descrição")
  public void euRealizarUmaTransferênciaDeReaisSemDescrição(String valor) {
    Usuario usuario = massaDeDados.get(1);
    transferenciaPage.inserirNumeroConta(usuario.getConta());
    transferenciaPage.inserirDigito(usuario.getConta());
    transferenciaPage.inserirValorTransferencia(valor);
    transferenciaPage.clicarTransferir();
    transferenciaPage.fecharModal();
    transferenciaPage.clicarVoltar();
    homePage.clickExtrato();
  }

  @Entao("devo visualizar a transação em vermelho com a descricao -")
  public void devoVisualizarATransaçãoEmVermelhoComADescricao() {
    String descricaoTransacao = extratoPage.obterDescricaoTransferencia();
    Assert.assertTrue("A transação sem descrição deve exibir '-'",
        descricaoTransacao.isEmpty());
    Assert.assertEquals("A descrição da transação deve ser '-'", "-",
        descricaoTransacao);
  }

  /*
   * @Entao("devo visualizar o saldo disponível atual")
   * public void devoVisualizarOSaldoDisponívelAtual() {
   * String saldo = extratoPage.obterSaldoDisponivel();
   * Assert.assertNotNull("O saldo disponível deve ser exibido", saldo);
   * Assert.assertFalse("O saldo disponível não deve estar vazio",
   * saldo.isEmpty());
   * }
   * 
   * @Entao("devo visualizar a transação com a descricao {string}")
   * public void devoVisualizarATransaçãoComADescricao(String descricaoEsperada) {
   * String descricaoAtual = extratoPage.obterDescricaoTransferencia();
   * Assert.assertEquals("A descrição da transação não corresponde",
   * descricaoEsperada, descricaoAtual);
   * }
   * 
   * @Quando("eu realizar uma transferência de {string} reais com a descrição {string}"
   * )
   * public void euRealizarUmaTransferênciaDeReaisComADescrição(String valor,
   * String descricao) {
   * homePage.clickTransferencia();
   * transferenciaPage.inserirValorTransferencia(valor);
   * transferenciaPage.inserirDescricao(descricao);
   * transferenciaPage.clicarTransferir();
   * transferenciaPage.fecharModal();
   * homePage.clickExtrato();
   * }
   * 
   * @Entao("devo visualizar a transação em vermelho com a descricao {string} e o valor negativo"
   * )
   * public void devoVisualizarATransaçãoEmVermelhoComADescricaoEOValor() {
   * String valorTransacao = extratoPage.obterValorTransferencia();
   * String corValor = extratoPage.obterCorValorTransacao();
   * Assert.assertTrue("A transação deve ser negativa e estar em vermelho",
   * extratoPage.isValorTransacaoNegativo());
   * Assert.assertEquals("A cor do valor da transação deve ser vermelho",
   * "rgba(255, 0, 0, 1)", corValor);
   * }
   * 
   * @Quando("eu receber uma transferência de {string} reais com a descrição {string}"
   * )
   * public void euReceberUmaTransferênciaDeReaisComADescrição(String valor,
   * String descricao) {
   * homePage.clickTransferencia();
   * transferenciaPage.inserirValorTransferencia(valor);
   * transferenciaPage.inserirDescricao(descricao);
   * transferenciaPage.clicarTransferir();
   * transferenciaPage.fecharModal();
   * homePage.clickExit();
   * 
   * Usuario usuario = massaDeDados.get(1);
   * loginPage.inserirEmail(usuario.getEmail());
   * loginPage.inserirSenha(usuario.getSenha());
   * loginPage.clickAcessar();
   * homePage.clickExtrato();
   * }
   * 
   * @Entao("devo visualizar a transação em verde com a descricao e o valor")
   * public void devoVisualizarATransaçãoEmVerdeComADescricaoEOValor() {
   * String valorTransacao = extratoPage.obterValorTransferencia();
   * String corValor = extratoPage.obterCorValorTransacao();
   * Assert.assertTrue("A transação deve ser positiva e estar em verde",
   * extratoPage.isValorTransacaoPositivo());
   * Assert.assertEquals("A cor do valor da transação deve ser verde",
   * "rgba(0, 128, 0, 1)", corValor);
   * }
   * 
   * @Quando("eu realizar uma transferência de {string} reais sem descrição")
   * public void euRealizarUmaTransferênciaDeReaisSemDescrição(String valor) {
   * Usuario usuario = massaDeDados.get(1);
   * transferenciaPage.inserirNumeroConta(usuario.getConta());
   * transferenciaPage.inserirDigito(usuario.getConta());
   * transferenciaPage.inserirValorTransferencia(valor);
   * transferenciaPage.clicarTransferir();
   * transferenciaPage.fecharModal();
   * transferenciaPage.clicarVoltar();
   * homePage.clickExtrato();
   * }
   * 
   * @Entao("devo visualizar a transação em vermelho com a descricao -")
   * public void devoVisualizarATransaçãoEmVermelhoComADescricao() {
   * String descricaoTransacao = extratoPage.obterDescricaoTransferencia();
   * Assert.assertTrue("A transação sem descrição deve exibir '-'",
   * descricaoTransacao.isEmpty());
   * Assert.assertEquals("A descrição da transação deve ser '-'", "-",
   * descricaoTransacao);
   * }
   * 
   * @Quando("eu realizar uma transferência de {string} reais com a descrição {string} e tipo {string}"
   * )
   * public void euRealizarUmaTransferênciaDeReaisComADescriçãoETipo(String valor,
   * String descricao, String tipo) {
   * // transferenciaPage.inserirValorTransferencia(valor);
   * // transferenciaPage.inserirDescricao(descricao);
   * // transferenciaPage.selecionarTipo(tipo);
   * // transferenciaPage.clicarTransferir();
   * }
   * 
   * @Entao("devo visualizar a transação com tipo {string}")
   * public void devoVisualizarATransaçãoComTipo(String tipoEsperado) {
   * //String tipoAtual = extratoPage.obterTipoTransferencia();
   * Assert.assertEquals("O tipo da transação não corresponde", tipoEsperado,
   * tipoAtual);
   * }
   */
}

package automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automacao.utils.PageUtils;
import automacao.utils.StepsUtils;

public class ExtratoPage {

  private PageUtils pageUtils;

  public ExtratoPage(WebDriver driver, Integer timeOut) {
    this.pageUtils = new PageUtils(driver, timeOut);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@id='textBalanceAvailable']")
  private WebElement saldoDisponivel;

  @FindBy(xpath = "/html/body/div/div/div[3]/div/div[2]/div[1]/div[2]/p[1]") // descrição abertura
  private WebElement descricaoAbertura;

  @FindBy(xpath = "/html/body/div/div/div[3]/div/div[2]/div[1]/div[2]/p[2]") // valor abertura
  private WebElement valorAbertura;

  @FindBy(xpath = "/html/body/div/div/div[3]/div/div[2]/div[2]/div[2]/p[1]") // descrição transferência
  private WebElement descricaoTransferencia;

  @FindBy(xpath = "/html/body/div/div/div[3]/div/div[2]/div[2]/div[2]/p[2]") // valor transferência
  private WebElement valorTransferencia;

  @FindBy(xpath = "/html/body/div/div/div[3]/div/div[2]/div[1]/div[1]/p[2]") // tipo abertura
  private WebElement tipoAbertura;

  @FindBy(xpath = "/html/body/div/div/div[3]/div/div[2]/div[2]/div[1]/p[2]") // tipo transferência
  private WebElement tipoTransferencia;

  @FindBy(xpath = "//*[@id='btnExit']")
  private WebElement exitButton;

  @FindBy(xpath = "//*[@id='btnBack']")
  private WebElement backButton;

  public String obterSaldoDisponivel() {
    String valor = pageUtils.obterTexto(saldoDisponivel);
    return StepsUtils.extraiValor(valor);
  }

  public String obterValorTransferencia() {
    String valor = pageUtils.obterTexto(valorTransferencia);
    return StepsUtils.extraiValor(valor);
  }

  public String obterValorAbertura() {
    String valor = pageUtils.obterTexto(valorAbertura);
    return StepsUtils.extraiValor(valor);
  }

  public String obterDescricaoTransferencia() {
    return pageUtils.obterTexto(descricaoTransferencia);
  }

  public String obterDescricaoAbertura() {
    return pageUtils.obterTexto(descricaoAbertura);
  }

  public String obterTipoTransacao() {
    return pageUtils.obterTexto(tipoTransferencia);
  }

  public String obterTipoAbertura() {
    return pageUtils.obterTexto(tipoAbertura);
  }

  public String obterValorTransacaoFormatado() {
    return pageUtils.obterTexto(valorTransferencia);
  }

  public boolean isValorTransacaoNegativo() {
    String valor = obterValorTransacaoFormatado();
    return valor.startsWith("-");
  }

  public boolean isValorTransacaoPositivo() {
    return !isValorTransacaoNegativo();
  }

  public String obterCorValorTransacao() {
    return valorTransferencia.getCssValue("color");
  }

  public void clickExit() {
    pageUtils.clicar(exitButton);
  }

  public void clicarVoltar() {
    pageUtils.clicar(backButton);
  }
}

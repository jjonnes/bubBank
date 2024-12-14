package automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import automacao.utils.PageUtils;
import automacao.utils.StepsUtils;

import java.time.Duration;

public class ExtratoPage {

  @SuppressWarnings("unused")
  private WebDriver driver;
  @SuppressWarnings("unused")
  private WebDriverWait wait;
  private PageUtils pageUtils;

  public ExtratoPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.pageUtils = new PageUtils(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@id='textBalanceAvailable']")
  public WebElement saldoDisponivel;

  @FindBy(xpath = "//*[@id='textTransferValue']")
  public WebElement valorTransferencia;

  @FindBy(xpath = "//*[@id='textDescription']")
  public WebElement descricaoTransferencia;

  @FindBy(xpath = "//*[@id='btnExit']")
  public WebElement exitButton;

  @FindBy(xpath = "//*[@id='btnBack']")
  public WebElement backButton;

  public String obterSaldoDisponivel() {
    String valor = pageUtils.obterTexto(saldoDisponivel);
    return StepsUtils.extraiValor(valor);
  }

  public String obterValorTransferencia() {
    String valor = pageUtils.obterTexto(valorTransferencia);
    return StepsUtils.extraiValor(valor);
  }

  public String obterDescricaoTransferencia() {
    return pageUtils.obterTexto(descricaoTransferencia);
  }

  public void clickExit() {
    pageUtils.clicar(exitButton);
  }

  public void clicarVoltar() {
    pageUtils.clicar(backButton);
  }
}

package automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automacao.utils.PageUtils;
import automacao.utils.StepsUtils;

import java.time.Duration;

public class HomePage {

  private WebDriver driver;
  private WebDriverWait wait;
  private PageUtils pageUtils;
  // private StepsUtils stepsUtils;

  public HomePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.pageUtils = new PageUtils(driver);
    // this.stepsUtils = new StepsUtils();
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@id=\"textName\"]")
  private WebElement textName;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[2]/div/div[1]/h1")
  private WebElement textNameInicial;

  @FindBy(xpath = "//*[@id=\"textAccountNumber\"]/span")
  private WebElement textAccountNumber;

  @FindBy(xpath = "//*[@id=\"textBalance\"]/span")
  private WebElement textBalance;

  @FindBy(xpath = "//*[@id=\"btn-TRANSFERÊNCIA\"]")
  private WebElement transferenciaButton;

  @FindBy(xpath = "//*[@id=\"btn-EXTRATO\"]")
  private WebElement extratoButton;

  @FindBy(xpath = "//*[@id=\"btnExit\"]")
  private WebElement exitButton;

  public String obterTextName() {
    String nome = pageUtils.obterTexto(textName);
    return StepsUtils.extraiNome(nome);
  }

  public String obterTextNameInicial() {
    String nome = pageUtils.obterTexto(textNameInicial);
    return StepsUtils.extraiInicial(nome);
  }

  public String obterTextAccountNumber() {
    return pageUtils.obterTexto(textAccountNumber);
  }

  public String obterTextBalance() {
    String valor = pageUtils.obterTexto(textBalance);
    return StepsUtils.extraiValor(valor);
  }

  public void clickTransferencia() {
    pageUtils.clicar(transferenciaButton);
  }

  public void clickExtrato() {
    pageUtils.clicar(extratoButton);
  }

  public void clickExit() {
    pageUtils.clicar(exitButton);
  }

  public String getCurrentUrl(String arg) {
    wait.until(ExpectedConditions.urlContains(arg));
    return driver.getCurrentUrl();
  }
}

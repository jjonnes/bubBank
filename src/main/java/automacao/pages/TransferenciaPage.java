package automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automacao.utils.PageUtils;
import java.time.Duration;

public class TransferenciaPage {

  private WebDriver driver;
  private WebDriverWait wait;
  private PageUtils pageUtils;

  public TransferenciaPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.pageUtils = new PageUtils(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/div[1]/div[1]/input")
  private WebElement accountNumber;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/div[1]/div[2]/input")
  private WebElement digit;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/div[2]/input")
  private WebElement transferValue;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/div[3]/input")
  private WebElement description;

  @FindBy(xpath = "//*[@id=\"__next\"]/div/div[3]/form/button")
  private WebElement transferButton;

  @FindBy(xpath = "//*[@id=\"btnBack\"]")
  private WebElement backButton;

  @FindBy(xpath = "//*[@id=\"modalText\"]")
  private WebElement alert;

  @FindBy(xpath = "//*[@id=\"btnCloseModal\"]")
  private WebElement closeModalButton;
}
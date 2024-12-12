package automacao.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automacao.utils.PageUtils;
import java.time.Duration;

public class ExtratoPage {

  private WebDriver driver;
  private WebDriverWait wait;
  private PageUtils pageUtils;

  public ExtratoPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    this.pageUtils = new PageUtils(driver);
    PageFactory.initElements(driver, this);
  }

  @FindBy(xpath = "")
  public WebElement botao;

}
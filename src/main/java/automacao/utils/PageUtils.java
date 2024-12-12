package automacao.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageUtils {

  @SuppressWarnings("unused")
  private WebDriver driver;
  private WebDriverWait wait;

  public PageUtils(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  }

  public void inserirElemento(WebElement elemento, String arg1) {
    wait.until(ExpectedConditions.visibilityOf(elemento)).clear();
    elemento.sendKeys(arg1);
  }

  public String obterTexto(WebElement elemento) {
    return wait.until(ExpectedConditions.visibilityOf(elemento)).getText();
  }

  public void clicar(WebElement elemento) {
    wait.until(ExpectedConditions.elementToBeClickable(elemento)).click();
  }
}

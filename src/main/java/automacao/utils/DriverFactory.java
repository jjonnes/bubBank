package automacao.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class DriverFactory {

  private static WebDriver driver;

  public static WebDriver getDriver() {
    if (driver == null) {
      PropertyReader config = PropertyReader.getInstance();

      switch (config.getBrowserType().toLowerCase()) {
        case "firefox":
          WebDriverManager.firefoxdriver().setup();
          FirefoxOptions firefoxOptions = new FirefoxOptions();
          if (config.isHeadless())
            firefoxOptions.addArguments("--headless");
          if (config.isIncognito())
            firefoxOptions.addArguments("-private");
          driver = new FirefoxDriver(firefoxOptions);
          break;

        case "edge":
          WebDriverManager.edgedriver().setup();
          EdgeOptions edgeOptions = new EdgeOptions();
          if (config.isHeadless())
            edgeOptions.addArguments("--headless");
          if (config.isIncognito())
            edgeOptions.addArguments("--inprivate");
          driver = new EdgeDriver(edgeOptions);
          break;

        default: // chrome
          WebDriverManager.chromedriver().setup();
          ChromeOptions chromeOptions = new ChromeOptions();
          if (config.isHeadless())
            chromeOptions.addArguments("--headless");
          if (config.isIncognito())
            chromeOptions.addArguments("--incognito");
          if (config.isBrowserMaximize())
            chromeOptions.addArguments("--start-maximized");
          driver = new ChromeDriver(chromeOptions);
      }

      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getDefaultTimeout()));
    }
    return driver;
  }

  public static void closeDriver() {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
}

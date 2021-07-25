import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class WebDriverFactory {
    private static Logger logger = LogManager.getLogger(WebDriverFactory.class);
    public static WebDriver getDriver(String browserName, PageLoadStrategy pageLoadStrategy) {
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                logger.info("Драйвер для браузера Google Chrome");
                WebDriver chromDriver = new ChromeDriver();

                // Специфичные настройки
                ChromeOptions options = new ChromeOptions();
                options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
                options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
                options.setAcceptInsecureCerts(false);
                options.setPageLoadStrategy(pageLoadStrategy);
                logger.info("Заданы специфичные настройки для браузера Google Chrome");

                // Добавление аргументов запуска Google Chrome
                options.addArguments("--start-maximized");
                options.addArguments("--incognito");
                return chromDriver;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                logger.info("Драйвер для браузера Mozilla Firefox");
                WebDriver firefoxDriver = new FirefoxDriver();
                // Специфичные настройки
                FirefoxOptions options2 = new FirefoxOptions();
                options2.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
                options2.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                options2.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
                options2.setAcceptInsecureCerts(false);
                options2.setPageLoadStrategy(pageLoadStrategy);
                logger.info("Заданы специфичные настройки для браузера Mozilla Firefox");

                // Добавление аргументов запуска Mozilla Firefox
                options2.addArguments("--start-maximized");
                options2.addArguments("--incognito");
                return firefoxDriver;
            default:
                throw new RuntimeException("Incorrect browser name");
        }
    }
}
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class SampleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(SampleTest.class);

    // Читаем передаваемый параметр browser (-Dbrowser)
    String env = System.getProperty("browser", "chrome");

    // Читаем передаваемый параметр browser (-Doption)
    PageLoadStrategy env2 = PageLoadStrategy.valueOf(System.getProperty("option", "NORMAL"));

    @BeforeEach
    public void setUp() {
        logger.info("env = " + env);
        logger.info("env = " + env2.name());
        driver = WebDriverFactory.getDriver(env.toLowerCase(),env2);
        logger.info("Драйвер стартовал!");
    }

    @Test
    public void openPage() {
        driver.get("https://www.dns-shop.ru");

        //Ожидание загрузки страницы
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

        // Отображение окна браузера в полноэкранном режиме
        driver.manage().window().fullscreen();

        logger.info("Открыта страница DNS - " + "https://www.dns-shop.ru");

        //вывод заголовка страницы
        String title = driver.getTitle();
        logger.info("title - " + title.toString());

        //вывод текущего URL
        String currentUrl = driver.getCurrentUrl();
        logger.info("current URL - " + currentUrl.toString());

        //  Закрыть  подтверждение города
        String buttonYes = "//a[text()='Да']";
        WebElement elementYes = driver.findElement(By.xpath(buttonYes));
        logger.info("WebElement: " + elementYes.getTagName() + " = " + elementYes.getText());
        elementYes.click();
        logger.info("Подтвержден город");

        //  Выбрать Бытовая техника
        String buttonTeh = "//a[text()='Бытовая техника']";
        WebElement elementTeh = driver.findElement(By.xpath(buttonTeh));
        logger.info("WebElement: " + elementTeh.getTagName() + " = " + elementTeh.getText());
        elementTeh.click();
        logger.info("Выбрана бытовая техника");

        //

        // Строка xpath запроса для поиска множества веб элементов
        String query = "//div[@class=\"subcategory__content\"]";
        // Поиск множества веб элементов
        List<WebElement> elements = driver.findElements(By.xpath(query));
        for (WebElement element : elements) {
            logger.info("WebElement: " + element.getText());
        }

        // Создание куки Cookie 1 и вывод информации по нему
        logger.info("Куки, которое добавили мы");
        driver.manage().addCookie(new Cookie("Cookie 1", "This Is Cookie 1"));
        Cookie cookie1 = driver.manage().getCookieNamed("Cookie 1");
        logger.info(String.format("Domain: %s", cookie1.getDomain()));
        logger.info(String.format("Expiry: %s", cookie1.getExpiry()));
        logger.info(String.format("Name: %s", cookie1.getName()));
        logger.info(String.format("Path: %s", cookie1.getPath()));
        logger.info(String.format("Value: %s", cookie1.getValue()));
        logger.info("--------------------------------------");

        // Вывод информации по кукам DNS
        logger.info("Куки, которое добавил DNS");
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            logger.info(String.format("Domain: %s", cookie.getDomain()));
            logger.info(String.format("Expiry: %s", cookie.getExpiry()));
            logger.info(String.format("Name: %s", cookie.getName()));
            logger.info(String.format("Path: %s", cookie.getPath()));
            logger.info(String.format("Value: %s", cookie.getValue()));
            logger.info("--------------------------------------");
        }

        //задержка sleep
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер остановлен!");
        }
    }
}

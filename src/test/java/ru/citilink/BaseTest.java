package ru.citilink;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Базовый класс {@code BaseTest} для тестов, содержащий общие настройки и инициализацию веб-драйвера.
 *
 * @author sergeyTrbv
 */
public class BaseTest {

    /**
     * Веб-драйвер, используемый для выполнения тестов.
     */
    protected WebDriver webDriver;

    /**
     * Метод {@code before()} используется для инициализации настроек перед каждым тестом.
     * Устанавливает путь к драйверу Chrome, создаёт экземпляр ChromeDriver с заданными настройками,
     * Открывает окно браузера на максимум.
     */
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");
        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().window().maximize();
    }
}

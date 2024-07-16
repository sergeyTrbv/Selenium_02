package ru.citilink;

import com.google.common.collect.ImmutableMap;
import helpers.Properties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * Базовый класс {@code BaseTest} для тестов, содержащий общие настройки и инициализацию веб-драйвера.
 * Этот класс предоставляет методы для настройки и закрытия веб-драйвера перед и после каждого теста.
 *
 * @author sergeyTrbv
 */
public class BaseTest {

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    protected WebDriver webDriver;

    /**
     * Метод, выполняемый перед каждым тестом для инициализации веб-драйвера.
     * Устанавливает свойства для ChromeDriver, настраивает его возможности и таймауты.
     */
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, Properties.
                testsProperties.pageLoadStrategy());
        capabilities.setCapability("timeouts", ImmutableMap.of("pageLoad",
                Properties.testsProperties.pageLoadTimeoutCapabilities()));

        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Properties.
                testsProperties.implicitWaitTimeout(), TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(Properties.
                testsProperties.pageLoadTimeout(), TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(Properties.
                testsProperties.scriptTimeout(), TimeUnit.SECONDS);
    }

    @AfterEach
    public void after() {
        webDriver.quit();
    }
}

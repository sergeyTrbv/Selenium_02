package ru.citilink;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {

    protected WebDriver webDriver;

    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY,"none");
        webDriver = new ChromeDriver(capabilities);

        webDriver.manage().window().maximize();
//        webDriver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
//        webDriver.manage().timeouts().pageLoadTimeout(6, TimeUnit.SECONDS);
//        webDriver.manage().timeouts().setScriptTimeout(6, TimeUnit.SECONDS);
    }
//    @AfterEach
//    public void after() {
//        webDriver.quit();
//    }
}

package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс {@code LoadingHelper} для ожидания загрузки элементов на странице.
 */
public class LoadingHelper {
    /**
     * Объект String с шаблоном XPath для элемента загрузки.
     */
    private static final String LOADING_ORANGE = "//div[@color='orange']";

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    private WebDriver driver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private WebDriverWait wait;

    /**
     * Конструктор класса.
     *
     * @param driver экземпляр WebDriver для управления браузером.
     * @param wait   экземпляр WebDriverWait для ожидания элементов на странице.
     */
    public LoadingHelper(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    /**
     * Метод {@code loading} ожидает, пока элемент загрузки исчезнет со страницы.
     */
    public void loading() {
        WebElement loadingElement = driver.findElement(By.xpath(LOADING_ORANGE));
        wait.until(ExpectedConditions.stalenessOf(loadingElement));
    }
}


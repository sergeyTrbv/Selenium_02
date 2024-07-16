package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс {@code CustomCookieHelper} предоставляет функциональность для взаимодействия с кнопкой согласия
 * на использование cookies на веб-странице.
 *
 * @author sergeyTrbv
 */
public class CustomCookieHelper {
    /**
     * Объект String с шаблоном XPath для кнопки согласия на использование cookies.
     */
    private static final String COOKIE_XPATH = "//button[ @data-meta-disabled='false']/span[text()='Я согласен']";

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    public WebDriver webDriver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    public WebDriverWait wait;

    /**
     * Конструктор класса CustomCookieHelper.
     *
     * @param webDriver экземпляр WebDriver для управления браузером.
     */
    public CustomCookieHelper(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }

    /**
     * Метод {@code clickCookieIfNeeded} для нажатия на кнопку согласия на использование cookies,
     * если она присутствует на странице.
     */
    @Step("Соглашаемся с cookie11")
    public void clickCookieIfNeeded() {
        WebElement cookieButon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(COOKIE_XPATH)));
        cookieButon.click();
    }
}

package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomCookieHelper {
    /**
     * Объект String с шаблоном XPath для кнопки согласия на использование cookies.
     */
    private static final String COOKIE_XPATH = "//button[ @data-meta-disabled=\"false\"]/span[text()='Я согласен']";

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    public WebDriver webDriver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    public WebDriverWait wait;

    public CustomCookieHelper(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, 10);
    }


    @Step("Соглашаемся с cookie11")
    public void clickCookieIfNeeded() {
        WebElement cookieButon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(COOKIE_XPATH)));
        cookieButon.click();
    }
}

package pages;


import helpers.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Класс {@code GoogleAfterSearch} предоставляет функциональность для работы с результатами поиска в Google.
 * Методы класса позволяют проверять наличие определенного текста в заголовках результатов поиска и
 * переходить по ссылкам.
 *
 * @author sergeyTrbv
 */
public class GoogleAfterSearch {

    /**
     * Префикс String с шаблоном XPath для поиска элементов результатов поиска.
     */
    private static final String SEARCH_RESULTS_HEAD = "//div[@id='rso']//a[@href]//h3[contains(.,'";

    /**
     * Суффикс String с шаблоном XPath для поиска элементов результатов поиска.
     */
    private static final String SEARCH_RESULT_TAIL = "')]";

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    private WebDriver driver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private WebDriverWait wait;

    /**
     * Конструктор класса, инициализирующий веб-драйвер и ожидание.
     *
     * @param driver Веб-драйвер для взаимодействия с браузером.
     */
    public GoogleAfterSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Конструктор класса, инициализирующий веб-драйвер, ожидание и выполняющий поиск по заданному запросу.
     *
     * @param driver      Веб-драйвер для взаимодействия с браузером.
     * @param searchQuery Поисковый запрос для выполнения поиска в Google.
     */
    public GoogleAfterSearch(WebDriver driver, String searchQuery) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        driver.get("https://www.google.ru/search?q=" + searchQuery);
    }

    /**
     * Метод {@code verifyTitleContainsText} проверяет наличие заданного текста в заголовках результатов поиска.
     *
     * @param link Текст, который должен содержаться в заголовке результата поиска.
     */
    public void verifyTitleContainsText(String link) {
        String xpath = SEARCH_RESULTS_HEAD + link + SEARCH_RESULT_TAIL;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assertions.assertFalse(driver.findElements(By.xpath(xpath)).isEmpty(),
                "Не найдено тайтла с текстом: '" + link);
    }

    /**
     * Метод {@code goToPageByLinkName} переходит на страницу по ссылке с заданным текстом в заголовке.
     *
     * @param link Текст, который должен содержаться в заголовке ссылки для перехода.
     */
    public void goToPageByLinkName(String link) {
        String xpath = SEARCH_RESULTS_HEAD + link + SEARCH_RESULT_TAIL;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        WebElement linkElement = driver.findElement(By.xpath(xpath));
        String originalWindow = driver.getWindowHandle();
        linkElement.click();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!originalWindow.equals(window)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
}

package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Класс {@code GoogleBeforeSearch} предоставляет функциональность для выполнения поиска в Google.
 *
 * @author sergeyTrbv
 */
public class GoogleBeforeSearch {

    /**
     * Объект String с шаблоном XPath для поиска для поля ввода поискового запроса. в Google.
     */
    private static final String SEARCH_FIELD = "//textarea[@title='Поиск']";

    /**
     * Объект String с шаблоном XPath для кнопки "Поиск в Google".
     */
    private static final String SEARCH_BUTTON = "//div[not (@jsname)]/center/*[@value='Поиск в Google']";

    /**
     * Объект String с шаблоном XPath для логотипа Google.
     */
    private static final String GOOGLE_LOGO = "//img[@alt='Google']";

    /**
     * Элемент поля ввода поискового запроса.
     */
    private WebElement inputSearch;

    /**
     * Элемент кнопки "Поиск в Google".
     */
    private WebElement buttonSearch;

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    private WebDriver driver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private final WebDriverWait wait;

    /**
     * Конструктор класса, инициализирующий веб-драйвер и ожидание, а также инициализирует необходимые элементы.
     *
     * @param driver Веб-драйвер для взаимодействия с браузером.
     */
    public GoogleBeforeSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_FIELD)));
        this.inputSearch = driver.findElement(By.xpath(SEARCH_FIELD));
        this.buttonSearch = driver.findElement(By.xpath(SEARCH_BUTTON));
    }

    /**
     * Метод {@code find} для выполнения поиска в Google по заданному тексту.
     *
     * @param text Текст поискового запроса.
     */
    @Step("Поиск в Google по слову {text}")
    public void find(String text) {
        inputSearch.sendKeys(text);
        driver.findElement(By.xpath(GOOGLE_LOGO)).click();
        buttonSearch.click();
    }
}

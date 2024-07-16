package pages.citilink;

import helpers.LoadingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Класс {@code SetParameters} предназначен для установки параметров на веб-сайте,
 * таких как диапазон цен и выбор брендов.
 *
 * @autor SergeyTrbv
 */
public class SetParameters {

    /**
     * Объект String с шаблоном XPath для поля ввода минимальной цены.
     */
    private static final String MIN_PRICE_FIELD = "//div[@data-meta-name='FilterDropdown' and " +
            "@data-meta-value='Цена']//input[@data-meta-name='FilterRangeGroup__input-min']";

    /**
     * Объект String с шаблоном XPath для поля ввода максимальной цены.
     */
    private static final String MAX_PRICE_FIELD = "//div[@data-meta-name='FilterDropdown' and " +
            "@data-meta-value='Цена']//input[@data-meta-name='FilterRangeGroup__input-max']";

    /**
     * Объект String с шаблоном XPath для таблицы выбора бренда товара.
     */
    private static final String BRAND_TABLE = "//div[@data-meta-name='FilterDropdown' " +
            "and @data-meta-value='Бренд']";

    /**
     * Объект String с началом шаблона XPath для чекбокса товара
     */
    private static final String HEAD_CHECKBOX = "//input[@type='checkbox' and @name='";

    /**
     * Объект String с закрывающим шаблоном XPath
     */
    private static final String TAIL_CHECKBOX = "']";

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    private WebDriver driver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private WebDriverWait wait;

    /**
     * Объект типа {@code LoadingHelper} для ожидания загрузки элементов на странице.
     */
    private LoadingHelper loadingHelper;

    /**
     * Конструктор класса.
     *
     * @param driver экземпляр WebDriver для управления браузером.
     */
    public SetParameters(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.loadingHelper = new LoadingHelper(driver, wait);
    }

    /**
     * Метод {@code setParameters} устанавливает параметры на сайте, включая диапазон цен и выбор брендов.
     *
     * @param minPrice минимальная цена для установки.
     * @param maxPrice максимальная цена для установки.
     * @param brands   список брендов для выбора.
     */
    @Step("Шаги по установке параметров")
    public void setParameters(String minPrice, String maxPrice, List<String> brands) {
        setPriceParameters(minPrice, maxPrice);
        choosingBrand(brands);
    }

    /**
     * Метод {@code setPriceParameters} устанавливает верхний и нижний диапазон цен.
     *
     * @param minPrice минимальная цена для установки.
     * @param maxPrice максимальная цена для установки.
     */
    @Step("Установка верхнего и нижнего диапазона цен")
    private void setPriceParameters(String minPrice, String maxPrice) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MIN_PRICE_FIELD)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(MAX_PRICE_FIELD)));
        WebElement minPriceField = driver.findElement(By.xpath(MIN_PRICE_FIELD));
        WebElement maxPriceField = driver.findElement(By.xpath(MAX_PRICE_FIELD));
        minPriceField.click();
        minPriceField.clear();
        minPriceField.sendKeys(minPrice);
        minPriceField.sendKeys(Keys.ENTER);
        loadingHelper.loading();
        maxPriceField.click();
        maxPriceField.clear();
        maxPriceField.sendKeys(maxPrice);
        minPriceField.sendKeys(Keys.ENTER);
        loadingHelper.loading();
    }

    /**
     * Метод {@code choosingBrand} выбирает указанные бренды из списка.
     *
     * @param brands список брендов для выбора.
     */
    @Step("Выбор бренда")
    private void choosingBrand(List<String> brands) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = (new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BRAND_TABLE))));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        for (String brand : brands) {
            WebElement checkBox = driver.findElement(By.xpath(HEAD_CHECKBOX
                    + brand + TAIL_CHECKBOX));
            checkBox.click();
            loadingHelper.loading();
        }
    }
}

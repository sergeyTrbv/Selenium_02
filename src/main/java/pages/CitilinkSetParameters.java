package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Класс {@code CitilinkSetParameters} для установки параметров на сайте Citilink.
 * Предоставляет методы для установки диапазона цен и выбора брендов.
 *
 * @author sergeyTrbv
 */
public class CitilinkSetParameters {

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
     * Объект String с шаблоном XPath для кнопки согласия с cookie.
     */
    private static final String COOKIE_BUTTON = "//button[@data-meta-disabled='false' and " +
            "@type='button']//span[text()='Я согласен']";

    /**
     * Объект String с шаблоном XPath для таблицы выбора бренда товара.
     */
    private static final String BRAND_TABLE = "//div[@data-meta-name='FilterDropdown' and @data-meta-value='Бренд']";

    /**
     * Объект String с шаблоном XPath для чекбокса выбора бренда HP.
     */
    private static final String CHECKBOX_HP = "//input[@type='checkbox' and @id='hp']";

    /**
     * Объект String с шаблоном XPath для чекбокса выбора бренда Lenovo.
     */
    private static final String CHECKBOX_LENOVO = "//input[@type='checkbox' and @id='lenovo']";

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
     */
    public CitilinkSetParameters(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Метод {@code setPriceParameters} устанавливает верхний и нижний диапазон цен,
     * а так же соглашается с условиями хранения cookie.
     *
     * @param minPrice минимальная цена для установки.
     * @param maxPrice максимальная цена для установки.
     */
    @Step("Установка верхнего и нижнего диапазона цен")
    private void setPriceParameters(String minPrice, String maxPrice) {
        wait.until(visibilityOfElementLocated(By.xpath(MIN_PRICE_FIELD)));
        wait.until(visibilityOfElementLocated(By.xpath(MAX_PRICE_FIELD)));
        WebElement minPriceField = driver.findElement(By.xpath(MIN_PRICE_FIELD));
        WebElement maxPriceField = driver.findElement(By.xpath(MAX_PRICE_FIELD));
        minPriceField.click();
        minPriceField.clear();
        minPriceField.sendKeys(minPrice);
        maxPriceField.click();
        maxPriceField.clear();
        maxPriceField.sendKeys(maxPrice);
        minPriceField.click();

        wait.until(visibilityOfElementLocated(By.xpath(COOKIE_BUTTON)));
        WebElement cookieButon = driver.findElement(By.xpath(COOKIE_BUTTON));
        cookieButon.click();
        //знаю что нельзя использовать, но не смог найти быстро решение получше
        //раскаиваюсь
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод {@code choosingBrand} выбирает бренды HP и Lenovo.
     */
    @Step("Выбор бренда")
    private void choosingBrand() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement brandTable = driver.findElement(By.xpath(BRAND_TABLE));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", brandTable);

        WebElement checkBoxHP = driver.findElement(By.xpath(CHECKBOX_HP));
        checkBoxHP.click();

        WebElement checkBoxLenovo = driver.findElement(By.xpath(CHECKBOX_LENOVO));
        checkBoxLenovo.click();
    }

    /**
     * Метод {@code setParameters} устанавливает параметры на сайте, включая диапазон цен и выбор брендов.
     *
     * @param minPrice минимальная цена для установки.
     * @param maxPrice максимальная цена для установки.
     */
    @Step("Шаги по установке параметров")
    public void setParameters(String minPrice, String maxPrice) {
        setPriceParameters(minPrice, maxPrice);
        choosingBrand();
    }
}

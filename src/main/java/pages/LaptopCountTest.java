package pages;


import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Класс {@code LaptopCountTest} предназначен для тестирования количества и характеристик ноутбуков в каталоге.
 *
 * @author sergeyTrbv
 */
public class LaptopCountTest {

    /**
     * Объект String с шаблоном XPath для карточек продуктов в каталоге.
     */
    private static final String PRODUCT_CARD = "//div[contains(@class, 'e1l56t9a0')]";

    /**
     * Объект String с шаблоном XPath для кнопки "Следующая" для перехода на следующую страницу каталога.
     */
    private static final String NEXT_BUTTON = "//div[text()='Следующая']";

    /**
     * Объект String с шаблоном XPath для элементов с описанием товара в каталоге.
     */
    private static final String TITLE_ELEMENTS_IN_CATALOG = ".//a[contains(@class, 'app-catalog-9gnskf') and " +
            "not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]";

    /**
     * Объект String с шаблоном XPath для элементов с ценами товара в каталоге.
     */
    private static final String PRICE_ELEMENTS_IN_CATALOG = ".//span[contains(@class, 'e1j9birj0') and " +
            "not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]";

    /**
     * Минимальное количество продуктов, которое должно быть на странице.
     */
    private static final int MIN_PRODUCT_COUNT = 12;

    /**
     * Минимальная цена продукта.
     */
    private static final int MIN_PRICE = 20000;

    /**
     * Максимальная цена продукта.
     */
    private static final int MAX_PRICE = 120000;

    /**
     * Экземпляр WebDriver для взаимодействия с браузером.
     */
    private WebDriver driver;

    /**
     * Экземпляр WebDriverWait для ожидания элементов на странице.
     */
    private WebDriverWait wait;

    /**
     * Текущая страница каталога.
     */
    private int currentPage = 1;

    /**
     * Конструктор класса, инициализирующий WebDriver и WebDriverWait.
     *
     * @param driver экземпляр WebDriver для взаимодействия с браузером.
     */
    public LaptopCountTest(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    /**
     * Метод {@code checkProductQuantityOnFirstPage} проверяет количество элементов на первой странице каталога.
     * Убеждается, что количество элементов не меньше {@code MIN_PRODUCT_COUNT}.
     */
    @Step("Проверка количества элементов на первой странице каталога")
    public void checkProductQuantityOnFirstPage() {
        List<WebElement> productItems = driver.findElements(By.xpath(PRODUCT_CARD));
        Assertions.assertTrue(productItems.size() >= MIN_PRODUCT_COUNT,
                "Количество позиций товара меньше " + MIN_PRODUCT_COUNT);
    }

    /**
     * Метод {@code checkConditionsOnAllPages} проверяет условия на всех страницах каталога.
     * Переходит на следующую страницу, пока она доступна, и проверяет условия на каждой странице.
     */
    @Step("Проверка соблюдения условий на всех страницах у всех элементов")
    public void checkConditionsOnAllPages() {
        boolean hasNextPage = true;
        while (hasNextPage) {
            checkPageConditions();
            hasNextPage = navigateToNextPage();
        }
    }

    /**
     * Метод {@code checkPageConditions} проверяет условия на текущей странице каталога.
     * Включает проверку названий брендов и цен.
     */
    @Step("Проверка условий на текущей странице")
    private void checkPageConditions() {
        waitForPageLoad();
        checkBrandNames();
        checkPrices();
    }

    /**
     * Метод {@code checkBrandNames} проверяет названия брендов на текущей странице.
     * Убеждается, что названия содержат "HP" или "Lenovo".
     */
    @Step("Проверка названий брендов на текущей странице")
    private void checkBrandNames() {
        List<WebElement> productElementsTitle = driver.findElements(By.xpath(TITLE_ELEMENTS_IN_CATALOG));
        for (WebElement productElement : productElementsTitle) {
            String title = productElement.getAttribute("title");
            Assertions.assertTrue(title.contains("HP") || title.contains("Lenovo"),
                    "На странице " + currentPage +
                            " название бренда техники не соответствует 'HP' или 'Lenovo'");
        }
    }

    /**
     * Метод {@code checkPrices} проверяет цены на текущей странице.
     * Убеждается, что цены находятся в диапазоне от {@code MIN_PRICE} до {@code MAX_PRICE}.
     */
    @Step("Проверка цен на текущей странице")
    private void checkPrices() {
        List<WebElement> productElementsPrice = driver.findElements(By.xpath(PRICE_ELEMENTS_IN_CATALOG));
        for (WebElement productElement : productElementsPrice) {
            String priceText = productElement.getText().replace(" ", "").
                    replace("₽", "");
            int price = Integer.parseInt(priceText);
            Assertions.assertTrue(price >= MIN_PRICE && price <= MAX_PRICE,
                    "На странице " + currentPage + " цена " + price +
                            " не соответствует вилке от " + MIN_PRICE + " до " + MAX_PRICE);
        }
    }

    /**
     * Метод {@code navigateToNextPage} переходит на следующую страницу каталога, если она доступна.
     *
     * @return {@code true}, если переход на следующую страницу был успешным, иначе {@code false}.
     */
    @Step("Переход на следующую страницу")
    private boolean navigateToNextPage() {
        List<WebElement> nextPageButtons = driver.findElements(By.xpath(NEXT_BUTTON));
        if (!nextPageButtons.isEmpty() && nextPageButtons.get(0).isEnabled()) {
            WebElement nextButton = nextPageButtons.get(0);
            scrollIntoViewAndClick(nextButton);
            currentPage++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод {@code scrollIntoViewAndClick} прокручивает элемент в поле зрения и кликает по нему.
     *
     * @param element элемент, который нужно прокрутить и кликнуть.
     */
    @Step("Прокрутка элемента в поле зрения и клик")
    private void scrollIntoViewAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        js.executeScript("arguments[0].click();", element);
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    /**
     * Ожидает загрузки страницы.
     * Используется Thread.sleep для простоты, хотя это не рекомендуется для реальных приложений.
     */
    @Step("знаю что нельзя")
    private void waitForPageLoad() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



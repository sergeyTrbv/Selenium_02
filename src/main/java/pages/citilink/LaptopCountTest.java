package pages.citilink;


import helpers.Assertions;
import helpers.LoadingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Класс {@code LaptopCountTest} предназначен для тестирования количества и характеристик ноутбуков в каталоге.
 *
 * @author sergeyTrbv
 */
public class LaptopCountTest {

    /**
     * Объект String с шаблоном XPath для карточек продуктов в каталоге.
     */
    private static final String PRODUCT_CARD = "//div[@data-meta-name='ProductVerticalSnippet']";

    /**
     * Объект String с шаблоном XPath для кнопки "Следующая" для перехода на следующую страницу каталога.
     */
    private static final String NEXT_BUTTON = "//div[text()='Следующая']";

    /**
     * Объект String с шаблоном XPath для элементов с описанием товара в каталоге.
     */
    private static final String TITLE_ELEMENTS_IN_CATALOG = "//div[@data-meta-name='ProductVerticalSnippet']" +
            "//a[@data-meta-name='Snippet__title']";

    /**
     * Объект String с шаблоном XPath для элементов с ценами товара в каталоге.
     */
    private static final String PRICE_ELEMENTS_IN_CATALOG = "//div[@data-meta-name='ProductVerticalSnippet']" +
            "//span[@data-meta-is-total='notTotal']";

    /**
     * Экземпляр WebDriver для взаимодействия с браузером.
     */
    private WebDriver driver;

    /**
     * Экземпляр WebDriverWait для ожидания элементов на странице.
     */
    private WebDriverWait wait;

    /**
     * Объект типа {@code LoadingHelper} для ожидания загрузки элементов на странице.
     */
    private LoadingHelper loadingHelper;

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
        this.loadingHelper = new LoadingHelper(driver, wait);
    }

    /**
     * Метод {@code checkProductQuantityOnFirstPage} проверяет количество элементов на первой странице каталога.
     * Убеждается, что количество элементов не меньше {@code MIN_PRODUCT_COUNT}.
     */
    @Step("Проверка количества элементов на первой странице каталога")
    public void checkProductQuantityOnFirstPage(int minProductCountInPage) {
        List<WebElement> productItems = driver.findElements(By.xpath(PRODUCT_CARD));
        Assertions.assertTrue(productItems.size() >= minProductCountInPage,
                "Количество позиций товара меньше " + minProductCountInPage);
    }

    /**
     * Метод {@code checkConditionsOnAllPages} проверяет условия на всех страницах каталога.
     * Переходит на следующую страницу, пока она доступна, и проверяет условия на каждой странице.
     */
    @Step("Проверка соблюдения условий на всех страницах у всех элементов")
    public void checkConditionsOnAllPages(List<String> brand, String minPrice, String maxPrice) {
        boolean hasNextPage = true;

        while (hasNextPage) {
            checkPageConditions(brand, minPrice, maxPrice);
            hasNextPage = navigateToNextPage();
        }
    }

    /**
     * Метод {@code checkPageConditions} проверяет условия на текущей странице каталога.
     * Включает проверку названий брендов и цен.
     */
    @Step("Проверка условий на текущей странице")
    private void checkPageConditions(List<String> brand, String minPrice, String maxPrice) {
//        waitForPageLoad();
        checkBrandNames(brand);
        checkPrices(minPrice, maxPrice);
    }

    /**
     * Метод {@code checkBrandNames} проверяет названия брендов на текущей странице.
     * Убеждается, что названия содержат "HP" или "Lenovo".
     */
    @Step("Проверка названий брендов на текущей странице")
    private void checkBrandNames(List<String> brand) {
        List<WebElement> productElementsTitle = driver.findElements(By.xpath(TITLE_ELEMENTS_IN_CATALOG));
        for (WebElement productElement : productElementsTitle) {
            String title = productElement.getAttribute("title");
            boolean containsBrand = brand.stream().anyMatch(b -> Pattern.compile(Pattern.quote(b), Pattern.CASE_INSENSITIVE).matcher(title).find());
            Assertions.assertTrue(containsBrand,
                    "На странице " + currentPage +
                            " название бренда техники не соответствует ни одному из ожидаемых брендов: " + brand);
        }
    }


    /**
     * Метод {@code checkPrices} проверяет цены на текущей странице.
     * Убеждается, что цены находятся в диапазоне от {@code MIN_PRICE} до {@code MAX_PRICE}.
     */
    @Step("Проверка цен на текущей странице")
    private void checkPrices(String minPrice, String maxPrice) {
        int minPriceInt = Integer.parseInt(minPrice);
        int maxPriceInt = Integer.parseInt(maxPrice);
        List<WebElement> productElementsPrice = driver.findElements(By.xpath(PRICE_ELEMENTS_IN_CATALOG));
        for (WebElement productElement : productElementsPrice) {
            String priceText = productElement.getText().replace(" ", "").
                    replace("₽", "");
            int price = Integer.parseInt(priceText);
            Assertions.assertTrue(price >= minPriceInt && price <= maxPriceInt,
                    "На странице " + currentPage + " цена " + price +
                            " не соответствует вилке от " + minPriceInt + " до " + maxPriceInt);
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
            clickElement(nextButton);
            currentPage++;
            return true;
        } else {
            return false;
        }
    }

    private void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //элемент перехвачен
        element.click();
        loadingHelper.loading();
        wait.until(ExpectedConditions.stalenessOf(element));
    }
}



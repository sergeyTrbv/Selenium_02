package pages;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
/**
 * Класс {@code CitilinkGetProduct} для автоматизации тестирования функционала получения и сравнения товаров на сайте Citilink.
 * Использует Selenium WebDriver для взаимодействия с веб-элементами.
 *
 * @author sergeyTrbv
 */
public class CitilinkGetProduct {

    /**
     * Объект String с шаблоном XPath для кнопки первой страницы в результатах поиска.
     */
    private static final String FIRST_PAGE_BUTTON = "//a[@data-meta-name='PageLink__page-1']";

    /**
     * Объект String с шаблоном XPath для первого продукта в списке результатов поиска.
     */
    private static final String PRODUCT = "//div[contains(@class, 'app-catalog-1bogmvw')][1]";

    /**
     * Объект String с шаблоном XPath для элемента, содержащего название продукта.
     */
    private static final String PRODUCT_TITLE = ".//a[@data-meta-name='Snippet__title']";

    /**
     * Объект String с шаблоном XPath для поля поиска на странице.
     */
    private static final String SEARCH_WINDOW = "//input[@type='search']";

    /**
     * Объект String с шаблоном XPath для кнопки поиска на странице.
     */
    private static final String SEARCH_BUTTON = "//button[@type='submit' and contains(@class, 'css-c064wa')]";

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
    public CitilinkGetProduct(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Метод {@code backToFirstPage} для возвращения на первую страницу результатов поиска.
     */
    @Step("Возвращаемся на первую страницу")
    private void backToFirstPage() {
        WebElement firstPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_PAGE_BUTTON)));
        firstPageButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRODUCT)));
    }

    /**
     * Метод {@code getProductTitle} для получения названия продукта по заданному XPath.
     *
     * @param productXpath XPath элемента продукта.
     * @return название продукта.
     */
    @Step("Запоминаем название продукта")
    private String getProductTitle(String productXpath) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productXpath)));
        WebElement productContainer = driver.findElement(By.xpath(productXpath));
        WebElement productLink = waitForElement(productContainer, By.xpath(PRODUCT_TITLE));
        String title = productLink.getAttribute("title");
        System.out.println("Title of the product: " + title);
        return title;
    }

    /**
     * Метод {@code waitForElement} для ожидания и получения вложенного элемента.
     *
     * @param parent родительский элемент.
     * @param by     стратегия поиска вложенного элемента.
     * @return вложенный элемент.
     */
    private WebElement waitForElement(WebElement parent, By by) {
        return wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, by)
        )).get(0);
    }

    /**
     * Метод {@code searchProduct} для ввода названия продукта в поле поиска.
     *
     * @param productTitle название продукта для поиска.
     */
    @Step("В поисковике ищем товар")
    private void searchProduct(String productTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_WINDOW)));
        WebElement searchProduct = driver.findElement(By.xpath(SEARCH_WINDOW));
        searchProduct.click();
        searchProduct.clear();
        searchProduct.sendKeys(productTitle);
    }

    /**
     * Метод {@code clickSearch} для нажатия кнопки поиска.
     */
    @Step("Нажимаем поиск")
    private void clickSearch() {
        WebElement clickSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_BUTTON)));
        clickSearch.click();
    }

    /**
     * Метод {@code compareProducts} для сравнения двух названий продуктов.
     *
     * @param product1 первое название продукта.
     * @param product2 второе название продукта.
     */
    @Step("Сравниваем товары")
    private void compareProducts(String product1, String product2) {
        Assertions.assertEquals(product1, product2, "Товары не равны");
    }

    /**
     * Метод {@code productComparison} для сравнения товаров, включающий все шаги от возвращения
     * на первую страницу до сравнения названий.
     */
    @Step("Сравнение товара")
    public void productComparison() {
        backToFirstPage();
        String firstProductTitle = getProductTitle(PRODUCT);
        searchProduct(firstProductTitle);
        clickSearch();
        String secondProductTitle = getProductTitle(PRODUCT);
        compareProducts(firstProductTitle, secondProductTitle);
    }
}


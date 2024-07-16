package pages.citilink;

import helpers.Assertions;
import helpers.LoadingHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс {@code ProductInFirstPage} предназначен для  выполнения операций на первой странице каталога продуктов.
 * Он включает методы для перехода на первую страницу, поиска продуктов, получения и сравнения их названий.
 *
 * @author sergeyTrbv
 */
public class ProductInFirstPage {

    /**
     * Объект String с шаблоном XPath для кнопки первой страницы в результатах поиска.
     */
    private static final String FIRST_PAGE_BUTTON = "//a[@data-meta-name='PageLink__page-1']";


    /**
     * Объект String с шаблоном XPath для элементов с описанием товара в каталоге.
     */
    private static final String TITLE_ELEMENTS_IN_CATALOG = "//div[@data-meta-name='ProductVerticalSnippet']" +
            "//a[@data-meta-name='Snippet__title']";

    /**
     * Объект String с шаблоном XPath для элемента, содержащего название продукта.
     */
    private static final String PRODUCT_TITLE = ".//a[@data-meta-name='Snippet__title']";

    /**
     * Объект String с шаблоном XPath для строки поиска на странице.
     */
    private static final String SEARCH_WINDOW = "//input[@type='search']";

    /**
     * Объект String с шаблоном XPath для кнопки поиска на странице.
     */
    private static final String SEARCH_BUTTON = "//div[@data-meta-name='input-search']//button[@type='submit']";

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
    public ProductInFirstPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.loadingHelper = new LoadingHelper(driver, wait);
    }
// loadingHelper.loading();

    /**
     * Метод {@code productComparison} для сравнения товаров, включающий все шаги от возвращения.
     * Осуществляет переход на первую страницу, получает название первого товара,
     * выполняет поиск этого товара, нажимает кнопку поиска, получает название второго товара
     * и сравнивает их.
     */
    @Step("Сравнение товара")
    public void productComparison() {
        backToFirstPage();
        String firstProductTitle = getProductTitleInPage(TITLE_ELEMENTS_IN_CATALOG);
        searchProduct(firstProductTitle);
        clickSearch();
        String secondProductTitle = getProductTitleInPage(TITLE_ELEMENTS_IN_CATALOG);
        compareProducts(firstProductTitle, secondProductTitle);
    }

    /**
     * Метод {@code getProductTitleInPage} для получения названия продукта на текущей странице.
     *
     * @param xpath XPath элемента заголовка продукта.
     * @return Название первого продукта на странице.
     * @throws NoSuchElementException если элементы с заданным XPath не найдены.
     */
    private String getProductTitleInPage(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        List<WebElement> productTitles = driver.findElements(By.xpath(TITLE_ELEMENTS_IN_CATALOG));
        if (productTitles.isEmpty()) {
            throw new NoSuchElementException("С помощью XPath названия продуктов не найдены: " + xpath);
        }
        WebElement firstProduct = productTitles.get(0);
        String firstProductTitle = firstProduct.getText();
        System.out.println("1-й товар : " + firstProductTitle);
        return firstProductTitle;
    }

    /**
     * Метод {@code backToFirstPage} для возвращения на первую страницу результатов поиска.
     */
    @Step("Возвращаемся на первую страницу")
    private void backToFirstPage() {
        List<WebElement> firstPageButtons = driver.findElements(By.xpath(FIRST_PAGE_BUTTON));
        if (!firstPageButtons.isEmpty()) {
            WebElement firstPageButton = wait.until(ExpectedConditions.
                    elementToBeClickable(By.xpath(FIRST_PAGE_BUTTON)));
            firstPageButton.click();
            System.out.println("Перехожу на первую страницу.");
            loadingHelper.loading();
        } else {
            System.out.println("Кнопка первой страницы не найдена. Продолжаем выполнение теста на текущей странице.");
        }
    }

    /**
     * Метод {@code getProductTitle} для получения названия продукта.
     *
     * @param productXpath XPath элемента продукта.
     * @return название продукта.
     */
    @Step("Запоминаем название продукта")
    private String getProductTitle(String productXpath) {

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
        //тут ошибка
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
     * @param productInFirstPage первое название продукта.
     * @param foundProduct       второе название продукта.
     */
    @Step("Сравниваем товары")
    private void compareProducts(String productInFirstPage, String foundProduct) {
        Assertions.assertEquals(productInFirstPage, foundProduct, "Товары не равны");
    }
}


package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

/**
 * Класс {@code stepsAll} содержит методы для выполнения различных шагов в тестах.
 * Каждый метод аннотирован с помощью {@code @Step}, что указывает на его роль в тестовом сценарии.
 *
 * @author sergeyTrbv
 */
public class stepsAll {
    private static WebDriverWait wait;
    private static WebDriver driver;

    /**
     * Открывает сайт по заданному URL и ждет, пока заголовок страницы не станет равным ожидаемому заголовку.
     *
     * @param url           URL сайта, который нужно открыть.
     * @param title         Ожидаемый заголовок страницы.
     * @param currentDriver Веб-драйвер, используемый для открытия сайта.
     */
    @Step("Переход на сайт: {url}")
    public static void openSite(String url, String title, WebDriver currentDriver) {
        driver = currentDriver;
        driver.get(url);
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * Выполняет поиск в Google по заданной ключевой фразе.
     *
     * @param searchQuery Ключевая фраза для поиска.
     */
    @Step("Поиск в поисковике по ключевой фразе: {searchQuery}")
    public static void searchInGoogle(String searchQuery) {
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(driver);
        googleBeforeSearch.find(searchQuery);
    }

    /**
     * Проверяет наличие заданного заголовка в результатах поиска Google.
     *
     * @param title Заголовок, наличие которого нужно проверить.
     */
    @Step("Проверка наличия тайтла: {title} в результатах поиска Google")
    public static void validateSearchInGoogle(String title) {
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(driver);
        googleAfterSearch.verifyTitleContainsText(title);
    }

    /**
     * Переходит на сайт по заданному заголовку.
     *
     * @param title Заголовок, по которому нужно перейти на сайт.
     */
    @Step("Переход на сайт по заголовку: {title}")
    public static void goSiteByLink(String title) {
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(driver);
        googleAfterSearch.goToPageByLinkName(title);
    }

    /**
     * Открывает каталог и выполняет поиск по заданному разделу.
     *
     * @param chapter     Раздел каталога для поиска.
     * @param expectedUrl Ожидаемый URL после поиска.
     */
    @Step("Поиск в каталоге раздела: {chapter}")
    public static void openCatalog(String chapter, String expectedUrl) {
        CitilinkSearchInCatalog citilinkSearchInCatalog = new CitilinkSearchInCatalog(driver);
        citilinkSearchInCatalog.searchChapterInCatalog(chapter, expectedUrl);
    }

    /**
     * Вводятся определённые параметры продукта.
     */
    @Step("Ввод параметров продукта")
    public static void setParameters(String minPrice, String maxPrice) {
        CitilinkSetParameters citilinkSetParameters = new CitilinkSetParameters(driver);
        citilinkSetParameters.setParameters(minPrice, maxPrice);
    }

    /**
     * Проверяются элементы на первой и остальных страницах.
     */
    @Step("Проверка элементов на первой и остальных страницах")
    public static void checkingElements() {
        LaptopCountTest laptopCountTest = new LaptopCountTest(driver);
        laptopCountTest.checkProductQuantityOnFirstPage();
        laptopCountTest.checkConditionsOnAllPages();
    }

    /**
     * Переход на первую страницу, получение первого элемента и повторный ипоиск его в поисковике сайта.
     */
    @Step("Возвращение на первую страницу, получение элемента и повторный его поиск")
    public static void returnAndSearchElement() {
        CitilinkGetProduct citilinkGetProduct = new CitilinkGetProduct(driver);
        citilinkGetProduct.productComparison();
    }
}

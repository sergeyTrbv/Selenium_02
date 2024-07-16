package steps;

import helpers.CustomCookieHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.citilink.*;

import java.util.List;

/**
 * Класс {@code StepsAll} содержит статические методы, которые представляют собой
 * шаги для выполнения различных действий
 * в процессе тестирования веб-сайта. Эти методы используются для навигации по сайту,
 * поиска и проверки элементов,
 * установки параметров и сравнения продуктов.
 *
 * @author sergeyTrbv
 */
public class StepsAll {

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private static WebDriverWait wait;

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    private static WebDriver driver;

    /**
     * Открывает сайт по заданному URL, ожидает, пока заголовок страницы не станет равным заданному значению,
     * и обрабатывает необходимые куки.
     *
     * @param startUrl      URL сайта, который нужно открыть.
     * @param title         ожидаемый заголовок страницы.
     * @param currentDriver веб-драйвер, используемый для взаимодействия с браузером.
     */
    @Step("Переход на сайт: {startUrl}")
    public static void openSite(String startUrl, String title, WebDriver currentDriver) {
        driver = currentDriver;
        driver.get(startUrl);
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleIs(title));

        CustomCookieHelper customCookieManager = new CustomCookieHelper(driver);
        customCookieManager.clickCookieIfNeeded();
    }

    /**
     * Открывает каталог и ищет раздел по заданным параметрам.
     *
     * @param textMenu текст меню для навигации.
     * @param section  секция каталога.
     * @param chapter  раздел каталога.
     */
    @Step("Открытие каталога и поиск раздела: {chapter}")
    public static void openCatalog(String textMenu, String section, String chapter) {
        SearchInCatalog citilinkSearchInCatalog = new SearchInCatalog(driver);
        citilinkSearchInCatalog.searchChapterInCatalog(textMenu, section, chapter);
    }

    /**
     * Устанавливает параметры продукта, такие как минимальная и максимальная цена, а также бренды.
     *
     * @param minPrice минимальная цена продукта.
     * @param maxPrice максимальная цена продукта.
     * @param brand    список брендов для фильтрации.
     */
    @Step("Ввод параметров продукта")
    public static void setParameters(String minPrice, String maxPrice, List<String> brand) {
        SetParameters citilinkSetParameters = new SetParameters(driver);
        citilinkSetParameters.setParameters(minPrice, maxPrice, brand);
    }

    /**
     * Проверяет количество продуктов на первой странице и условия на всех страницах каталога.
     *
     * @param minProductCountInPage минимальное ожидаемое количество продуктов на странице.
     * @param brand                 список брендов для проверки.
     * @param minPrice              минимальная цена для проверки.
     * @param maxPrice              максимальная цена для проверки.
     */
    @Step("Проверка элементов на первой и остальных страницах")
    public static void checkingElements(int minProductCountInPage, List<String> brand,
                                        String minPrice, String maxPrice) {
        ProductsMeetsConditions laptopCountTest = new ProductsMeetsConditions(driver);
        laptopCountTest.checkProductQuantityOnFirstPage(minProductCountInPage);
        laptopCountTest.checkConditionsOnAllPages(brand, minPrice, maxPrice);
    }

    /**
     * Возвращается на первую страницу, получает элемент и повторно ищет его для сравнения.
     */
    @Step("Возвращение на первую страницу, получение элемента и повторный его поиск")
    public static void returnAndSearchElement() {
        ProductInFirstPage citilinkGetProduct = new ProductInFirstPage(driver);
        citilinkGetProduct.productComparison();
    }
}

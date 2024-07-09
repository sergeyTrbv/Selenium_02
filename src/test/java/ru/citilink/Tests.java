package ru.citilink;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static helpers.Properties.testsProperties;
import static steps.stepsAll.*;

/**
 * Класс {@code Tests} содержит тестовые методы, которые проверяют функциональность поисковика и интернет-магазина.
 * Наследуется от класса {@code BaseTest}, который предоставляет базовую настройку и инициализацию веб-драйвера.
 *
 * @author sergeyTrbv
 */
public class Tests extends BaseTest {

    /**
     * Метод {@code testCitilinkProductStepsAll} проверяет поиск товара на сайте Citilink.
     * Использует параметризованный тест с данными, предоставленными методом
     * {@code providerCheckingLaptop} из класса {@code DataProvider}.
     *
     * @param searchQuery строка запроса для поиска в Google
     * @param title       заголовок страницы, который должен быть найден в результатах поиска
     * @param chapter     название раздела, который должен быть открыт на сайте Citilink
     * @param expectedUrl ожидаемый URL страницы с ноутбуками на сайте Citilink
     * @param minPrice    минимальная стоимость товара
     * @param maxPrice    максимальная стоимость товара
     */
    @Feature("Проверка поисковика citilink")
    @DisplayName("Проверка поисковика citilink - всё в степах")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingLaptop")
    public void testCitilinkProductStepsAll(String searchQuery, String title, String chapter, String expectedUrl,
                                            String minPrice, String maxPrice) {

        openSite(testsProperties.googleUrl(), "Google", webDriver);
        searchInGoogle(searchQuery);
        validateSearchInGoogle(title);
        goSiteByLink(title);
        openCatalog(chapter, expectedUrl);
        setParameters(minPrice, maxPrice);
        checkingElements();
        returnAndSearchElement();
    }
}

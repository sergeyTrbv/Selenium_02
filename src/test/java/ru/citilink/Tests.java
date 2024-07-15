package ru.citilink;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static steps.StepsAll.*;

/**
 * Класс {@code Tests} содержит параметризованные тесты для проверки функциональности поисковика сайта Citilink.
 * Тесты используют шаги, определенные в классе {@code StepsAll}, и данные, предоставляемые {@code DataProvider}.
 *
 * @author sergeyTrbv
 */
public class Tests extends BaseTest {

    /**
     * Метод проверяет функциональность поисковика Citilink, используя предоставленные параметры.
     *
     * @param startUrl URL начальной страницы для открытия сайта.
     * @param title Ожидаемый заголовок страницы.
     * @param textMenu Текст меню для навигации.
     * @param section Секция каталога для выбора.
     * @param chapter Глава каталога для выбора.
     * @param minPrice Минимальная цена для фильтрации товаров.
     * @param maxPrice Максимальная цена для фильтрации товаров.
     * @param brand Список брендов для фильтрации товаров.
     * @param minProductCountInPage Минимальное ожидаемое количество товаров на странице.
     */
    @Feature("Проверка поисковика citilink")
    @DisplayName("Проверка поисковика citilink - всё в степах")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingLaptop")
    public void testCitilinkProductStepsAll(String startUrl, String title, String textMenu, String section,
                                            String chapter, String minPrice, String maxPrice, List<String> brand,
                                            int minProductCountInPage) {


        openSite(startUrl, title, webDriver);
        openCatalog(textMenu, section, chapter);
        setParameters(minPrice, maxPrice, brand);
        checkingElements(minProductCountInPage, brand, minPrice, maxPrice);
        returnAndSearchElement();
    }
}

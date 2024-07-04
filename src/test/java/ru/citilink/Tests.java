package ru.citilink;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static steps.stepsAll.*;

public class Tests extends BaseTest {

    @Feature("Проверка поисковика citilink")
    @DisplayName("Проверка поисковика citilink - всё в степах")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#providerCheckingLaptop")
    public void testCitilinkSearchLaptopStepsAll(String searchQuery, String title,String chapter, String expectedUrl) {

        openSite("https://www.google.ru/", "Google", webDriver);
        searchInGoogle(searchQuery);
        validateSearchInGoogle(title);
        goSiteByLink(title);
        openLaptopCatalog(chapter,expectedUrl);
        setParameters();
        checkQuantityOnFirstPage();
        GetProductInTheFirstPage();

    }

}

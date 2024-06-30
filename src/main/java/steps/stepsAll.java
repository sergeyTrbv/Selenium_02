package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CitilinkSearchInCatalog;
import pages.GoogleAfterSearch;
import pages.GoogleBeforeSearch;

public class stepsAll {

    private static WebDriverWait wait;
    private static WebDriver driver;

    @Step("Переходим на сайт: {url}")
    public static void openSite(String url, String title, WebDriver currentDriver){
        driver=currentDriver;
        driver.get(url);
        wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleIs(title));
    }

    @Step("Ищем в поисковике по ключевой фразе: {searchQuery}")
    public static void searchInGoogle(String searchQuery){
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(driver);
        googleBeforeSearch.find(searchQuery);
    }

        @Step("Проверяем наличие тайтла: {title} в результатах поиска Google")
    public static void validateSearchInGoogle(String title){
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(driver);
        googleAfterSearch.checkingTitleByText(title);
    }

    @Step("Переходим на сайт по заголовку: {title}")
    public static void goSiteByLink(String title){
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(driver);
        googleAfterSearch.goPageByLinkName(title);
    }

    @Step("Наводим курсор на раздел \"Ноутбуки и компьютеры\" и переходим в раздел: {chapter}")
    public static void openCatalog(String chapter){
        CitilinkSearchInCatalog citilinkSearchInCatalog = new CitilinkSearchInCatalog(driver);
        citilinkSearchInCatalog.searchChapterInCatalog(chapter);

    };
}

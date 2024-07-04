package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GoogleAfterSearch {


    private static final String SEARCH_RESULTS_HEAD = "//div[@id='rso']//div[@data-snc or @class='g']//a[@href]//h3[contains(.,'";
    //Вместо SEARCH_RESULTS_HEAD:
    // private static final String SEARCH_RESULTS_HEAD = "//div[@id='rso']//a[@href]//h3[contains(.,'";
    private static final String SEARCH_RESULT_TAIL = "')]";

    private WebDriver driver;

    private WebDriverWait wait;


    public WebDriver getWebDriver() {
        return driver;
    }

    public GoogleAfterSearch(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public GoogleAfterSearch(WebDriver driver, String searchQuery) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        driver.get("https://www.google.ru/search?q=" + searchQuery);
    }

    public void checkingTitleByText(String link) {
        wait.until(visibilityOfElementLocated(By.xpath(SEARCH_RESULTS_HEAD + link + SEARCH_RESULT_TAIL)));
        Assertions.assertFalse(driver.findElements(By.xpath(SEARCH_RESULTS_HEAD + link +
                SEARCH_RESULT_TAIL)).size() == 0, "Не найдено тайтла с текстом: '" + link);
    }

    public void goPageByLinkName(String link) {
        wait.until(visibilityOfElementLocated(By.xpath(SEARCH_RESULTS_HEAD + link + SEARCH_RESULT_TAIL)));
        driver.findElement(By.xpath(SEARCH_RESULTS_HEAD + link + SEARCH_RESULT_TAIL)).click();
        Set<String> tabs = driver.getWindowHandles();
        for (String tab : tabs)
            driver.switchTo().window(tab);
    }
}

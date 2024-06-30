package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class GoogleBeforeSearch {

    private static final String SEARCH_FIELD = "//textarea[@title='Поиск']";
    private static final String SEARCH_BUTTON = "//div[not (@jsname)]/center/*[@value='Поиск в Google']";
    private static final String GOOGLE_LOGO = "//img[@alt='Google']";

    private WebElement inputSearch;
    private WebElement buttonSearch;
    private WebDriver driver;

    private WebDriverWait wait;

    public GoogleBeforeSearch(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, 10);
        wait.until(visibilityOfElementLocated(By.xpath(SEARCH_FIELD)));
        this.inputSearch = driver.findElement(By.xpath(SEARCH_FIELD));
        this.buttonSearch = driver.findElement(By.xpath(SEARCH_BUTTON));
    }

    @Step("Поиск в Google по слову {text}")
    public void find(String text){
        inputSearch.sendKeys(text);
        driver.findElement(By.xpath(GOOGLE_LOGO)).click();
        buttonSearch.click();
    }

}

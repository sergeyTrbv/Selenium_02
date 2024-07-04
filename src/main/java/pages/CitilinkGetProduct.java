package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CitilinkGetProduct {

    private static final String FIRST_PAGE_BUTTON = "//a[@data-meta-name='PageLink__page-1']";
    private static final String FIRST_PRODUCT = "//div[contains(@class, 'app-catalog-1bogmvw')][1]";
//    private static final String PRODUCT_TITLE = "//div[contains(@class, 'app-catalog-1bogmvw')][1]//a[@data-meta-name='Snippet__title']";
    private static final String PRODUCT_TITLE = ".//a[@data-meta-name='Snippet__title']";
    private static final String SEARCH_WINDOW = "//input[@type='search']";
    private static final String SEARCH_BUTTON= "//button[@type='submit' and contains(@class, 'css-c064wa')]";
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement firstPageButton;
    private WebElement firstProduct;

    public CitilinkGetProduct(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.firstPageButton = driver.findElement(By.xpath(FIRST_PAGE_BUTTON));
    }

    @Step("Возвращаемся на первую страницу")
    private void backPage() {
        WebElement firstPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_PAGE_BUTTON)));
        firstPageButton.click();
    }

    @Step("Запоминаем первый продукт")
    private String getFirstproduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(FIRST_PRODUCT)));
        WebElement firstProductContainer = driver.findElement(By.xpath(FIRST_PRODUCT));
//        WebElement productLink = firstProductContainer.findElement(By.xpath(PRODUCT_TITLE));
//        WebElement productLink = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(firstProductContainer, By.xpath(PRODUCT_TITLE))).get(0);

        WebElement productLink = waitForElement(firstProductContainer, By.xpath(PRODUCT_TITLE));

        String title = productLink.getAttribute("title");
        System.out.println("Title of the first product: " + title);
        return title;
    }


    private WebElement waitForElement(WebElement parent, By by) {
        return wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, by)
        )).get(0);
    }



    @Step("В поисковике ищем товар")
    private void searchProduct(String productTitle) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SEARCH_WINDOW)));
        WebElement searchProduct = driver.findElement(By.xpath(SEARCH_WINDOW));
        searchProduct.click();
        searchProduct.clear();
        searchProduct.sendKeys(productTitle);

    }

    @Step("Нажимаем поиск")
    private void clickSearch() {
        WebElement clickSearch = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SEARCH_BUTTON)));
        clickSearch.click();
    }

    @Step("Сравнение товара")
    public void productComparison() {
        backPage();
        String product = getFirstproduct();
        searchProduct(product);
        clickSearch();
    }

}

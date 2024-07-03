package pages;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CitilinkSearchInCatalog {

//    private static final String CATALOG_PRODUCT_BUTTON = "//a[@href='/catalog/' and contains(@class, 'css-15x7smt') and @data-meta-name='DesktopHeaderFixed__catalog-menu']";
    private static final String CATALOG_PRODUCT_BUTTON = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']";
//    private static final String CATALOG_PRODUCT_BUTTON = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']//span[@class='e1pbr73b0 css-bh6qcy e1dsa0940']";
    private static final String LAPTOP_AND_PC_BUTTON = "//div[@class='PopupScrollContainer']//span[@color='None' and text()='Ноутбуки и компьютеры']";
    private static final String LAPTOP_BUTTON = "//a[@href='/catalog/noutbuki/?ref=mainmenu']//span[text()='Ноутбуки']";

    private WebDriver driver;
    private WebDriverWait wait;


    public CitilinkSearchInCatalog(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }


    @Step("Открытие каталога")
    private void openCatalog() {
//        wait.until(visibilityOfElementLocated(By.xpath(CATALOG_PRODUCT_BUTTON)));
//        WebElement catalogProductButton = driver.findElement(By.xpath(CATALOG_PRODUCT_BUTTON));
        WebElement catalogProductButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(CATALOG_PRODUCT_BUTTON)));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        catalogProductButton.click();
    }

    @Step("Наведение курсора на 'Ноутбуки и компьютеры'")
    private void hoverOverLaptopAndPC() {
        wait.until(visibilityOfElementLocated(By.xpath(LAPTOP_AND_PC_BUTTON)));
        WebElement laptopAndPCButton = driver.findElement(By.xpath(LAPTOP_AND_PC_BUTTON));
        Actions actions = new Actions(driver);
        actions.moveToElement(laptopAndPCButton).perform();
    }

    @Step("Выбор 'Ноутбуки'")
    private void selectLaptop() {
        wait.until(visibilityOfElementLocated(By.xpath(LAPTOP_BUTTON)));
        WebElement laptopButton = driver.findElement(By.xpath(LAPTOP_BUTTON));
        laptopButton.click();
    }

    @Step("Шаги по поиску раздела и проверка раздела {chapter}")
    public void searchChapterInCatalog(String chapter, String expectedUrl) {
        openCatalog();
        hoverOverLaptopAndPC();
        selectLaptop();
        verifyTransitionToLaptopPage(chapter, expectedUrl);
    }

    @Step("Проверка перехода на страницу {chapter}")
    private void verifyTransitionToLaptopPage(String chapter, String expectedUrl) {
        wait.until(visibilityOfElementLocated(By.xpath("//h1[text()='Ноутбуки']")));
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, currentUrl, "URL страницы не соответствует ожидаемому");
    }


}

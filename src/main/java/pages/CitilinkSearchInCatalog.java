package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CitilinkSearchInCatalog {


//    private static final String CATALOG_PRODUCT_BUTTON = "//a[@href='/catalog/' and @data-meta-name='DesktopHeaderFixed__catalog-menu']";
//    private static final String CATALOG_PRODUCT_BUTTON = "//a[@href='/catalog/' and @data-meta-name='DesktopHeaderFixed__catalog-menu']//span[text()='Каталог товаров']";

    //1
    private static final String CATALOG_PRODUCT_BUTTON = "//a[@href='/catalog/' and contains(@class, 'css-15x7smt') and @data-meta-name='DesktopHeaderFixed__catalog-menu']";
    //2
    private static final String LAPTOP_AND_PC_BUTTON = "//span[@color='None' and text()='Ноутбуки и компьютеры']";


    private WebElement catalogProductButton;
    private WebElement laptopAndPCButton;
    private WebDriver driver;
    private WebDriverWait wait;

    public CitilinkSearchInCatalog(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, 10);

        wait.until(visibilityOfElementLocated(By.xpath(CATALOG_PRODUCT_BUTTON)));
        this.catalogProductButton= driver.findElement(By.xpath(CATALOG_PRODUCT_BUTTON));

//        this.laptopAndPCButton= driver.findElement(By.xpath(LAPTOP_AND_PC_BUTTON));
//        wait.until(visibilityOfElementLocated(By.xpath(LAPTOP_AND_PC_BUTTON)));
    }


    @Step("Поиск в Google по слову {text}")
    public void searchChapterInCatalog(String text){
        catalogProductButton.click();

//        wait.until(visibilityOfElementLocated(By.xpath(LAPTOP_AND_PC_BUTTON)));

        this.laptopAndPCButton= driver.findElement(By.xpath(LAPTOP_AND_PC_BUTTON));
        laptopAndPCButton.click();

    }
}

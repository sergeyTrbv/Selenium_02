package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CitilinkSetParameters {

    private static final String MIN_PRICE_FIELD = "//div[@data-meta-name='FilterDropdown' and @data-meta-value='Цена']//input[@data-meta-name='FilterRangeGroup__input-min']";
    private static final String MAX_PRICE_FIELD = "//div[@data-meta-name='FilterDropdown' and @data-meta-value='Цена']//input[@data-meta-name='FilterRangeGroup__input-max']";
    private static final String COOKIE_BUTTON = "//button[@data-meta-disabled='false' and @type='button']//span[text()='Я согласен']";
        private static final String CHECKBOX_HP = "//input[@type='checkbox' and @id='hp']";
    private static final String CHECKBOX_LENOVO = "//input[@type='checkbox' and @id='lenovo']";
    private WebDriver driver;
    private WebDriverWait wait;


    public CitilinkSetParameters(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    //можно передать в параметрах цену
    @Step("Установка верхнего и нижнего диапазона цен")
    private void setPriceParameters() {


        wait.until(visibilityOfElementLocated(By.xpath(MIN_PRICE_FIELD)));
        wait.until(visibilityOfElementLocated(By.xpath(MAX_PRICE_FIELD)));

        WebElement minPriceField = driver.findElement(By.xpath(MIN_PRICE_FIELD));
        WebElement maxPriceField = driver.findElement(By.xpath(MAX_PRICE_FIELD));

        minPriceField.click();
        minPriceField.clear();
        minPriceField.sendKeys("20000");

        maxPriceField.click();
        maxPriceField.clear();
        maxPriceField.sendKeys("120000");
        minPriceField.click();

        wait.until(visibilityOfElementLocated(By.xpath(COOKIE_BUTTON)));
        WebElement cookieButon = driver.findElement(By.xpath(COOKIE_BUTTON));
        cookieButon.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Выбор бренда")
    private void choosingBrand() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//div[@data-meta-name='FilterDropdown' and @data-meta-value='Бренд']"));
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);

        WebElement checkBoxHP = driver.findElement(By.xpath(CHECKBOX_HP));
        checkBoxHP.click();

        WebElement checkBoxLenovo = driver.findElement(By.xpath(CHECKBOX_LENOVO));
        checkBoxLenovo.click();
    }

    @Step("Шаги по установке параметров")
    public void setParameters() {
        setPriceParameters();
        choosingBrand();
    }
}

package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class CitilinkSetParameters {

    private static final String MIN_PRICE_FIELD = "//div[@data-meta-name='FilterDropdown' and @data-meta-value='Цена']//input[@data-meta-name='FilterRangeGroup__input-min']";
    private static final String MAX_PRICE_FIELD = "//div[@data-meta-name='FilterDropdown' and @data-meta-value='Цена']//input[@data-meta-name='FilterRangeGroup__input-max']";
    private static final String CHECKBOX_HP = "//input[@type='checkbox' and @id='hp']";
    private static final String CHECKBOX_LENOVO = "//input[@type='checkbox' and @id='lenovo']";
    private WebDriver driver;
    private WebDriverWait wait;


    public CitilinkSetParameters(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    //можно передать в параметрах цену
    @Step("Задание верхнего и нижнего диапазона цен {chapter}")
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
    }
    @Step("Выбор бренда {chapter}")
    private void choosingBrand() {
        wait.until(visibilityOfElementLocated(By.xpath(CHECKBOX_HP)));
        wait.until(visibilityOfElementLocated(By.xpath(CHECKBOX_LENOVO)));

        WebElement checkBoxHP = driver.findElement(By.xpath(CHECKBOX_HP));
        WebElement checkBoxLenovo = driver.findElement(By.xpath(CHECKBOX_LENOVO));
    }

    @Step("Шаги по установке параметров {chapter}")
    public void setParameters() {
        setPriceParameters();
        choosingBrand();
    }
}

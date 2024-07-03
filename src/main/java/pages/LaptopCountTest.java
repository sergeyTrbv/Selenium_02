package pages;


import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static helpers.Assertions.assertTrue;

public class LaptopCountTest {

    private WebDriver driver;
    private WebDriverWait wait;
    static private int page = 1;


    public LaptopCountTest(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    @Step("тест на 12 позиций")
    public void checkQuantity() {
        List<WebElement> productItems = driver.findElements(By.xpath("//div[contains(@class, 'e1l56t9a0')]"));
        assertTrue(productItems.size() >= 12, "Количество позиций товара меньше 12");
    }

    @Step("тест проверка условий на всех страницах")
    public void checkConditionsOnAllPages() {
        boolean hasNextPage = true;
        while (hasNextPage) {
            checkConditions();

            List<WebElement> nextPageButtons = driver.findElements(By.xpath("//div[text()='Следующая']"));
            if (!nextPageButtons.isEmpty() && nextPageButtons.get(0).isEnabled()) {
                page++;

                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", nextPageButtons.get(0));

                nextPageButtons.get(0).click();
                wait.until(ExpectedConditions.stalenessOf(nextPageButtons.get(0)));
            } else {
                hasNextPage = false;
            }
        }
    }

    @Step("тест проверка условий")
    public void checkConditions() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> productElementsTitle = driver.findElements(By.xpath(".//a[contains(@class, 'app-catalog-9gnskf') and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]"));
        for (WebElement productElement : productElementsTitle) {
            String title = productElement.getAttribute("title");
            Assertions.assertTrue(title.contains("HP") || title.contains("Lenovo"), "На странице " + page + " название бренда техники не соответствует 'HP' или 'Lenovo'");
        }

        List<WebElement> productElementsPrice = driver.findElements(By.xpath(".//span[contains(@class, 'e1j9birj0')and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]"));
        for (WebElement productElement : productElementsPrice) {
            String priceText = productElement.getText().replace(" ", "").replace("₽", "");
            int price = Integer.parseInt(priceText);
            Assertions.assertTrue(price >= 20000 && price <= 120000, "На странице " + page + " цена  " + price + " не соответствует вилке от 20000 до 120000");
        }
    }
}



package pages;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static helpers.Assertions.assertTrue;

public class LaptopCountTest {

    private WebDriver driver;
    private WebDriverWait wait;

    public LaptopCountTest(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
//
//    @Step("fefef")
//    public void testLaptopCount() {
//
//        // Открыть страницу с ноутбуками
//        driver.get("https://www.citilink.ru/catalog/noutbuki/");
//
//        // Найти все элементы, соответствующие ноутбукам с использованием XPath
//        List<WebElement> laptopElements = driver.findElements(By.xpath("//div[contains(@class, 'e1l56t9a0') and contains(@class, 'app-catalog-edir76') and contains(@class, 'e1fz0vwk0')]"));
//
//
//        // Подсчитать количество элементов
//        int laptopCount = laptopElements.size();
//
//        // Проверить, что количество ноутбуков больше 0
//        Assertions.assertTrue(laptopCount > 0, "проверка");
//
//        // Вывести количество ноутбуков в консоль
//        System.out.println("Количество ноутбуков: " + laptopCount);
//    }



    @Step("тест на 12 позиций")
    public void checkQuantity() {

        List<WebElement> productItems = driver.findElements(By.xpath("//div[contains(@class, 'e1l56t9a0')]"));
        assertTrue(productItems.size() >= 12, "Количество позиций товара меньше 12");
        System.out.println("Проверил что товара больше 12 штук");
        System.out.println(productItems.size());
    }





}

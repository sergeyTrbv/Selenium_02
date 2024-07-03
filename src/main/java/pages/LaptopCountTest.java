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
    int count = 1;

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
            // Выполняю метод
            checkConditions();


            // Проверяем, есть ли кнопка следующей страницы
            WebElement nextPageButton = driver.findElement(By.xpath("//div[text()='Следующая']"));


            // Ждем, пока кнопка станет кликабельной
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", nextPageButton);

            // если есть...
            if (nextPageButton.isEnabled()) {

                System.out.println("______________________________________________________________________________________");
                System.out.println("Проверил условия страницы: " + count);
                System.out.println("______________________________________________________________________________________");
                count++;

                // Ждем, пока кнопка станет кликабельной
//                wait.until(ExpectedConditions.elementToBeClickable(nextPageButton));

//                JavascriptExecutor js = (JavascriptExecutor) driver;
//                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", nextPageButton);

                // то кликаю
                nextPageButton.click();

//                try {
//                    Thread.sleep(4000); // Ждем, пока страница загрузится
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            } else {
                hasNextPage = false;
            }
        }

    }

//    @Step("тест проверка условий")
//    public void checkConditions() {
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Найти все элементы товаров
//        List<WebElement> productElements = driver.findElements(By.xpath("//div[contains(@class, 'e1l56t9a0')]"));
//
//        // печатаю все элементы страницы
////        for (WebElement productElement : productElements) {
////            System.out.println();
////            System.out.println(productElement.getText());
////            System.out.println();
////        }
//
//        // Итерируюсь по всем элементам для проверки:
//        for (WebElement productElement : productElements) {
//
//            WebElement titleElement = productElement.findElement(By.xpath(".//a[contains(@class, 'app-catalog-9gnskf') and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]"));
//            String title = titleElement.getAttribute("title");
//            if (!(title.contains("HP") || title.contains("Lenovo"))) {
//                System.out.println("Название бренда техники не соответствует 'HP' или 'Lenovo' на странице " + count + ": " + title);
//            }
//
//            //WebElement priceElement = productElement.findElement(By.xpath(".//span[contains(@class, 'e1j9birj0')and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]"));
////            String priceText = priceElement.getText().replace(" ", "").replace("₽", "");
//            String priceText = productElement.getText().replace(" ", "").replace("₽", "");
//            int price = Integer.parseInt(priceText);
//            if (!(price >= 20000 && price <= 120000)) {
//                System.out.println("Цена не соответствует вилке от 20000 до 120000 на странице " + count + ": " + title + " - " + price + "₽");
//            }
//
//
//        }
//
//
//    }


    @Step("тест проверка условий")
    public void checkConditions() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Найти все элементы товаров
//        List<WebElement> productElements = driver.findElements(By.xpath("//div[contains(@class, 'e1l56t9a0')]"));

        // печатаю все элементы страницы
//        for (WebElement productElement : productElements) {
//            System.out.println();
//            System.out.println(productElement.getText());
//            System.out.println();
//        }

        //Фоткаю все нужные мне элементы
        List<WebElement> productElements = driver.findElements(By.xpath(".//a[contains(@class, " +
                "'app-catalog-9gnskf') and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]"));
        // Итерируюсь по всем элементам для проверки:
        for (WebElement productElement : productElements) {
            //     WebElement titleElement = productElement.findElement(By.xpath(".//a[contains(@class, 'app-catalog-9gnskf') and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]"));

//            System.out.println(productElement.getText());

            String title = productElement.getAttribute("title");
//            if (!(title.contains("HP") || title.contains("Lenovo"))) {
//                System.out.println("Название бренда техники не соответствует 'HP' или 'Lenovo' на странице " + count + ": " + title);
//            }
            Assertions.assertTrue(title.contains("HP") || title.contains("Lenovo"), "На странице " + count + " название бренда техники не соответствует 'HP' или 'Lenovo'");
        }

        //Фоткаю все нужные мне элементы
        List<WebElement> productElementsPrice = driver.findElements(By.xpath(".//span[contains(@class, 'e1j9birj0')and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]"));
        for (WebElement productElement : productElementsPrice) {
            //WebElement priceElement = productElement.findElement(By.xpath(".//span[contains(@class, 'e1j9birj0')and not(ancestor::div[@data-meta-name='ProductsCompilation__slide'])]"));

//            System.out.println(productElement.getText());

            String priceText = productElement.getText().replace(" ", "").replace("₽", "");
            int price = Integer.parseInt(priceText);
//            if (!(price >= 20000 && price <= 120000)) {
//                System.out.println("Цена не соответствует вилке от 20000 до 120000 на странице " + count + " - " + price + "₽");
//            }
            Assertions.assertTrue(price >= 20000 && price <= 120000, "На странице " + count + " цена  "+ price + " не соответствует вилке от 20000 до 120000");
        }


    }
}


//            WebElement titleElement = productElement.findElement(By.xpath("//div[contains(@class, 'e1l56t9a0')]//a[contains(@class, 'app-catalog-9gnskf')]"));
//            String title = titleElement.getAttribute("title");
//            Assertions.assertTrue(title.contains("HP") || title.contains("Lenovo"), "На странице " + count + " название бренда техники не соответствует 'HP' или 'Lenovo'");
//
//            WebElement priceElement = productElement.findElement(By.xpath("//div[contains(@class, 'e1l56t9a0')]//span[contains(@class, 'e1j9birj0')]"));
//            String priceText = priceElement.getText().replace(" ", "").replace("₽", "");
//            int price = Integer.parseInt(priceText);
//            Assertions.assertTrue(price >= 20000 && price <= 120000, "На странице " + count + " цена не соответствует вилке от 20000 до 120000 у " + title);


//            WebElement titleElement = productElement.findElement(By.xpath("//div[contains(@class, 'e1l56t9a0')]//a[contains(@class, 'app-catalog-9gnskf')]"));
//            String title = titleElement.getAttribute("title");
//            if (!(title.contains("HP") || title.contains("Lenovo"))) {
//                System.out.println("Название бренда техники не соответствует 'HP' или 'Lenovo' на странице " + count + ": " + title);
//            }
//
//            WebElement priceElement = productElement.findElement(By.xpath("//div[contains(@class, 'e1l56t9a0')]//span[contains(@class, 'e1j9birj0')]"));
//            String priceText = priceElement.getText().replace(" ", "").replace("₽", "");
//            int price = Integer.parseInt(priceText);
//            if (!(price >= 20000 && price <= 120000)) {
//                System.out.println("Цена не соответствует вилке от 20000 до 120000 на странице " + count + ": " + title + " - " + price + "₽");
//            }


////        List<WebElement> productItems = driver.findElements(By.xpath("//div[contains(@class, 'e1l56t9a0')]"));
////        for (WebElement productItem : productItems) {
////            System.out.println(productItem.getText());
////        }
package pages;

import helpers.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс {@code CitilinkSearchInCatalog} для выполнения поиска и проверки разделов в каталоге интернет-магазина Citilink.
 *
 * @author sergeyTrbv
 */
public class CitilinkSearchInCatalog {

    /**
     * Объект String с шаблоном XPath кнопки каталога в заголовке страницы.
     */
    private static final String CATALOG_MENU_BUTTON = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']";

    /**
     * Объект String с шаблоном XPath кнопки "Ноутбуки и компьютеры" в выпадающем меню каталога.
     */
    private static final String LAPTOP_AND_PC_BUTTON = "//div[@class='PopupScrollContainer']//span[@color='None' " +
            "and text()='Ноутбуки и компьютеры']";

    /**
     * Объект String с шаблоном XPath кнопки "Ноутбуки" в подменю "Ноутбуки и компьютеры".
     */
    private static final String LAPTOP_BUTTON = "//a[@href='/catalog/noutbuki/?ref=mainmenu']" +
            "//span[text()='Ноутбуки']";

    /**
     * Объект String с шаблоном XPath заголовка страницы "Ноутбуки".
     */
    private static final String LAPTOP_PAGE_TITLE = "//h1[text()='Ноутбуки']";

    /**
     * Объект типа {@code WebDriver} для взаимодействия с браузером.
     */
    private WebDriver driver;

    /**
     * Объект типа {@code WebDriverWait} использующийся для ожидания элементов на странице.
     */
    private WebDriverWait wait;

    /**
     * Конструктор класса, инициализирующий WebDriver и WebDriverWait.
     *
     * @param driver экземпляр WebDriver для управления браузером.
     */
    public CitilinkSearchInCatalog(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Метод {@code openCatalog} открывает каталог, нажимая на кнопку каталога в заголовке страницы.
     */
    @Step("Открытие каталога")
    private void openCatalog() {
        WebElement catalogProductButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.xpath(CATALOG_MENU_BUTTON)));
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        catalogProductButton.click();
    }

    /**
     * Метод {@code hoverOverLaptopAndPC} наводит курсор на кнопку "Ноутбуки и компьютеры" в выпадающем меню каталога.
     */
    @Step("Наведение курсора на 'Ноутбуки и компьютеры'")
    private void hoverOverLaptopAndPC() {
        WebElement laptopAndPCButton = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(LAPTOP_AND_PC_BUTTON)));
        Actions actions = new Actions(driver);
        actions.moveToElement(laptopAndPCButton).perform();
    }

    /**
     * Метод {@code selectLaptop} выбирает раздел "Ноутбуки" в подменю "Ноутбуки и компьютеры".
     */
    @Step("Выбор 'Ноутбуки'")
    private void selectLaptop() {
        WebElement laptopButton = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(LAPTOP_BUTTON)));
        laptopButton.click();
    }

    /**
     * Метод {@code verifyTransitionToLaptopPage} проверяет, что произошел переход на страницу "Ноутбуки" и
     * URL соответствует ожидаемому.
     *
     * @param chapter     название раздела (для логирования).
     * @param expectedUrl ожидаемый URL страницы "Ноутбуки".
     */
    @Step("Проверка перехода на страницу {chapter}")
    private void verifyTransitionToLaptopPage(String chapter, String expectedUrl) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LAPTOP_PAGE_TITLE)));
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, currentUrl, "URL страницы не соответствует ожидаемому");
    }

    /**
     * Метод {@code searchChapterInCatalog} выполняет последовательность действий для поиска раздела "Ноутбуки" в
     * каталоге и проверяет переход на соответствующую страницу.
     *
     * @param chapter     название раздела (для логирования).
     * @param expectedUrl ожидаемый URL страницы "Ноутбуки".
     */
    @Step("Шаги по поиску раздела и проверка раздела {chapter}")
    public void searchChapterInCatalog(String chapter, String expectedUrl) {
        openCatalog();
        hoverOverLaptopAndPC();
        selectLaptop();
        verifyTransitionToLaptopPage(chapter, expectedUrl);
    }
}

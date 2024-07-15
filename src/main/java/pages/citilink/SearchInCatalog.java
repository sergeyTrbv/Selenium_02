package pages.citilink;

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
public class SearchInCatalog {

    /**
     * Объект String с шаблоном  начала XPath для элемента "Меню" на главной странице.
     */
    private static final String HEAD_CATALOG_MENU_BUTTON = "//a[@data-meta-name='DesktopHeaderFixed__catalog-menu']" +
            "//span[contains(text(), '";

    /**
     * Объект String с шаблоном XPath кнопки "Ноутбуки и компьютеры" в выпадающем меню каталога.
     */
    private static final String HEAD_LAPTOP_AND_PC_BUTTON = "//div[@class='PopupScrollContainer']" +
            "//span[@color='None' and text()='";

    /**
     * Объект String с шаблоном XPath кнопки "Ноутбуки" в подменю "Ноутбуки и компьютеры".
     */
    private static final String HEAD_LAPTOP_BUTTON = "//div[@class='rcs-inner-container']//span[text()='";

    /**
     * Объект String с шаблоном XPath заголовка страницы "Ноутбуки".
     */
    private static final String HEAD_CHECK_PAGE_NAME = "//div[@data-meta-name='SubcategoryPageTitle']" +
            "//h1[@color='Main' and text()='";

    private static final String CATALOG_MENU_TAIL = "')]";
    private static final String TAIL = "']";


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
     * @param currentDriver экземпляр WebDriver для управления браузером.
     */
    public SearchInCatalog(WebDriver currentDriver) {
        this.driver = currentDriver;
        this.wait = new WebDriverWait(driver, 10);
    }

    /**
     * Метод {@code searchChapterInCatalog} выполняет последовательность действий для поиска раздела "Ноутбуки" в
     * каталоге и проверяет переход на соответствующую страницу.
     *
     * @param chapter название раздела (для логирования).
     */
    @Step("Шаги по поиску раздела и проверка раздела {chapter}")
    public void searchChapterInCatalog(String textMenu, String section, String chapter) {
        openCatalog(textMenu);
        hoverOverLaptopAndPC(section);
        selectLaptop(chapter);
        verifyTransitionToLaptopPage(chapter);
    }

    /**
     * Метод {@code openCatalog} открывает каталог, нажимая на кнопку каталога в заголовке страницы.
     */
    @Step("Открытие каталога")
    private void openCatalog(String textMenu) {
        WebElement catalogProductButton = wait.until(ExpectedConditions.
                elementToBeClickable(By.xpath(HEAD_CATALOG_MENU_BUTTON + textMenu + CATALOG_MENU_TAIL)));
        catalogProductButton.click();
    }

    /**
     * Метод {@code hoverOverLaptopAndPC} наводит курсор на кнопку "Ноутбуки и компьютеры" в выпадающем меню каталога.
     */
    @Step("Наведение курсора на {section}")
    private void hoverOverLaptopAndPC(String section) {
        WebElement laptopAndPCButton = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(HEAD_LAPTOP_AND_PC_BUTTON + section + TAIL)));
        Actions actions = new Actions(driver);
        actions.moveToElement(laptopAndPCButton).perform();
    }

    /**
     * Метод {@code selectLaptop} выбирает раздел "Ноутбуки" в подменю "Ноутбуки и компьютеры".
     */
    @Step("Выбор {chapter}")
    private void selectLaptop(String chapter) {
        WebElement laptopButton = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(HEAD_LAPTOP_BUTTON + chapter + TAIL)));
        laptopButton.click();
    }


    @Step("Проверка перехода на страницу {chapter}")
    private void verifyTransitionToLaptopPage(String chapter) {
        WebElement actualChapter = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(HEAD_CHECK_PAGE_NAME + chapter + TAIL)));
        String actualText = actualChapter.getText();
        Assertions.assertEquals(chapter, actualText, "URL страницы не соответствует ожидаемому");
    }
}
